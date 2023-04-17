package tiu.mobility;

import java.util.ArrayList;

import tiu.core.Rental;

/**
 * Represents a scooter.
 * Is must have an unique identifying code, a 
 * current rental (if one is active), a list of past rentals,
 * its total range and remaining range, speed, total distance,
 * and the distance traveled in the current rental
 */
public class Scooter {
	
	// Attributes declaration.
	private String id;
	private Rental currentRental;
	private ArrayList<Rental> previousRentals = new ArrayList<Rental>();
	private int autonomy, remainingAutonomy, distanceTraveled, totalDistance;
	private final int speed;
	private boolean charging, underMaintenance, moving;

	/** starts a rental
	 * @param r the rental to start
	 */
	public void startRental(Rental r) {
		if(!this.isUnavailable()) {
			if(this.getRemainingAutonomy() > 500) {
				this.setCurrentRental(r);
			}
		}
	}
	
	/** terminate current rental
	 * 
	 */
	public void terminateRental( ) {
		if(this.getCurrentRental() != null) {
			this.addRental(this.currentRental);
			this.setCurrentRental(null);
			this.moving = false;
		}
	}
	
	/** Scooter starts to move
	 */
	public void move() {
		if(this.currentRental != null) {
			this.moving = true;
			this.autonomy += this.speed;
			this.distanceTraveled += this.speed;
			this.totalDistance += this.speed;
			this.remainingAutonomy -= this.speed;
		}
	}
	
	/** Stops the scooter
	 * Don't know how!!!
	 */
	public void stop() {
		if(this.currentRental != null) {
			this.moving = false;
		}
	}
	
	/** Tells if the scooter is moving
	 * @return true if it is moving, false if it is stopped
	 */
	public boolean isMoving() {
		if(this.moving) {
			return true;
		}
		return false;
	}
	
	/** Tells if the scooter is in use, i. e., it is being rented
	 * @return true if it is being used
	 */
	public boolean inUse() {
		if(this.currentRental != null) {
			return true;
		}
		return false;
	}

	/** Tells if the scooter is charging
	 * @return true if the scooter is charging
	 */
	public boolean isCharging() {
		if(this.charging) {
			return true;
		}
		return false;
	}
	
	/** Called once per second, by the system, to simulate
	 * the scooter being used. It must update is status
	 * (distance, range, etc)
	 */
	public void update() {
		this.move();
	}

	/** places the scooter under maintenance or removes it from maintenance
	 * @param maintain true to put under maintenance
	 * false to remove from maintenance
	 */
	public void setInMaintenance(boolean maintain) {
		if(!this.inUse()) {
			this.terminateRental();
		}
		this.underMaintenance = maintain;
	}
	
	/** Tell if the scooter is unvailable
	 * A scooter is unavailable if:<br>
	 *   - its under maintenance<br>
	 *   - its charging<br>
	 *   - it is not under rental and has a limited remaining range<br>
	 * @return true if it is unavailable
	 */
	public boolean isUnavailable() {
		if(this.inUse()) {
			if(this.isCharging() || this.underMaintenance == true || this.remainingAutonomy <= 500) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/** Sets the scooter charging.
	 * If the scooter is being used it CANNOT be put to charge
	 * @param charge true to put to charge
	 * false to remove from charge
	 */
	public void setCharging( boolean charge ) {
		if(this.currentRental == null) {
			this.charging = charge;
		}
	}

	
	// Getters and Setters.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Rental getCurrentRental() {
		return currentRental;
	}

	public void setCurrentRental(Rental currentRental) {
		this.currentRental = currentRental;
	}

	public int getAutonomy() {
		return autonomy;
	}

	public void setAutonomy(int autonomy) {
		this.autonomy = autonomy;
	}

	public int getRemainingAutonomy() {
		return remainingAutonomy;
	}

	public void setRemainingAutonomy(int remainingAutonomy) {
		this.remainingAutonomy = remainingAutonomy;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public ArrayList<Rental> getPreviousRentals() {
		return previousRentals;
	}
	
	public void addRental(Rental e) {
		this.previousRentals.add(e);
	}
	
	public void removeRental(Rental e) {
		this.previousRentals.remove(e);
	}
	
	// Constructor.
	public Scooter(String id, Rental currentRental, int speed) {
		super();
		this.id = id;
		this.currentRental = currentRental;
		this.speed = speed;
	}
}