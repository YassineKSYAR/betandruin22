package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.*;

import java.io.IOException;

public class RegisterController implements Controller {
    private BlFacade businessLogic;

    private MainUser mainUser;
    private MainGUI mainGUI;
    private Home home;

    @FXML
    private PasswordField cpasswordF;

    @FXML
    private Label cpasswordLab;

    @FXML
    private TextField emailF;

    @FXML
    private Label emailLab;

    @FXML
    private TextField fnameF;

    @FXML
    private Label fnameLab;

    @FXML
    private TextField lnameF;

    @FXML
    private Label lnameLab;

    @FXML
    private PasswordField passwordF;

    @FXML
    private Label passwordLab;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField userNameF;

    @FXML
    private Label userNameLab;

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
                businessLogic.createU(fnameF.getText(), lnameF.getText(), userNameF.getText(), emailF.getText(), passwordF.getText());
               Stage stage =(Stage) registerBtn.getScene().getWindow();
               home.showLogin();



        }

    }





    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI=mainGUI;
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
