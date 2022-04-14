package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.*;



public class HomeController implements Controller{
    BlFacade bl ;

    @FXML
    private Button RegisterBtn;

    @FXML
    private Button loginBtn;
    private  Home home;

    @FXML
    void onLogin(ActionEvent event) {
        home.showLogin();
    }

    @FXML
    void onRegister(ActionEvent event) {
        home.showRegister();
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
