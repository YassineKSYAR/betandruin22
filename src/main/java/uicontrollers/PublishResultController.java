package uicontrollers;

import businessLogic.BlFacade;
import domain.Event;
import domain.Matches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.Home;
import ui.MainGUI;
import ui.MainUser;
import Api.Api;

import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PublishResultController implements Controller{

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    MainUser mainUser;

    private List<Matches> events = new ArrayList<>();




    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnPublish;

    @FXML
    private TextField awayTeam;

    @FXML
    private TextField awayTeamScore;

    @FXML
    private TextField homeTeam;

    @FXML
    private TextField homeTeamScore;

    @FXML
    private TextField idEvent;

    @FXML
    private TextField winnerTeam;

    @FXML
    private TextField eventStatus;
     Event event;


    public PublishResultController(BlFacade bl){
        this.businessLogic = bl;
    }

    int Index ;

    @FXML
    void initialize(){

        events = Api.setEvents();
        Index = 0;
        setInputFeild(Index);
        idEvent.setDisable(true);
        homeTeam.setDisable(true);
        awayTeam.setDisable(true);
        idEvent.setDisable(true);
        homeTeamScore.setDisable(true);
        awayTeamScore.setDisable(true);
        winnerTeam.setDisable(true);
        eventStatus.setDisable(true);

        this.event=businessLogic.getEvent(Long.parseLong(idEvent.getText())).get(0);
        if (this.event.isPublished()==true){
            btnPublish.setDisable(true);
        }else {
            btnPublish.setDisable(false);
        }

    }

    public void clearInput(){
        idEvent.setText("");
        homeTeam.setText("");
        awayTeam.setText("");
        idEvent.setText("");
        homeTeamScore.setText("");
        awayTeamScore.setText("");
        winnerTeam.setText("");

    }

    public void setInputFeild(int Ind){
        idEvent.setText(String.valueOf(events.get(Index).id));
        homeTeam.setText(String.valueOf(events.get(Index).homeTeam.name));
        awayTeam.setText(String.valueOf(events.get(Index).awayTeam.name));
        idEvent.setText(String.valueOf(events.get(Index).id));
        eventStatus.setText(events.get(Index).status);
        if((events.get(Index).status).equals("SCHEDULED")){
            homeTeamScore.setText("");
            awayTeamScore.setText("");
        }else if((events.get(Index).status).equals("FINISHED")){
            homeTeamScore.setText(String.valueOf(events.get(Index).score.fullTime.homeTeam));
            awayTeamScore.setText(String.valueOf(events.get(Index).score.fullTime.awayTeam));
        }else{
            homeTeamScore.setText("In Play");
            awayTeamScore.setText("In Play");
            winnerTeam.setText("In Play");
        }

        if(events.get(Index).score.winner == null){
            winnerTeam.setText("");
        }else if((events.get(Index).score.winner).equals("HOME_TEAM")) {
            winnerTeam.setText(String.valueOf(events.get(Index).homeTeam.name));
        }else if((events.get(Index).score.winner).equals("AWAY_TEAM")){
            winnerTeam.setText(String.valueOf(events.get(Index).awayTeam.name));
        }
    }

    @FXML
    void onNext(ActionEvent event) {


        clearInput();
        if(Index < events.size()-1){
            Index++;
            setInputFeild(Index);
            this.event=businessLogic.getEvent(Long.parseLong(idEvent.getText())).get(0);
            btnPrevious.setDisable(false);
            System.out.println(events.get(Index));

        }else{
            btnNext.setDisable(true);
        }
        if (this.event.isPublished()==true){
            btnPublish.setDisable(true);
        }else {
            btnPublish.setDisable(false);
        }

    }

    @FXML
    void onPrevious(ActionEvent event) {
        clearInput();
        if(Index > 0){
            Index--;
            setInputFeild(Index);
            this.event=businessLogic.getEvent(Long.parseLong(idEvent.getText())).get(0);
            btnNext.setDisable(false);
            System.out.println(events.get(Index));
        }else{
            btnPrevious.setDisable(true);
        }
        if (this.event.isPublished()==true){
            btnPublish.setDisable(true);
        }else {
            btnPublish.setDisable(false);
        }
    }

    @FXML
    void onPublish(ActionEvent event) throws ParseException {
        if((events.get(Index).status).equals("SCHEDULED")){
            System.out.println("Event not finished yet");
        }else if((events.get(Index).status).equals("IN_PLAY") && (events.get(Index).status).equals("PAUSED")){
            System.out.println("Event not finished yet");
        }else if((events.get(Index).status).equals("FINISHED")&&winnerTeam.getText().length()>0){
            int id = Integer.parseInt(idEvent.getText());
            String win = winnerTeam.getText();
            System.out.println(win + " " + id);
            businessLogic.publishResult(id,win);
        }
        this.event=businessLogic.getEvent(Long.parseLong(idEvent.getText())).get(0);
        if (this.event.isPublished()==true){
            btnPublish.setDisable(true);
        }else {
            btnPublish.setDisable(false);
        }

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void setMainApp(MainUser mainUser) {
        this.mainUser=mainUser;
    }

    @Override
    public void setHomeApp(Home home) {

    }
}
