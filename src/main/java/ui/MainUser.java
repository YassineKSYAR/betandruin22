package ui;

import businessLogic.BlFacade;

import domain.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import uicontrollers.*;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainUser {

    private Window mainLag, makeBet,depMoeny,removeBet;

    private BlFacade businessLogic;
    private Stage stage;
    private Scene scene;
    public  User user ;

    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }

    public MainUser(BlFacade bl,User user) {
            try {
                setBusinessLogic(bl);
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.user=user;


    }

    public User getUser() {
        return user;
    }


    class Window {
        Controller c;
        Parent ui;
    }

    private Window load(String fxmlfile) throws IOException {
        Window window = new Window();
        FXMLLoader loader = new FXMLLoader(MainUser.class.getResource(fxmlfile), ResourceBundle.getBundle("Etiquetas", Locale.getDefault()));
        loader.setControllerFactory(controllerClass -> {

            if (controllerClass == MakeBetController.class) {
                return new MakeBetController(businessLogic);
            }
            if(controllerClass == DepMoneyController.class){
                return new DepMoneyController(businessLogic);
            }
            if(controllerClass == RemoveBetController.class){
                return new RemoveBetController(businessLogic);
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
        ((Controller) loader.getController()).setMainApp(this);
        window.c = loader.getController();
        return window;
    }

    public void init(Stage stage) throws IOException {

        this.stage = stage;

        mainLag = load("/MainUser.fxml");
        makeBet= load("/MakeBet.fxml");
        depMoeny=load("/DepMoney.fxml");
        removeBet=load("/RemoveBet.fxml");
        showMain();

    }

//  public void start(Stage stage) throws IOException {
//      init(stage);
//  }


    public void showMain(){
        setupScene(mainLag.ui, "MainTitle", 393, 289);
    }

    public void showMakeBet() {
        setupScene(makeBet.ui, "MakeBet", 1058, 680);
    }
    public void showDepMoeny(){
        setupScene(depMoeny.ui, "DepositMoeny", 600, 400);}

    public void showRemoveBet(){
        setupScene(removeBet.ui, "RemoveBet", 1600, 584);}




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

//  public static void main(String[] args) {
//    launch();
//  }




}
