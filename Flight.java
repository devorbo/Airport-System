import java.util.*;

public class Flight extends Thread {
	private int numFlight;
	private Airport airDepart;
	private Airport airLand;
	
	public Flight(int numFlight,Airport airDepart,Airport airLand) {
		this.numFlight = numFlight;
		this.airDepart = airDepart;
		this.airLand = airLand;
	}
	
	@Override
	public void run() {
		//flight simulation
		System.out.println(airDepart.getName()+" to "+airLand.getName()+" Flight "+numFlight);
		Random rnd = new Random();
		int rndSec = (rnd.nextInt(4)+2)*100;
		int routeDepart=this.airDepart.depart(numFlight);
		//depart time
		try {
			sleep(rndSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.airDepart.freeRunway(numFlight,routeDepart);//free route
		//flight time
		rndSec = (rnd.nextInt(4)+2)*100;//between 200 and 500 milliseconds
		try {
			sleep(rndSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int routeLand=this.airLand.land(numFlight);
		//land time
		rndSec=(rnd.nextInt(4)+2)*100;
		try {
			sleep(rndSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.airLand.freeRunway(numFlight, routeLand);
	}
}
