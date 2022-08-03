import java.util.LinkedList;
import java.util.Queue;

public class Controller {
	private Queue<Integer> available;
	private Queue<Integer> inUse;
	private Queue<Integer> flights;
	private int numRoutes;
	public Controller(int numRoutes) {
		this.numRoutes=numRoutes;
		available=new LinkedList<Integer>();
		for(int i=0;i<numRoutes;i++) {
			available.add(i);//add routes available to airport
		}
		inUse=new LinkedList<Integer>();
		flights=new LinkedList<Integer>();
	}
	public synchronized int routeAvailable(int numFlight) {
		//will wait to until there is an available rout
		while(available.isEmpty())
		{
			try {
				wait();
			}
			catch (InterruptedException e1){
				e1.printStackTrace();
			}
		}
		inUse.add(available.peek());// make route in use
		flights.add(numFlight);//flight will be add to the list of flights
		return available.poll();
	}
	public synchronized void add(int numFlight,int route) {//free route
		if(!flights.isEmpty() && !inUse.isEmpty()) {
			flights.remove(numFlight);
			inUse.remove(route);// remove route from use, will turn to be available
		}
		available.add(route);//add to available
		notifyAll();//wake up all threads that are waiting
	}
	
	public synchronized boolean waitForThreds() {
		if(available.isEmpty()) {
			try {
				wait();
			}
			catch (InterruptedException e1){
				e1.printStackTrace();
			}

		}		
		return !available.isEmpty() ;// there is an available route
	}
}
