package com.hotelreservation;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {
	
	private static Scanner userInput = new Scanner(System.in);
	private static List<Room> rooms = new ArrayList<>();
	private static List<Reservation> reservations = new ArrayList<>();
	private static int reservationId = 0;

	public static void main(String[] args) throws ParseException {
		initializeRooms();
		boolean flag = true;
		displayOperationDetails();
		while(flag) {
			int operations = userInput.nextInt();
			switch(operations) {
			case 0:
				displayOperationDetails();
				break;
			case 1:
				searchAvailableRooms();
				break;
			case 2:
				makeReservation();
				break;
			case 3:
				viewBookingDetails();
				break;
			case 4:
				System.out.println("Thank you for using Hotel Reservation System. Have a nice day!");
				flag = false;
				break;
			default:
				System.out.println("Invoice choice of operations, please enter valid operations code.");
			}
		}
		userInput.close();
	}

	private static void displayOperationDetails() {
		System.out.println("0. Display Operations Details");
		System.out.println("1. Search Available Rooms");
		System.out.println("2. Room Reservation");
		System.out.println("3. View Room Details");
	}
	
	private static void initializeRooms() {
		rooms.add(new Room(2011, "Delux Room", 2000));
		rooms.add(new Room(2022, "Standard Room", 1000));
		rooms.add(new Room(2033, "Standard Room", 1000));
		rooms.add(new Room(2044, "Delux Room", 2000));
		rooms.add(new Room(2055, "Delux Room", 2000));
	}
	
	private static void searchAvailableRooms() {
		System.out.println("Available Rooms are:-");
		System.out.println("*************************");
		for(Room room: rooms) {
			if(room.isAvailable()) {
				System.out.println("Room Number: " + room.getRoomNumber());
				System.out.println("Room Category: " + room.getRoomCategory());
				System.out.println(" ");
			}
		}
		System.out.println("*************************");
	}
	
	private static void makeReservation() throws ParseException {
		System.out.println("Enter your name: ");
		String name = userInput.next();
		
		System.out.println("Enter Check-In Date (YYYY-MM-DD): ");
		LocalDate checkInDate = parseDate(userInput.next());
		
		System.out.println("Enter Check-Out Date (YYYY-MM-DD): ");
		LocalDate checkOutDate = parseDate(userInput.next());
		
		System.out.println("Enter Room Number for which you want to make reservation: ");
		int roomNumber = userInput.nextInt();
		
		Room selectedRoom = findRoomById(roomNumber);
		if(selectedRoom == null || !selectedRoom.isAvailable()) {
			System.out.println("Invalid Room Number or Room is not available!");
			return;
		}
		
		Reservation reservation = new Reservation(name + "000" + ++reservationId, name, checkInDate, checkOutDate, selectedRoom);
		reservations.add(reservation);
		System.out.println("Reservation successful. Your Reservation ID is: " + reservation.getReservationId() + 
				" and your payment amount " + selectedRoom.getRoomPrice() + " is due");
		System.out.println("Do you want to proceed with the payment then press OK");
		String payment = userInput.next();
		if("OK".equalsIgnoreCase(payment)) {
			makePayment();
		}else {
			System.out.println("Payment failed!");
		}
	}
	
	private static void makePayment() {
		System.out.println("Your payment is successful!");
	}
	
	private static Room findRoomById(int roomNumber) {
		for(Room room : rooms) {
			if(roomNumber == room.getRoomNumber()) {
				return room;
			}
		}
		return null;
	}
	
	private static void viewBookingDetails() {
		System.out.println("Enter Reservation ID: ");
		String reservationId = userInput.next();
		
		Reservation reservationDetails = findByReservationId(reservationId);
		if(reservationDetails == null) {
			System.out.println("Reservation Not Found, Please Provide Valid Reservation Id by pressing 3 for view reservation details.");
			return;
		}
		
		System.out.println("********Reservation Details*************");
		System.out.println("Reservation ID: " + reservationDetails.getReservationId());
		System.out.println("Guest Name: " + reservationDetails.getGuestName());
		System.out.println("Check-In Date: " + reservationDetails.getCheckInDate());
		System.out.println("Check-Out Date: " + reservationDetails.getCheckOutDate());
		System.out.println("Room Number: " + reservationDetails.getRoom().getRoomNumber());
		System.out.println("Room Category: " + reservationDetails.getRoom().getRoomCategory());
		System.out.println("********Reservation Details*************");
	}
	
	private static Reservation findByReservationId(String reservationId) {
		for(Reservation reservation : reservations) {
			if(reservationId.equals(reservation.getReservationId())) {
				return reservation;
			}
		}
		return null;
	}
	
	private static LocalDate parseDate(String date) {
		LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		return parsedDate;
	}
}
