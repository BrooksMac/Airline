package sait.frms.gui;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
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
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) throws FileNotFoundException {
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

		JPanel southPanel= createSouthPanel();
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

		}

	}
		//**************************************************************************************************************
		//ADDED BY BROOKS
		//**************************************************************************************************************

	private JPanel createEastPanel(){
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		JLabel reserveJLabel = new JLabel("Reserve");
		reserveJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		reserveJLabel.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 0, 20, 0);
		constraints.gridwidth = 2;
		constraints.gridy = 0;
		panel.add(reserveJLabel, constraints);

		constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;

		JLabel flightJLabel = new JLabel("Flight:");
		flightJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(flightJLabel, constraints);

		JTextField flightField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(flightField, constraints);

		JLabel airlineJLabel = new JLabel("Airline:");
		airlineJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(airlineJLabel, constraints);

		JTextField airlineField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(airlineField, constraints);

		JLabel dayJLabel = new JLabel("Day:");
		dayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(dayJLabel, constraints);

		JTextField dayField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		panel.add(dayField, constraints);

		JLabel timeJLabel = new JLabel("Time:");
		timeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(timeJLabel, constraints);

		JTextField timeField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		panel.add(timeField, constraints);

		JLabel costJLabel = new JLabel("Cost:");
		costJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(costJLabel, constraints);

		JTextField costField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 5;
		panel.add(costField, constraints);

		JLabel nameJLabel = new JLabel("Name:");
		nameJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(nameJLabel, constraints);

		JTextField nameField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 6;
		panel.add(nameField, constraints);

		JLabel citizenshipJLabel = new JLabel("Citizenship:");
		timeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(citizenshipJLabel, constraints);

		JTextField citizenshipField =  new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 7;
		panel.add(citizenshipField, constraints);



		JButton reserveButton = new JButton("Reserve");
		constraints=new GridBagConstraints();
		//MAKING THE GRIDWITH SPAN 2 CELLS
		constraints.gridwidth=2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//NO GRID X BECAUSE WE WANT TO MAKE THE BUTTON SPAN THE ENTIRE GRID
		constraints.gridy = 8;
		//PADDING THE TOP OF THE BUTTON
		constraints.insets = new Insets(15, 0, 0, 0);
		panel.add(reserveButton, constraints);

		panel.setPreferredSize(new Dimension(200, 30));


		return panel;
	}
	private JPanel createSouthPanel() throws FileNotFoundException{

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());

		JLabel reserveJLabel = new JLabel("Flight Finder");
		reserveJLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		reserveJLabel.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 0, 5, 0);
		constraints.gridy = 0;
		upperPanel.add(reserveJLabel, constraints);
		southPanel.add(upperPanel, BorderLayout.NORTH);

		JPanel middlePanel = new JPanel();

		middlePanel.setLayout(new GridBagLayout());

		JLabel fromJLabel = new JLabel("From:");
		fromJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 1;
		middlePanel.add (fromJLabel, constraints);
		
		//loop that calls the loadAirports() method to populate the JComboBox from the airports.csv file
		ArrayList<String> portCodes = loadAirports();
		JComboBox<String> fromBox = new JComboBox();
		for (int i=0; i < portCodes.size(); i++){
		fromBox.addItem(portCodes.get(i));
		}


		constraints.gridy = 1;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		middlePanel.add(fromBox, constraints);



		JLabel toJLabel = new JLabel("To:");
		toJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 2;
		middlePanel.add(toJLabel, constraints);

		//I don't know why this works but it's an alternative to the JComboBox population above, very epico
		JComboBox<String> toBox = new JComboBox<>(new Vector<>(loadAirports()));
		constraints.gridy = 2;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		middlePanel.add(toBox, constraints);

		JLabel dayJLabel = new JLabel("Day:");
		dayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 3;

		middlePanel.add(dayJLabel, constraints);

		JComboBox dayBox = new JComboBox();
		dayBox.addItem("Any");
		constraints.gridy = 3;
		constraints.gridx = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = constraints.weighty = 1.0;

		middlePanel.add(dayBox, constraints);


		southPanel.add(middlePanel, BorderLayout.CENTER);





		JPanel lowerPanel = new JPanel();

		lowerPanel.setLayout(new GridBagLayout());

		JButton findButton = new JButton("Find Flights");

		constraints.fill = GridBagConstraints.HORIZONTAL;

		constraints.insets = new Insets(15, 0, 0, 0);

		constraints.weightx = constraints.weighty = 1.0;

		lowerPanel.add(findButton, constraints);

		southPanel.add(lowerPanel, BorderLayout.SOUTH);



		return southPanel;
	}

	private ArrayList<String> loadAirports() throws FileNotFoundException {
		ArrayList<String> portCode = new ArrayList<>();

		Scanner in = new Scanner(new File("E:\\OOPProjects\\Airline\\res\\airports.csv"));
		in.useDelimiter(",|\r\n");

		while (in.hasNext()){
			portCode.add(in.next());
			String throwaway = in.next();
		}
		return portCode;
	}
}