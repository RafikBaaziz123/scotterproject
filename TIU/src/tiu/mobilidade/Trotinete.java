package tiu.mobilidade;

import tiu.core.Aluguer;

/**
 * Classe que representa uma trotinete
 * Deve ter o código identificativo, se tem algum aluguer no momento,
 * uma lista de alugueres que já realizou, a autonomia,
 * a autonomia restante, a velocidade de deslocamento,
 * a distância total percorrida,
 * e a distância percorrida no aluguer atual.  
 * 
 */
public class Trotinete {

	/** inicia um aluguer
	 * @param alu o aluguer a começar
	 */
	public void iniciaAluguer(Aluguer alu) {
	}
	
	/** termina o aluguer atual
	 * 
	 */
	public void terminaAluguer( ) {
	}
	
	/** Coloca a trotinete em andamento
	 */
	public void mover() {
	}
	
	/** Pára a trotinete
	 */
	public void parar() {
	}
	
	/** indica se a trotinete está em andamento
	 * @return se a trotinete está em andamento
	 */
	public boolean emAndamento() {
		return false;
	}
	
	/** indica se a trotinete está em uso, isto é,
	 * se está a ser alugada
	 * @return se a trotinete está em uso
	 */
	public boolean emUso() {
		return false;
	}

	/** indica se a trotinete está em carga
	 * @return se a trotinete está em carga
	 */
	public boolean emCarga() {
		return false;
	}
	
	/** Método chamado pelo sistema a cada segundo
	 * para que a trotinete atualize o seu estado,
	 * simulando assim o movimento ou carga da mesma
	 */
	public void atualizar() {
	}

	/** coloca/retira a trotinete da manutenção
	 * @param indisponivel true para colocar em manutenção,
	 * false para retirar da manutenção
	 */
	public void setEmManutencao(boolean indisponivel) {
	}
	
	/** indica se a trotinete está indisponível.
	 * A trotinete está indisponível se:<br>
	 *   - está em manutenção<br>
	 *   - está a carregar<br>
	 *   - se não está num aluguer e tem pouca autonomia<br>
	 * @return se a trotinete está indisponível
	 */
	public boolean estaIndisponivel() {
		return false;
	}
	
	/** Coloca/retira a trotinete em carga.
	 * Se a trotinete está em uso NÃO pode ser colocada em carga
	 * @param carga true para por a carregar e
	 * false para retirar do carregamento
	 */
	public void setEmCarga( boolean carga  ) {
	}
}