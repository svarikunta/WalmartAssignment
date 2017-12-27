package com.walmart.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.walmart.constants.Constants;
import com.walmart.model.Customer;
import com.walmart.model.FindAndHoldRequest;
import com.walmart.model.FindAndHoldResponse;
import com.walmart.model.FindAvailableResponse;
import com.walmart.model.ReserveAndCommitRequest;
import com.walmart.model.ReserveAndCommitResponse;
import com.walmart.model.Seat;
import com.walmart.service.TicketingService;
import com.walmart.service.TicketingServiceImpl;

public class TicketingServiceUtility {

	public static void main(String Args[]) {

		TicketingService ts = new TicketingServiceImpl();

		Scanner input = new Scanner(System.in);

		int selection;

		do {

			System.out.println();
			System.out.println("1 - Display Seat Map");
			System.out.println("2 - Find Available, Hold and Reserved seats");
			System.out.println("3 - Find and Hold Seats with customer preferred row and reserve held seats");
			System.out.println(
					"4 - Hold seats with customer preferred row and column and reserve held seats");
			System.out.println(
					"5 -Find and Hold Seats with system preferred best available and reserve held seats");
			System.out.println("6 - Exit");

			selection = input.nextInt();

			FindAndHoldRequest findAndHoldRequest = new FindAndHoldRequest();
			FindAndHoldResponse findAndHoldResponse = null;
			ReserveAndCommitRequest reserveAndCommitRequest = new ReserveAndCommitRequest();
			ReserveAndCommitResponse reserveAndCommitResponse = null;

			switch (selection) {
			case 1:

				ts.displaySeatMap();
				break;

			case 2:

				FindAvailableResponse findAvailableResponse = ts.findNoOfSeatsAvailable();
				System.out.println("No of Available seats: " + findAvailableResponse.getTotalAvailableSeats());
				System.out.println("No of Held seats: " + findAvailableResponse.getTotalHeldSeats());
				System.out.println("No of Reserved seats: " + findAvailableResponse.getTotalReservedSeats());
				break;

			case 3:
				
				System.out.println("Enter customer name");
				String name = input.next();
				System.out.println("Enter customer Ph number");
				String phNum = input.next();
				
				while(!phNum.matches("[0-9]{10}")){
					System.out.println("Wrong input. Enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust = new Customer(name, phNum);

				findAndHoldRequest.setCustomer(cust);
				System.out.println("Enter preferred Row only Single Character between A to " + Constants.ENDING_ROW);
				Character row = input.next().toUpperCase().charAt(0);
				
				while(!validateRow(row)){
					
					System.out.println("Wrong input. Please enter preferred Row only Single Character between A  to " + Constants.ENDING_ROW);
					row = input.next().toUpperCase().charAt(0);
				}
				
				findAndHoldRequest.setCustPreferredRow(row);
				System.out.println("Enter required number of seats");
				int reqNumbOfSeats = input.nextInt();
				findAndHoldRequest.setReqNoOfSeats(reqNumbOfSeats);
				findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);
				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.NeedConfirm)) {
					
					for(Seat st:findAndHoldResponse.getBestAvailableSeats()){
						System.out.println("Row :"+st.getRowNo()+"Column :"+st.getColNo());
					}

					System.out.println(findAndHoldResponse.getMesaage());
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input. Please enter either Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {
						findAndHoldRequest.setUserConfirm(true);
						findAndHoldRequest.setCustPreferredSeats(findAndHoldResponse.getBestAvailableSeats());
						findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);

						if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {
							reserveAndCommitRequest.setCustmore(cust);
							reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
							reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);
							System.out.println(reserveAndCommitResponse.getMessage());
						} else {

							System.out.println(findAndHoldResponse.getMesaage());
						}
					}

				} else if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input. Please enter either Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMessage());
					}

				} else {

					System.out.println(findAndHoldResponse.getMesaage());
				}

				break;
				
			case 4:

				System.out.println("Enter customer Name \n");
				String name1 = input.next();
				System.out.println("Enter customer Ph Number \n");
				String phNum1 = input.next();
				
				while(!phNum1.matches("[0-9]{10}")){
					System.out.println("Wrong input. Enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust1 = new Customer(name1, phNum1);
				
				System.out.println("Enter required number of seats");
				int reqNuOfSeats = input.nextInt();
				List<Seat> custPreferredSeats = new ArrayList<Seat>();
				
				for(int i=1;i<=reqNuOfSeats;){
					
					System.out.println("Enter preferred Row only Single Character between A  to " + Constants.ENDING_ROW +" for Seat "+ i);
					Character r = input.next().toUpperCase().charAt(0);
					
					while(!validateRow(r)){
						
						System.out.println("Wrong input. Enter preferred Row only Single Character between A to " + Constants.ENDING_ROW +" for Seat "+ i);
						r = input.next().toUpperCase().charAt(0);
					}
					System.out.println("Enter preferred column only Single integer between 1 to " + Constants.NUMBER_COLUMNS+" for Seat "+ i);
					int c=input.nextInt();
					
                    while(!validateColumn(c)){
						
                    	System.out.println("Wrong input. Enter preferred column only Single integer between 1 to " + Constants.NUMBER_COLUMNS+" for Seat "+ i);
						c = input.nextInt();
					}
                    Seat st = new Seat(r,c);
                    if(!contains(custPreferredSeats,st)){
					custPreferredSeats.add(new Seat(r,c));
					i++;
                    }
                    else
                    {
                    	System.out.println("This seat already choosen please choose diffrent seat");
                    }
					
				}
				findAndHoldRequest.setCustPreferredSeats(custPreferredSeats);
				findAndHoldRequest.setCustomer(cust1);
				findAndHoldResponse = ts.holdSeats(findAndHoldRequest);
				
				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input. Please enter either Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust1);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMessage());
					}

				} else {

					System.out.println(findAndHoldResponse.getMesaage());
				}
								
				break;

			case 5:
				
				System.out.println("Enter customer name \n");
				String name2 = input.next();
				System.out.println("Enter customer Ph number \n");
				String phNum2 = input.next();
				
				while(!phNum2.matches("[0-9]{10}")){
					System.out.println("Wrong input enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust2 = new Customer(name2, phNum2);

				findAndHoldRequest.setCustomer(cust2);

				System.out.println("Enter required number of seats");
				int reqNumOfSeats = input.nextInt();
				findAndHoldRequest.setReqNoOfSeats(reqNumOfSeats);
				findAndHoldRequest.setCustPreferredRow(null);
				findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);

				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input. Please enter Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust2);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMessage());
					}

				} else {

					System.out.println(findAndHoldResponse.getMesaage());
				}

				break;

			}

		} while (selection < 6);

		input.close();

	}

	
	private static boolean validateRow(Character row){
		
		char r= row.charValue();
		
		boolean b=('A' <= r && r <= Constants.ENDING_ROW.charValue());
	
		return b;
	}
	
	private static boolean validateColumn(int col){
		
		boolean b= (1 <= col && col <= Constants.NUMBER_COLUMNS);
		
		return b;
	}

	
	private static boolean contains(List<Seat> custPreferredSeats, Seat st){
		
		for(Seat custSt:custPreferredSeats){
			
			if(custSt.equals(st))
				return true;
		}
		
		
		return false;
		
	}
	
}
