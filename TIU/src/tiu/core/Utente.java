package tiu.core;

import java.util.ArrayList;

/**
 * Class that represents a user of the system
  * A user has a user name and his name,
  * if you currently have any rentals,
  * a list of all rentals you have already made. 
 * 
 */
public class Utente {
	
	//Attributes declaration.
	private String username, name;
	private Aluguer currentRental;
	private ArrayList<Aluguer> previousRentals;
	
	
	/** The user has started a new lease. You should only accept
	 * if you do not currently have any rentals, 
	 * otherwise should ignore the new lease
	 * @param alu the lease started
	 */	
	public void comecaAluguer(Aluguer alu) {
		if(this.getCurrentRental() == null) {
			this.setCurrentRental(alu);
		}
	}
	
	/** Ends the current Rent.
	 */
	public void terminaAluguer() {
		if(this.getCurrentRental() != null) {
			this.addAluguer(this.getCurrentRental());
			this.setCurrentRental(null);
		}
	}
	
	/** indicates if you are currently renting a scooter
	 * @return true if you are renting, false otherwise
	 */
	public boolean estaAlugar() {
		if(this.currentRental != null) {
			return true;
		}
		return false;
	}
	
	
	/** 
	 * Getter And Setters
	 */
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

	public Aluguer getCurrentRental() {
		return currentRental;
	}

	public void setCurrentRental(Aluguer currentRental) {
		this.currentRental = currentRental;
	}

	public ArrayList<Aluguer> getPreviousRentals() {
		return previousRentals;
	}

	public void addAluguer(Aluguer alu) {
		this.previousRentals.add(alu);
	}
	
	public void removeAluguer(Aluguer alu) {
		this.previousRentals.remove(alu);
	}
	
	/**
	 * Creating Constructors
	 */

	public Utente(String username, String name, Aluguer currentRental) {
		this.setUsername(username);
		this.setName(name);
		this.setCurrentRental(currentRental);
	}

	public Utente(String username, String name) {
		this.setUsername(username);
		this.setName(name);
	}
	
	
}
