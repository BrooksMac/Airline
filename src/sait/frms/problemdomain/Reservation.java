package sait.frms.problemdomain;

public class Reservation {
    private String code;
    private String flightCode;
    private String airline;
    private String name;
    private String citizenship;
    private double cost;
    private boolean active;

    /**
     *
     */
    public Reservation() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param code
     * @param flightCode
     * @param name
     * @param citizenship
     * @param cost
     * @param active
     */
    public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active) {
        super();
        this.code = code;
        this.flightCode = flightCode;
        this.name = name;
        this.airline = airline;
        this.citizenship = citizenship;
        this.cost = cost;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
                + ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + ", getCode()=" + getCode()
                + ", getFlightCode()=" + getFlightCode() + ", getAirline()=" + getAirline() + ", getName()=" + getName()
                + ", getCitizenship()=" + getCitizenship() + ", getCost()=" + getCost() + ", isActive()=" + isActive()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
