package ui;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.kordamp.bootstrapfx.BootstrapFX;
import uicontrollers.BrowseQuestionsController;
import uicontrollers.LoginController;

import java.io.IOException;

public class LoginGUI {
    BlFacade businessLogic;
    private Scene scene;
    private Stage stage;
    private Window login;

    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }
    public LoginGUI(BlFacade bl) {

            try {
                setBusinessLogic(bl);
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }


    }


    public void init(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 696, 613);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();


    }



}

