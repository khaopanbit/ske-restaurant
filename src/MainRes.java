import java.util.ArrayList;
import java.util.Scanner;
/*Restaurant manager provides service of the restaurant 
 * user can change the menu items and price in menu.txt
 * @author Pittayoot R.R.Ratanakul        */
public class MainRes {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Integer> order = new ArrayList<Integer>();
	static double sum = 0;

	public static void menu() {
		String[] menu = RestaurantManeger.getMenuItems();
		double[] price = RestaurantManeger.getPrices();
		System.out.println("==============Welcome to SKE Restaurant=============");
		for (int i = 0; i <= price.length - 1; i++) {
			System.out.printf("%d", i + 1);
			System.out.printf("%10s  %.0f Baht.\n", menu[i], price[i]);
		}
		System.out.println("Press : ");
		System.out.println("[m] to menu");
		System.out.println("[o] to order list");
		System.out.println("[t] to total");
		System.out.println("[q] to quit");
	}

	public static void setOrder() {
		for (int i = 0; i < RestaurantManeger.getMenuItems().length; i++) {
			order.add(0);
		}
	}

	public static double order(double sum, String choice) {
		String[] menu = RestaurantManeger.getMenuItems();
		double[] price = RestaurantManeger.getPrices();
		double total = 0;
		if (choice.equalsIgnoreCase("o")) {
			System.out.println("SKE Restaurant");
			System.out.println("||============Menu============||=========Quantity=========||=======Price======||");
			for (int i = 0; i <= price.length - 1; i++) {
				if (price[i] * order.get(i) != 0) {
					System.out.printf("|| \t\t %s||\t\t\t\t%d||     \t\t%.0f||\n", menu[i], order.get(i), price[i] * order.get(i));
				}

			}
			for (int j = 0; j < menu.length; j++) {
				total = total + order.get(j);
			}
			System.out.println("===============================================================================");
			System.out.printf("||\t\t\tTotal ||\t\t\t %.0f||\t\t%7.0f||\n", total, sum);
		}
		return sum;
	}
 
	public static double price(String choice, int quantity) {
		double price = 0;
		int orders, all;
		double[] calculateprice = RestaurantManeger.getPrices();
		ArrayList<Integer> finalqty = new ArrayList<Integer>();
		for (int i = 0; i < calculateprice.length; i++) {
			finalqty.add(0);
		}
		int choicesec = Integer.parseInt(choice);
		for (int j = 0; j < calculateprice.length; j++) {
			if (choicesec == j+1) {
				orders = order.get(j) + quantity;
				order.add(j, orders);
				order.remove(j+1);
				all = quantity - finalqty.get(j);
				finalqty.add(j, all);
				finalqty.remove(j+1);
				price = all * calculateprice[j];
				break;
			}
		}
		return price;
	}

	public static void total(double lastPrice) {
		double payment;
		do {
			System.out.print("Pay: ");
			payment = input.nextDouble();
			if (payment < lastPrice) {
				System.out.println("Not enough money.Please try again.");
			}
		} while (payment < lastPrice);
		System.out.printf("Change: %.0f\n", payment - lastPrice);
		System.exit(0);
	}

	public static void userChoice() {
		int qty = 0;
		double price = 0;
		String choice;
		do {
			System.out.println("Enter your choice : ");
			choice = input.next();
			price = order(sum, choice);
			if ((!choice.equalsIgnoreCase("q") && !choice.equalsIgnoreCase("t") && !choice.equalsIgnoreCase("m")
					&& !choice.equalsIgnoreCase("o") && !choice.equalsIgnoreCase(checkInt(choice)))) {
				System.out.println("Unknown Input \n Please enter agian");
				continue;
			}
			if (choice.equalsIgnoreCase("m")) {
				menu();
			} else if (choice.equalsIgnoreCase("t")) {
				total(price);
			} else if (choice.equalsIgnoreCase("q")) {
				System.out.println("=======Thankyou=======\n");
				break;
			} else if (!choice.equalsIgnoreCase("m") && !choice.equalsIgnoreCase("t")
					&& !choice.equalsIgnoreCase("q")
					&& !choice.equalsIgnoreCase("o")) {
				System.out.print("Enter Quantity: ");
				qty = input.nextInt();
			} else {
				continue;
			}
			sum = sum + price(choice, qty);
		} while (!choice.equalsIgnoreCase("q"));

	}

	public static String checkInt(String choice) {
		String string;
		for (int i = 1; i <= RestaurantManeger.getPrices().length; i++) {
			string = Integer.toString(i);
			if (choice.equals(string)) {
				return string;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		RestaurantManeger.init();
		menu();
		setOrder();
		userChoice();

	}
}
