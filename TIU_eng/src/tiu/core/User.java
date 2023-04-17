package tiu.core;

import java.util.ArrayList;

/**
 * Represents a user of the system
 * A user has a username and a full name, 
 * an ongoing rental (if one is active) and a list
 * of all past rentals.  
 */
public class User {
	
	// Attributes declaration.
	private String username, name;
	private Rental currentRental;
	private ArrayList<Rental> previousRentals = new ArrayList<Rental>();
	
	/** User started a new rental. He can only accept the new rental
	 * is he does not currently have a rental, otherwise he must
	 * ignore the new rental
	 * @param r the rental to start
	 */
	public void startRental(Rental e) {
		if(this.getCurrentRental() == null) {
			this.setCurrentRental(e);
		}
	}
	
	/** Terminates the current rental.
	 */
	public void terminateRental() {
		if(this.getCurrentRental() != null) {
			this.addRental(this.currentRental);
			this.setCurrentRental(null);
		}
	}
	
	/** Tells if the user is currently renting a scooter
	 * @return true is he is renting, false otherwise
	 */
	public boolean isRenting() {
		if(this.currentRental != null) {
			return true;
		}
		return false;
	}

	
	// Getters and setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rental getCurrentRental() {
		return currentRental;
	}

	public void setCurrentRental(Rental currentRental) {
		this.currentRental = currentRental;
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
	
}
