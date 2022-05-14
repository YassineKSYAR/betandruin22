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

public class MainGUI {

  private Window mainLag,createQuestionLag,browseQuestionsLag,setFee,createEventLag,removeEventLag,publishResultLag;
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
  public  MainGUI(BlFacade bl) {

    try {
      setBusinessLogic(bl);
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
    FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(fxmlfile), ResourceBundle.getBundle("Etiquetas", Locale.getDefault()));
    loader.setControllerFactory(controllerClass -> {

      if (controllerClass == BrowseQuestionsController.class) {
        return new BrowseQuestionsController(businessLogic);
      }
      if (controllerClass == CreateQuestionController.class) {
        return new CreateQuestionController(businessLogic);
      } if(controllerClass == SetFeeController.class){
        return new SetFeeController(businessLogic);
      }if(controllerClass == CreateEventController.class){
        return new CreateEventController(businessLogic);
      }if(controllerClass == RemoveEventController.class){
        return new RemoveEventController(businessLogic);
      }if(controllerClass == PublishResultController.class){
        return new PublishResultController(businessLogic);
      } else {
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

    mainLag = load("/MainGUI.fxml");
    browseQuestionsLag = load("/BrowseQuestions.fxml");
    createQuestionLag = load("/CreateQuestion.fxml");
    setFee=load("/SetFeeGUI.fxml");

    ///////////////////////////////////////////////////////////
    createEventLag = load("/CreateEvent.fxml");
    removeEventLag = load("/RemoveEvent.fxml");
    publishResultLag = load("/PublishResult.fxml");
    //////////////////////////////////////////////////////////

    showMain();

  }

//  public void start(Stage stage) throws IOException {
//      init(stage);
//  }


  public void showMain(){
    setupScene(mainLag.ui, "MainTitle", 553, 400);
  }

  public void showBrowseQuestions() {
    setupScene(browseQuestionsLag.ui, "BrowseQuestions", 1000, 500);
  }

  public void showCreateQuestion() {
    setupScene(createQuestionLag.ui, "CreateQuestion", 550, 400);
  }

  public void showSetFee() {
    setupScene(setFee.ui, "SetFee", 990, 571);
  }

  /////////////////////////////////////////////////////////////////////////////
  public void showCreateEvent(){ setupScene(createEventLag.ui, "CreateEvent", 600,500); }

  public void showRemoveEvent(){ setupScene(removeEventLag.ui, "RemoveEvent", 610,500); }

  public void showPublishResult(){ setupScene(publishResultLag.ui, "PublishResult", 650,507); }

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
