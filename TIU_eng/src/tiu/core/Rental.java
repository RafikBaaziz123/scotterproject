package tiu.core;

import java.time.Duration;
import java.time.LocalDateTime;

import tiu.mobility.Scooter;

/**
 * Represents a rental process.
 * The rental must have a starting date (LocalDateTime),
 * the user that made the rental, the rented scooter and,
 * when finished, the end date, the cost and distance traveled
 */
public class Rental {
	
	// Attributes declaration.
	private LocalDateTime startingTime, finishTime;
	private User user;
	private Scooter scooter;
	private float cost;
	private int distanceTraveled;
	
	/** terminates the rental
	 */
	public void terminate() {
		this.user.terminateRental();
		this.scooter.terminateRental();
		this.finishTime = LocalDateTime.now();
	}

	/** returns the duration. If the rental is over, it returns 
	 * the total duration of the rental. If the rental is still
	 * going on, it returns the actual duration.
	 * @return the duration of the rental (total or at the moment)
	 */
	public Duration getDuration( ) {
		if(this.startingTime != null) {
			if(this.finishTime != null) {
				return Duration.between(this.startingTime, this.finishTime);
			}
			return Duration.between(this.startingTime, LocalDateTime.now());
		}
		return null;
	}
	
	/** returns the total cost.
	 * Is the rental is finished returns the total cost.
	 * If the rental is still going on it returns the cost
	 * up to now.
	 * @return the cost of the rental (total or at the moment)
	 */
	public float getCost() {
		if(this.startingTime != null) {
			float durationInMinutes = this.getDuration().toMinutes();
			if(this.finishTime != null) {
				return (float) (durationInMinutes * 0.15);
			}
			return (float) ((durationInMinutes * 0.15) + 0.50);
		}
		return 0;
	}
	
	/** Returns the total distance traveled.
	 * If the rental is finished it returns the total distance.
	 * If the rental is still going on it returns
	 * the distance up to now. 
	 * @return the distance traveled (total or at the moment)
	 */
	public int getDistance() {
		return this.distanceTraveled;
	}

	// Getters and Setters.
	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Scooter getScooter() {
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}

	// Constructor.
	public Rental(User user, Scooter scooter) {
		super();
		this.startingTime = LocalDateTime.now();
		this.user = user;
		this.scooter = scooter;
	}
	
}
