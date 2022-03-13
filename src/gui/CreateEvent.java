package gui;

import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Question;
import exceptions.EventAlreadyExist;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class CreateEvent extends JFrame {

	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;
	private Calendar previousCalendar;
	private Calendar currentCalendar;
	private JCalendar calendar = new JCalendar();
	private java.util.Date currentDate = null;
	
	private int day;
	private int month;
	private int year;

	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();

	private DefaultTableModel eventTableModel;
	

	private String[] eventColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] questionColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QuestionN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question")
	};
	private JTextField InputEvent;


	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public CreateEvent(BlFacade bl) {
		setBackground(Color.YELLOW);
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {
		
		calendar.setBounds(new Rectangle(40, 50, 225, 150));

		// jLabelMsg.setSize(new Dimension(305, 20));

		this.getContentPane().add(calendar, null);

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);
		

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertychangeevent.getNewValue());
				} 
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					currentCalendar = (Calendar) propertychangeevent.getNewValue();
					//currentDate=(Date)currentCalendar.getTime();
					System.out.println("Selected Calendar date is : "+currentCalendar.getTime());
					currentDate =(java.util.Date)(currentCalendar.getTime());
					System.out.println("Selected date is : "+ currentDate);
					
					//Calendar calInt = currentCalendar.getInstance();
					 day = currentCalendar.get(currentCalendar.DATE);
					 month = currentCalendar.get(currentCalendar.MONTH);
					 year = currentCalendar.get(currentCalendar.YEAR);
					
				}

			}
			
		});
		
		/////////////////////////////////////////////////////////////////////////////////////
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(350, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		
		JButton createEventBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		createEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButtonCreateEvent(e);
			}

			private void JButtonCreateEvent(ActionEvent e) {
				// TODO Auto-generated method stub
				//msgLbl1.setText("");
				
				// Displays an exception if the query field is empty
				String inputEvent = InputEvent.getText();

				if (inputEvent.length() > 0) {

					System.out.println("year = " + year + "month = " + month + "day = " + day);
					//businessLogic.createEvent(currentDate,description);
					businessLogic.createEvent(inputEvent, UtilDate.newDate(year, month, day));
					System.out.println(UtilDate.newDate(year, month, day)+"****");
					//msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("EventCreated"));
					
				} else {
					//msgLbl.setText("Inpu Feild is empty");
				    System.out.println("Event not created");
				}
			}
		});
		createEventBtn.setBackground(Color.BLACK);
		createEventBtn.setForeground(Color.WHITE);
		createEventBtn.setBounds(40, 408, 85, 21);
		getContentPane().add(createEventBtn);
		
		JButton close = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		close.setForeground(Color.WHITE);
		close.setBackground(Color.BLACK);
		close.setBounds(241, 408, 85, 21);
		getContentPane().add(close);
		
		JLabel EventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.EventLbl.text")); //$NON-NLS-1$ //$NON-NLS-2$
		EventLbl.setBounds(40, 246, 71, 14);
		getContentPane().add(EventLbl);
		
		InputEvent = new JTextField();
		InputEvent.setText("");
		InputEvent.setBounds(40, 271, 225, 20);
		getContentPane().add(InputEvent);
		InputEvent.setColumns(10);
		
		JLabel msgLbl1 = new JLabel("");
		msgLbl1.setForeground(Color.RED);
		msgLbl1.setBounds(144, 246, 121, 14);
		getContentPane().add(msgLbl1);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);
	}
	
}