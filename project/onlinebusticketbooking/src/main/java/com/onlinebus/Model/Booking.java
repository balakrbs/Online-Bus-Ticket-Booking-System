package com.onlinebus.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {
    

    private String busnumber;
    private String busname;
    private String arrival;
    private String destination;
    private String arrivaltime;
    private String departuretime;
    private String fare;
    private String passengername;
    private int numberofpassengers;
    private String address;
    private String email;
    private String phonenumber;
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
	public String getPassengername() {
		return passengername;
	}
	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}
	public int getNumberofpassengers() {
		return numberofpassengers;
	}
	public void setNumberofpassengers(int numberofpassengers) {
		this.numberofpassengers = numberofpassengers;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Booking(String busnumber, String busname, String arrival, String destination, String arrivaltime,
			String departuretime, String fare, String passengername, int numberofpassengers, String address,
			String email, String phonenumber) {
		super();
		this.busnumber = busnumber;
		this.busname = busname;
		this.arrival = arrival;
		this.destination = destination;
		this.arrivaltime = arrivaltime;
		this.departuretime = departuretime;
		this.fare = fare;
		this.passengername = passengername;
		this.numberofpassengers = numberofpassengers;
		this.address = address;
		this.email = email;
		this.phonenumber = phonenumber;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(String busNumber2, String passengerName2, int numberOfPassengers2, String address2, String email2,
			String phoneNumber2) {
		// TODO Auto-generated constructor stub
	}

    
    
    
}
