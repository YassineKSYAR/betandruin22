package uicontrollers;

import businessLogic.BlFacade;
import domain.User;
import emailsends.SendConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import ui.*;

import java.io.IOException;

public class RegisterController implements Controller {
    private BlFacade businessLogic;




    private Home home;



    @FXML
    private PasswordField cpasswordF;



    @FXML
    private TextField emailF;



    @FXML
    private TextField fnameF;


    @FXML
    private TextField lnameF;



    @FXML
    private PasswordField passwordF;


    @FXML
    private Button registerBtn;

    @FXML
    private TextField userNameF;


    @FXML
    private Label labelF;

    @FXML
    private Label labelFp;


    public RegisterController(BlFacade bl) {
        businessLogic = bl;
    }

    @FXML
    void onCreateUser(ActionEvent event) throws IOException {
        if(fnameF.getText().equals("") || lnameF.getText().equals("") || userNameF.getText().equals("") || emailF.getText().equals("") || passwordF.getText().equals("") ||cpasswordF.getText().equals(""))
        {
            labelF.setText("A field is empty!!");
            System.out.println("Erreur");
        }
        else if(!(passwordF.getText().equals(cpasswordF.getText()))){
            labelFp.setText("Password and password confirmation are different!!");

        }
        else {
            User user=new User(fnameF.getText(), lnameF.getText(), userNameF.getText(), emailF.getText(), passwordF.getText());
            Stage stage = (Stage) registerBtn.getScene().getWindow();

            SendConfirmation sendConfirmation =new SendConfirmation();
            int code= sendConfirmation.sendEmail(user.getEmail(),user.getFname(),user.getLname());
            new Confirmation(businessLogic,user,code);
            stage.close();
        }


    }


    @FXML
    void onLogin(MouseEvent event) {
        home.showLogin();
    }
    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    @Override
    public void setMainApp(MainUser mainUser) {


    }

    @Override
    public void setHomeApp(Home home) {
        this.home=home;
    }
}
