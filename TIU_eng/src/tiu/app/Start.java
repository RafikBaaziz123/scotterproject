package tiu.app;

import tiu.core.*;
import tiu.mobility.Scooter;

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
		
		// TODO create user windows (one for each user) (DONE)
		int i=0;
		for( int k=0; k < central.getUsersList().size(); k++ ) {
			// TODO replace null with the correct value (DONE)
			UserWindow ju = new UserWindow( jp, central, central.getUsersList().get(i) );
			ju.setLocation( 20 + i*(ju.getWidth()+10), 20);
			ju.setVisible( true );
			i++;
		}

		
		// TODO create scooter windows (one for each scooter) (DONE)
		i=0;
		for( int k = 0; k < central.getScootersList().size(); k++ ) {
			// TODO replace null with the correct value (DONE)
			ScooterWindow jt = new ScooterWindow( jp, central.getScootersList().get(i) );
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
		
		// TODO create users (DONE)
		User u1 = new User( "fsergio@ipcb.pt", "Sérgio Barbosa" );
		central.addUser(u1);
		User u2 = new User( "jojo89@g.com", "João José Silva" );
		central.addUser(u2);
		User u3 = new User( "codeguru@guru.com", "Harry Hacker" );
		central.addUser(u3);
		User u4 = new User( "aziz.zina@ipcbcampus.pt", "Aziz Zina" );
		central.addUser(u4);
		
		// TODO create scooters (DONE)
		Scooter s1 = new Scooter( "PooTr1", 20000, 1000, 5 );
		central.addScooter(s1);
		Scooter s2 = new Scooter( "PooTr2", 20000, 20000, 5 );
		central.addScooter(s2);
		Scooter s3 = new Scooter( "PooGo", 30000, 8000, 6 );
		central.addScooter(s3);
		Scooter s4 = new Scooter( "Poo20", 30000, 30000, 7 );
		central.addScooter(s4);
		
		
		// TODO setup remaining range for the scooters
	
		return central;
	}
}
