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

import tiu.core.Rental;
import tiu.core.CentralTIU;
import tiu.core.User;
import tiu.mobility.Scooter;

public class MainWindow extends JFrame {

	/** app version */
	private static final long serialVersionUID = 2406490016666309649L;

	// central used by this window
	private CentralTIU central;

	// current selected user 
	private User selectedUser;

	// graphic elements
	private DefaultTableModel usedScooterModel, idleScootersModel;
	private DefaultTableModel userModel, rentalModel;
	private JLabel userLbl, nameLbl;
	
	/** Creates the window
	 * @param c central to be used 
	 */
	public MainWindow( CentralTIU c) {
		super( "TIU by EST" );
		central = c;
		setupInterface();	
		
		// TODO for each user, place the his/her information in the table
		for( int i = 0; i < 0; i++ )
			updateUserData( null );
	}
	
	/** Method that is called whenever the user interface needs to be updated 
	 */
	protected void updateInterface() {
		// TODO for each scooter we need to see if it is being used or
		//      is inactive to decide in which table to put it
		for( int i=0; i < 0; i++ ) {
			Scooter s = null;
			if( s.inUse() )
				updateInUse( s );
			else
				updateIdle( s );
		}
		
		// if there is a selected user, update his info 
		if( selectedUser != null ) 
			updateSelectedUserInfo( );
	}

	/** This method updates the used scooters table with
	 * a scooter
	 * @param s the scooter to add to the table
	 */
	private void updateInUse( Scooter s ) {
		// TODO put the correct values on the variables
		String code = "Code";
		int range = 20000;
		int speed = 7;
		Rental r = null;
		LocalDateTime begin = null;
		String dh = getDateHour( begin );
		float cost = 0;
		int dist = 0;
		String moving = s.isMoving()? "Moving": "Stopped";
		
		// update the interface
		Object dataColumn[] = {code, range, speed, dh, cost, dist, moving };
		usedScooterModel.addRow(dataColumn);
		
	}

	/** method that updates the idle scooter table with a scooter
	 * @param s the scooter to add to the table
	 */
	private void updateIdle(Scooter s) {
		// TODO put the correct values on the variables
		String cod = "code";
		int range = 8500;
		int speed = 6;

		// updates the interface
		Object data[] = {cod, range, speed, s.isCharging(), s.isUnavailable() };
		idleScootersModel.addRow(data);
	}

	/** method called when a scooter is put to charge or removed from charge
	 * @param scooterCode the scooter code of the scooter to charge
	 * @param charging true if is to charge, or false if is to remove from charging
	 */
	protected void putScooterCharging(String scooterCode, boolean charging) {
		// TODO implement this method
	}

	/** method called when a scooter is put under (or removed from) maintenance
	 * @param scooterCode the scooter code 
	 * @param charging true is is to charge, or false if is to remove from charging
	 */
	protected void putScooterUnderMaintenance(String scooterCode, boolean maintain) {
		// TODO implement this method
	}
	
	/** Called when is it necessary to update user data in the user table
	 * @param u user info to be updated
	 */
	private void updateUserData(User u) {
		// TODO put the correct values in the variables
		String userName = "username";
		String name = "full name";
		
		// update the interface
		Object data[] = {userName, name };
		userModel.addRow(data);	
	}
	
	
	/** method called to update the selected user info 
	 */
	private void updateSelectedUserInfo() {
		// TODO put the correct values in the variables
		String userName = "username";
		String name = "full name";

		// update the interface
		rentalModel.setRowCount( 0 );
		userLbl.setText( userName );
		nameLbl.setText( name );
		
		// TODO for each rental update the table
		for( int i = 0; i < 0; i++ )
			displayRental( null );
	}
	
	/** called when a user is selected 
	 * @param user the username of the selected user
	 */
	protected void userSelection(String user) {
		// TODO change the selected user
		selectedUser = null;
	}

	/** Called when the "New user" button is pressed
	 */
	private void createUser() {
		// ask for user data
		String data[] = askUserData();
		if( data == null )
			return;
		
		String username = data[0];
		String name = data[1];

		// TODO check if data is correct
		//      username must be unique and non null
		//      name cannot be empty or null
		
		// TODO create the user and add it to the system
		
		// TODO update the interface with the new user
		updateUserData( null );
	}
	
	/** Called when a rental info must be updated in the rental table
	 * @param r the rental to add to the table
	 */
	private void displayRental(Rental r) {
		// TODO put the correct values in the variables
		String start = getDateHour( null /* rental start date */ );
		String end = getDateHour( null /* rental finish date */ );
		int distance = 0;
		float cost = 0;
		String scooterCode = "Code";
		
		// update the interface
		Object rowData[] = { start, end, distance, cost, scooterCode };
		rentalModel.addRow( rowData );		
	}

	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	
	/** Transforms a LocalDateTime into a String,
	 * ready to be displaied on a table cell
	 * @param ld time to be converted
	 * @return a string with the date and hour in the required format
	 */
	private String getDateHour(LocalDateTime ld ) {
		String date = String.format( "%2d/%2d/%2d", ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear() ); 
		String hour = String.format( "%02d:%02d:%02d", ld.getHour(), ld.getMinute(), ld.getSecond() ); 
		return date + " " + hour;
	}

	/** Asks for the data used to create a user
	 * @return an array with the entered username and full name, if 
	 * the ok button was pressed, null if the CANCEL button was pressed
	 */
	private String[] askUserData() {
		JTextField userName = new JTextField(10);
		JTextField nome = new JTextField(25);
		JPanel myPanel = new JPanel( new GridLayout(0,1));
		myPanel.add(new JLabel("Username:"));
		myPanel.add( userName );
		myPanel.add(new JLabel("Full name:"));
		myPanel.add( nome );

		int res = JOptionPane.showConfirmDialog(null, myPanel, 
				"New User Data", JOptionPane.OK_CANCEL_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			String []valores = { userName.getText(), nome.getText() }; 
			return valores;
		} else {
			return null;
		}		
	}
	
	/** initializes the window interface
	 */
	private void setupInterface() {
		setSize( 1000, 350 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel trotPanel = setupInterfaceTrotinetes();
		JPanel utentePanel = setupInterfaceUtentes();
		
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Scooters", trotPanel );
		tp.addTab("Users", utentePanel );
		getContentPane().add( tp, BorderLayout.CENTER );
		
		Timer t = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idleScootersModel.setRowCount( 0 );
				usedScooterModel.setRowCount( 0 );
				rentalModel.setRowCount( 0 );
				//modeloUtentes.setRowCount( 0 );
				updateInterface();
			}
		});
		t.start();
	}

	private JPanel setupInterfaceTrotinetes() {
		JPanel panel = new JPanel( new GridLayout( 0, 1) );
		
		String nomesNaoUsadas[] = {"Code", "Range", "Speed", "Charging", "Maintenance" };
		idleScootersModel = new DefaultTableModel( nomesNaoUsadas, 3 ){
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
		JTable naoUsadasTb = new JTable( idleScootersModel );
		naoUsadasTb.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        int filaSel = naoUsadasTb.rowAtPoint(e.getPoint());
		        int colunaSel = naoUsadasTb.columnAtPoint(e.getPoint());
		        if( colunaSel == 3 ) {
		        	String trotCod = (String)idleScootersModel.getValueAt(filaSel, 0);
		        	boolean por = (Boolean)idleScootersModel.getValueAt(filaSel, 3);
		        	putScooterCharging( trotCod, por );
		        }
		        if( colunaSel == 4 ) {
		        	String trotCod = (String)idleScootersModel.getValueAt(filaSel, 0);
		        	boolean por = (Boolean)idleScootersModel.getValueAt(filaSel, 4);
		        	putScooterUnderMaintenance( trotCod, por );
		        }
		 	}
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for( int i=0; i < 3; i++ )
			naoUsadasTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		JScrollPane pnu = new JScrollPane( naoUsadasTb );
		pnu.setBorder( BorderFactory.createTitledBorder("Idle"));
		panel.add( pnu );
		
		String nomesUsadas[] = {"Code", "Range", "speed", "Begin", "Cost", "Distance", "Moving" };
		usedScooterModel = new DefaultTableModel( nomesUsadas, 3 );
		JTable usadasTb = new JTable( usedScooterModel );		
		for( int i=0; i < usadasTb.getColumnCount(); i++ )
			usadasTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		JScrollPane pu = new JScrollPane( usadasTb );
		pu.setBorder( BorderFactory.createTitledBorder("In Use"));
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

		userLbl = new JLabel( "Username" );
		userLbl.setFont( new Font("Roman", Font.BOLD, 18 ) );
		nameLbl = new JLabel( "Full name" );
		nameLbl.setFont( new Font("Roman", Font.BOLD, 14 ) );
		userPanel.add( userLbl );
		userPanel.add( nameLbl );
		
		String alugNomes[] = {"Begin", "End", "Distance", "Cost", "Scooter" };
		rentalModel = new DefaultTableModel( alugNomes, 0 );
		JTable alugsTb = new JTable( rentalModel );	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for( int i=0; i < alugsTb.getColumnCount(); i++ )
			alugsTb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		JScrollPane sp = new JScrollPane( alugsTb );
		sp.setBorder( BorderFactory.createTitledBorder("Past rentals"));
		userPanel.add( sp );
		userPanel.setBorder( BorderFactory.createTitledBorder("User info"));
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userLbl, 0, SpringLayout.HORIZONTAL_CENTER, userPanel );
		layout.putConstraint(SpringLayout.NORTH, userLbl, 5, SpringLayout.NORTH, userPanel );
	
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLbl, 0, SpringLayout.HORIZONTAL_CENTER, userPanel );
		layout.putConstraint(SpringLayout.NORTH, nameLbl, 5, SpringLayout.SOUTH, userLbl );

		layout.putConstraint(SpringLayout.WEST, sp, 0, SpringLayout.WEST, userPanel );
		layout.putConstraint(SpringLayout.EAST, sp, 0, SpringLayout.EAST, userPanel );
		layout.putConstraint(SpringLayout.NORTH, sp, 5, SpringLayout.SOUTH, nameLbl );
		layout.putConstraint(SpringLayout.SOUTH, sp, 5, SpringLayout.SOUTH, userPanel );
		return userPanel;
	}

	private JPanel setupLadoEsquerdo() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		panel.setPreferredSize( new Dimension(300,50) );
		String nomesUtentes[] = {"User", "Name" };
		userModel = new DefaultTableModel( nomesUtentes, 0 );
		JTable utentesTb = new JTable( userModel );
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
				String user = (String)userModel.getValueAt(sel, 0);
				userSelection( user );
				updateInterface();
			}
		});
		
		JScrollPane pu = new JScrollPane( utentesTb );
		pu.setBorder( BorderFactory.createTitledBorder("User"));
		panel.add( pu );
		
		JButton addBt = new JButton( "New User" );
		addBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createUser();
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
