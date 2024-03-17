package com.hotelreservation;

public class Room {

	private int roomNumber;
	private String roomCategory;
	private double roomPrice;
	private boolean isAvailable = true;
	
	public Room(int roomNumber, String roomCategory, double roomPrice) {
		this.roomNumber = roomNumber;
		this.roomCategory = roomCategory;
		this.roomPrice = roomPrice;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomCategory() {
		return roomCategory;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomCategory=" + roomCategory + ", isAvailable=" + isAvailable
				+ "]";
	}
	
	
}
