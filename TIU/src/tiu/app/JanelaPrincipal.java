package tiu.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tiu.core.Aluguer;
import tiu.core.CentralTIU;
import tiu.core.Utente;
import tiu.mobilidade.Trotinete;

public class JanelaPrincipal extends JFrame {

	/** versão da aplicação*/
	private static final long serialVersionUID = 2406490016666309649L;

	// a central usada pela janela
	private CentralTIU central;

	// o utente atualmente a ser selecionado
	private Utente utenteSel;

	// elementos gráficos
	private DefaultTableModel modeloTrotUsadas, modeloTrotNaoUsadas;
	private DefaultTableModel modeloUtentes, modeloAlugueres;
	private JLabel userLbl, nomeLbl;
	
	/** cria a janela, indicando qual a central a ela associada
	 * @param c central do TIU a ser usada 
	 */
	public JanelaPrincipal( CentralTIU c) {
		super( "TIU by EST" );
		central = c;
		setupInterface();	
		
		// TODO para cada utente do sistema colocar a informação na tabela
		for( int i = 0; i < 0; i++ )
			atualizarUtente( null );
	}
	
	/** Método que é chamado sempre que é preciso atualizar a 
	 * informação na janela
	 */
	protected void atualizarInterface() {
		// TODO para cada trotinete é preciso ver se está em uso em inativa
		//      para saber em qual das tabelas colcoar a trotinete
		for( int i=0; i < 0; i++ ) {
			Trotinete t = null;
			if( t.emUso() )
				atualizarEmUso( t );
			else
				atualizarInativa( t );
		}
		
		// se houver um utente selecionado, atualizar a sua informação 
		if( utenteSel != null ) 
			atualizarInfoUtenteSel( );
	}

	/** método que atualiza a tabela das trotinetes em uso com
	 * a informação de uma trotinete
	 * @param t a trotine a acrescentar à tabela das em uso
	 */
	private void atualizarEmUso(Trotinete t ) {
		// TODO preencher as variáveis com os dados corretos
		String codigo = "Codigo";
		int autonomia = 20000;
		int velocidade = 7;
		Aluguer alu = null;
		LocalDateTime inicio = null;
		String dh = getDataHora( inicio );
		float custo = 0;
		int dist = 0;
		String movendo = t.emAndamento()? "Andar": "Parada";
		
		// atualizar a interface
		Object dataColuna[] = {codigo, autonomia, velocidade, dh, custo, dist, movendo };
		modeloTrotUsadas.addRow(dataColuna);
		
	}

	/** método que atualiza a tabela das trotinetes inativas com
	 * a informação de uma trotinete
	 * @param t a trotine a acrescentar à tabela das inativas
	 */
	private void atualizarInativa(Trotinete t) {
		// TODO preencher as variáveis com os dados corretos
		String cod = "Código";
		int autonomia = 8500;
		int velocidade = 6;

		// atualizar a interface
		Object data[] = {cod, autonomia, velocidade, t.emCarga(), t.estaIndisponivel() };
		modeloTrotNaoUsadas.addRow(data);
	}

	/** método chamado quando o utilizador pretende colocar/retirar
	 * uma trotinete em carga
	 * @param trotCod o código da trotinete a por/retirar em carga
	 * @param por true se é para por, false se for para tirar
	 */
	protected void porTrotineteEmCarga(String trotCod, boolean por) {
		// TODO implementar este método
	}

	/** método chamado quando o utilizador pretende colocar/retirar
	 * uma trotinete da manutenção
	 * @param trotCod o código da trotinete a por/retirar da manutenção
	 * @param por true se é para por, false se for para tirar
	 */
	protected void porTrotineteInativa(String trotCod, boolean por) {
		// TODO implementar este método
	}
	
	/** método chamado quando é preciso atualizar a informação
	 * de um utente na tabela
	 * @param u o utente a ser atualizado
	 */
	private void atualizarUtente(Utente u) {
		// TODO preencher as variáveis com os dados corretos
		String user = "user name";
		String nome = "nome completo";
		
		// atualizar a interface
		Object data[] = {user, nome };
		modeloUtentes.addRow(data);	
	}
	
	
	/** método que é chamado quando é necessário atualizar 
	 * a informação do utente que está atualmente selecionado
	 */
	private void atualizarInfoUtenteSel() {
		// TODO preencher as variáveis com os dados corretos
		String userName = "user name";
		String nome = "nome completo";

		// atualizar a interface
		modeloAlugueres.setRowCount( 0 );
		userLbl.setText( userName );
		nomeLbl.setText( nome );
		
		// TODO para cada aluguer do utente é preciso mostrar na tabela
		for( int i = 0; i < 0; i++ )
			mostrarALuguer( null );
	}
	
	/** método chamado quando o utilizado seleciona um utente na lista
	 * @param user o user name do utente selecionado
	 */
	protected void selecionarUtente(String user) {
		// TODO atualizar o utente selecionado
		utenteSel = null;
	}

	/** método chamado quando o utilizador
	 * pressiona o botão de criar utente
	 */
	private void adicionarUtente() {
		// pedir os dados do utente
		String dados[] = pedirDadosUtente();
		if( dados == null )
			return;
		
		String userName = dados[0];
		String nome = dados[1];

		// TODO verificar a validade dos dados:
		//      user name tem de ser único e não pode ser null
		//      nome não pode ser vazio ou null
		
		// TODO criar o utente e adicioná-lo ao sistema
		
		// TODO atualizar a interface com o novo utente
		atualizarUtente( null );
	}
	
	/** método chamado sempre que é preciso mostrar os dados
	 * de um aluguer do utente
	 * @param a o aluguer a mostrar
	 */
	private void mostrarALuguer(Aluguer a) {
		// TODO preencher as variáveis com os dados corretos
		String dataInicio = getDataHora( null /* inicio do aluguer */ );
		String dataFim = getDataHora( null /* fim do aluguer */ );
		int distancia = 0;
		float custo = 0;
		String codigoTrotinete = "Código";
		
		// atualizar a interface
		Object rowData[] = { dataInicio, dataFim, distancia, custo, codigoTrotinete };
		modeloAlugueres.addRow( rowData );		
	}

	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	// DAQUI EM DIANTE, NÃO É PRECISO ALTERAR NADA
	
	/** Transforma uma LocalDateTime numa String,
	 * pronta a ser apresentada na tabela
	 * @param ld tempo a ser convertido
	 * @return uma string com a informação de data e hora
	 */
	private String getDataHora(LocalDateTime ld ) {
		String data = String.format( "%2d/%2d/%2d", ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear() ); 
		String hora = String.format( "%02d:%02d:%02d", ld.getHour(), ld.getMinute(), ld.getSecond() ); 
		return data + " " + hora;
	}

	/** pede os dados do utente a ser criado
	 * @return um array com o userName e o nome introduzidos, se 
	 * foi pressinado o botão OK, null se for pressionado o botão CANCEL
	 */
	private String[] pedirDadosUtente() {
		JTextField userName = new JTextField(10);
		JTextField nome = new JTextField(25);
		JPanel myPanel = new JPanel( new GridLayout(0,1));
		myPanel.add(new JLabel("User name:"));
		myPanel.add( userName );
		myPanel.add(new JLabel("Nome:"));
		myPanel.add( nome );

		int res = JOptionPane.showConfirmDialog(null, myPanel, 
				"Introduza dados para novo utente", JOptionPane.OK_CANCEL_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			String []valores = { userName.getText(), nome.getText() }; 
			return valores;
		} else {
			return null;
		}		
	}
	
	/** inicializa a interface da janela
	 */
	private void setupInterface() {
		setSize( 1000, 350 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel trotPanel = setupInterfaceTrotinetes();
		JPanel utentePanel = setupInterfaceUtentes();
		
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Trotinetes", trotPanel );
		tp.addTab("Utentes", utentePanel );
		getContentPane().add( tp, BorderLayout.CENTER );
		
		Timer t = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloTrotNaoUsadas.setRowCount( 0 );
				modeloTrotUsadas.setRowCount( 0 );
				modeloAlugueres.setRowCount( 0 );
				//modeloUtentes.setRowCount( 0 );
				atualizarInterface();
			}
		});
		t.start();
	}

	private JPanel setupInterfaceTrotinetes() {
		JPanel panel = new JPanel( new GridLayout( 0, 1) );
		
		String nomesNaoUsadas[] = {"Código", "Autonomia", "Velocidade", "Carregar", "Manutenção" };
		modeloTrotNaoUsadas = new DefaultTableModel( nomesNaoUsadas, 3 ){
		      public Class getColumnClass(int column) {
		    	  Object o = getValueAt(0, column); 
		          return o != null? o.getClass(): Object.class; 
		        }
		      
		      @Override
		    public boolean isCellEditable(int row, int column) {
		    	if( column < 3 )
		    		return false;
		    	return super.isCellEditable(row, column);
		    }
		};
		JTable naoUsadasTb = new JTable( modeloTrotNaoUsadas );
		naoUsadasTb.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        int filaSel = naoUsadasTb.rowAtPoint(e.getPoint());
		        int colunaSel = naoUsadasTb.columnAtPoint(e.getPoint());
		        if( colunaSel == 3 ) {
		        	String trotCod = (String)modeloTrotNaoUsadas.getValueAt(filaSel, 0);
		        	boolean por = (Boolean)modeloTrotNaoUsadas.getValueAt(filaSel, 3);
		        	porTrotineteEmCarga( trotCod, por );
		        }
		        if( colunaSel == 4 ) {
		        	String trotCod = (String)modeloTrotNaoUsadas.getValueAt(filaSel, 0);
		        	boolean por = (Boolean)modeloTrotNaoUsadas.getValueAt(filaSel, 4);
		        	porTrotineteInativa( trotCod, por );
		        }
		 	}
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for( int i=0; i < 3; i++ )
			naoUsadasTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		JScrollPane pnu = new JScrollPane( naoUsadasTb );
		pnu.setBorder( BorderFactory.createTitledBorder("Não usadas"));
		panel.add( pnu );
		
		String nomesUsadas[] = {"Código", "Autonomia", "Velocidade", "Início", "Custo", "Distância", "Mover" };
		modeloTrotUsadas = new DefaultTableModel( nomesUsadas, 3 );
		JTable usadasTb = new JTable( modeloTrotUsadas );		
		for( int i=0; i < usadasTb.getColumnCount(); i++ )
			usadasTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		JScrollPane pu = new JScrollPane( usadasTb );
		pu.setBorder( BorderFactory.createTitledBorder("Usadas"));
		panel.add( pu );
		return panel;
	}

	
	private JPanel setupInterfaceUtentes() {
		JPanel panel = new JPanel( new BorderLayout() );
		
		JPanel pe = setupLadoEsquerdo();
		panel.add( pe );
		JPanel pd = setupLadoDireito();
		panel.add( pd );
		panel.add( pe, BorderLayout.WEST );
		panel.add( pd, BorderLayout.CENTER );
		return panel;
	}

	private JPanel setupLadoDireito() {
		SpringLayout layout = new SpringLayout();
		JPanel userPanel = new JPanel( layout );

		userLbl = new JLabel( "UserName" );
		userLbl.setFont( new Font("Roman", Font.BOLD, 18 ) );
		nomeLbl = new JLabel( "Nome Completo" );
		nomeLbl.setFont( new Font("Roman", Font.BOLD, 14 ) );
		userPanel.add( userLbl );
		userPanel.add( nomeLbl );
		
		String alugNomes[] = {"Inicio", "Fim", "Distância", "Custo", "Trotinete" };
		modeloAlugueres = new DefaultTableModel( alugNomes, 0 );
		JTable alugsTb = new JTable( modeloAlugueres );	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for( int i=0; i < alugsTb.getColumnCount(); i++ )
			alugsTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		JScrollPane sp = new JScrollPane( alugsTb );
		sp.setBorder( BorderFactory.createTitledBorder("Alugueres passados"));
		userPanel.add( sp );
		userPanel.setBorder( BorderFactory.createTitledBorder("Dados do Utente"));
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userLbl, 0, SpringLayout.HORIZONTAL_CENTER, userPanel );
		layout.putConstraint(SpringLayout.NORTH, userLbl, 5, SpringLayout.NORTH, userPanel );
	
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nomeLbl, 0, SpringLayout.HORIZONTAL_CENTER, userPanel );
		layout.putConstraint(SpringLayout.NORTH, nomeLbl, 5, SpringLayout.SOUTH, userLbl );

		layout.putConstraint(SpringLayout.WEST, sp, 0, SpringLayout.WEST, userPanel );
		layout.putConstraint(SpringLayout.EAST, sp, 0, SpringLayout.EAST, userPanel );
		layout.putConstraint(SpringLayout.NORTH, sp, 5, SpringLayout.SOUTH, nomeLbl );
		layout.putConstraint(SpringLayout.SOUTH, sp, 5, SpringLayout.SOUTH, userPanel );
		return userPanel;
	}

	private JPanel setupLadoEsquerdo() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		panel.setPreferredSize( new Dimension(300,50) );
		String nomesUtentes[] = {"User", "Nome" };
		modeloUtentes = new DefaultTableModel( nomesUtentes, 0 );
		JTable utentesTb = new JTable( modeloUtentes );
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for( int i=0; i < utentesTb.getColumnCount(); i++ )
			utentesTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		utentesTb.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		utentesTb.setRowSelectionAllowed( true );
		utentesTb.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if( e.getValueIsAdjusting() )
					return;
				int sel = ((DefaultListSelectionModel)e.getSource()).getMinSelectionIndex();
				String user = (String)modeloUtentes.getValueAt(sel, 0);
				selecionarUtente( user );
				atualizarInterface();
			}
		});
		
		JScrollPane pu = new JScrollPane( utentesTb );
		pu.setBorder( BorderFactory.createTitledBorder("Utentes"));
		panel.add( pu );
		
		JButton addBt = new JButton( "Novo Utente" );
		addBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarUtente();
			}
		});
		panel.add( addBt );
		
		layout.putConstraint( SpringLayout.EAST, pu, 0, SpringLayout.EAST, panel );
		layout.putConstraint( SpringLayout.NORTH, pu, 0, SpringLayout.NORTH, panel );
		layout.putConstraint( SpringLayout.WEST, pu, 0, SpringLayout.WEST, panel );

		layout.putConstraint( SpringLayout.EAST, addBt, 0, SpringLayout.EAST, panel );
		layout.putConstraint( SpringLayout.SOUTH, pu, 0, SpringLayout.NORTH, addBt );
		layout.putConstraint( SpringLayout.SOUTH, addBt, 0, SpringLayout.SOUTH, panel );
		layout.putConstraint( SpringLayout.WEST, addBt, 0, SpringLayout.WEST, panel );
		
		
		return panel;
	}
}
