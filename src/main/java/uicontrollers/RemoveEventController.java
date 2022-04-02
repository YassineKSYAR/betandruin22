package uicontrollers;

import businessLogic.BlFacade;
import domain.Event;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.util.Callback;
import ui.Home;
import ui.MainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.MainUser;
import utils.Dates;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


public class RemoveEventController implements Controller{

    private MainGUI mainGUI;

    private BlFacade businessLogic;
    MainUser mainUser;

    @FXML
    private Button CloseBtn;

    @FXML
    private TableColumn<Event, Integer> ec1;

    @FXML
    private TableColumn<Event, String> ec2;

    @FXML
    private Button RemoveBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Event> tblEvent;

    LocalDate localDate;

    private int day;
    private Month month;
    private int year;

    @FXML
    private Label lblMessage;


    private List<LocalDate> holidays = new ArrayList<>();

    public RemoveEventController(BlFacade bl){
        this.businessLogic = bl;
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

        //setupEventSelection();

        setEventsPrePost(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());

        datePicker.setOnMouseClicked(e -> {
            // get a reference to datepicker inner content
            // attach a listener to the  << and >> buttons
            // mark events for the (prev, current, next) month and year shown
            DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
            skin.getPopupContent().lookupAll(".button").forEach(node -> {
                node.setOnMouseClicked(event -> {
                    List<Node> labels = skin.getPopupContent().lookupAll(".label").stream().toList();
                    String month = ((Label) (labels.get(0))).getText();
                    String year = ((Label) (labels.get(1))).getText();
                    YearMonth ym = Dates.getYearMonth(month + " " + year);
                    setEventsPrePost(ym.getYear(), ym.getMonthValue());
                });
            });

        });


        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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
        datePicker.setOnAction(actionEvent -> {
            tblEvent.getItems().clear();
            Vector<Event> events = businessLogic.getEvents(Dates.convertToDate(datePicker.getValue()));
            for (domain.Event ev : events) {
                tblEvent.getItems().add(ev);
            }
        });

        // Bind columns to Event attributes
        ec1.setCellValueFactory(new PropertyValueFactory<>("eventNumber"));
        ec2.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    void onCloseBtn(ActionEvent event) {
        lblMessage.setText("");
        tblEvent.getItems().clear();
        mainGUI.showMain();
    }

    @FXML
    void onRemoveBtn(ActionEvent event) {
        Event ev = tblEvent.getSelectionModel().getSelectedItem();
        System.out.println(ev);

        if(ev == null){
            System.out.println("There's no event!!");
            lblMessage.setStyle("-fx-text-fill: red;");
            lblMessage.setText("No event is selected!!");
        }else{
            businessLogic.removeEventQuestions(ev);
            businessLogic.removeEvent(ev);
            System.out.println("Event has been deleted :) :)");
            lblMessage.setStyle("-fx-text-fill: green;");
            lblMessage.setText("Event Deleted");
        }
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

    }
}
