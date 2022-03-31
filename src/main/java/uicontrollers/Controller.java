package uicontrollers;

import ui.Home;
import ui.MainGUI;
import ui.MainUser;

public interface Controller {

  void setMainApp(MainGUI mainGUI);

  void setMainApp(MainUser mainUser);
  void setHomeApp(Home home);
}
