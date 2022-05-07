package uicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class MainUserController implements Controller{

    private MainUser mainUser;


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
