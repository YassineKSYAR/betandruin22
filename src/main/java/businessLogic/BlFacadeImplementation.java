package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;


import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Results;
import domain.User;
import domain.Bet;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


/**
 * Implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BlFacade")
public class BlFacadeImplementation implements BlFacade {

	DataAccess dbManager;
	ConfigXML config = ConfigXML.getInstance();

	public BlFacadeImplementation()  {
		System.out.println("Creating BlFacadeImplementation instance");
		boolean initialize = config.getDataBaseOpenMode().equals("initialize");
		dbManager = new DataAccess(initialize);
		if (initialize)
			dbManager.initializeDB();
		dbManager.close();
	}

	public BlFacadeImplementation(DataAccess dam)  {
		System.out.println("Creating BlFacadeImplementation instance with DataAccess parameter");
		if (config.getDataBaseOpenMode().equals("initialize")) {
			dam.open(true);
			dam.initializeDB();
			dam.close();
		}
		dbManager = dam;
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 *
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		//The minimum bid must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").
					getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);
		dbManager.close();
		return qry;
	}

	@WebMethod
	public Event createEvent(Date date, String eventDescription) {

		//The minimum bid must be greater than 0
		dbManager.open(false);
		Event ev = null;

		/*if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").
					getString("ErrorEventHasFinished"));*/
		ev = dbManager.createEvent(date, eventDescription);
		dbManager.close();
		return ev;
	}

	@WebMethod
	public void removeEvent(Event event){
		dbManager.open(false);
		dbManager.removeEvent(event);
		dbManager.close();
	}

	@WebMethod
	public void removeEventQuestions(Event event){
		dbManager.open(false);
		dbManager.removeEventQuestions(event);
		dbManager.close();
	}


	@WebMethod
	public User createU(String fname, String lname, String userName, String email, String password){
		dbManager.open(false);
		User user=null;
		user=dbManager.createUser(fname,lname,userName,email,password);
		dbManager.close();
		return user;
	}
	@WebMethod
	public Results createResult(int idevent, int idQuestion, String result, float fee){
		dbManager.open(false);
		Results results=null;
		results=dbManager.createResult(idevent,idQuestion,result,fee);
		dbManager.close();
		return results;
	}



	@WebMethod
	public Bet createBet(long idResults,int amount,float fee,long idUser){
		dbManager.open(false);
		Bet bet =null;
		bet=dbManager.createBet(idResults,amount,fee,idUser);
		dbManager.close();
		return bet;
	}

	@WebMethod
	public User  deleteMoeny(User user,int money){
		dbManager.open(false);
		dbManager.deleteMoeny(user,money);
		dbManager.close();
		return user;
	}

	@WebMethod
	public User  depMoeny(User user,float money){
		dbManager.open(false);
		dbManager.depMoeny(user,money);
		dbManager.close();
		return user;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 *
	 * @param date in which events are retrieved
	 * @return collection of events
	 */

	@WebMethod
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	@WebMethod
	public  List<Event> getEvent(long id){
		dbManager.open(false);
		List<Event>  event = dbManager.getEvent(id);
		dbManager.close();
		return event;

	}
	@WebMethod
	public List<Question> getQuestion(long id){
		dbManager.open(false);
		List<Question>  questions = dbManager.getQuestion(id);
		dbManager.close();
		return questions;


	}

	@WebMethod
	public List<User>  getMony(User user){
		dbManager.open(false);
		List<User> users= dbManager.getMony(user);
		dbManager.close();
		return users;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 *
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */

	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@WebMethod
	public List<User> getUser(){
		dbManager.open(false);
		System.out.println(">> DataAccess: getUsers");
		List<User> users = dbManager.getUsers();
		dbManager.close();
		return users;
	}

	@WebMethod
	public  List<Results>  getResults(int idEvent,int idQuestion){
		dbManager.open(false);
		List<Results> results = dbManager.getResults(idEvent,idQuestion);
		dbManager.close();
		return  results;
	}

	@WebMethod
	public List<Results> getResult(long idResult){
		dbManager.open(false);
		List<Results> results = dbManager.getResult(idResult);
		dbManager.close();
		return  results;
	}

	@WebMethod
	public List<Bet> getBet(User user){
		dbManager.open(false);
		System.out.println(">> DataAccess: getBet");
		List<Bet> bets = dbManager.getBet(user);
		dbManager.close();
		return bets;
	}

	@WebMethod
	public void  deleteBet(long idU,long idBet){
		dbManager.open(false);
		System.out.println(">>DataAccess: deleteBet");
		dbManager.deleteBet(idU,idBet);
		dbManager.close();
	}

	@WebMethod
	public  List<Bet>  getBetByID(long idU,long idB){
		dbManager.open(false);
		System.out.println(">> DataAccess: getBetById");
		List<Bet> bets = dbManager.getBetByID(idU,idB);
		dbManager.close();
		return bets;

	}



	public void close() {
		dbManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
}
