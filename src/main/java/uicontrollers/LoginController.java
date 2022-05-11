package uicontrollers;

import businessLogic.BlFacade;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class LoginController implements Controller{

    private BlFacade businessLogic;
    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField passwordF;



    @FXML
    private TextField userNameF;



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



    public User LoginU(){

        User user=businessLogic.getUser(userNameF.getText(),passwordF.getText());

        System.out.println("Database content");
        try{
            if(user.getId()!=0) {
                if ((user.getUserName().equals(userNameF.getText()) || user.getEmail().equals(userNameF.getText())) & user.getPassword().equals(passwordF.getText()) & user.getAdmin().equals(false)) {
                    Stage stage = (Stage) LoginBtn.getScene().getWindow();
                    stage.close();
                    new MainUser(businessLogic, user);

                } else if ((user.getUserName().equals(userNameF.getText()) || user.getEmail().equals(userNameF.getText())) & user.getPassword().equals(passwordF.getText()) & user.getAdmin().equals(true)) {
                    Stage stage = (Stage) LoginBtn.getScene().getWindow();
                    stage.close();
                    new MainGUI(businessLogic);
                    System.out.println("Admin Area");
                }
               else if (userNameF.getText().equals("") || passwordF.getText().equals("")) {
                    labelFu.setText("A field is empty!!");
                }


            }
            else {
                System.out.println(user);
                labelFp.setText("UserName or password is incorrect!!");

            }
        }catch (Exception e){
            System.err.println(e);
        }
        return user;
    }


    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    @Override
    public void setMainApp(MainUser mainUser) {

    }

    @Override
    public void setHomeApp(Home home) {

    }
}
