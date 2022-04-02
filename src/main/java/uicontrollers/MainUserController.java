package uicontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class MainUserController implements Controller{

    @FXML
    private Label selectOptionLbl;

    @FXML
    private Button MakeBetBtn;

    @FXML
    private Button createQuestionBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button depMoney;

    private MainGUI mainGui;
    private MainUser mainUser;
    private  Home home;


    @FXML
    private Button removeBetBtn;

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
    void initialize() {


    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGui=mainGUI;
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
