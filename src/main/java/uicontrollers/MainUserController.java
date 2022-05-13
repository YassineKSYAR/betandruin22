package uicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.Home;

import ui.MainGUI;
import ui.MainUser;



public class MainUserController implements Controller{


    private MainUser mainUser;


    @FXML
    private Button logOutBtn;


    @FXML
    void MakeBet(ActionEvent event) {
        mainUser.showMakeBet();

    }

    @FXML
    void ondepMoeny(ActionEvent event) {
        mainUser.showDepMoeny();
        System.out.println(mainUser.getUser());
    }

    @FXML
    void onRemoveBet(ActionEvent event) {
        mainUser.showRemoveBet();
    }

    @FXML
    void onShowMvm(ActionEvent event) {
        mainUser.showMvm();
    }



    @FXML
    void onContactUs(ActionEvent event) {
        mainUser.showContactUs();
    }
    @FXML
    void onlogOut(ActionEvent event) {
        Stage stage=(Stage) logOutBtn.getScene().getWindow();
        stage.close();
        this.mainUser.user=null;
        Home home=new Home();

        home.showMain();
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
