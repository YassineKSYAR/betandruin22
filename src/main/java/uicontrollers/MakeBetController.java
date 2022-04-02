package uicontrollers;

import java.net.URL;
import java.time.*;
import java.util.*;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;
import domain.Results;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;
import utils.Dates;

public class MakeBetController implements Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;


    @FXML
    private Label moenyIns;
    @FXML
    private Label betCr;
    @FXML
    private Label balanceInp;

    @FXML
    private Label labelBalnce;



    @FXML
    private  Spinner amountInpute=new Spinner();

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableColumn<Event, Integer> ec1;

    @FXML
    private TableColumn<Event, String> ec2;

    @FXML
    private TableColumn<Event, Integer> qc1;

    @FXML
    private TableColumn<Event, Integer> qc2;

    @FXML
    private TableColumn<Results, String> r1;

    @FXML
    private TableColumn<Results, Float> r2;

    @FXML
    private TableView<Event> tblEvents;

    @FXML
    private TableView<Question> tblQuestions;

    @FXML
    private TableView<Results> tblResults;

    @FXML
    private Button betBtn;

    @FXML
    private TextField betinput;



    private MainGUI mainGUI;
    private MainUser mainUser;
    private Home home;

    private List<LocalDate> holidays = new ArrayList<>();

    private BlFacade businessLogic;


    public MakeBetController (BlFacade bl) {
        businessLogic = bl;
    }


    @FXML
    void closeClick(ActionEvent event) {
        mainUser.showMain();
        moenyIns.setText("");
        betCr.setText("");

    }

    private void setEvents(int year, int month) {
        Date date = Dates.toDate(year,month);

        for (Date day : businessLogic.getEventsMonth(date)) {
            holidays.add(Dates.convertToLocalDateViaInstant(day));
        }
    }

    private void setEventsPrePost(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        setEvents(date.getYear(), date.getMonth().getValue());
        setEvents(date.plusMonths(1).getYear(), date.plusMonths(1).getMonth().getValue());
        setEvents(date.plusMonths(-1).getYear(), date.plusMonths(-1).getMonth().getValue());
    }

    @FXML
    void initialize() {

        setupEventSelection();
        setupResulttSelection();

        setEventsPrePost(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());

        datepicker.setOnMouseClicked(e -> {
            // get a reference to datepicker inner content
            // attach a listener to the  << and >> buttons
            // mark events for the (prev, current, next) month and year shown
            DatePickerSkin skin = (DatePickerSkin) datepicker.getSkin();
            skin.getPopupContent().lookupAll(".button").forEach(node -> {
                node.setOnMouseClicked(event -> {
                    List<Node> labels = skin.getPopupContent().lookupAll(".label").stream().toList();
                    String month = ((Label) (labels.get(0))).getText();
                    String year =  ((Label) (labels.get(1))).getText();
                    YearMonth ym = Dates.getYearMonth(month + " " + year);
                    setEventsPrePost(ym.getYear(), ym.getMonthValue());
                });
            });


        });

        datepicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            if (holidays.contains(item)) {
                                this.setStyle("-fx-background-color: pink");
                            }
                        }
                    }
                };
            }
        });

        // a date has been chosen, update the combobox of Events
        datepicker.setOnAction(actionEvent -> {
            tblEvents.getItems().clear();
            Vector<domain.Event> events = businessLogic.getEvents(Dates.convertToDate(datepicker.getValue()));
            for (domain.Event ev : events) {
                tblEvents.getItems().add(ev);
            }
        });

        // Bind columns to Event attributes
        ec1.setCellValueFactory(new PropertyValueFactory<>("eventNumber"));
        ec2.setCellValueFactory(new PropertyValueFactory<>("description"));
        // Bind columns to Question attributes
        qc1.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        qc2.setCellValueFactory(new PropertyValueFactory<>("question"));

        r1.setCellValueFactory(new PropertyValueFactory<>("result"));
        r2.setCellValueFactory(new PropertyValueFactory<>("fee"));





    }

    private void setupEventSelection() {
        tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                tblQuestions.getItems().clear();
                for (Question q : tblEvents.getSelectionModel().getSelectedItem().getQuestions()) {
                    tblQuestions.getItems().add(q);
                }
            }


        });


    }
    private void setupResulttSelection() {
        tblQuestions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                tblResults.getItems().clear();
                Event event=tblEvents.getSelectionModel().getSelectedItem();
                Question q=tblQuestions.getSelectionModel().getSelectedItem();

                for (Results r : businessLogic.getResults(event.getEventNumber(),q.getQuestionNumber())) {
                    tblResults.getItems().add(r);
                }
                amountInpute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory((int) q.getBetMinimum(),100000, (int) q.getBetMinimum()));
            }
        });
    }

    @FXML
    void onBet(ActionEvent event) {
        Results results=tblResults.getSelectionModel().getSelectedItem();
        System.out.println(mainUser.getUser().getMoney());
        float money=businessLogic.getMony(mainUser.getUser()).get(0).getMoney();
        if((int)money>=((int)amountInpute.getValue())) {
            businessLogic.deleteMoeny(mainUser.user, (int) amountInpute.getValue());
            businessLogic.createBet(results.getIdR(), (int) amountInpute.getValue(), results.getFee(), mainUser.getUser().getId());
            betCr.setText("Your bet has been created");
            moenyIns.setText("");
            labelBalnce.setText("");
            balanceInp.setText("");
            labelBalnce.setText("My balance ");
            balanceInp.setText(""+businessLogic.getMony(mainUser.user).get(0).getMoney());
        }else {
            moenyIns.setText("Insufficient balance");
            betCr.setText("");
        }
    }


    @FXML
    void onShowBalance(ActionEvent event) {
        labelBalnce.setText("My balance ");
        balanceInp.setText(""+businessLogic.getMony(mainUser.getUser()).get(0).getMoney());

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void setMainApp(MainUser mainUser) {
        this.mainUser=mainUser;

    }

    @Override
    public void setHomeApp(Home home) {
        this.home=home;
    }


}
