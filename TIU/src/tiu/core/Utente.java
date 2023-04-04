package tiu.core;

/**
 * Class that represents a user of the system
  * A user has a user name and his name,
  * if you currently have any rentals and a list
  * of all rentals you have already made. 
 * 
 */
public class Utente {
	
	/** The user has started a new lease. You should only accept
	 * if you do not currently have any rentals, 
	 * otherwise should ignore the new lease
	 * @param alu the lease started
	 */	
	public void comecaAluguer(Aluguer alu) {
	}
	
	/** Ends the current Rent.
	 */
	public void terminaAluguer() {
	}
	
	/** indicates if you are currently renting a scooter
	 * @return true if you are renting, false otherwise
	 */
	public boolean estaAlugar() {
		return false;
	}
}
