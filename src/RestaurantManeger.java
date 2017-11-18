import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/* @author Pittayoot R.R.Ratanakul        */
public class RestaurantManeger {

	private static ArrayList<String> names = new ArrayList<>();
	private static ArrayList<Double> prices = new ArrayList<>();

	public static String[] getMenuItems() {
		String[] menuname = names.toArray(new String[names.size()]);
		return menuname;
	}

	public static double[] getPrices() {
		double[] menuprice = new double[prices.size()];
		for (int i = 0; i < menuprice.length; i++) {
			menuprice[i] = prices.get(i);
		}
		return menuprice;
	}

	public static void recordOrder(String[] menu, double[] price, double total, double sum, ArrayList<Integer> order) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("order.txt");
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.println("SKE Restaurant");
			writer.println("||============Menu============||=========Quantity=========||=======Price======||");
			for (int i = 0; i < price.length; i++) {
				if (price[i] * order.get(i) != 0) {
					writer.printf("|| \t\t %s||\t\t\t\t%d||     \t\t%.0f||\n", menu[i], order.get(i),
							price[i] * order.get(i));
					writer.println();
				}
			}
			writer.println("===============================================================================");
			writer.printf("||\t\t\tTotal ||\t\t\t %.0f||\t\t%7.0f||\n", total, sum);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
