package sait.frms.manager;

import sait.frms.problemdomain.Flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * declaring variables to be used later.
 */
public class FlightManager extends Flight {
    public final String WEEKDAY_ANY = "Any";
    public final String WEEKDAY_SUNDAY = "Sunday";
    public final String WEEKDAY_MONDAY = "Monday";
    public final String WEEKDAY_TUESDAY = "Tuesday";
    public final String WEEKDAY_WEDNESDAY = "Wednesday";
    public final String WEEKDAY_THURSDAY = "Thursday";
    public final String WEEKDAY_FRIDAY = "Friday";
    public final String WEEKDAY_SATURDAY = "Saturday";
    private static ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<String> airports = new ArrayList<>();

    /**
     * run the populateFlights() method
     */
    public FlightManager() {
        populateFlights();
    }


    /**
     * method for returning an ArrayList of Flight objects
     * @return flights
     */
    public ArrayList<Flight> getFlights() {
        return flights;
    }


    /**
     * method for returning an ArrayList of Flight objects
     * @return airports
     */
    public ArrayList<String> getAirports() {
        return airports;
    }


    /**
     *method for finding an airport given an airport code
     * @param code
     * @return foundAirport
     */
    public String findAirportByCode(String code) {
        String foundAirport="";
        for (String airport : airports) {
            if (airport.contains(code)) {
                foundAirport = airport.substring(4);
            }
        }
        return foundAirport;
    }


    /**
     *method for finding flight by code
     * @param code
     * @return flightCode
     */
    public Flight findFlightByCode(String code) {
        int index = 0;
        for (Flight flight: flights){
            if (code.equals(flight.getCode())){
                index = flights.indexOf(flight);
            }
        }
        return flights.get(index);
    }


    /**
     *method to find flights which match searched parameters
     * @param from
     * @param to
     * @param weekday
     * @return
     */
    public static ArrayList<Flight> findFlights(String from, String to, String weekday) {
        ArrayList<Flight> foundFlights=new ArrayList<>();
        for (Flight flight: flights){
            if ((weekday.equals("Any") || weekday.equals(flight.getWeekday()))  &&  from.equals(flight.getFrom())  &&  to.equals(flight.getTo())){
                foundFlights.add(flight);
            }
        }

        return foundFlights;
    }


    /**
     * method for creating an ArrayList of Flight objects from a .csv file
     */
    private static void populateFlights() {
        try {
            Flight flight;
            File file = new File("res/flights.csv");
            Scanner fileScanner = new Scanner(file);


            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] fields = line.split(",");
                flight = new Flight (fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Integer.parseInt(fields[6]), Double.parseDouble(fields[7]));
                flights.add(flight);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The necessary file to populate flights could not be found");
        }
    }


    /**
     *method for populating airports from a .csv file
     */
    private void populateAirports() {
        try {
            File file = new File("res/airports.csv");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String airport = fileScanner.nextLine();
                airports.add(airport);
            }
            fileScanner.close();
        }catch (FileNotFoundException e){
            System.out.println("The necessary file to populate airports could not be found");
        }
    }
}
