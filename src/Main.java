
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
		
		Iterator<Integer> iterator = HSorders.values().iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

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
