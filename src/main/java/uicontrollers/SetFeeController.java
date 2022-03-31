package uicontrollers;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;
import domain.Results;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.util.Callback;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;
import utils.Dates;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class SetFeeController implements Controller  {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

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
    private TableView<Event> tblEvents;

    @FXML
    private TableView<Question> tblQuestions;



    @FXML
    private TextField resultF;

    @FXML
    private Label resultLab;

    @FXML
    private Button setfeeBtn;
    @FXML
    private TextField feeF;

    @FXML
    private Label setFeeText;

    @FXML
    private Label feeLab;

    private MainGUI mainGUI;
    private Home home;

    private List<LocalDate> holidays = new ArrayList<>();

    private BlFacade businessLogic;


    private Results resu=new Results();

    public SetFeeController(BlFacade bl) {
        businessLogic = bl;
    }
    @FXML
    void closeClick(ActionEvent event) {
        mainGUI.showMain();
        setFeeText.setText("");
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
       setupQuestionSelection();



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
            Vector<Event> events = businessLogic.getEvents(Dates.convertToDate(datepicker.getValue()));
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




    }


    private   void setupEventSelection() {
        tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tblQuestions.getItems().clear();
                resu.setIdevent(tblEvents.getSelectionModel().getSelectedItem().getEventNumber());

                for (Question q : tblEvents.getSelectionModel().getSelectedItem().getQuestions()) {
                    tblQuestions.getItems().add(q);
                }

            }
        });

    }

    private void setupQuestionSelection() {
        tblQuestions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                resu.setIdQuestion(tblQuestions.getSelectionModel().getSelectedItem().getQuestionNumber());
            }
        });


    }




    @FXML
    void onSetFee(ActionEvent event) {

        if(!resultF.getText().equals("")||!feeF.getText().equals("")) {

            resu.setResult(resultF.getText());
            resu.setFee(Float.parseFloat(feeF.getText()));
            try {
                businessLogic.createResult(resu.getIdevent(), resu.getIdQuestion(), resu.getResult(), resu.getFee());
            } catch (Exception e) {
                System.err.println(e);
            }
            System.out.println(resu);
        }else {
            System.out.println("Erreur");
        }

        setFeeText.setText("The results has been creared");

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void setMainApp(MainUser mainUser) {

    }

    @Override
    public void setHomeApp(Home home) {
        this.home=home;
    }


}
