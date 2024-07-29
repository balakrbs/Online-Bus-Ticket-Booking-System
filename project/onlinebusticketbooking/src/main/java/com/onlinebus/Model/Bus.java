package com.onlinebus.Model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buses")
public class Bus {
    private String busnumber;
    private String busname;
    private String arrival;
    private String destination;
    private String arrivaltime;
    private String departuretime;
    private String fare;

    
    //junit test
    public Bus(String string, String string2, String string3, String string4, String string5, String string6,
			double d) {
		// TODO Auto-generated constructor stub
	}

	public String getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
