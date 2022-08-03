public class Airport {
	private String name;
	private Controller con;
	
	public Airport(String airportName,int numRoutes) {
		System.out.println("New Airport "+airportName+" num routes "+numRoutes);
		name = airportName;
		this.con = new Controller(numRoutes);//controller for this airport
	}
	public String getName() {
		return this.name;
	}
	public Controller getCon() {
		return con;
	}
	
	public int depart(int numFlight) {
		System.out.println("Airport "+this.name+" Flight "+numFlight+" depart");
		return con.routeAvailable(numFlight);//get a route to depart from this airport
	}
	
	public int land(int numFlight) {
		System.out.println("Airport "+this.name+" Flight "+numFlight+" land");
		return con.routeAvailable(numFlight);//get a route to land from this airport
	}
	public void freeRunway(int numFlight,int route) {
		System.out.println("Airport "+this.name+" Flight "+numFlight+" route "+route+" free");
		con.add(numFlight, route);//free the route
	}

}
