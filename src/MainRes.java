import java.util.ArrayList;
import java.util.Scanner;

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
	// public static double pizza = 0, chickens = 0, coke = 0, total = 0, exit;
	// public static Scanner input = new Scanner(System.in);
	// public static int quanpizza = 0, quanchickens = 0, quancoke = 0;
	// static int[] quantity = new int[6];
	//
	// public static int calculator(int choice) {
	// while (choice != 4 && choice != 5) {
	// System.out.printf("Enter Quantity: ");
	// if (choice == 1) {
	// quanpizza = input.nextInt();
	// quantity[1]=quanpizza;
	// pizza = 250 * quanpizza;
	// }
	// if (choice == 2) {
	// quanchickens = input.nextInt();
	// quantity[2]=quanchickens;
	// chickens = 120 * quanchickens;
	// }
	// if (choice == 3) {
	// quancoke = input.nextInt();
	// quantity[3]=quancoke;
	// coke = 45 * quancoke;
	// }
	// if (choice == 6) {
	// System.out.print("Reset");
	// quanpizza = 0;
	// quancoke = 0;
	// quanchickens = 0;
	// pizza = 0;
	// chickens = 0;
	// coke = 0;
	// }
	// System.out.printf("\nEnter your Choice: ");
	// choice = input.nextInt();
	// }
	// if (choice == 4) {
	// total = pizza + chickens + coke;
	// System.out.printf("♥========= MENU =========♥== Qty ==♥== Price
	// ====♥\n");
	// if (pizza != 0) {
	// System.out.printf("| Pizza | %d | %.0f\t |\n", quantity[1], pizza);
	// }
	// if (chickens != 0) {
	// System.out.printf("| Chickens | %d | %.0f\t |\n", quantity[2], chickens);
	// }
	// if (coke != 0) {
	// System.out.printf("| Coke | %d | %.0f\t |\n", quantity[3], coke);
	// }
	// System.out.println("♥========================♥=========♥=============♥");
	// if (total != 0) {
	// System.out.printf("| Total | %.0f\t |\n", total);
	// }
	// System.out.println("♥========================♥=========♥=============♥");
	// }
	// if (choice == 5) {
	// System.out.print("♥♥♥♥♥ Thank you ♥♥♥♥♥");
	// }
	// return choice;
	// }

	public static void main(String[] args) {
		RestaurantManeger.init();
		menu();
		setOrder();
		userChoice();
		// System.out.println("----♥---- Welcome to SKE Restaurant ----♥----");
		// System.out.printf("1.) Pizza\t %5d Baht.\n", 250);
		// System.out.printf("2.) Chickens\t %5d Baht.\n", 120);
		// System.out.printf("3.) Coke\t %5d Baht. \n", 45);
		// System.out.printf("4.) Total\n");
		// System.out.printf("5.) Exit\n");
		// System.out.printf("6.) Reset\n");
		// System.out.print("\n");
		// System.out.print("\n");
		// System.out.print("Enter your Choice: ");
		// int choice = input.nextInt();
		// calculator(choice);
	}
}
