package uicontrollers;

import businessLogic.BlFacade;
import emailsends.SendEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class ContactUs  implements Controller {

    @FXML
    private Button SendBtn;

    @FXML
    private TextArea description;

    @FXML
    private Label labelSend;

    @FXML
    private TextField subject;

    private MainUser mainUser;


    private BlFacade businessLogic;



    public ContactUs (BlFacade bl) {
        businessLogic = bl;
    }
    @FXML
    void onClose(ActionEvent event) {
        mainUser.showMain();
        labelSend.setStyle("-fx-background-color:none");
    }

    @FXML
    void onSend(ActionEvent event) {
        SendEmail sendEmail=new SendEmail();
        sendEmail.receiveEmailContactUs(mainUser.getUser().getEmail(),mainUser.user.getFname(),mainUser.getUser().getLname(),subject.getText(),description.getText());
        sendEmail.sendEmailContactUs(mainUser.getUser().getEmail(),mainUser.user.getFname(),mainUser.getUser().getLname(),subject.getText(),description.getText());
        labelSend.setText("Your email has been sent");
        labelSend.setStyle("-fx-background-color: green");
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    @Override
    public void setMainApp(MainUser mainUser) {
        this.mainUser=mainUser;

    }

    @Override
    public void setHomeApp(Home home) {

    }
}