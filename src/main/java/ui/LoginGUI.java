package ui;

import businessLogic.BlFacade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

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
        stage.setResizable(false);


    }



}

