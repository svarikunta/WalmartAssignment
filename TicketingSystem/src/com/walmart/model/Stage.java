package com.walmart.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.walmart.constants.Constants;

public class Stage {
	
	
	char noOfRow;
	int noOfColumns;
	public static AtomicInteger seatsAvailable;
	public static AtomicInteger seatsReserved;
	public static AtomicInteger seatsHeld;  

	ConcurrentHashMap<String,Seat> seatMap = new ConcurrentHashMap<String,Seat>();
	
	private Stage(Character noOfRow,int noOfColumns){
		
		seatsAvailable= new AtomicInteger((noOfRow-'A'+1)*noOfColumns);
		seatsReserved= new AtomicInteger(0);
		seatsHeld=new AtomicInteger(0);
		
		this.noOfRow=noOfRow;
		this.noOfColumns=noOfColumns;
		
		for(Character row='A';row<=noOfRow;row++){
			for(int col=1;col<=noOfColumns;col++){
				Seat st =new Seat(row,col);				
				String key=row.toString()+col;
				seatMap.put(key, st);
			}
		}
		
		
	}
	
	private static Stage stage=null;
	
	public static Stage getInstance() {
	      if(stage == null) {
	    	  stage = new Stage(Constants.ENDING_ROW, Constants.NUMBER_COLUMNS);
	      }
	      return stage;
	   }

	public char getNoOfRow() {
		return noOfRow;
	}

	public void setNoOfRow(char noOfRow) {
		this.noOfRow = noOfRow;
	}

	public int getNoColumns() {
		return noOfColumns;
	}

	public void setNoColumns(int noColumns) {
		this.noOfColumns = noColumns;
	}

	public ConcurrentHashMap<String, Seat> getSeatMap() {
		return seatMap;
	}

	public void setSeatMap(ConcurrentHashMap<String, Seat> seatMap) {
		this.seatMap = seatMap;
	}
	

}
