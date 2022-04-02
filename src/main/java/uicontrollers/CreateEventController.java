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
        private MainUser mainUser;

        @FXML
        private Button CloseBtn;

        @FXML
        private Button createBtn;

        @FXML
        private DatePicker datePicker;

        @FXML
        private TextField eventInput;

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

        @FXML
        void initialize() {

            //setupEventSelection();

            setEventsPrePost(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());

            datePicker.setOnMouseClicked(e -> {
                // get a reference to datepicker inner content
                // attach a listener to the  << and >> buttons
                // mark events for the (prev, current, next) month and year shown
                DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
                localDate = datePicker.getValue();
                if(localDate == null){
                    System.out.println("date is null");
                }else{
                    day = localDate.getDayOfMonth();
                    month = localDate.getMonth();
                    year = localDate.getYear();
                    System.out.println("year = " + year + " month = " + month + " day = " + day);
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
            eventInput.clear();
            mainGUI.showMain();
        }

        @FXML
        void onCreateBtn(ActionEvent event) {
            String inputEvent = eventInput.getText();

            eventDate = new Date(year - 1900,month.getValue() - 1,day);
            Date currentDate = new Date();

            if(eventDate.compareTo(currentDate) > 0) {
                if (inputEvent.length() > 0 && (!eventDate.equals(null))) {

                    System.out.println("year = " + year + " month = " + month + " day = " + day);
                    //businessLogic.createEvent(currentDate,description);
                    businessLogic.createEvent(eventDate , inputEvent);
                    System.out.println(localDate.toString());
                    msgLbl.setStyle("-fx-text-fill: green;");
                    msgLbl.setText("Event created");
                }else if(eventDate.equals(null)){
                    msgLbl.setStyle("-fx-text-fill: red;");
                    msgLbl.setText("Enter a Date!!");
                } else {
                    msgLbl.setStyle("-fx-text-fill: red;");
                    msgLbl.setText("Enter event!!");
                    System.out.println("Event not created");
                }
            }else{
                msgLbl.setStyle("-fx-text-fill: red;");
                msgLbl.setText("This date is in the past!!");
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


