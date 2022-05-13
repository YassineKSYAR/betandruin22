	package dataAccess;

import java.util.*;

import javax.persistence.*;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;
import Api.Api;
import domain.Matches;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

	/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess  {

	protected EntityManager  db;
	protected EntityManagerFactory emf;

	ConfigXML config = ConfigXML.getInstance();

	private List<Matches> events = new ArrayList<>();

	public DataAccess(boolean initializeMode)  {
		System.out.println("Creating DataAccess instance => isDatabaseLocal: " +
				config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());
		open(initializeMode);
	}

	public DataAccess()  {
		this(false);
	}


	/**
	 * This method initializes the database with some trial events and questions.
	 * It is invoked by the business logic when the option "initialize" is used
	 * in the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB(){

		events = Api.setEvents();

		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) { month = 0; year += 1;}

			events = Api.setEvents();

			for(Matches ev: events){

				DateTime dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(ev.utcDate);

				Event event = new Event(ev.id,ev.homeTeam.name + "-" + ev.awayTeam.name,UtilDate.newDate(dateTime.getYear(),dateTime.getMonthOfYear()-1,dateTime.getDayOfMonth()),false);

				//Question q2;
				Question q1Test;
				q1Test = event.addQuestion("Who will win the match?", 3);

				db.persist(q1Test);
				db.persist(event);

			}

			/*Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));


			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;*/

			/*if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			}
			else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}*/

			User admin =new User("admin","admin","admin","admin","admin");
			admin.setAdmin(true);
			User user=new User("test","test","test","test","test");
			User user1=new User("test1","test1","test1","test1","test1");
			User user2=new User("test2","test2","test2","test2","test2");

			/*Results r1= new Results(11,3,"Atletico", 2.5F);
			Results r2= new Results(11,4,"5", 2.5F);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);*/

			db.persist(admin);
			db.persist(user);
			db.persist(user1);
			db.persist(user2);


			/*db.persist(r1);
			db.persist(r2);*/

			db.getTransaction().commit();
			System.out.println("The database has been initialized");


			for(Matches ev: events){
				Event event=findEventId(ev.id);
				DateTime dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(ev.utcDate);
				setQuestion(ev.id,event.getQuestions(),ev.homeTeam.name,ev.awayTeam.name);

			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 *
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */


	public List<Question> setQuestion(int id,Vector<Question> questions,String homeTeam,String awayTeam){
		for (Question question:questions) {
			Results r1 = new Results(id, question.getQuestionNumber(), homeTeam, 2.5F);
			Results r2 = new Results(id, question.getQuestionNumber(), awayTeam, 2.5F);
			db.getTransaction().begin();
			db.persist(r1);
			db.persist(r2);
			db.getTransaction().commit();
		}
		return questions;
	}
	public Question createQuestion(Event event, String question, float betMinimum)
			throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event = " + event + " question = " +
				question + " minimum bet = " + betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.doesQuestionExist(question)) throw new QuestionAlreadyExist(
				ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExists"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added
		// in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;
	}

	public void removeEventQuestions(Event event){
		System.out.println(">> DataAccess: createQuestion=> event = " + event );

		Event ev = db.find(Event.class, event.getEventNumber());

		db.getTransaction().begin();
		ev.removeQuestions();
		db.persist(ev);
		db.getTransaction().commit();
	}
	public void removeBet(long resultId){
		db.getTransaction().begin();
		TypedQuery<Bet> query = db.createQuery("SELECT b FROM Bet b WHERE b.idResults=?1",
				Bet.class);
		query.setParameter(1,resultId);
		List<Bet> Bets = query.getResultList();
		db.getTransaction().commit();
		if(Bets == null){
			System.out.println("No bets to delete!!!");
		}else{
			for(Bet bet: Bets){
				db.getTransaction().begin();
				Bet bet1 = db.find(Bet.class, bet);
				db.remove(bet1);
				db.getTransaction().commit();
			}

		}


	}
	public void removeResult(int eventID){
		db.getTransaction().begin();
		TypedQuery<Results> query = db.createQuery("SELECT r FROM Results r WHERE r.idevent=?1",
				Results.class);
		query.setParameter(1,eventID);
		db.getTransaction().commit();
		List<Results> Results1 = query.getResultList();
		if(Results1 == null){
			System.out.println("No results to delete!!!");
		}else{
			for(Results r: Results1){
				removeBet(r.getIdR());
				db.getTransaction().begin();
				Results R = db.find(Results.class, r);
				db.remove(R);
				db.getTransaction().commit();
			}
		}

	}

		/////////////////////////////////////// Create event in database ////////////////////////////
		public Event createEvent(Date date, String eventDescription)  {
			System.out.println(">> DataAccess: createEvent=> Date = " + date + " description = " +
					eventDescription);

			Vector<Integer> eventNumbers = new Vector<Integer>();
			TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev",
					Event.class);
			List<Event> events = query.getResultList();
			for (Event ev:events){
				System.out.println(ev.toString());
				eventNumbers.add(ev.getEventNumber());
			}
			Collections.sort(eventNumbers);
			int eventNumber = eventNumbers.get(eventNumbers.size() - 1) + 1;

			Event event=new Event( eventNumber, eventDescription, date);

		/*if (event.doesEventExist()) throw new EventAlreadyExist (
				ResourceBundle.getBundle("Etiquetas").getString("ErrorEventAlreadyExist"));*/

			db.getTransaction().begin();

			db.persist(event);

			db.getTransaction().commit();
			System.out.println("Event Added to Database");
			return event;
		}

		//////////////////////////////////////////////////Remove Event///////////////////////
		public void removeEvent(Event event){
			removeResult(event.getEventNumber());
			db.getTransaction().begin();
			Event ev = db.find(Event.class, event.getEventNumber());
			db.remove(ev);
			db.getTransaction().commit();
			System.out.println("object removed id:" + event.getEventNumber() + ", description:"+ event.getDescription());
		}


	public User createUser(String fname, String lname, String userName, String email, String password){

		System.out.println(">> DataAccess: createUser=> fname = " + fname + " lnmae = " +
				lname + " userName = " + userName + " password = " +password);
		User user = new User(fname,lname,userName,email,password);

		db.getTransaction().begin();

		db.persist(user);
		db.getTransaction().commit();
		return user;
	}

	public Results createResult(int idevent,int idQuestion,String result,float fee){
		System.out.println(">> DataAccess: createResultr=> idEvent = " + idevent + " idQuestion = " +
				idQuestion + " result = " + result + " fee = " +fee);
		Results r = new Results(idevent,idQuestion,result,fee);

		db.getTransaction().begin();

		db.persist(r);
		db.getTransaction().commit();
		return r;
	}
	public boolean findEventById(int eventId){
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber="+eventId,
				Event.class);
		List<Event> ev = query.getResultList();
		System.out.println();
		if(ev.isEmpty()){
			return false;
		}else if(!ev.isEmpty()){
			return true;
		}
		return false;
	}
	public Event findEventId(int eventId){
			System.out.println(">> DataAccess: getEvent");
			TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber="+eventId,
					Event.class);
			List<Event> ev = query.getResultList();
			System.out.println();
			return ev.get(0);
		}

	public List<Bet> publishResult(int eventId,String winner,String loser){
		List<Bet> WinningBets = null;
		List<Bet> losersBets = null;
		Event event1=findEventId(eventId);
		if(findEventById(eventId) == false){
			System.out.println("Event is not available anymore!!");
			System.out.println(findEventById(eventId));
		}else if(findEventById(eventId) == true&& winner.length()>0){
			if(event1.isPublished()==false){
			Results WinningResults = getResults(eventId,winner).get(0);
			Results losers = getResults(eventId,loser).get(0);

			WinningBets = getBetByResultID(WinningResults.getIdR());
			losersBets = getBetByResultID(losers.getIdR());


			for(Bet B:WinningBets){
				addMoney((int) B.getIdUser(),B.getAmount()*B.getFee());
				deleteBet(B.getIdUser(),B.getId());

			}
			for(Bet Bl:losersBets){
					deleteBet(Bl.getIdUser(),Bl.getId());

			}
			Published(event1,true);

		}

		}
		else if (winner.length()==0) {
			Published(event1,true);
		}
		return WinningBets;
	}
	public void Published(Event event,Boolean published){
			db.getTransaction().begin();
			Event ev =db.find(Event.class,event);
			ev.setPublished(published);
			db.persist(ev);
			db.getTransaction().commit();
			System.out.println(ev.toString() + " has been updated");
		}
	public  List<Results>  getResults(int idEvent,String winner){
		System.out.println(">> DataAccess: getResults");
		System.out.println(winner);
		TypedQuery<Results> r1 = db.createQuery("SELECT u FROM Results u where u.idevent="+idEvent+" and u.result=?1", Results.class);
		r1.setParameter(1, winner);
		System.out.println(r1);
			List<Results> results = r1.getResultList();
			return results;
	}
	public Bet createBet(long idResults,int amount,float fee,long idUser){

			System.out.println(">> DataAccess: createBet=> amount"+amount);
			Bet bet = new Bet(idResults,amount,fee,idUser);

			db.getTransaction().begin();

			db.persist(bet);
			db.getTransaction().commit();
			return bet;
		}
		public Movements createMvm(Date date,long idResults,String type,int amount,long idUser){

			System.out.println(">> DataAccess: createMvm=> Date = "+date+"amount"+amount);
			Movements movements = new Movements(idResults,date,type,amount,idUser);
			db.getTransaction().begin();
			db.persist(movements);
			db.getTransaction().commit();
			return movements;
		}

	/**
	 * This method retrieves from the database the events of a given date
	 *
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",
				Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}


		public List<Event> getEvent(long id) {
			System.out.println(">> DataAccess: getEvent");
			TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber="+id,
					Event.class);
			List<Event> event = query.getResultList();
			if(event.size()== 0){
				return null;
			}
			return event;
		}

		public List<Question> getQuestion(long id) {
			System.out.println(">> DataAccess: getQuestion");
			TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q.questionNumber="+id,
					Question.class);
			List<Question> questions = query.getResultList();
			return questions;
		}


	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 *
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev "
				+ "WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}
	public User getUsers(String userName,String password){
		User user=new User();
		System.out.println(">> DataAccess: getUsers");
		TypedQuery<User> q1 = db.createQuery("SELECT u FROM User u where u.userName=?1 and u.password=?2",
				User.class);
		q1.setParameter(1, userName);
		q1.setParameter(2, password);
		List<User> users = q1.getResultList();
		if(users.size()==0){
			System.out.println(users);
			return user;
		}else {

			return users.get(0);
		}
	}

	public List<User> getMony(User user) {

			TypedQuery<User> q1 = db.createQuery("SELECT u from User u where u.idUser="+user.getId(),
					User.class);
			List<User> users = q1.getResultList();

		return users;
	}


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " +
				config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

		String fileName = config.getDataBaseFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (config.isDataAccessLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", config.getDataBaseUser());
			properties.put("javax.persistence.jdbc.password", config.getDataBasePassword());

			emf = Persistence.createEntityManagerFactory("objectdb://" + config.getDataAccessNode() +
					":"+config.getDataAccessPort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion => event = " + event +
				" question = " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.doesQuestionExist(question);
	}

	public void depMoeny(User user,float mn){
		if (user == null)
					System.out.println("User" + user.toString() + " is not in the db");
		else {
		db.getTransaction().begin();
		User u =db.find(User.class,user);
		u.addMoney(mn);
		db.persist(u);
		db.getTransaction().commit();
		System.out.println(u.toString() + " has been updated");
	    }
    }

		public void addMoney(int idUser,float mn){
				db.getTransaction().begin();
				User u =db.find(User.class,idUser);
				u.addMoney(mn);
				db.persist(u);
				db.getTransaction().commit();
				System.out.println(u.toString() + " has been updated");
			}


		public void deleteMoeny(User user,int mn){
			if (user == null)
				System.out.println("User" + user.toString() + " is not in the db");
			else {
				db.getTransaction().begin();
				User u =db.find(User.class,user);
				u.SubMoney(mn);
				db.persist(u);
				db.getTransaction().commit();
				System.out.println(u.toString() + " has been updated");
			}
		}


    public  List<Results>  getResults(int idEvent,int idQuestion){
		System.out.println(">> DataAccess: getResults");
		TypedQuery<Results> r1 = db.createQuery("SELECT u FROM Results u where u.idevent="+idEvent+" AND u.idQuestion="+idQuestion,
				Results.class);
		List<Results> results = r1.getResultList();
		return results;

    }

     public  List<Results>  getResult(long idResult){
			System.out.println(">> DataAccess: getResults");
			TypedQuery<Results> r1 = db.createQuery("SELECT u FROM Results u where u.idR="+idResult,
					Results.class);
			List<Results> results = r1.getResultList();
			return results;
	 }

		public List<Results> getResult(int  idEvent){
			System.out.println(">> DataAccess: getResults");
			TypedQuery<Results> r1 = db.createQuery("SELECT u FROM Results u where u.idevent="+idEvent,
					Results.class);
			List<Results> results = r1.getResultList();
			return results;
		}

    public  List<Bet>  getBet(User user){
			System.out.println(">> DataAccess: getBet");
			TypedQuery<Bet> b = db.createQuery("SELECT u FROM Bet  u where u.idUser="+user.getId(),Bet.class);
			List<Bet>  bet= b.getResultList();
			return bet;
	}

		public  List<Bet>  getBetByID(long idU,long idB){
			System.out.println(">> DataAccess: getBet");
			TypedQuery<Bet> b = db.createQuery("SELECT u FROM Bet  u where u.idUser="+idU+" AND u.id="+idB,Bet.class);
			List<Bet>  bet= b.getResultList();
			return bet;
		}

		public  List<Bet>  getBetByResultID(long idR){
			System.out.println(">> DataAccess: getBet");
			TypedQuery<Bet> b = db.createQuery("SELECT u FROM Bet  u where u.idResults="+idR,Bet.class);
			List<Bet>  bet= b.getResultList();
			return bet;
		}



		public  List<Movements >  getBetMvm(User user){
			System.out.println(">> DataAccess: getBet");
			TypedQuery<Movements> q = db.createQuery("SELECT u FROM Movements  u where u.idUser="+user.getId(),Movements .class);
			List<Movements> movements= q.getResultList();
			return movements;
		}

	public void deleteBet(long idU,long idBet) {
		    db.getTransaction().begin();
			Bet b=db.find(Bet.class,getBetByID(idU,idBet).get(0).getId());
			System.out.println(b);
			db.remove(b);
		    db.getTransaction().commit();
	}








	public void close(){
		db.close();
		System.out.println("DataBase is closed");
	}
}
