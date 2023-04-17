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
import tiu.core.User;

public class UserWindow extends JDialog {

	/** window serial number */
	private static final long serialVersionUID = 1L;
	
	// the central which the user app communicates with
	private CentralTIU central;
	
	// the user associated with this app
	private User user;
	
	// graphic elements
	private JLabel timeLbl, costLbl;
	private Timer timer; 
	private static final String INACTIVE = "Inativo";
	private static final String IN_USE = "EmUso";

	/** Creates the window that simulates the user app
	 * @param owner main window
	 * @param c central which the user app communicates with
	 * @param u user associated with this app
	 * @throws HeadlessException
	 */
	public UserWindow( JFrame owner, CentralTIU c, User u ) throws HeadlessException {
		// TODO replace "USER NAME" with the full user name
		super( owner, "USER NAME" );
		central = c;
		user = u;
		setupInterface();
	}

	/** called when the unlock button is pressed
	 */
	protected void unlockScooter() {
		// ask for the scooter code
		String codigo = JOptionPane.showInputDialog("Scooter code?");
		
		// ask the central to proceed with the rental 
		int res = central.doRental( user, codigo);
		if( res != CentralTIU.OK ) {
			switch( res ) {
			case CentralTIU.SCOOTER_UNKNOWN:
				JOptionPane.showMessageDialog( this, "Is the code right? Unknown scooter!");
				return;
			case CentralTIU.SCOOTER_CHARGING:
				JOptionPane.showMessageDialog( this, "Scooter is charging and thus not available!");
				return;	
			case CentralTIU.SCOOTER_MOVING:
			case CentralTIU.SCOOTER_IN_USE:
				JOptionPane.showMessageDialog( this, "Scooter is already being rented!");
				return;	
			case CentralTIU.SCOOTER_UNAVAILABLE:
				JOptionPane.showMessageDialog( this, "Scooter under maintenance!");
				return;
			}
			// is case we forgot a error condition, here's a saveguard
			JOptionPane.showMessageDialog( this, "System error, please try again later!");
			return;
		}		
		// the rental process was successful
		changeToInUse();		
	}
	
	/** called when the terminate rental button is pressed
	 */
	protected void terminateRental() {
		// ask the central to terminate the rental process
		// TODO null must be replaced with the correct value
		int res = central.terminateRental( null );
		if( res != CentralTIU.OK ) {
			switch( res ) {
			case CentralTIU.SCOOTER_MOVING:
				JOptionPane.showMessageDialog( this, "Please, stop and park the scooter!");
				return;
			}
			// is case we forgot a error condition, here's a safeguard
			JOptionPane.showMessageDialog( this, "System error, please try again later!");
			return;
		}		
		
		// the rental process was terminated
		mudaParaInativo();		
	}

	/** called to update the info in the window. Is is called once per second
	 */
	protected void atualizarDisplay() {
		// TODO fill with the correct values
		Duration duration = null;
		float cost = 0;
		
		// update the interface
		updateTime( duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart() );
		updateCost( cost );
	}
	
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	
	/** update the time in the display 
	 * @param horas hours
	 * @param minutos minutes
	 * @param segundos seconds
	 */
	protected void updateTime( int horas, int minutos, int segundos ) {
		timeLbl.setText( String.format("%02d:%02d:%02d", horas, minutos, segundos));
	}
	
	/** updates the cost in the display
	 * @param cost in euros
	 */
	protected void updateCost( float cost ) {
		costLbl.setText( String.format("%.2f€", cost));
	}
	
	/** changes the interface to the rental display
	 */
	private void changeToInUse() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, IN_USE);
		timer.start();
	}
	
	/** changes the interface to the idle display
	 */
	private void mudaParaInativo() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, INACTIVE);
		timer.stop();
	}
	
	/** inicializa a interface gráfica
	 */
	private void setupInterface() {
		setSize( 150, 150 );
		JPanel inativoPanel = setupInterfaceInativo();	
		
		JPanel emUsoPanel = setupInterfaceEmUSo();
		
		JPanel cardPanel = new JPanel( new CardLayout() );
		cardPanel.add( inativoPanel, INACTIVE );
		cardPanel.add( emUsoPanel, IN_USE);
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
		JButton btDesbloquear = new JButton( "Unlock" );
		btDesbloquear.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				unlockScooter( );
				
			}
		});
		inativoPanel.add( btDesbloquear );
		return inativoPanel;
	}
	
	private JPanel setupInterfaceEmUSo() {
		JPanel emUsoPanel = new JPanel( new GridLayout( 0, 1) );
		timeLbl = new JLabel("00:00:00", JLabel.CENTER );
		timeLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( timeLbl );

		costLbl = new JLabel("0.00€", JLabel.CENTER );
		costLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( costLbl );

		JButton btTerminar = new JButton( "Finish");
		btTerminar.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				terminateRental();				
			}
		});
		emUsoPanel.add( btTerminar );
		return emUsoPanel;
	}
}
