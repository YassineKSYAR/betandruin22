package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.util.Callback;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;
import utils.Dates;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateEventController implements Controller {

        LocalDate localDate;

        private BlFacade businessLogic;

        public CreateEventController(BlFacade bl){
            businessLogic = bl;
        }

        private MainGUI mainGUI;


        @FXML
        private DatePicker datePicker;

        @FXML
        private TextField eventInput;

        @FXML
        private Button createBtn;

       @FXML
       private Label LblDate;

        @FXML
        private Label msgLbl;

        Date eventDate;

        private int day;
        private Month month;
        private int year;

        //////////////////////////////////////////////////////////////////////
        private List<LocalDate> holidays = new ArrayList<>();

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

        String inputEvent;

        @FXML
        void initialize() {

            //setupEventSelection();

            createBtn.setDisable(true);

            setEventsPrePost(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());

            datePicker.setOnMouseClicked(e -> {
                // get a reference to datepicker inner content
                // attach a listener to the  << and >> buttons
                // mark events for the (prev, current, next) month and year shown
                DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
                localDate = datePicker.getValue();
                if(localDate == null){
                    System.out.println("date is null");
                    LblDate.setStyle("-fx-background-color: red;-fx-background-radius: 5px;-fx-text-fill: white;");
                    LblDate.setText("Enter a Date!!");
                    createBtn.setDisable(true);
                }else{
                    day = localDate.getDayOfMonth();
                    month = localDate.getMonth();
                    year = localDate.getYear();
                    System.out.println("year = " + year + " month = " + month + " day = " + day);
                    eventDate = new Date(year - 1900,month.getValue() - 1,day);
                    LblDate.setText("");
                    LblDate.setStyle("");
                    createBtn.setDisable(false);
                }

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
            });
        }

        @FXML
        void onCloseBtn(ActionEvent event) {
            msgLbl.setText("");
            LblDate.setText("");
            msgLbl.setStyle("");
            LblDate.setStyle("");
            eventInput.clear();
            mainGUI.showMain();
        }

        @FXML
        void onCreateBtn(ActionEvent event) {
            String inputEvent = eventInput.getText();
            Date currentDate = new Date();
            if(eventDate.compareTo(currentDate) > 0) {
                if (inputEvent.length() > 0 && (eventDate != null)) {
                    System.out.println("year = " + year + " month = " + month + " day = " + day);
                    businessLogic.createEvent(eventDate , inputEvent);
                    System.out.println(localDate.toString());
                    msgLbl.setStyle("-fx-background-color: green;-fx-background-radius: 5px;-fx-text-fill: white;");
                    msgLbl.setText("Event created");
                    eventInput.setText("");
                }else {
                    msgLbl.setStyle("-fx-background-color: red;-fx-background-radius: 5px;-fx-text-fill: white;");
                    msgLbl.setText("Enter event details!!");
                }
            }else{
                msgLbl.setStyle("-fx-background-color: red;-fx-background-radius: 5px;-fx-text-fill: white;");
                msgLbl.setText("This date is in the past!!");
            }

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
    }

}


