package tiu.core;

import java.time.Duration;
import java.time.LocalDateTime;

import tiu.mobilidade.Trotinete;

/**
 * Class representing a rental in the system.
 * The rental must have the start date (LocalDateTime)
 * the user that made the rental, the scooter that was rented
 * and, if it has finished, it must also have an end date,
 * the cost and distance traveled
 */
public class Aluguer {
	
	//Attributes declaration.
	private Utente user;
	private Trotinete scooter;
	private LocalDateTime startTime, finishTime;
	private int distanceTraveled;
	private float cost;
	
	
	/** method responsible for terminating the rent
	 */
	public void terminar() {
		this.user.terminaAluguer();
		this.scooter.terminaAluguer();
		this.finishTime = LocalDateTime.now();
	}

	/** Indicates the duration of the rental. If the rental has
	 * has ended, returns the total length of the rental.
	 * If the rental is still ongoing, it will return the current duration of the rental
	 * 
	 * @return the rental duration
	 */
	public Duration getDuracao( ) {
		if(this.startTime != null && this.finishTime != null) {
			return Duration.between(this.startTime, this.finishTime);
		}else {
			return Duration.between(this.startTime, LocalDateTime.now());
		}
	}
	
	/** returns the current cost of the rental
	 * If the lease is terminated this is the total cost,
	 * otherwise it represents the cost so far
	 * @return the current cost of rent.
	 */
	public float getCusto() {
		double minutes = this.getDuracao().toMinutes();
		return (float) ((float)(minutes * 0.15) + 0.5);
	}
	
	/** returns the distance traveled during the rental
	 * If the rental is still running, returns the distance that has been traveled so far. 
	 * @return the distance traveled during the rental
	 */
	public int getDistancia() {
		return this.scooter.getDistanceTraveled();
	}
	
	
}
