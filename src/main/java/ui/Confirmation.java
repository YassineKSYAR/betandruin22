package ui;

import businessLogic.BlFacade;
import domain.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uicontrollers.VerificationEmail;

import java.io.IOException;

public class Confirmation {
    BlFacade businessLogic;
    private int code;


    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }
    public Confirmation(BlFacade bl,User user,int code) {

        try {
            setBusinessLogic(bl);
            init(new Stage(),user,code);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void init(Stage stage, User user,int code) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/VerficationEmail.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new VerificationEmail(businessLogic,user,code));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setTitle("Confirmation code");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }
}
