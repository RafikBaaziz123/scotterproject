package tiu.mobilidade;

import java.util.ArrayList;
import java.util.List;

import tiu.core.Aluguer;

/**
 * Class that represents a scooter It must have the identifying code, if it has
 * any rentals at the moment a list of rentals it has already made, the autonomy
 * the remaining autonomy, is batteri charge the travel speed the total distance
 * traveled and the distance traveled in the current rental.
 * 
 */
public class Trotinete {

	/**
	 * starts a rental
	 * 
	 * @param alu the rent to start
	 */
	private String id;
	private float autonomy;
	private float remAutonomy;
	private ArrayList<Aluguer> previousRentals = new ArrayList<>();
	final private int speed;
	private int totalDistanceTraveled;
	private int distanceTraveled;
	private boolean charging;
	private boolean maintenance;
	private Aluguer currentRental;

	public Trotinete(String id, float autonomy, float remAutonomy, int speed, int totalDistanceTraveled,
			int distanceTraveled, Aluguer currentRental) {
		super();
		this.id = id;
		this.autonomy = autonomy;
		this.remAutonomy = remAutonomy;
		this.speed = speed;
		this.totalDistanceTraveled = totalDistanceTraveled;
		this.distanceTraveled = distanceTraveled;
		this.currentRental = currentRental;
	}

////////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getAutonomy() {
		return autonomy;
	}

	public void setAutonomy(float autonomy) {
		this.autonomy = autonomy;
	}

	public float getRemAutonomy() {
		return remAutonomy;
	}

	public void setRemAutonomy(float remAutonomy) {
		this.remAutonomy = remAutonomy;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}

	public void setTotalDistanceTraveled(int totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
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

	/// start RENT
	public void iniciaAluguer(Aluguer alu) {// you add this rent to list
		if (this.getCurrentRental() == null) {
			this.setCurrentRental(alu);
		}
	}

	/**
	 * the current rent ends
	 * 
	 */
	public void terminaAluguer() {
		if (this.getCurrentRental() != null) {
			this.addAluguer(this.getCurrentRental());
			this.setCurrentRental(null);
		}
	}

	/**
	 * Start the scooter
	 */
	public void mover() {
		this.distanceTraveled += this.speed;
		this.totalDistanceTraveled += this.distanceTraveled;
		this.remAutonomy -= this.distanceTraveled;
		/////// we can add that we change the remainig autonomy
	}

	/**
	 * Stop the scooter
	 */
	public void parar() {
		if (this.getSpeed() == 0) {
			System.out.println("the scotter is stopped ");

		}
	}

	/**
	 * indicates whether the scooter is running
	 * 
	 * @return if the scooter is running
	 */
	public boolean emAndamento() {
		if (this.getSpeed() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * indicates whether the scooter is in use, that is, if it is being rented
	 * 
	 * @return if the scooter is in use
	 */
	public boolean emUso() {

		if (this.currentRental != null) {
			return true;// if in use
		}
		return false;

	}

	/**
	 * indicates whether the scooter is charging
	 * 
	 * @return if the scooter is charged
	 */
	public boolean emCarga() {

		if (this.remAutonomy == this.autonomy) {

			return true;// if charged

		} else {
			return false;
		}

	}

	/**
	 * Method called by the system every second for the scooter to update its state
	 * thus simulating its movement or load
	 */
	public void atualizar() {
		// i think i should call all prev methodes
		this.mover();// distance, total distance

	}

	/**
	 * put on/take off the maintenance scooter
	 * 
	 * @param indisponivel true to put into maintenance, false to take out of
	 *                     maintenance
	 */
	public void setEmManutencao(boolean indisponivel) {
		if (indisponivel)// if not disponibel
		{
			this.maintenance = true;
		} else {
			this.maintenance = false;
		}
	}

	/**
	 * indicates if the scooter is unavailable. The scooter is unavailable if:<br>
	 * - is in maintenance<br>
	 * - is charging<br>
	 * - if it is not on a lease and has little autonomy<br>
	 * 
	 * @return if the scooter is unavailable
	 */
	public boolean estaIndisponivel() {
		if ((this.maintenance) || (this.emCarga() == false) && (this.emUso() == false))

			return false; // cant use it
		else {
			return true;
		}
	}

	/**
	 * Place/retract the scooter under charging. If the scooter is in use, it can
	 * NOT be charged
	 * 
	 * @param carga true to load false to remove from the load
	 */
	public void setEmCarga(boolean carga) {
		carga = false;
		if (this.emUso() == false && this.emCarga() == false) {
			carga = true;
		}
		if (carga == true) {
			this.remAutonomy += 400;
		}

	}
}