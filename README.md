# betandruin22
Groupe 1 Hantofix
Hejbane Mohamed
Kerbal Abdellah
Wiame Abdouss
Ksyar Yassine

Firts Iteration:

In order to Complete the requirements of the first itteration we had to creat new classes:
1)-In package "dataAcess" we add RegistreDb class to store new users credentials and store them in database
2)-In package "domain" creation of class Users to define the user whether is an admin or a normal user
3)-In package "exceptions" we add event already exist exception
4)-In package "gui" we created Login,Registre,Createevent and setfee features

If the User is an admin, there are three ways:
Browse events and related questions to event,or create a new event or create question for a certain event with the option to set a fee


Second Iteration:

1)-Creation of new classes:
     *Bet:It contains informations needed to identify a bet: user,amount and fee
     *MyBet: This class is used so we can retrieve bets and delete them.
     *Result: It contains details about any result related to a question.
     
2)-Creation of new controllers:
     *"CreateEventController": 
       -create events and add them to the databsae: 'businessLogic.createEvent(eventDate , inputEvent);'
     *"RemoveEventController":
       -It retreive all the events related to the chosen date from database and delete any available event you want
        'businessLogic.removeEventQuestions(ev);'
        'businessLogic.removeEvent(ev);'
     *"DepMoneyController":
       -So the user could make bets, we need to deposet in the account andd add it to user balance.
     *"MakeBetController":
      -The users choose the wanted event and the fee they want to bet on,then store thier bets on database
     *"SetFeeController":
      -add fee to Questions so users can bet and store the fee in database
     *"RemoveBetController"
     *"RegistreController"
     *"HomeController"
     *"LoginController"
     *"MainGUIController"
     *"MainUserController"


