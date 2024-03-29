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
import ui.Home;
import ui.MainGUI;
import ui.MainUser;

import javafx.scene.input.MouseEvent;

import java.util.Date;
import java.util.List;

public class RemoveBetController implements Controller{
    private MainUser mainUser;

    @FXML
    private TableColumn<MyBet, Float> betB;

    @FXML
    private Button removeBtn;




    @FXML
    private TableColumn<MyBet, Date> dateB;


    @FXML
    private TableColumn<MyBet, String> eventB;

    @FXML
    private TableColumn<MyBet, Float> feeB;

    @FXML
    private TableColumn<MyBet, Long> id;

    @FXML
    private TableColumn<MyBet, String> questionB;

    @FXML
    private TableColumn<MyBet, String> resultB;

    @FXML
    private TableView<MyBet> tblBet;


    @FXML
    private Label removeSuc;

    BlFacade businessLogic;



    public RemoveBetController(BlFacade bl) {
        businessLogic = bl;
    }



    @FXML
    void closeClick (ActionEvent event) {
        mainUser.showMain();
        removeSuc.setText("");

    }


    @FXML
    void onShowBet(MouseEvent event) {

        tblBet.getItems().clear();
            displayBets();


    }



    void  displayBets(){
        System.out.println(mainUser.getUser());
        List<Bet> bets=businessLogic.getBet(mainUser.getUser());
        for(Bet bet:bets){
            List<Results> results =businessLogic.getResult(bet.getIdResults());
            for(Results result:results){
                List<Question> questions=businessLogic.getQuestion(result.getIdQuestion());
                List<Event> events=businessLogic.getEvent(result.getIdevent());

                long idR=bet.getId();
                Date date =events.get(0).getEventDate();
                String ev=events.get(0).getDescription();
                String q=questions.get(0).getQuestion();
                String re=result.getResult();
                float f=result.getFee();
                float b= bet.getAmount();
                MyBet myBet=new MyBet(idR,date,ev,q,re,f,b);
                tblBet.getItems().add(myBet);
                id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                dateB.setCellValueFactory(new PropertyValueFactory<>("date"));
                eventB.setCellValueFactory(new PropertyValueFactory<>("ev"));
                questionB.setCellValueFactory(new PropertyValueFactory<>("q"));
                resultB.setCellValueFactory(new PropertyValueFactory<>("re"));
                feeB.setCellValueFactory(new PropertyValueFactory<>("f"));
                betB.setCellValueFactory(new PropertyValueFactory<>("b"));


            }
        }
        if(tblBet.getItems().size()>0){
            removeBtn.setDisable(false);
        }else {
            removeBtn.setDisable(true);
        }


    }


    @FXML
    void onremove(ActionEvent event) {
        Date date =new Date();
        float bet=tblBet.getSelectionModel().getSelectedItem().getB();
        businessLogic.depMoeny(mainUser.user,bet);
        long idBet=tblBet.getSelectionModel().getSelectedItem().getId();
        businessLogic.deleteBet(mainUser.user.getId(),idBet);
        businessLogic.createMvm(date,0,"Remove a Bet",(int)bet,mainUser.getUser().getId());
        tblBet.getItems().clear();
        removeSuc.setText("Your bet has been deleted");
        displayBets();
        if(tblBet.getItems().size()>0){
            removeBtn.setDisable(false);
        }else {
            removeBtn.setDisable(true);
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




