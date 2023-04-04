package tiu.mobilidade;

import tiu.core.Aluguer;

/**
 * Class that represents a scooter
 * It must have the identifying code, 
 * if it has any rentals at the moment a list of rentals it has already made, 
 * the autonomy the remaining autonomy, 
 * the travel speed the total distance traveled
 * and the distance traveled in the current rental.  
 * 
 */
public class Trotinete {

	/** starts a rental
	 * @param alu the rent to start
	 */
	public void iniciaAluguer(Aluguer alu) {
	}
	
	/** the current rent ends
	 * 
	 */
	public void terminaAluguer( ) {
	}
	
	/** Start the scooter
	 */
	public void mover() {
	}
	
	/** Stop the scooter
	 */
	public void parar() {
	}
	
	/** indicates whether the scooter is running
	 * @return if the scooter is running
	 */
	public boolean emAndamento() {
		return false;
	}
	
	/** indicates whether the scooter is in use, that is,
	 * if it is being rented
	 * @return if the scooter is in use
	 */
	public boolean emUso() {
		return false;
	}

	/** indicates whether the scooter is charging
	 * @return if the scooter is charged
	 */
	public boolean emCarga() {
		return false;
	}
	
	/** Method called by the system every second
	 * for the scooter to update its state
	 * thus simulating its movement or load
	 */
	public void atualizar() {
	}

	/** put on/take off the maintenance scooter
	 * @param indisponivel true to put into maintenance,
	 * false to take out of maintenance
	 */
	public void setEmManutencao(boolean indisponivel) {
	}
	
	/** indicates if the scooter is unavailable.
	 * The scooter is unavailable if:<br>
	 *   - is in maintenance<br>
	 *   - is charging<br>
	 *   - if it is not on a lease and has little autonomy<br>
	 * @return if the scooter is unavailable
	 */
	public boolean estaIndisponivel() {
		return false;
	}
	
	/** Place/retract the scooter under load.
	 * If the scooter is in use, it can NOT be charged
	 * @param carga true to load
	 * false to remove from the load
	 */
	public void setEmCarga( boolean carga  ) {
	}
}