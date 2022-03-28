package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.*;
import sait.frms.problemdomain.*;


/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase
{
	//ADDED BY BROOKS*************************************************************************************
	JComboBox<String> fromBox;
	JComboBox<String> dayBox;
	JComboBox<String> toBox;

	JTextField flightField;
	JTextField airlineField;
	JTextField dayField;
	JTextField timeField;
	JTextField costField;
	JTextField nameField;
	JTextField citizenshipField;

	Flight selectedFlight;


	//****************************************************************************************************

	//Added stuff

	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	
	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		//**************************************************************************************************************
		//ADDED BY BROOKS
		//**************************************************************************************************************

		//Creating the EastPanel and the SouthPanel
		JPanel eastPanel= createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel= null;
		southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		//**************************************************************************************************************
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
		flightsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
	}




	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {

			//**************************************************************************************************************
			//ADDED BY BROOKS
			//**************************************************************************************************************

			//checks to see if there is something selected to avoid NullPointerException
			if (flightsList.getSelectedValue() != null) {

				//grabbing the selected flight from the Jlist flightsList and also populating the East Panel with selected input.
				selectedFlight = flightsList.getSelectedValue();
				flightField.setText(selectedFlight.getCode());
				airlineField.setText(selectedFlight.getAirlineName());
				dayField.setText(selectedFlight.getWeekday());
				timeField.setText(selectedFlight.getTime());
				costField.setText("$" + (selectedFlight.getCostPerSeat()));
			}


		}

	}

	/**
	 * creation of the eastPanel dubbed panel
	 * @return eastPanel
	 */
	private JPanel createEastPanel(){
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		//creating the "Reserve" header and making it look purty
		JLabel reserveJLabel = new JLabel("Reserve");
		reserveJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		reserveJLabel.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 0, 20, 0);
		constraints.gridwidth = 2;
		constraints.gridy = 0;
		//adding our purty header
		panel.add(reserveJLabel, constraints);

		constraints = new GridBagConstraints();

		//adding the horizontal fill constraint to our constraint variable
		constraints.fill = GridBagConstraints.HORIZONTAL;

		//adding the "Flight:" label
		JLabel flightJLabel = new JLabel("Flight:");
		flightJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(flightJLabel, constraints);

		//adding the "Flight:" textfield
		flightField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(flightField, constraints);

		//adding the "Airline:" label
		JLabel airlineJLabel = new JLabel("Airline:");
		airlineJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(airlineJLabel, constraints);

		//adding the "Airline:" textfield
		airlineField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(airlineField, constraints);

		//adding the "Day:" label
		JLabel dayJLabel = new JLabel("Day:");
		dayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(dayJLabel, constraints);

		//adding the "Day:" textfield
		dayField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		panel.add(dayField, constraints);


		//adding the "Time:" label
		JLabel timeJLabel = new JLabel("Time:");
		timeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(timeJLabel, constraints);

		//adding the "Time:" textfield
		timeField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		panel.add(timeField, constraints);

		//adding the "Cost:" label
		JLabel costJLabel = new JLabel("Cost:");
		costJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(costJLabel, constraints);

		//adding the "Cost:" textField
		costField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 5;
		panel.add(costField, constraints);

		//adding the "Name:" label
		JLabel nameJLabel = new JLabel("Name:");
		nameJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(nameJLabel, constraints);

		//adding the "Name:" textfield
		nameField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 6;
		panel.add(nameField, constraints);

		//adding the "Citizenship:" label
		JLabel citizenshipJLabel = new JLabel("Citizenship:");
		timeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(citizenshipJLabel, constraints);

		//adding the "Citizenship:" textfield
		citizenshipField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 7;
		panel.add(citizenshipField, constraints);


		//creating the "reserve" Button
		JButton reserveButton = new JButton("Reserve");
		//linking the button to an action listener
		reserveButton.addActionListener(new reserveButtonListener());
		constraints=new GridBagConstraints();
		//MAKING THE GRIDWITH SPAN 2 CELLS
		constraints.gridwidth=2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//NO GRID X BECAUSE WE WANT TO MAKE THE BUTTON SPAN THE ENTIRE GRID
		constraints.gridy = 8;
		//PADDING THE TOP OF THE BUTTON
		constraints.insets = new Insets(15, 0, 0, 0);
		//adding the button to the panel with the constraints we established
		panel.add(reserveButton, constraints);

		panel.setPreferredSize(new Dimension(200, 30));

		//the panel is fully formed and will now be sent back to be added to the main panel
		return panel;
	}




	/**
	 * Creation of the southPanel dubbed panel
	 * @return panel
	 */
	private JPanel createSouthPanel(){

		//This panel will use a borderLayout
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());

		//creating the upperPanel of the southPanel
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());

		//making the "Flight Finder" header look purty
		JLabel reserveJLabel = new JLabel("Flight Finder");
		reserveJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		reserveJLabel.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 0, 5, 0);
		constraints.gridy = 0;
		//adding our purty header to the inner upperPanel
		upperPanel.add(reserveJLabel, constraints);
		//adding our purty header to the outer southPanel
		southPanel.add(upperPanel, BorderLayout.NORTH);



		//creating the middlePanel of the southPanel
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridBagLayout());

		//adding the "From:" label
		JLabel fromJLabel = new JLabel("From:");
		fromJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 1;
		middlePanel.add (fromJLabel, constraints);

		//Creating a comboBox for the from airport selection
		//loop that calls the loadAirports() method to populate the JComboBox from the airports.csv file
		ArrayList<String> portCodes = loadAirports();
		fromBox = new JComboBox();
		for (int i=0; i < portCodes.size(); i++){
		fromBox.addItem(portCodes.get(i));
		}
		constraints.gridy = 1;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//adding the fromBox to the middle panel
		middlePanel.add(fromBox, constraints);


		//adding the "To:" label
		JLabel toJLabel = new JLabel("To:");
		toJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 2;
		middlePanel.add(toJLabel, constraints);

		//Creating a comboBox for the to airport selection
		//I don't know why this works but it's an alternative to the JComboBox population above, very epico, wth is a Vector???
		toBox = new JComboBox<>(new Vector<>(loadAirports()));
		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//adding the toBox to the middle panel
		middlePanel.add(toBox, constraints);

		//adding the "Day:" label
		JLabel dayJLabel = new JLabel("Day:");
		dayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 3;
		middlePanel.add(dayJLabel, constraints);

		//Creating a comboBox for the day selection
		//ugly but functional, I slightly better option would have been filling it with a String[] array.
		dayBox = new JComboBox();
		dayBox.addItem("Any");
		dayBox.addItem("Sunday");
		dayBox.addItem("Monday");
		dayBox.addItem("Tuesday");
		dayBox.addItem("Wednesday");
		dayBox.addItem("Thursday");
		dayBox.addItem("Friday");
		dayBox.addItem("Saturday");
		constraints.gridy = 3;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = constraints.weighty = 1.0;
		//adding the dayBox to the middle panel
		middlePanel.add(dayBox, constraints);

		//adding the now completed inner middlePanel to the outer southPanel
		southPanel.add(middlePanel, BorderLayout.CENTER);




		//creating the lowerPanel of the southPanel
		JPanel lowerPanel = new JPanel();

		lowerPanel.setLayout(new GridBagLayout());
		//creating the "Find Flights" button
		JButton findButton = new JButton("Find Flights");
		//linking our button to an action listener
		findButton.addActionListener(new findButtonListener());

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(15, 0, 0, 0);
		constraints.weightx = constraints.weighty = 1.0;
		//adding the created findButton to the lowerPanel
		lowerPanel.add(findButton, constraints);
		//adding our inner lowerPanel to the outer southPanel
		southPanel.add(lowerPanel, BorderLayout.SOUTH);



		//the panel is fully formed and will now be sent back to be added to the main panel
		return southPanel;
	}


	/**
	 *
	 * @return ArrayList of airport codes for use with the JComboBox fromBox
	 */
	private ArrayList<String> loadAirports(){
		ArrayList<String> portCode = new ArrayList<>();


		//going to the necessary file and setting a delimiter
		Scanner in = null;
		try {
			in = new Scanner(new File("res/airports.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("What da heck the penguins must have stolen the file location");
		}
		in.useDelimiter(",|\r\n");

		//adding the codes the ArrayList and throwing away the full airport names.
		while (in.hasNext()){
			portCode.add(in.next());
			String throwaway = in.next();
		}
		return portCode;
	}


	/**
	 * listener for the reserveButton
	 */
	private class reserveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			//getting variables that need to be passed to the makeReservation() method
			String name = nameField.getText();
			String citizenship = citizenshipField.getText();
			Flight reserveFlight = selectedFlight;

			try {
				reservationManager.makeReservation(reserveFlight, name, citizenship);
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Reservation created. Your code is " );
		}
	}


	/**
	 * listener for the findButton
	 */
	private class findButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event){

			//empty all the text fields from the East Panel
			flightField.setText("");
			airlineField.setText("");
			dayField.setText("");
			timeField.setText("");
			costField.setText("");
			nameField.setText("");
			citizenshipField.setText("");

			//grab the parameters needed for the findFlights() method
			String fromInput = fromBox.getSelectedItem().toString();
			String toInput = toBox.getSelectedItem().toString();
			String dayInput = dayBox.getSelectedItem().toString();

			//this will clear the panel of previous searches if multiple searches are conducted.
			flightsModel.clear();

			//create an ArrayList of Flight objects from the findFlights() return
			ArrayList<Flight> foundFlights = flightManager.findFlights(fromInput, toInput, dayInput);
			//iterate through the created ArrayList and populate the flightsModel Jlist to show flights that fit the searched parameters
			for (Flight f: foundFlights){
				flightsModel.addElement(f);
			}
		}
	}
}
