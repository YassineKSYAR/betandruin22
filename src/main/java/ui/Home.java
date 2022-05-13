package ui;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import domain.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import uicontrollers.*;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Home {

    private Window home, login, register;
    private User user;
    private BlFacade businessLogic;
    private Stage stage;
    private Scene scene;
    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }
    public  Home(BlFacade bl) {
        Platform.startup(() -> {

            try {
                setBusinessLogic(bl);
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public  Home() {
        setBusinessLogic(new BlFacadeImplementation());
            try {
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }


    }




    class Window {
        Controller c;
        Parent ui;
    }

    private Window load(String fxmlfile) throws IOException {
        Window window = new Window();
        FXMLLoader loader = new FXMLLoader(Home.class.getResource(fxmlfile), ResourceBundle.getBundle("Etiquetas", Locale.getDefault()));
        loader.setControllerFactory(controllerClass -> {

            if (controllerClass == LoginController.class) {
                return new LoginController(businessLogic);
            }
            if(controllerClass == RegisterController.class){
                return new RegisterController(businessLogic);
            }

            else {
                // default behavior for controllerFactory:
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            }


        });
        window.ui = loader.load();
        ((Controller) loader.getController()).setHomeApp(this);
        window.c = loader.getController();
        return window;
    }

    public void init(Stage stage) throws IOException {

        this.stage = stage;

        home= load("/HomeGUI.fxml");
        login = load("/LoginGUI.fxml");
        register = load("/RegisterGUI.fxml");


        showMain();

    }

//  public void start(Stage stage) throws IOException {
//      init(stage);
//  }


    public void showMain(){
        setupScene(home.ui, "Home", 485, 356);
    }

    public void showLogin() {
        setupScene(login.ui, "Login", 590, 490);
    }

    public void showRegister() {
        setupScene(register.ui, "Register", 696, 550);
    }



    private void setupScene(Parent ui, String title, int width, int height) {
        if (scene == null){
            scene = new Scene(ui, width, height);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        }
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle(ResourceBundle.getBundle("Etiquetas",Locale.getDefault()).getString(title));
        scene.setRoot(ui);
        stage.show();
        stage.setResizable(false);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    //  public static void main(String[] args) {
//    launch();
//  }




}
