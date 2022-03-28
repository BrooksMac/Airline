package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 *
 */
public class ReservationsTab extends TabBase {
	// So persist from ReservationManager can access these variables;
	public static String persistCode;
	public static String persistFlightCode;
	public static String persistAirline;
	public static String persistName;
	public static String persistCitizenship;
	public static double persistCost;
	public static boolean persistActive;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	private JList<Reservation> reservationsList;

	// added
	private DefaultListModel<Reservation> reservationModel;
	// by jon

	private Reservation selectedReserve;
	// South Fields
	private JTextField codeField;
	private JTextField airlineField;
	private JTextField nameField;

	// East Fields
	private JTextField eCodeField;
	private JTextField eFlightField;
	private JTextField eAirlineField;
	private JTextField eCostField;
	private JTextField eNameField;
	private JTextField eCitizenshipField;
	private JComboBox<String> eStatusBox;

	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Creates the north panel.
	 *
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel westPanel = new JPanel();
		// JPanel eastPanel = new JPanel();

		westPanel.setLayout(new BorderLayout());

		reservationModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationModel);

		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);

		reservationsList.addListSelectionListener(new MyListSelectionListener());

		westPanel.setPreferredSize(new Dimension(200, 30));
		westPanel.add(scrollPane);

		return westPanel;

	}

	// private class MyListSelectionListener implements ListSelectionListener
	class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			eCodeField.setEditable(false);
			eFlightField.setEditable(false);
			eAirlineField.setEditable(false);
			eCostField.setEditable(false);

			selectedReserve = reservationsList.getSelectedValue();
			if (selectedReserve != null) {
				eCodeField.setText(selectedReserve.getCode());
				eFlightField.setText(selectedReserve.getFlightCode());
				eAirlineField.setText(selectedReserve.getAirline());
				eCostField.setText(String.valueOf(selectedReserve.getCost()));
			}
		}

	}

	private JPanel createEastPanel() {
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

		JLabel eCodeLabel = new JLabel("Code:");
		eCodeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(eCodeLabel, constraints);

		eCodeField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(eCodeField, constraints);

		JLabel eFlightLabel = new JLabel("Flight:");
		eFlightLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(eFlightLabel, constraints);

		eFlightField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(eFlightField, constraints);

		JLabel eAirlineLabel = new JLabel("Airline:");
		eAirlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(eAirlineLabel, constraints);

		eAirlineField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		panel.add(eAirlineField, constraints);

		JLabel eCostLabel = new JLabel("Cost:");
		eCostLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(eCostLabel, constraints);

		eCostField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 4;
		panel.add(eCostField, constraints);

		JLabel eNameLabel = new JLabel("Name:");
		eNameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(eNameLabel, constraints);

		eNameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 5;
		panel.add(eNameField, constraints);

		JLabel eCitizenshipLabel = new JLabel("Citizenship:");
		eCitizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(eCitizenshipLabel, constraints);

		eCitizenshipField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 6;
		panel.add(eCitizenshipField, constraints);

		JLabel statusLabel = new JLabel("Status");
		panel.add(statusLabel, constraints);
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(statusLabel, constraints);

		eStatusBox = new JComboBox<String>();
		eStatusBox.addItem("Active");
		eStatusBox.addItem("Inactive");
		constraints.gridx = 1;
		constraints.gridy = 7;
		panel.add(eStatusBox, constraints);

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new updateReserveListener());
		constraints = new GridBagConstraints();
		// MAKING THE GRIDWITH SPAN 2 CELLS
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		// NO GRID X BECAUSE WE WANT TO MAKE THE BUTTON SPAN THE ENTIRE GRID
		constraints.gridy = 8;
		// PADDING THE TOP OF THE BUTTON
		constraints.insets = new Insets(15, 0, 0, 0);
		panel.add(updateButton, constraints);

		panel.setPreferredSize(new Dimension(200, 30));

		return panel;

	}

	// added
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		JLabel search = new JLabel("Search", SwingConstants.CENTER);
		search.setFont(new Font("serif", Font.PLAIN, 29));
		upperPanel.add(search);

		middlePanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		JLabel codeJLabel = new JLabel("Code: ");
		codeJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.weightx = 0.01;
		constraints.gridx = 0;
		constraints.gridy = 0;
		middlePanel.add(codeJLabel, constraints);

		codeField = new JTextField(10);
		constraints.weightx = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		middlePanel.add(codeField, constraints);

		JLabel airlineJLabel = new JLabel("Airline: ");
		airlineJLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.01;
		middlePanel.add(airlineJLabel, constraints);

		airlineField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 1;
		middlePanel.add(airlineField, constraints);

		JLabel nameLabel = new JLabel(" Name: ");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.01;
		middlePanel.add(nameLabel, constraints);

		nameField = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 1;
		middlePanel.add(nameField, constraints);

		lowerPanel.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		JButton searchReservation = new JButton("Find Reservations");
		searchReservation.addActionListener(new searchButtonListener());

		constraints.weightx = 2;
		lowerPanel.add(searchReservation, constraints);

		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(middlePanel, BorderLayout.CENTER);
		panel.add(lowerPanel, BorderLayout.SOUTH);
		return panel;
	}

	class updateReserveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (reservationsList.getSelectedValue() != null) {
				eCodeField.setText(selectedReserve.getCode());
				eFlightField.setText(selectedReserve.getFlightCode());
				eAirlineField.setText(selectedReserve.getAirline());
				eCostField.setText(String.valueOf(selectedReserve.getCost()));
				// Store the values from the east text fields to our static variables
				persistCode = eCodeField.getText();
				persistFlightCode = eFlightField.getText();
				persistAirline = eAirlineField.getText();
				persistName = eNameField.getText();
				persistCitizenship = eCitizenshipField.getText();
				persistCost = Double.parseDouble(eCostField.getText());
				boolean TorF = false;
				if (eStatusBox.getSelectedItem().toString() == "Active") {
					TorF = true;
				} else if (eStatusBox.getSelectedItem().toString() == "Inactive") {
					TorF = false;
				}
				persistActive = TorF;

				try {
					ReservationManager findReserve = new ReservationManager();
					findReserve.persist();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			reservationModel.clear();
		}

	}

	class searchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = codeField.getText();
			String airline = airlineField.getText();
			String name = nameField.getText();
			try {

				ReservationManager findReserve = new ReservationManager();

				reservationModel.clear();

				if (code.isEmpty() == false && airline.isEmpty() && name.isEmpty()) {
					// This method according to the UML only returns a Reservation Object so it can
					// only return one
					Reservation findByCode = findReserve.findReservationsByCode(code);
					reservationModel.addElement(findByCode);
				} else if (code.isEmpty() == false && airline.isEmpty() == false && name.isEmpty() == false) {
					// This is an arrayList however so we can get more than one Reservation Objects.
					ArrayList<Reservation> str = findReserve.findReservations(code, airline, name);
					for (Reservation r : str) {
						reservationModel.addElement(r);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}