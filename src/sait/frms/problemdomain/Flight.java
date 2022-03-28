package sait.frms.problemdomain;

public class Flight {
    private String code;
    private String airlineName;
    private String to;
    private String from;
    private String weekday;
    private String time;
    private int seats;
    private double costPerSeat;

    /**
     *basic constructor
     */
    public Flight() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @param code
     * @param airlineName
     * @param to
     * @param from
     * @param weekday
     * @param time
     * @param seats
     * @param costPerSeat
     */
    public Flight(String code, String airlineName, String to, String from, String weekday, String time, int seats, double costPerSeat) {
        super();
        this.code = code;
        this.airlineName = airlineName;
        this.to = to;
        this.from = from;
        this.weekday = weekday;
        this.time = time;
        this.seats = seats;
        this.costPerSeat = costPerSeat;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;


    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getWeekday() {
        return weekday;
    }
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public double getCostPerSeat() {
        return costPerSeat;
    }
    public void setCostPerSeat(double costPerSeat) {
        this.costPerSeat = costPerSeat;
    }

    public boolean isDomestic() {
        return true;

 /*	if ((getFrom() == "YEC")||(getFrom() == "YEG")||(getFrom() == "YUL")etc...)&&((getTo()=="YEC")||(getTo()=="YEG")||(getTo()=="YUL")etc...){
		return true;
		}else
		return false;*/
    }

    private void parseCode(String code){

    }
    @Override
    public String toString() {
        return code+", From: "+getFrom()+", To: "+getTo()+", Day: "+getWeekday()+", Cost: $"+getCostPerSeat();
    }
}
