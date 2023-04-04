package tiu.core;

/** Represents the system's control center.
  * Must have all users and all scooters
  * as well as managing the various rentals
 */
public class CentralTIU {

	// definition of the costants for the various error cases
	public static final int OK = 0;
	//UNKNOWN SCOOTER = 1
	public static final int TROTINETE_DESCONHECIDA = 1;
	//SCOOTER IN USE = UNKNOWN SCOOTER
	public static final int TROTINETE_EM_USO = TROTINETE_DESCONHECIDA + 1;
	
	//SCOOTER IN PROGRESS = SCOOTER IN USE
	public static final int TROTINETE_EM_ANDAMENTO = TROTINETE_EM_USO + 1;
	
	//SCOOTER ON CHARGE = SCOOTER IN PROGRESS
	public static final int TROTINETE_EM_CARGA = TROTINETE_EM_ANDAMENTO + 1;
	
	//SCOOTER UNAVAILABLE = SCOOTER ON CHARGE
	public static final int TROTINETE_INDISPONIVEL = TROTINETE_EM_CARGA + 1;
	
	//USER IN RENTAL = SCOOTER UNAVAILABLE
	public static final int UTENTE_EM_ALUGUER = TROTINETE_INDISPONIVEL + 1;
	
	/** Create a new rental for a customer
	 * @param utente the user who wants the rental
	 * @param codigo the code of the scooter the user wants to rent
	 * @return OK, if everything went well.
	 * <br>TROTINETE_DESCONHECIDA, if the code does not correspond to a scooter
	 * <br>TROTINETE_EM_USO, if the scooter is being used 
	 * <br>TROTINETE_EM_CARGA, if the scooter is loaded
	 * <br>TROTINETE_INDISPONIVEL, if the scooter is unavailable
	 * <br>UTENTE_EM_ALUGUER, if the user is already renting another scooter
	 */
	//Make rent
	public int fazAluguer(Utente utente, String codigo) {
		return OK;
	}
	
	/** Ending a rental process
	 * @param aluguer the rental to be finished
	 * @return OK, if everything went good
	 * <br> TROTINETE_EM_ANDAMENTO if the scooter is still running.
	 */
	//Finish Rent
	public int terminarAluguer(Aluguer aluguer) {
		return OK;
	}
	
}

