
package uicontrollers;

import businessLogic.BlFacade;
import domain.User;
import emailsends.SendEmail;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import ui.Home;
import javafx.event.ActionEvent;
import ui.MainGUI;
import ui.MainUser;




public class VerificationEmail implements Controller{
    private BlFacade businessLogic;
    private MainUser user;
    private User userConfirm;


    @FXML
    private Button okBtn;

    @FXML
    private Label sendOk;

    @FXML
    private TextField confirmationCode;

    @FXML
    private Label error;
    private int code;



    public VerificationEmail(BlFacade bl,User userConfirm,int code){
        businessLogic = bl;
        this.userConfirm=userConfirm;
        this.code=code;


    }

    public void setBusinessLogic(BlFacade businessLogic) {
        this.businessLogic = businessLogic;
    }


    @FXML
    void onCreateUser(ActionEvent event) {
        System.out.println(code);
        String codeTostr=""+code+"";
        if((confirmationCode.getText().equals(codeTostr))){
            error.setText("");
            error.setStyle("-fx-background-color: white");
            sendOk.setText("");
            sendOk.setStyle("-fx-background-color: white");
            User u=businessLogic.createU(userConfirm.getFname(), userConfirm.getLname(), userConfirm.getUserName(), userConfirm.getEmail(), userConfirm.getPassword());

            Stage stage = (Stage) okBtn.getScene().getWindow();
            stage.close();
            new MainUser(businessLogic,u);
        }else {
            error.setText("the confirmation code is incorrect!!");
            error.setStyle("-fx-background-color: red");
            sendOk.setText("");
            sendOk.setStyle("-fx-background-color: white");
        }
    }

    @FXML
    void onSendCode(ActionEvent event) {
        SendEmail sendEmail =new SendEmail();
        this.code= sendEmail.sendEmail(userConfirm.getEmail(), userConfirm.getFname(), userConfirm.getLname());
        error.setText("");
        error.setStyle("-fx-background-color: white");
        sendOk.setText("Your new code has been sent");
        sendOk.setStyle("-fx-background-color: green");
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    @Override
    public void setMainApp(MainUser mainUser) {
        this.user=mainUser;

    }

    @Override
    public void setHomeApp(Home home) {


    }
}

