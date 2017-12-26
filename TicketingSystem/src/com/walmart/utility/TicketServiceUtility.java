package com.walmart.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.walmart.Constats.Constants;
import com.walmart.service.TicketingService;
import com.walmart.service.TicketingServiceImpl;
import com.walmat.model.Customer;
import com.walmat.model.FindAndHoldRequest;
import com.walmat.model.FindAndHoldResponse;
import com.walmat.model.FindAvailableResponse;
import com.walmat.model.ReserveAndCommitRequest;
import com.walmat.model.ReserveAndCommitResponse;
import com.walmat.model.Seat;

public class TicketServiceUtility {

	public static void main(String Args[]) {

		TicketingService ts = new TicketingServiceImpl();

		Scanner input = new Scanner(System.in);

		int selection;

		do {

			System.out.println();
			System.out.println("1 - Display seat Map");
			System.out.println("2 - Find Available, Held and Reserved seats");
			System.out.println("3 - Find and Hold Seats with customer preferred row and Reserve held seats");
			System.out.println(
					"4 - Hold seats with customer with customer Preferred row and column and Reserve held seats");
			System.out.println(
					"5 -Find and Hold Seats with system preferred best available and Reserve held seats");
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

				FindAvailableResponse findAvailableResponse = ts.findNoOfSeatsAvilable();
				System.out.println("No of Available seats: " + findAvailableResponse.getTotalAvailableSeats());
				System.out.println("No of Held seats: " + findAvailableResponse.getTotalHeldSeats());
				System.out.println("No of Reserved seats: " + findAvailableResponse.getTotalReservedSeats());
				break;

			case 3:
				
				System.out.println("Enter Customer Name");
				String name = input.next();
				System.out.println("Enter Ph Number");
				String phNum = input.next();
				
				while(!phNum.matches("[0-9]{10}")){
					System.out.println("Wrong input enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust = new Customer(name, phNum);

				findAndHoldRequest.setCustmore(cust);
				System.out.println("Enter Preferred Row only Single Character between A  to " + Constants.ENDING_ROW);
				Character row = input.next().toUpperCase().charAt(0);
				
				while(!validateRow(row)){
					
					System.out.println("Wrong input Enter Preferred Row only Single Character between A  to " + Constants.ENDING_ROW);
					row = input.next().toUpperCase().charAt(0);
				}
				
				findAndHoldRequest.setCustPreferredRow(row);
				System.out.println("Enter required number of seats");
				int reqNumbOfSeats = input.nextInt();
				findAndHoldRequest.setReqNumbOfSeats(reqNumbOfSeats);
				findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);
				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.NeedConfirm)) {
					
					for(Seat st:findAndHoldResponse.getBestAvilableSeats()){
						System.out.println("Row :"+st.getRowNo()+"Column :"+st.getColNo());
					}

					System.out.println(findAndHoldResponse.getMesaage());
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input Please enter Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {
						findAndHoldRequest.setUserConfirm(true);
						findAndHoldRequest.setCustPreferdSeats(findAndHoldResponse.getBestAvilableSeats());
						findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);

						if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {
							reserveAndCommitRequest.setCustmore(cust);
							reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
							reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);
							System.out.println(reserveAndCommitResponse.getMesaage());
						} else {

							System.out.println(findAndHoldResponse.getMesaage());
						}
					}

				} else if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input Please enter Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMesaage());
					}

				} else {

					System.out.println(findAndHoldResponse.getMesaage());
				}

				break;
				
			case 4:

				System.out.println("Enter Customer Name \n");
				String name1 = input.next();
				System.out.println("Enter Ph Number \n");
				String phNum1 = input.next();
				
				while(!phNum1.matches("[0-9]{10}")){
					System.out.println("Wrong input enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust1 = new Customer(name1, phNum1);
				
				System.out.println("Enter required number of seats");
				int reqNuOfSeats = input.nextInt();
				List<Seat> cutsPreferdSeats = new ArrayList<Seat>();
				
				for(int i=1;i<=reqNuOfSeats;){
					
					System.out.println("Enter preferred Row only Single Character between A  to " + Constants.ENDING_ROW +" for Seat "+ i);
					Character r = input.next().toUpperCase().charAt(0);
					
					while(!validateRow(r)){
						
						System.out.println("Wrong input. Enter preferred Row only Single Character between A  to " + Constants.ENDING_ROW +" for Seat "+ i);
						r = input.next().toUpperCase().charAt(0);
					}
					System.out.println("Enter preferred column only Single integer between 1 to " + Constants.NUMBER_COLUMNS+" for Seat "+ i);
					int c=input.nextInt();
					
                    while(!validateColumn(c)){
						
                    	System.out.println("Wrong input. Enter preferred column only Single integer between 1 to " + Constants.NUMBER_COLUMNS+" for Seat "+ i);
						c = input.nextInt();
					}
                    Seat st = new Seat(r,c);
                    if(!contains(cutsPreferdSeats,st)){
					cutsPreferdSeats.add(new Seat(r,c));
					i++;
                    }
                    else
                    {
                    	System.out.println("This seat already choosen please choose diffrent seat");
                    }
					
				}
				findAndHoldRequest.setCustPreferdSeats(cutsPreferdSeats);
				findAndHoldRequest.setCustmore(cust1);
				findAndHoldResponse = ts.holdSeats(findAndHoldRequest);
				
				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input Please enter Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust1);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMesaage());
					}

				} else {

					System.out.println(findAndHoldResponse.getMesaage());
				}
								
				break;

			case 5:
				
				System.out.println("Enter Customer Name \n");
				String name2 = input.next();
				System.out.println("Enter Ph Number \n");
				String phNum2 = input.next();
				
				while(!phNum2.matches("[0-9]{10}")){
					System.out.println("Wrong input enter only 10 digits ");
					phNum = input.next();
				}
				Customer cust2 = new Customer(name2, phNum2);

				findAndHoldRequest.setCustmore(cust2);

				System.out.println("Enter required number of seats");
				int reqNumOfSeats = input.nextInt();
				findAndHoldRequest.setReqNumbOfSeats(reqNumOfSeats);
				findAndHoldRequest.setCustPreferredRow(null);
				findAndHoldResponse = ts.findAndHoldSeats(findAndHoldRequest);

				if (findAndHoldResponse.getStatus().equals(FindAndHoldResponse.Status.Sucess)) {

					System.out.println(findAndHoldResponse.getMesaage());
					System.out.println("Enter Y to reserve N to ignore");
					Character userCh = input.next().toUpperCase().charAt(0);
					while (!userCh.equals('Y') && !userCh.equals('N')) {
						System.out.println(" Wrong Input Please enter Y or N");
						userCh = input.next().toUpperCase().charAt(0);
					}
					if (userCh.equals('Y')) {

						reserveAndCommitRequest.setCustmore(cust2);
						reserveAndCommitRequest.setSeatsHeld(findAndHoldResponse.getSeatsHeld());
						reserveAndCommitResponse = ts.reserveAndCommitSeats(reserveAndCommitRequest);

						System.out.println(reserveAndCommitResponse.getMesaage());
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

	
	private static boolean contains(List<Seat> custmerprefedSeats, Seat st){
		
		for(Seat custSt:custmerprefedSeats){
			
			if(custSt.equals(st))
				return true;
		}
		
		
		return false;
		
	}
	
}
