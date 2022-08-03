import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program {
	public static void main(String[] args) {
		System.out.println("Airport and flights system");
		Random rnd=new Random();
		int numRoutes=3, numFlights=10;
		Airport[] air=new Airport[2];
		air[0]=new Airport("NJ", numRoutes);
		air[1]=new Airport("KF", numRoutes);
		
		try {
			ExecutorService e=Executors.newFixedThreadPool(numRoutes);//num of threads available 
			int j=0;
			while(air[0].getCon().waitForThreds() && air[1].getCon().waitForThreds() && j<numFlights)
			{
				int rndNum=rnd.nextInt(2);
				e.execute(new Flight((j+1)*1000+j,air[rndNum] , air[1-rndNum]));//pass in two arrays from the top
				j++;
			}
			e.shutdown();
			e.awaitTermination(1, TimeUnit.MINUTES);
		} 
		catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}

	}

}
