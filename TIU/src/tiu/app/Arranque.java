package tiu.app;

import tiu.core.*;

/** Esta classe é responsável por criar os componentes do sistema,
 *  inicializar os dados com a configuração de teste e
 *  apresentar as várias janelas
 */
public class Arranque {
	
	public static void main(String[] args) {
		// criar a central e fazer o respetivo setup
		CentralTIU central = setupSistema();

		// criar a janela principal
		JanelaPrincipal jp = new JanelaPrincipal( central );
		jp.setLocation( 20, 380 );
		jp.setVisible( true );
		
		// TODO criar as janelas dos utentes (criar uma para cada utente)
		int i=0;
		for( int k=0; k < 1; k++ ) {
			// TODO cuidado com o null, substituir pelo valor correto
			JanelaUtente ju = new JanelaUtente( jp, central, null );
			ju.setLocation( 20 + i*(ju.getWidth()+10), 20);
			ju.setVisible( true );
			i++;
		}

		
		// TODO criar as janelas das trotinetes (criar uma por cada trotinete)
		i=0;
		for( int k = 0; k < 1; k++ ) {
			// TODO cuidado com o null, substituir pelo valor correto
			JanelaTrotinete jt = new JanelaTrotinete( jp, null );
			jt.setLocation( 20 + i*(jt.getWidth()+10), 200);
			jt.setVisible( true );
			i++;
		}	
	}

	/** cria a central e usa a configuração de testes
	 * @return a central criada
	 */
	private static CentralTIU setupSistema() {
		CentralTIU central = new CentralTIU();
		
		// TODO criar os utentes
		
		// TODO criar as trotinetes
		
		// TODO configurar as autonomias restantes para as trotinetes
	
		return central;
	}
}
