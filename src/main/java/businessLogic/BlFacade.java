package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BlFacade  {

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
	Question createQuestion(Event event, String question, float betMinimum) 
			throws EventFinished, QuestionAlreadyExist;

	@WebMethod
	 User createU(String fname, String lname, String userName, String email, String password);

	@WebMethod
	Results createResult(int idevent, int idQuestion, String result, float fee);

	@WebMethod
	Bet createBet(long idResults, int amount,float fee,long idUser);

	@WebMethod
	User  depMoeny(User user,float money);
	@WebMethod
	List<Results>  getResults(int idEvent,int idQuestion);
	@WebMethod
	List<Results> getResult(long idResult);

	@WebMethod
	 List<Bet> getBet(User user);
	@WebMethod List<User>  getMony(User user);


	/**
	 * This method retrieves all the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	@WebMethod  List<Event> getEvent(long id);
	@WebMethod public List<User> getUser();
	@WebMethod List<Question> getQuestion(long id);
	@WebMethod List<Bet>  getBetByID(long idU,long idB);

	@WebMethod  void deleteBet(long idU,long idBet);
	@WebMethod User  deleteMoeny(User user,int money);



	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);

}