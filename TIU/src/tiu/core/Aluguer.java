package tiu.core;

import java.time.Duration;

/**
 * Classe que representa um aluguer no sistema.
 * O aluguer deve ter a data de inicio (LocalDateTime)
 * o utente que fez o aluguer, a trotinete que foi alugada
 * e, caso tenha terminado, deve ter ainda a data de fim,
 * o custo e a distância percorrida
 */
public class Aluguer {
	
	/** método responsável por terminar o aluguer
	 */
	public void terminar() {
	}

	/** Indica a duração do aluguer. Se o aluguer já
	 * tiver terminado, retorna a duração total do aluguer.
	 * Se o aluguer ainda estiver a decorrer, deve retornar
	 * a duração atual do aluguer
	 * 
	 * @return a duração do aluguer
	 */
	public Duration getDuracao( ) {
		return null;
	}
	
	/** retorna o custo atual do aluguer.
	 * Se o aluguer esiver terminado este é o custo total,
	 * senão representa o custo até ao momento
	 * @return o custo atual do aluguer
	 */
	public float getCusto() {
		return 0;
	}
	
	/** retorna a distância percorrida durante o aluguer
	 * Se o aluguer ainda estiver a decorrer, retorna a
	 * distância que foi percorrida até ao momento. 
	 * @return a distância percorrida durante o aluguer
	 */
	public int getDistancia() {
		return 0;
	}
}
