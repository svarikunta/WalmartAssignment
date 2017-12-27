package com.walmart.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import com.walmart.constants.Constants;
import com.walmart.model.Seat;
import com.walmart.model.Stage;

public class HoldTimer {

	int holdTime = Constants.HOLD_TIME;

	/**
	 * This method releases hold seats if hold time expires.
	 * @param heldSeats
	 */
	public void startHoldTimer(List<Seat> heldSeats) {

		ActionListener releaseHoldSeats = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				for (Seat st : heldSeats) {

						if (st.isHeld()) {
							st.setState(Seat.State.A);
							st.setCustomer(null);
							Stage.seatsAvailable.getAndIncrement();
							Stage.seatsHeld.getAndDecrement();
						}
					
				}
			}
		};

		Timer timer = new Timer(holdTime, releaseHoldSeats);
		timer.setRepeats(false);
		timer.start();
	}

}
