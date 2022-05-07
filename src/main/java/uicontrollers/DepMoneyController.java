package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class DepMoneyController implements Controller {

    private BlFacade businessLogic;

    private MainUser mainUser;


    @FXML
    private Button depMoenyBtn;
    @FXML
    private Label okLabel;

    @FXML
    private TextField depMoneyF;

    @FXML
    private Label myBalance;


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
        if (depMoneyF.getText().length()>0){
            businessLogic.depMoeny(mainUser.user, Float.parseFloat(depMoneyF.getText()));
            okLabel.setText("Your balance has been added");

            myBalance.setText(""+businessLogic.getMony(mainUser.user).get(0).getMoney());
            depMoneyF.clear();
        }

        if(depMoneyF.getText().length()>0){
            depMoenyBtn.setDisable(false);
        }else {
            depMoenyBtn.setDisable(true);
        }

    }

    @FXML
    void onShowBalance(MouseEvent event) {
        if(depMoneyF.getText().length()>0){
            depMoenyBtn.setDisable(false);
        }else {
            depMoenyBtn.setDisable(true);
        }

            myBalance.setText(""+businessLogic.getMony(mainUser.user).get(0).getMoney());

    }


    @FXML
    void onSetBtn(KeyEvent event) {
        depMoenyBtn.setDisable(false);
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
