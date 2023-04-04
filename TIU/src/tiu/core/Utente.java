package tiu.core;

/**
 * Classe que representa um utente do sistema
 * Um utente tem um user name e o seu nome, 
 * se tem algum aluguer no momento e uma lista
 * dos alugueres todos que já realizou. 
 * 
 */
public class Utente {
	
	/** O utente começou um novo aluguer. Só deve aceitar
	 * se não tiver nenhum aluguer atualmente, caso contrário
	 * deve ignorar o novo aluguer
	 * @param alu o aluguer começado
	 */
	public void comecaAluguer(Aluguer alu) {
	}
	
	/** Termina o aluguer atual.
	 */
	public void terminaAluguer() {
	}
	
	/** indica se está atualmente a alugar alguma trotinete
	 * @return true se está a alugar, false caso contrário
	 */
	public boolean estaAlugar() {
		return false;
	}
}
