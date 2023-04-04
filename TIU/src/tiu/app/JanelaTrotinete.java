package tiu.app;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tiu.core.Aluguer;
import tiu.mobilidade.Trotinete;

public class JanelaTrotinete extends JDialog {

	/** número de série da janela */
	private static final long serialVersionUID = 1628477706244793006L;

	// A trotinete associada a esta janela 
	private Trotinete trot;

	// Mantém se a plicação está com o aspeto de em uso ou em inativo
	private boolean emUso = false;

	// elementos gráficos
	private JLabel tempoLbl, distanciaLbl, autonomiaLbl;
	private Timer timer; 
	private JButton btMover;
	private static final String INATIVO = "Inativo";
	private static final String EM_USO = "EmUso";
	
	
	/** Cria uma janela que simula uma trotinete
	 * @param owner janela principal
	 * @param t trotinete simulada pela janela
	 */
	public JanelaTrotinete( JFrame owner, Trotinete t ) {
		// TODO substituir "CÒDIGO TROTI" pelo valor correto
		super( owner, "CODIGO TROTI" );
		trot = t;
		setupInterface( "CODIGO TROTE" );
	}

	/** Método chamado quando é necessário atualizar
	 * a interface (1 vez por segundo)
	 */
	public void atualizarInterface( ) {
		// chamar o método que simula a trotinete
		trot.atualizar();
		
		if( trot.emUso() ) {
			// se agora está em uso, mas antes não estava
			// é preciso mudar o aspeto da interface
			if( !emUso )
				mudaParaEmUso();
			
			// TODO preencher as variáveis com os dados corretos
			Duration duracao = null;
			int distanciaPercorrida = 0; 
			int autonomiaRestante = 8500;

			// atualiza a interface
			atualizarTempo( duracao.toHoursPart(), duracao.toMinutesPart(), duracao.toSecondsPart() );
			atualizarDistancias( distanciaPercorrida, autonomiaRestante );
		}			
		else if( emUso )
			mudaParaInativo();
	}
	
	/** método chamado quando o botão mover/parar é premido
	 */
	protected void processarMover() {
		// TODO se está em andamento tem de parar e mudar o nome do botão
		if( Math.abs( 2 ) == 2 ) {
			// ...
			btMover.setText( "Mover" );
		} else {
		// TODO senão é porque tem de se colocar em andamento 
			// ...
			btMover.setText( "Parar" );
		}			
	}
	
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA


	/** atualiza na interface o tempo de aluguer 
	 * @param horas as horas
	 * @param minutos os minutos
	 * @param segundos os segundos
	 */
	protected void atualizarTempo( int horas, int minutos, int segundos ) {
		tempoLbl.setText( String.format("%02d:%02d:%02d", horas, minutos, segundos));
	}
	
	/** atualiza na interface as distâncias
	 * @param dist distância percorrida
	 * @param auto autonomia restante
	 */
	protected void atualizarDistancias( int dist, int auto ) {
		distanciaLbl.setText( String.format("%dm", dist));
		autonomiaLbl.setText( String.format("%dm", auto));
	}

	/** muda para a interface de em uso
	 */
	private void mudaParaEmUso() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, EM_USO);
		emUso = true;
	}

	/** muda para a interface de estar inativa
	 */
	private void mudaParaInativo() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, INATIVO);
		emUso = false;
	}
	
	/** inicializa a interface da janela
	 */
	private void setupInterface( String codigo ) {
		setSize( 150, 150 );
		setResizable( false );
		JPanel inativoPanel = setupInterfaceInativo( codigo );	
		
		JPanel emUsoPanel = setupInterfaceEmUSo();
		
		JPanel cardPanel = new JPanel( new CardLayout() );
		cardPanel.add( inativoPanel, INATIVO );
		cardPanel.add( emUsoPanel, EM_USO);
		setContentPane(cardPanel);
		
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarInterface();				
			}
		});
		timer.start();
	}

	private JPanel setupInterfaceInativo( String codigo ) {
		JPanel inativoPanel = new JPanel( new GridLayout( 0,1) );
		inativoPanel.add( new JLabel("<html><center>Desbloqueie<br>usando<br> " + codigo + "</html>", JLabel.CENTER) );
		return inativoPanel;
	}
	
	private JPanel setupInterfaceEmUSo() {
		JPanel emUsoPanel = new JPanel( new GridLayout( 0, 1) );
		tempoLbl = new JLabel("00:00:00", JLabel.CENTER );
		tempoLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( tempoLbl );

		distanciaLbl = new JLabel("0m", JLabel.CENTER );
		distanciaLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( distanciaLbl );

		autonomiaLbl = new JLabel("0m", JLabel.CENTER );
		autonomiaLbl.setFont( new Font("Roman", Font.BOLD, 10 ) );
		emUsoPanel.add( autonomiaLbl );

		btMover = new JButton( "Andar");
		btMover.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				processarMover();				
			}
		});
		emUsoPanel.add( btMover );
		return emUsoPanel;
	}

	

}

