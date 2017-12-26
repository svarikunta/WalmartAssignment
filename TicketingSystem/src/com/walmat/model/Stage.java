package com.walmat.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.walmart.Constats.Constants;

public class Stage {
	
	
	char noOfRow;
	int noColums;
	public static AtomicInteger seatsAvilable;
	public static AtomicInteger seatsReserved;
	public static AtomicInteger seatsHeld;  

	ConcurrentHashMap<String,Seat> seatMap = new ConcurrentHashMap<String,Seat>();
	
	private Stage(Character noOfRow,int noColums){
		
		seatsAvilable= new AtomicInteger((noOfRow-'A'+1)*noColums);
		seatsReserved= new AtomicInteger(0);
		seatsHeld=new AtomicInteger(0);
		
		this.noOfRow=noOfRow;
		this.noColums=noColums;
		
		for(Character row='A';row<=noOfRow;row++){
			for(int col=1;col<=noColums;col++){
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

	public int getNoColums() {
		return noColums;
	}

	public void setNoColums(int noColums) {
		this.noColums = noColums;
	}

	public ConcurrentHashMap<String, Seat> getSeatMap() {
		return seatMap;
	}

	public void setSeatMap(ConcurrentHashMap<String, Seat> seatMap) {
		this.seatMap = seatMap;
	}
	

}
