package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class DepMoneyController implements Controller {
    private BlFacade businessLogic;
    private MainGUI mainGUI;
    private MainUser mainUser;
    private Home home;
    @FXML
    private Button depMoenyBtn;

    @FXML
    private Label okLabel;

    @FXML
    private TextField depMoneyF;



    public DepMoneyController(BlFacade bl) {
        businessLogic = bl;
    }

    @FXML
    void closeClick(ActionEvent event) {
        mainUser.showMain();
        okLabel.setText("");
    }

    @FXML
    void onSetMoeny(ActionEvent event) {
        businessLogic.depMoeny(mainUser.user, Float.parseFloat(depMoneyF.getText()));
        okLabel.setText("Your balance has been added");
        depMoneyF.clear();

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
