package tiu.core;

import java.time.Duration;

/**
 * Class representing a rental in the system.
 * The rental must have the start date (LocalDateTime)
 * the user that made the rental, the scooter that was rented
 * and, if it has finished, it must also have an end date,
 * the cost and distance traveled
 */
public class Aluguer {
	
	/** method responsible for terminating the rent
	 */
	public void terminar() {
	}

	/** Indicates the duration of the rental. If the rental has
	 * has ended, returns the total length of the rental.
	 * If the rental is still ongoing, it will return the current duration of the rental
	 * 
	 * @return the rental duration
	 */
	public Duration getDuracao( ) {
		return null;
	}
	
	/** returns the current cost of the rental.
	 * If the lease is terminated this is the total cost,
	 * otherwise it represents the cost so far
	 * @return the current cost of rent
	 */
	public float getCusto() {
		return 0;
	}
	
	/** returns the distance traveled during the rental.
	 * If the rental is still running, returns the distance that has been traveled so far. 
	 * @return the distance traveled during the rental
	 */
	public int getDistancia() {
		return 0;
	}
}
