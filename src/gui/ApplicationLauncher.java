package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML config = ConfigXML.getInstance();

		Locale.setDefault(new Locale(config.getLocale()));
		System.out.println("Locale: " + Locale.getDefault());
		Home log=new Home();
		log.show();
		
	}
}