package tiu.app;

import tiu.core.*;

/** This class is the one responsible for creating the system's 
 * components and preparing the initial configuration. 
 */
public class Start {
	
	public static void main(String[] args) {
		// create the central and do its setup
		CentralTIU central = setupSystem();

		// create main window
		MainWindow jp = new MainWindow( central );
		jp.setLocation( 20, 380 );
		jp.setVisible( true );
		
		// TODO create user windows (one for each user)
		int i=0;
		for( int k=0; k < 1; k++ ) {
			// TODO replace null with the correct value
			UserWindow ju = new UserWindow( jp, central, null );
			ju.setLocation( 20 + i*(ju.getWidth()+10), 20);
			ju.setVisible( true );
			i++;
		}

		
		// TODO create scooter windows (one for each scooter)
		i=0;
		for( int k = 0; k < 1; k++ ) {
			// TODO replace null with the correct value
			ScooterWindow jt = new ScooterWindow( jp, null );
			jt.setLocation( 20 + i*(jt.getWidth()+10), 200);
			jt.setVisible( true );
			i++;
		}	
	}

	/** creates the central and configures it to the test configuration
	 * @return the created central 
	 */
	private static CentralTIU setupSystem() {
		CentralTIU central = new CentralTIU();
		
		// TODO create users
		
		// TODO create scooters
		
		// TODO setup remaining range for the scooters
	
		return central;
	}
}
