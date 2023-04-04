package tiu.app;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import tiu.core.CentralTIU;
import tiu.core.Utente;

public class JanelaUtente extends JDialog {

	/** número de série da janela */
	private static final long serialVersionUID = 1L;
	
	// a central com que a aplicação de utente comunica
	private CentralTIU central;
	
	// o utente associado a esta aplicação
	private Utente utente;
	
	// elementos gráficos
	private JLabel tempoLbl, custoLbl;
	private Timer timer; 
	private static final String INATIVO = "Inativo";
	private static final String EM_USO = "EmUso";

	/** cria a aplicação do utente
	 * @param owner janela da aplicação principal
	 * @param c central com a qual a aplicação do utente comunica
	 * @param u utente associado a esta aplicação
	 * @throws HeadlessException
	 */
	public JanelaUtente( JFrame owner, CentralTIU c, Utente u ) throws HeadlessException {
		// TODO substituir "NOME DO UTILIZADOR" pelo valor correto
		super( owner, "NOME DO UTILIZADOR" );
		central = c;
		utente = u;
		setupInterface();
	}

	/** método chamado quando se pressiona o botão de desbloquear
	 */
	protected void desbloquearTrotinete() {
		// pedir o código da trotinete
		String codigo = JOptionPane.showInputDialog("Qual o código da trotinete?");
		
		// pedir para realizar o aluguer 
		int res = central.fazAluguer( utente, codigo);
		if( res != CentralTIU.OK ) {
			switch( res ) {
			case CentralTIU.TROTINETE_DESCONHECIDA:
				JOptionPane.showMessageDialog( this, "Inseriu bem o código? Trotinete Desconhecida!");
				return;
			case CentralTIU.TROTINETE_EM_CARGA:
				JOptionPane.showMessageDialog( this, "Trotinete está temporariamente indisponível, por estar em carga!");
				return;	
			case CentralTIU.TROTINETE_EM_ANDAMENTO:
			case CentralTIU.TROTINETE_EM_USO:
				JOptionPane.showMessageDialog( this, "Trotinete está alugada!");
				return;	
			case CentralTIU.TROTINETE_INDISPONIVEL:
				JOptionPane.showMessageDialog( this, "Trotinete temporariamente indisponível!");
				return;
			}
			// caso nos tenhamos esquecido de algum erro, fica aqui a salvaguarda
			JOptionPane.showMessageDialog( this, "Erro no sistema, tente mais tarde!");
			return;
		}		
		// se chegou aqui é porque o aluguer esteve bem
		mudaParaEmUso();		
	}
	
	/** método chamado quando se pressiona o
	 * botão de terminar o aluguer
	 */
	protected void terminarAluguer() {
		// pedir à central para terminar o aluguer
		// TODO null tem de ser substituido
		int res = central.terminarAluguer( null );
		if( res != CentralTIU.OK ) {
			switch( res ) {
			case CentralTIU.TROTINETE_EM_ANDAMENTO:
				JOptionPane.showMessageDialog( this, "Por favor, pare e estacione a Trotinete!");
				return;
			}
			// caso nos tenhamos esquecido de algum erro, fica aqui a salvaguarda
			JOptionPane.showMessageDialog( this, "Erro no sistema, tente mais tarde!");
			return;
		}		
		
		// se chegou aqui é porque terminou o aluguer
		mudaParaInativo();		
	}

	/** método chamado sempre que é preciso atualizar a
	 * informação na aplicação (1 vez por segundo)
	 */
	protected void atualizarDisplay() {
		// TODO preencher as variáveis com os dados corretos
		Duration duracao = null;
		float custo = 0;
		
		// atualizar a interface
		atualizarTempo( duracao.toHoursPart(), duracao.toMinutesPart(), duracao.toSecondsPart() );
		atualizarCusto( custo );
	}
	
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	
	/** atualiza o tempo na janela
	 * @param horas as horas
	 * @param minutos os minutos
	 * @param segundos os segundos
	 */
	protected void atualizarTempo( int horas, int minutos, int segundos ) {
		tempoLbl.setText( String.format("%02d:%02d:%02d", horas, minutos, segundos));
	}
	
	/** atualiza o custo na janela
	 * @param custo o custo em euros
	 */
	protected void atualizarCusto( float custo ) {
		custoLbl.setText( String.format("%.2f€", custo));
	}
	
	/** muda a interface para o modo de em uso
	 */
	private void mudaParaEmUso() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, EM_USO);
		timer.start();
	}
	
	/** muda a interface para o modo inativo
	 */
	private void mudaParaInativo() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, INATIVO);
		timer.stop();
	}
	
	/** inicializa a interface gráfica
	 */
	private void setupInterface() {
		setSize( 150, 150 );
		JPanel inativoPanel = setupInterfaceInativo();	
		
		JPanel emUsoPanel = setupInterfaceEmUSo();
		
		JPanel cardPanel = new JPanel( new CardLayout() );
		cardPanel.add( inativoPanel, INATIVO );
		cardPanel.add( emUsoPanel, EM_USO);
		setContentPane(cardPanel);
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarDisplay();
			}
		}  );
	}


	private JPanel setupInterfaceInativo() {
		JPanel inativoPanel = new JPanel( new GridLayout(0,1));
		JButton btDesbloquear = new JButton( "Desbloquear" );
		btDesbloquear.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desbloquearTrotinete( );
				
			}
		});
		inativoPanel.add( btDesbloquear );
		return inativoPanel;
	}
	
	private JPanel setupInterfaceEmUSo() {
		JPanel emUsoPanel = new JPanel( new GridLayout( 0, 1) );
		tempoLbl = new JLabel("00:00:00", JLabel.CENTER );
		tempoLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( tempoLbl );

		custoLbl = new JLabel("0.00€", JLabel.CENTER );
		custoLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( custoLbl );

		JButton btTerminar = new JButton( "Terminar");
		btTerminar.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				terminarAluguer();				
			}
		});
		emUsoPanel.add( btTerminar );
		return emUsoPanel;
	}

	

}
