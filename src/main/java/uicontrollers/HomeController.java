package uicontrollers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import ui.*;


public class HomeController implements Controller{


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
