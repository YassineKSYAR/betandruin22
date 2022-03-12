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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class CreateEvent extends JFrame {

	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;

	private final JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("EventDate"));
	
	private final JLabel eventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Events")); 

	// Code for JCalendar
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar;
	private Calendar currentCalendar;

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
	private JTextField eventNum;
	private final JTextField eventDesc = new JTextField();
	private final JLabel icon = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("");


	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public CreateEvent(BlFacade bl) {
		setBackground(Color.YELLOW);
		eventDesc.setText("");
		eventDesc.setBounds(40, 348, 225, 19);
		eventDesc.setColumns(10);
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(350, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		eventDateLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		eventDateLbl.setHorizontalAlignment(SwingConstants.CENTER);

		eventDateLbl.setBounds(new Rectangle(50, 10, 225, 25));
		eventLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		eventLbl.setHorizontalAlignment(SwingConstants.CENTER);
		eventLbl.setBounds(40, 237, 225, 16);

		this.getContentPane().add(eventDateLbl, null);
		this.getContentPane().add(eventLbl);

		calendar.setBounds(new Rectangle(40, 50, 225, 150));

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				if (propertyChangeEvent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertyChangeEvent.getNewValue());
				}
				else if (propertyChangeEvent.getPropertyName().equals("calendar")) {
					previousCalendar = (Calendar) propertyChangeEvent.getOldValue();
					currentCalendar = (Calendar) propertyChangeEvent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());
					Date firstDay = UtilDate.trim(new Date(calendar.getCalendar().getTime().getTime()));

					int previousMonth = previousCalendar.get(Calendar.MONTH);
					int currentMonth = currentCalendar.get(Calendar.MONTH);

					if (currentMonth != previousMonth) {
						if (currentMonth == previousMonth + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, 
							// devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							currentCalendar.set(Calendar.MONTH, previousMonth + 1);
							currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}						

						calendar.setCalendar(currentCalendar);
						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.
								getDate());
					}
					
				}
			} 
		});

		this.getContentPane().add(calendar, null);
		
		JButton createEventBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		createEventBtn.setBackground(Color.BLACK);
		createEventBtn.setForeground(Color.WHITE);
		createEventBtn.setBounds(230, 408, 85, 21);
		getContentPane().add(createEventBtn);
		
		JButton close = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		close.setForeground(Color.WHITE);
		close.setBackground(Color.BLACK);
		close.setBounds(10, 408, 85, 21);
		getContentPane().add(close);
		
		eventNum = new JTextField();
		eventNum.setText("");
		eventNum.setBounds(40, 263, 225, 19);
		getContentPane().add(eventNum);
		eventNum.setColumns(10);
		
		getContentPane().add(eventDesc);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(40, 313, 225, 13);
		getContentPane().add(lblNewLabel);
		icon.setBounds(0, 0, 336, 340);
		getContentPane().add(icon);
		icon.setIcon(new ImageIcon("./icone.jpeg"));
		lblNewLabel_1.setIcon(new ImageIcon("./icone.jpeg"));
		lblNewLabel_1.setBounds(0, 336, 336, 127);
		
		getContentPane().add(lblNewLabel_1);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);
	}
}