package ui;

import businessLogic.BlFacade;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterGUI  {
    private BlFacade businessLogic;


    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }
    public RegisterGUI(BlFacade bl) {

            try {
                setBusinessLogic(bl);
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    public void init(Stage stage) throws IOException{
        System.out.println("sds");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RegisterGUI.fxml"));
        System.out.println(RegisterGUI.class.getResource("/RegisterGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 696, 613);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();


    }

}
