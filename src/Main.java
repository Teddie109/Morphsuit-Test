
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		File totals = new File("CSV/totals.csv");
		
		File[] orders = new File[6];
		
		orders[0] = new File("CSV/hurricane_sandy_orders.csv");
		
		for (int i = 1; i < 6; i++) {
			orders[i] = new File("CSV/hurricane_sandy_orders_" + i + ".csv");
		}
		
		HashMap<Integer, Integer> HSorders = new HashMap<Integer, Integer>();
		
		compileMasterList(totals, HSorders);
		
		compareOrderLists(HSorders, orders);
		
//		Iterator<Integer> iterator = HSorders.values().iterator();
//		
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}

	}
	
	private static void compareOrderLists(HashMap<Integer, Integer> HSorders, File[] orders) {
		
		System.out.println("Comparing lists. There are: " + HSorders.size() + " Orders in the totals file");
		
		try {
			BufferedWriter totalsonly = new BufferedWriter(new FileWriter("TotalsOnly.txt"));
			BufferedWriter HSordersonly = new BufferedWriter(new FileWriter("HSordersOnly.txt"));
			BufferedWriter both = new BufferedWriter(new FileWriter("Both.txt"));
			
			Scanner csvscanner = null;
		
			for (int i = 0; i < orders.length; i++) {	
			
				System.out.println("Comparing List: " + orders[i].getName());
				csvscanner = new Scanner(orders[i]);
				csvscanner.next();
				csvscanner.next();
				
				while (csvscanner.hasNext()) {
					int next = csvscanner.nextInt();
					
					if (HSorders.containsKey(next)) {
						both.write(next + "\r\n");
						HSorders.remove(next);
					}
					else {
						HSordersonly.write(next + "\r\n");
					}
				}
				
			}
			
			Iterator<Integer> iterator = HSorders.values().iterator();
			
			while (iterator.hasNext()) {
				totalsonly.write(iterator.next() + "\r\n");
			}
			
			csvscanner.close();
			both.close();
			HSordersonly.close();
			totalsonly.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Finished comparisons. " + HSorders.size() + " orders were not found in any list");
		
	}
	
	private static void compileMasterList(File total, HashMap<Integer, Integer> HSorders) {
		
		try {
			Scanner csvscanner = new Scanner(total);
			
			System.out.println("Reading master list");
			csvscanner.next();
			csvscanner.next();
			
			while (csvscanner.hasNext()) {
				int ordernumber = csvscanner.nextInt();
				HSorders.put(ordernumber, ordernumber);
			}
			
			csvscanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
