import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantManeger {

	private static ArrayList<String> names = new ArrayList<>();
	private static ArrayList<Double> prices = new ArrayList<>();

	public static String[] getMenuItems() {
		String[] menuname = names.toArray(new String[names.size()]);
		return menuname;
	}

	public static double[] getPrices() {
		double[] menuprice = new double[prices.size()];
		for(int i=0;i<menuprice.length;i++){
			menuprice[i] = prices.get(i);
		}
		return menuprice;
	}

	public static void recordOrder(int orderNumber, int[] order, double total) {

	}

	static void setMenu(String filename) {
		ClassLoader loader = RestaurantManeger.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(filename);
		if (in == null) {
			System.out.println("Error reading file : " + filename);
			return;
		}
		Scanner console = new Scanner(in);
		while (console.hasNextLine()) {
			String list = console.nextLine();
			if (list.startsWith("#")) {
				continue;
			}
			String[] seperate = list.split(";");
			names.add(seperate[0]);
			prices.add(Double.parseDouble(seperate[1]));
		}
		console.close();
	}

	static void init() {
		setMenu("Data/Menu.txt");
	}
}

