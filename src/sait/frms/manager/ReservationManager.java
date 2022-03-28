package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;
import sait.frms.gui.*;

public class ReservationManager extends Reservation {
    private static final String BINARY_FILE = "res/reserves.bin";
    private static final String MODE = "rw";
    private static final int RESERVE_SIZE = 331; // 7 + 9 + 102 + 102 + 102 + 8 + 1

    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public ReservationManager() throws IOException {
        populateFromBinary();
    }

    private String generateReservationCode(Flight flight) {
        int min = 1000;
        int max = 9999;
        String reservationCode;

        boolean isDomestic = flight.isDomestic();
        if (isDomestic) {
            reservationCode = "D";
        } else {
            reservationCode = "I";
        }

        int randomNumber = (int) (min + Math.random() * ((max - min) + 1));

        reservationCode = reservationCode + Integer.toString(randomNumber);
        return reservationCode;
    }

    private int getAvailableSeats(Flight flight) {
        return flight.getSeats();
    }

    public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(BINARY_FILE, MODE);
        Reservation added = new Reservation(generateReservationCode(flight), flight.getCode(), flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), true);
        FlightManager updateSeats = new FlightManager();
        int Available = updateSeats.findFlightByCode(flight.getCode()).getSeats();
        //close raf is seats are 0
        if (Available == 0) {
            raf.close();
            System.out.println("Can't reserve Seats are full");
            return null;
        } else {
            updateSeats.findFlightByCode(flight.getCode()).setSeats(updateSeats.findFlightByCode(flight.getCode()).getSeats() - 1);
            new FlightManager();
            int Remaining = updateSeats.findFlightByCode(flight.getCode()).getSeats();
            System.out.println("You have reserved. Seats Remaining: " + Remaining);

        }
        raf.seek(raf.length());
        //5 + 2
        String code = String.format("%-5s", added.getCode());
        raf.writeUTF(code);

        //7 + 2
        String flightCode = String.format("%-7s", added.getFlightCode());
        raf.writeUTF(flightCode);

        //100 + 2
        String airline = String.format("%-100s", added.getAirline());
        raf.writeUTF(airline);

        //100 + 2
        String addName = String.format("%-100s", added.getName());
        raf.writeUTF(addName);

        //100 + 2
        String addCitizenship = String.format("%-100s", added.getCitizenship());
        raf.writeUTF(addCitizenship);

        //8
        raf.writeDouble(added.getCost());

        //1
        raf.writeBoolean(added.isActive());
        raf.close();

        //Print what is added
        System.out.println(added.toString());
        reservations.add(added);
        return added;
    }

    public void persist() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(BINARY_FILE, MODE);

        //call static variables
        String code = ReservationsTab.persistCode;
        String flight = ReservationsTab.persistFlightCode;
        String airline = ReservationsTab.persistAirline;
        String name = ReservationsTab.persistName;
        String citizenship = ReservationsTab.persistCitizenship;
        double cost = ReservationsTab.persistCost;
        boolean isActive = ReservationsTab.persistActive;
        System.out.println(code + "," + flight + "," + airline + "," + name + "," + citizenship + "," + cost + "," + isActive);

        FlightManager updateSeats = new FlightManager();

        //Update the objects
        int j = 0;
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getCode().equals(code)) {
                if (reservations.get(i).getFlightCode().equals(flight)) {
                    if (reservations.get(i).getAirline().equals(airline)) {
                        reservations.get(i).setName(name);
                        reservations.get(i).setCitizenship(citizenship);
                        j = i;
                        if (isActive == reservations.get(i).isActive()) {
                            break; //break out of loop
                        } else if (isActive && !reservations.get(i).isActive()) {
                            updateSeats.findFlightByCode(flight).setSeats(updateSeats.findFlightByCode(flight).getSeats() - 1);
                            reservations.get(i).setActive(true);
                            break; //break out of loop
                        } else {
                            updateSeats.findFlightByCode(flight).setSeats(updateSeats.findFlightByCode(flight).getSeats() + 1);
                            reservations.get(i).setActive(false);

                            break; //break out of loop
                        }
                    }
                }
            }
        }

        System.out.println(j);
        //save objects to binary file
        for (int i = 0, pos = 0; i < reservations.size(); i++, pos += RESERVE_SIZE) {

            String updateCode = String.format("%-5s", reservations.get(i).getCode());
            String updateFlight = String.format("%-7s", reservations.get(i).getFlightCode());
            String updateAirline = String.format("%-100s", reservations.get(i).getAirline());
            String updateName = String.format("%-100s", reservations.get(i).getName());
            String updateCitizenship = String.format("%-100s", reservations.get(i).getCitizenship());
            double updateCost = reservations.get(i).getCost();
            boolean updateisActive = reservations.get(i).isActive();


            raf.seek(pos);
            raf.writeUTF(updateCode);
            raf.writeUTF(updateFlight);
            raf.writeUTF(updateAirline);
            raf.writeUTF(updateName);
            raf.writeUTF(updateCitizenship);
            raf.writeDouble(updateCost);
            raf.writeBoolean(updateisActive);
        }
        raf.close();
    }

    /**
     * Store reservationsArray with the random access file values.
     *
     * @return Reservation instance
     * @throws IOException
     */
    private void populateFromBinary() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(BINARY_FILE, MODE);
        for (long position = 0; position < raf.length(); position += RESERVE_SIZE) {
            raf.seek(position);
            String code = raf.readUTF().trim();
            String flightCode = raf.readUTF().trim();
            String airline = raf.readUTF().trim();
            String name = raf.readUTF().trim();
            String citizenship = raf.readUTF().trim();
            double cost = raf.readDouble();
            boolean isActive = raf.readBoolean();

            Reservation r = new Reservation(code, flightCode, airline, name, citizenship, cost, isActive);
            reservations.add(r);
        }
        raf.close();
    }

    /**
     * Find the reservation by code, airline, name as Reservation array list.
     *
     * @param code
     * @param airline
     * @param name
     * @return null or reservation at index object
     */
    public ArrayList<Reservation> findReservations(String code, String airline, String name) {
        int i = 0;
        ArrayList<Reservation> r = new ArrayList<Reservation>();

        for (i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getCode().equals(code)) {
                if (reservations.get(i).getAirline().equals(airline)) {
                    if (reservations.get(i).getName().equals(name)) {
                        r.add(reservations.get(i));
                    }
                }
            }
        }
        return r;
    }

    /**
     * Find the reservation by code as Reservation object
     *
     * @param code
     * @return null or reservation at index object
     */
    public Reservation findReservationsByCode(String code) {

        int i = 0;
        for (i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getCode().equals(code)) {
                return reservations.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param args
     * @throws IOException
     */
}