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

import tiu.core.Rental;
import tiu.mobility.Scooter;

public class ScooterWindow extends JDialog {

	/** window serial number */
	private static final long serialVersionUID = 1628477706244793006L;

	// The scooter associated with this window 
	private Scooter scooter;

	// Is the interface with the inactive or active display?
	private boolean isInUse = false;

	// graphic elements 
	private JLabel timeLbl, distanceLbl, rangeLbl;
	private Timer timer; 
	private JButton moveBt;
	private static final String INACTIVE = "Inactive";
	private static final String IN_USE = "InUse";
	
	
	/** Creates a window that simulates a scooter
	 * @param owner main window
	 * @param s scooter that is simulated by this window
	 */
	public ScooterWindow( JFrame owner, Scooter s ) {
		// TODO replace "SCOOTER CODE" for its correct value (DONE)
		super( owner, s.getId() );
		scooter = s;
		setupInterface( s.getId() );
	}

	/** Called to update the interface (it is called once per second)
	 */
	public void atualizarInterface( ) {
		// call the method that simulates the scooter
		scooter.update();
		
		if( scooter.inUse() ) {
			// if the scooter started to be in use  
			// we need to change the interface accordingly
			if( !isInUse )
				changeToInUse();
			
			// TODO put the correct values into the variables (DONE)
			Duration duration = scooter.getCurrentRental().getDuration();
			int distance = scooter.getCurrentRental().getDistance(); 
			int remainingRange = scooter.getRemainingAutonomy();

			// update the interface
			updateTime( duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart() );
			updateDistance( distance, remainingRange );
		}			
		else if( isInUse )
			changeToIdle();
	}
	
	/** Called when the move/stop button is pressed
	 */
	protected void processarMover() {
		// TODO if it is moving then it must stop (and change the button to move) (DONE)
		if( scooter.isMoving() ) {
			// ...
			scooter.stop();
			moveBt.setText( "Move" );
		} else {
		// TODO otherwise it must move (and change the button to stop) (DONE)
			// ...
			scooter.move();
			moveBt.setText( "Stop" );
		}			
	}
	
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  
	// NO CHANGES ARE REQUIRED BELOW THIS POINT  


	/** Updates the rental time in the window 
	 * @param horas hours
	 * @param minutos minutes
	 * @param segundos seconds
	 */
	protected void updateTime( int horas, int minutos, int segundos ) {
		timeLbl.setText( String.format("%02d:%02d:%02d", horas, minutos, segundos));
	}
	
	/** update distances int he window
	 * @param dist distance of travel 
	 * @param auto remaining range
	 */
	protected void updateDistance( int dist, int auto ) {
		distanceLbl.setText( String.format("%dm", dist));
		rangeLbl.setText( String.format("%dm", auto));
	}

	/** changes the display to simulate a scooter in use  
	 */
	private void changeToInUse() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, IN_USE);
		isInUse = true;
	}

	/** changes the display to simulate an idle scooter 
	 */
	private void changeToIdle() {
		Container cpane = getContentPane();
		CardLayout cl = (CardLayout)cpane.getLayout();
		cl.show( cpane, INACTIVE);
		isInUse = false;
	}
	
	/** inicializa a interface da janela
	 */
	private void setupInterface( String codigo ) {
		setSize( 150, 150 );
		setResizable( false );
		JPanel inativoPanel = setupInterfaceInativo( codigo );	
		
		JPanel emUsoPanel = setupInterfaceEmUSo();
		
		JPanel cardPanel = new JPanel( new CardLayout() );
		cardPanel.add( inativoPanel, INACTIVE );
		cardPanel.add( emUsoPanel, IN_USE);
		setContentPane(cardPanel);
		
		timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarInterface();				
			}
		});
		timer.start();
	}

	private JPanel setupInterfaceInativo( String code ) {
		JPanel inativoPanel = new JPanel( new GridLayout( 0,1) );
		inativoPanel.add( new JLabel("<html><center>Ublock<br>using<br> " + code + "</html>", JLabel.CENTER) );
		return inativoPanel;
	}
	
	private JPanel setupInterfaceEmUSo() {
		JPanel emUsoPanel = new JPanel( new GridLayout( 0, 1) );
		timeLbl = new JLabel("00:00:00", JLabel.CENTER );
		timeLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( timeLbl );

		distanceLbl = new JLabel("0m", JLabel.CENTER );
		distanceLbl.setFont( new Font("Roman", Font.BOLD, 20 ) );
		emUsoPanel.add( distanceLbl );

		rangeLbl = new JLabel("0m", JLabel.CENTER );
		rangeLbl.setFont( new Font("Roman", Font.BOLD, 10 ) );
		emUsoPanel.add( rangeLbl );

		moveBt = new JButton( "Move");
		moveBt.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				processarMover();				
			}
		});
		emUsoPanel.add( moveBt );
		return emUsoPanel;
	}

	

}

