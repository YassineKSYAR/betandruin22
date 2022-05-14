package uicontrollers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public class MainGUIController implements Controller{

    @FXML
    private Button logOutBtn;
    ///////////////////////////////////////////////
    @FXML
    public Button removeEventBtn;

    @FXML
    private Button publishResultBtn;

    /////////////////////////////////////////////
    private MainGUI mainGUI;


    @FXML
    void browseQuestions(ActionEvent event) {
        mainGUI.showBrowseQuestions();
    }

    @FXML
    void createQuestion(ActionEvent event) {
        mainGUI.showCreateQuestion();
    }

    @FXML
    void setFee(ActionEvent event) {mainGUI.showSetFee();}

    ////////////////////////////////////////////////////////
    @FXML
    void createEvent(ActionEvent event){ mainGUI.showCreateEvent(); }
    //////////////////////////////////////////////////////

    @FXML
    void onRemoveBtn(ActionEvent event){ mainGUI.showRemoveEvent(); }

    @FXML
    void onPublishResult(ActionEvent event) { mainGUI.showPublishResult(); }

    @FXML
    void onlogOut(ActionEvent event) {
        Stage stage=(Stage) logOutBtn.getScene().getWindow();
        stage.close();
        this.mainGUI=null;
        Home home=new Home();

        home.showMain();
    }





    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI=mainGUI;
    }

    @Override
    public void setMainApp(MainUser mainUser) {

    }

    @Override
    public void setHomeApp(Home home) {

    }



}
