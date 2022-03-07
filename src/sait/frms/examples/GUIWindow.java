package sait.frms.examples;

import javax.swing.*;
import java.awt.*;

public class GUIWindow extends JFrame {

    private JPanel northJPanel;
    private JPanel southJPanel;
    private JPanel eastJPanel;
    private JPanel westJPanel;

    private GUIWindow() {
        setTitle("Awesome");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildFrame();
        pack();
        setVisible(true);
    }
    //private void buildFrame(){
    private void buildFrame() {
        setLayout(new BorderLayout());

        northJPanel = new JPanel();
        southJPanel = new JPanel();
        eastJPanel = new JPanel();
        westJPanel = new JPanel();

        buildNorthPanel();
        buildSouthPanel();
        buildEastPanel();
        buildWestPanel();

        add(northJPanel, BorderLayout.NORTH);
        add(southJPanel, BorderLayout.SOUTH);
        add(eastJPanel, BorderLayout.EAST);
        add(westJPanel, BorderLayout.WEST);

    }

    //private void build west panel
    private void buildWestPanel() {
        westJPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameJLabel = new JLabel("Name");
        nameJLabel.setHorizontalAlignment(JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 0;
        westJPanel.add(nameJLabel, constraints);

        JTextField nameField =  new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        westJPanel.add(nameField, constraints);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setHorizontalAlignment(JLabel.RIGHT);
        // constraints.weightx = 0.5;
        // constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        westJPanel.add(addressLabel, constraints);
        JTextField addressField = new JTextField(10);
        // constraints.weightx = 0.5;
        // constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        westJPanel.add(addressField, constraints);
        JLabel phoneLabel = new JLabel("Phone#");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 2;
        westJPanel.add(phoneLabel, constraints);
        JTextField phoneField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        westJPanel.add(phoneField, constraints);


        JLabel yearsLabel = new JLabel("Year of birth");
        yearsLabel.setHorizontalAlignment(JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 3;
        westJPanel.add(yearsLabel, constraints);


        JComboBox yearsBox = new JComboBox();
        for (int i = 1950; i <= 2021; i++) {
            yearsBox.addItem(i);
        }
        constraints.gridx = 1;
        constraints.gridy = 3;
        westJPanel.add(yearsBox, constraints);



        JLabel monthsLabel = new JLabel("Month of birth");
        monthsLabel.setHorizontalAlignment(JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 4;
        westJPanel.add(monthsLabel, constraints);



        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        JComboBox monthsBox = new JComboBox(months);
        constraints.gridx = 1;
        constraints.gridy = 4;
        westJPanel.add(monthsBox, constraints);




        JLabel daysLabel = new JLabel("Day of birth");
        daysLabel.setHorizontalAlignment(JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 5;
        westJPanel.add(daysLabel, constraints);
        JComboBox daysBox = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            daysBox.addItem(i);
        }
        constraints.gridx = 1;
        constraints.gridy = 5;
        westJPanel.add(daysBox, constraints);

        westJPanel.setPreferredSize(new Dimension(200, 30));
    }


    private void buildEastPanel() {
        JTextArea comments = new JTextArea(10, 15);
        eastJPanel.add(comments);

        eastJPanel.setPreferredSize(new Dimension(180, 200));

    }


    private void buildSouthPanel() {
        southJPanel.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setLayout(new FlowLayout());
        JLabel semesterJLabel = new JLabel("Semester: ");
        JRadioButton winterButton = new JRadioButton("Winter");
        JRadioButton springButton = new JRadioButton("Spring");
        JRadioButton fallButton = new JRadioButton("Fall");
        ButtonGroup group = new ButtonGroup();
        group.add(winterButton);;
        group.add(springButton);
        group.add(fallButton);
        upperPanel.add(semesterJLabel);
        upperPanel.add(winterButton);
        upperPanel.add(springButton);
        upperPanel.add(fallButton);

        middlePanel.setLayout(new FlowLayout());
        //middlePanel.setPreferredSize(new Dimension(350, 30));
        JLabel coursesLabel = new JLabel("Courses: ");
        JCheckBox cprg251CheckBox = new JCheckBox("CPRG251");
        JCheckBox cprg250CheckBox = new JCheckBox("CPRG250");
        JCheckBox cmps303CheckBox = new JCheckBox("CMPS303");
        middlePanel.add(coursesLabel);
        middlePanel.add(cprg251CheckBox);
        middlePanel.add(cprg250CheckBox);
        middlePanel.add(cmps303CheckBox);


        JButton submitButton = new JButton("Submit");
        lowerPanel.add(submitButton);


        southJPanel.add(upperPanel, BorderLayout.NORTH);
        southJPanel.add(middlePanel, BorderLayout.CENTER);
        southJPanel.add(lowerPanel, BorderLayout.SOUTH);
    }


    private void buildNorthPanel() {
        JLabel registerJLabel = new JLabel("Register");
        northJPanel.add(registerJLabel);
    }



    public static void main(String[] args) {
        new GUIWindow();
    }
}