package sait.frms.application;

import sait.frms.gui.MainWindow;
import sait.frms.gui.*;
import sait.frms.manager.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {


		new Manager();

		MainWindow mainWindow = new MainWindow();

		mainWindow.display();

	}

}
