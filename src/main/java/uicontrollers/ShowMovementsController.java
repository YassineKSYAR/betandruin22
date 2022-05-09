package uicontrollers;

import businessLogic.BlFacade;
import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

import java.util.Date;
import java.util.List;

public class ShowMovementsController implements Controller {
    private BlFacade businessLogic;
    private MainUser mainUser;

    @FXML
    private Label labelBalance;

    @FXML
    private TableColumn<MyBet, Date> date;


    @FXML
    private TableColumn<MyBet,Float> amount;

    @FXML
    private TableColumn<MyBet, Long> id;


    @FXML
    private TableColumn<MyBet, String> type;

    @FXML
    private TableView<MyBet> tblBet;




    public ShowMovementsController(BlFacade bl) {
        businessLogic = bl;
    }


    @FXML
    void closeClick (ActionEvent event) {
        mainUser.showMain();



    }

    @FXML
    void onShowBet(MouseEvent event) {

        float myBalance=businessLogic.getMony(mainUser.user).get(0).getMoney();
        labelBalance.setText(""+myBalance);
        tblBet.getItems().clear();
        displayBets();


    }

    void  displayBets(){

        System.out.println(mainUser.getUser());
        List<Movements> movements=businessLogic.getBetMvm(mainUser.getUser());

        for(Movements movement:movements){
            if(movement.getIdResults()!=0) {
                List<Results> results = businessLogic.getResult(movement.getIdResults());
                for (Results result : results) {
                    Date date1 = movement.getDate();
                    float amount1 = movement.getAmount();
                    int idR = (int) movement.getIdResults();
                    String type1 = movement.getType();

                    MyBet myBet = new MyBet(idR, date1, type1, - amount1);
                    System.out.println(myBet);
                    tblBet.getItems().add(myBet);
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    amount.setCellValueFactory(new PropertyValueFactory<>("f"));
                    type.setCellValueFactory(new PropertyValueFactory<>("type"));

                }
            }
            else {
                Date date2 = movement.getDate();
                float amount2 = movement.getAmount();
                String type2 = movement.getType();
                MyBet myBet = new MyBet(0, date2, type2, + amount2);
                System.out.println(myBet);
                tblBet.getItems().add(myBet);
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                amount.setCellValueFactory(new PropertyValueFactory<>("f"));
                type.setCellValueFactory(new PropertyValueFactory<>("type"));


            }



        }



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
