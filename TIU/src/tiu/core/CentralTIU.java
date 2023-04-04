package tiu.core;

/** Represents the system's control center.
  * Must have all users and all scooters
  * as well as managing the various rentals
 */
public class CentralTIU {

	// definição das costantes para os vários casos de erro
	public static final int OK = 0;
	//UNKNOWN SCOOTER = 1
	public static final int TROTINETE_DESCONHECIDA = 1;
	//SCOOTER IN USE = UNKNOWN SCOOTER
	public static final int TROTINETE_EM_USO = TROTINETE_DESCONHECIDA + 1;
	
	//SCOOTER IN PROGRESS = SCOOTER IN USE
	public static final int TROTINETE_EM_ANDAMENTO = TROTINETE_EM_USO + 1;
	
	//SCOOTER ON CHARGE = SCOOTER IN PROGRESS
	public static final int TROTINETE_EM_CARGA = TROTINETE_EM_ANDAMENTO + 1;
	
	//SCOOTERUNAVAILABLE = SCOOTER ON CHARGE
	public static final int TROTINETE_INDISPONIVEL = TROTINETE_EM_CARGA + 1;
	
	//USER IN RENTAL = SCOOTER UNAVAILABLE
	public static final int UTENTE_EM_ALUGUER = TROTINETE_INDISPONIVEL + 1;
	
	/** Cria um novo aluguer para um cliente
	 * @param utente o utente que pretende o aluguer
	 * @param codigo o código da trotinete que o utente pretende alugar
	 * @return OK, se correu tudo bem
	 * <br>TROTINETE_DESCONHECIDA, se o código não correspode a uma trotinete
	 * <br>TROTINETE_EM_USO, se a trotinete está a ser usada 
	 * <br>TROTINETE_EM_CARGA, se a trotinete está em carga
	 * <br>TROTINETE_INDISPONIVEL, se a trotinete está indisponível
	 * <br>UTENTE_EM_ALUGUER, se o utente já está a alugar outra trotinete
	 */
	//Make rent
	public int fazAluguer(Utente utente, String codigo) {
		return OK;
	}
	
	/** Terminar um processo de aluguer
	 * @param aluguer o aluguer a ser terminado
	 * @return OK, se correu tudo bem
	 * <br> TROTINETE_EM_ANDAMENTO se a trotinete ainda se encontrar em andamento
	 */
	//Finish Rent
	public int terminarAluguer(Aluguer aluguer) {
		return OK;
	}	
	
	int x = 10;
}

