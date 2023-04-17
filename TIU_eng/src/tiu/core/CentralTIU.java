package tiu.core;

import java.util.ArrayList;

import tiu.mobility.Scooter;

/** Represents the control central of the system.
 * Must store all users and scooters
 * and process all rentals
 */
public class CentralTIU {
	
	//
	private ArrayList<User> usersList = new ArrayList<User>();
	private ArrayList<Scooter> scootersList =  new ArrayList<Scooter>();
	private ArrayList<Rental> rentalsList = new ArrayList<Rental>();

	// constants to define error codes
	public static final int OK = 0;
	public static final int SCOOTER_UNKNOWN = 1;
	public static final int SCOOTER_IN_USE = SCOOTER_UNKNOWN + 1;
	public static final int SCOOTER_MOVING = SCOOTER_IN_USE + 1;
	public static final int SCOOTER_CHARGING = SCOOTER_MOVING + 1;
	public static final int SCOOTER_UNAVAILABLE = SCOOTER_CHARGING + 1;
	public static final int USER_IS_RENTING = SCOOTER_UNAVAILABLE + 1;
	
	/** Creates a new rental process for a user
	 * @param u the user that wants the rental
	 * @param code the code of the scooter the user wants to rent
	 * @return OK, if the rental is successful
	 * <br>SCOOTER_UNKNOWN, if the code is not associated with a scooter
	 * <br>SCOOTER_IN_USE, if the scooter is being used
	 * <br>SCOOTER_CHARGING, if the scooter is charging
	 * <br>SCOOTER_UNAVAILABLE, if the scooter is unavailable
	 * <br>USER_IS_RENTING, if the user is already renting another scooter
	 */
	public int doRental(User u, String code) {
		boolean test = false;
		int i = 0;
		Scooter currentScooter = null;
		while (!test) {
			if(this.scootersList.get(i).getId().equals(code)){
				test = true;
				currentScooter = this.scootersList.get(i);
			}else {
				i++;
			}
		}
		if(currentScooter == null) {
			return this.SCOOTER_UNKNOWN;
		}else {
			if(currentScooter.inUse()) {
				return this.SCOOTER_IN_USE;
			}else if(currentScooter.isCharging()) {
				return this.SCOOTER_CHARGING;
			}else if(currentScooter.isUnavailable()) {
				return this.SCOOTER_UNAVAILABLE;
			}else if(u.isRenting()) {
				return this.USER_IS_RENTING;
			}else {
				Rental r = new Rental(u, currentScooter);
				u.startRental(r);
				currentScooter.startRental(r);
				return OK;
			}
		}
	}
	
	/** Terminates the rental process
	 * @param r the rental process to terminate
	 * @return OK, if successful
	 * <br> SCOOTER_MOVING if the scooter is still moving (it must be stopped)
	 */
	public int terminateRental(Rental r) {
		if(r.getScooter().isMoving()) {
			return this.SCOOTER_MOVING;
		}else {
			r.terminate();
			this.rentalsList.add(r);
			return OK;
		}
	}	
}

