package com.hotelreservation;

import java.time.LocalDate;

public class Reservation {

	private String reservationId;
	private String guestName;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Room room;
	
	public Reservation(String reservationId, String guestName, LocalDate checkInDate, LocalDate checkOutDate, Room room) {
		this.reservationId = reservationId;
		this.guestName = guestName;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		room.setAvailable(false);
	}

	public String getReservationId() {
		return reservationId;
	}

	public String getGuestName() {
		return guestName;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public Room getRoom() {
		return room;
	}
	
	
}
