package uicontrollers;

import businessLogic.BlFacade;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

import java.util.List;

public class LoginController implements Controller{

    private BlFacade businessLogic;
    private MainGUI mainGUI;
    private MainUser mainUser;
    private Home home;



    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField passwordF;

    @FXML
    private Label passwordLab;

    @FXML
    private TextField userNameF;

    @FXML
    private Label userNameLab;

    @FXML
    private Label labelFu;

    @FXML
    private Label labelFp ;

    public LoginController(BlFacade bl) {
        businessLogic = bl;
    }




    @FXML
    void onLogin(ActionEvent event) {
        LoginU();

    }



    public List<User> LoginU(){

        List<User> users=businessLogic.getUser();

        System.out.println("Database content");
        try{
        for (User  u:users){
            if((u.getUserName().equals(userNameF.getText())||u.getEmail().equals(userNameF.getText()))& u.getPassword().equals(passwordF.getText())&u.getAdmin().equals(false)){
                Stage stage =(Stage) LoginBtn.getScene().getWindow();
                stage.close();
              new MainUser(businessLogic,u);

            }else if((u.getUserName().equals(userNameF.getText())||u.getEmail().equals(userNameF.getText()))& u.getPassword().equals(passwordF.getText())& u.getAdmin().equals(true)){
                Stage stage =(Stage) LoginBtn.getScene().getWindow();
                stage.close();
                new MainGUI(businessLogic);


                System.out.println("Admin Area");
            }else if(!(u.getUserName().equals(userNameF.getText()))||!(u.getEmail().equals(userNameF.getText()))||!(u.getPassword().equals(passwordF.getText()))){
                labelFp.setText("UserName or password is incorrect!!");
            }else if(userNameF.getText().equals("")||passwordF.getText().equals("")) {
                labelFu.setText("A field is empty!!");
            }

        }
        }catch (Exception e){
            System.err.println(e);
        }
        return users;
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
