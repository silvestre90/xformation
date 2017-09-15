package orderingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class mainMethod {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String answer;
		double totalPrice = 0;
		
	
		List<Lunch> lunchList = loadLunchMenu();
		List<Drinks> drinkList = loadDrinkMenu();
		List<Dessert> dessertList = loadDessertMenu();
		List<String> additionsList = loadAdditionsMenu();

		String errText = "Wrong key pressed. Please read instructions below:";

		boolean flag = true;
		do {
			System.out.println("What would you like to order?");
			System.out.println("1 - Lunch");
			System.out.println("2 - Drink");
			System.out.println("E - Exit");

			answer = reader.readLine();

			if (isEntryCorrect(answer, new String[] { "1", "2", "E" })) {

				flag = false;

			} else {
				System.out.println(errText);
			}
		} while (flag);

		if (answer.equals("1")) {
			// lunch
			String[] cuisines = {"Polish","Italian","Mexican"};
			boolean cuisineFlag = true;
			do {
				System.out.println("What cuisine do you prefer?:");
				System.out.println("1 - " + cuisines[0]);
				System.out.println("2 - " + cuisines[1]);
				System.out.println("3 - " + cuisines[2]);

				String chosenCuisine = reader.readLine();

				if (isEntryCorrect(chosenCuisine, new String[] { "1", "2", "3" })) {
					cuisineFlag = false;
					List<Lunch> lunchCountryList = new ArrayList<>();
					for (Lunch l : lunchList) {
						if (l.getCousineType().equals(cuisines[Integer.parseInt(chosenCuisine) - 1])) {
							lunchCountryList.add(l);
						}
					}

					String[] lunchMenuIndexes = loadValidationList(lunchCountryList.size());
					boolean lunchFlag = true;
					do {
						String text = generateTextLunch(lunchCountryList);
						System.out.println(text);

						String chosenLunch = reader.readLine();

						if (isEntryCorrect(chosenLunch, lunchMenuIndexes)) {
							lunchFlag = false;
							//////////////////////
							boolean dessertFlag = true;
							String[] dessertMenuIndexes = loadValidationList(dessertList.size());
							do {
								text = generateTextDessert(dessertList);
								System.out.println(text);

								String chosenDessert = reader.readLine();

								if (isEntryCorrect(chosenDessert, dessertMenuIndexes)) {
									dessertFlag = false;

									System.out.println("Your order is: ");
									System.out.println("Lunch: "
											+ lunchCountryList.get(Integer.parseInt(chosenLunch) - 1).getName() + " - "
											+ lunchCountryList.get(Integer.parseInt(chosenLunch) - 1).getPrice());
									System.out.println("Dessert: "
											+ drinkList.get(Integer.parseInt(chosenDessert) - 1).getName() + " - "
											+ drinkList.get(Integer.parseInt(chosenDessert) - 1).getPrice());
									totalPrice = lunchCountryList.get(Integer.parseInt(chosenLunch) - 1).getPrice() + drinkList.get(Integer.parseInt(chosenDessert) - 1).getPrice(); 
									System.out.println("Total price: " + totalPrice );
								} else {
									System.out.println(errText);
								}

							} while (dessertFlag);
							/////////////
						} else {
							System.out.println(errText);
						}

					} while (lunchFlag);

				} else {
					System.out.println(errText);
				}

			} while (cuisineFlag);

			//

		} else if (answer.equals("2")) {
			// drink
			boolean drinkflag = true;
			String[] drinksMenuIndexes = loadValidationList(drinkList.size());
			do {
				String text = generateTextDrinks(drinkList);
				System.out.println(text);

				String chosenDrink = reader.readLine();
				
				if (isEntryCorrect(chosenDrink, drinksMenuIndexes)) {
					drinkflag = false;

					boolean additionFlag = true;
					String[] additionsMenuIndexes = loadValidationList(additionsList.size());
					do {
						String addText = generateTextAdditions(additionsList);
						System.out.println(addText);

						String chosenAddition = reader.readLine();
						if (isEntryCorrect(chosenAddition, additionsMenuIndexes)) {
							additionFlag = false;

							System.out.println("Your order is" + "\n" + "Drink: "
									+ drinkList.get(Integer.parseInt(chosenDrink) - 1).getName() + "\n" + "Addition: "
									+ additionsList.get(Integer.parseInt(chosenAddition) - 1) + "\n" + "Total price: "
									+ drinkList.get(Integer.parseInt(chosenDrink) - 1).getPrice());
						} else {
							System.out.println(errText);
						}

					} while (additionFlag);

				} else {
					System.out.println(errText);
				}

			} while (drinkflag);

		}

	} // end of main method

	private static List<String> loadAdditionsMenu() {
		
		List<String> additionsList = new ArrayList<String>();
		
		additionsList.add("Ice cubes");
		additionsList.add("Lemon");
		additionsList.add("Ice cubes and Lemon");
		additionsList.add("None");
		
		return additionsList;
	}

	public static String[] loadValidationList(int numberOfEntries) {

		String[] table = new String[numberOfEntries];

		for (int i = 0; i < numberOfEntries; i++) {
			table[i] = String.valueOf(i + 1);
		}

		return table;
	}

	public static boolean isEntryCorrect(String answer, String[] allowedValues) {

		for (int i = 0; i < allowedValues.length; i++) {
			if (answer.equals(allowedValues[i])) {
				return true;
			}
		}

		return false;

	}

	public static String generateTextDessert(List<Dessert> desserts) {

		String txt = "Which dessert would you like to choose?";

		for (Dessert d : desserts) {
			txt = txt + "\n" + (desserts.indexOf(d) + 1) + " - " + d.getName() + " - " + d.getPrice();
		}

		return txt;

	}

	public static String generateTextAdditions(List<String> additions) {

		String txt = "Would you like to order some addition to drink from the list below:";

		for (String add : additions) {
			txt = txt + "\n" + (additions.indexOf(add) + 1) + " - " + add;
		}

		return txt;

	}

	public static String generateTextDrinks(List<Drinks> drinks) {

		String txt;

		txt = "Which drink would you like to choose?";

		for (Drinks d : drinks) {
			txt = txt + "\n" + (drinks.indexOf(d) + 1) + " - " + d.getName() + " - " + d.getPrice();
		}

		return txt;

	}

	public static String generateTextLunch(List<Lunch> lunchs) {

		String txt;

		txt = "Which dish would you like to choose?";

		for (Lunch d : lunchs) {
			txt = txt + "\n" + (lunchs.indexOf(d) + 1) + " - " + d.getName() + " - " + d.getPrice();
		}

		return txt;

	}

	
	
	
	public static List<Dessert> loadDessertMenu() {

		List<Dessert> dsrt = new ArrayList<Dessert>();

		dsrt.add(new Dessert("Ice cream", 7.5));
		dsrt.add(new Dessert("Apple pie", 8.0));
		dsrt.add(new Dessert("Fruit salad", 9.5));

		return dsrt;
	}

	public static List<Drinks> loadDrinkMenu() {

		List<Drinks> d = new ArrayList<Drinks>();

		d.add(new Drinks("Beer", 8.0));
		d.add(new Drinks("Orange Juice", 6.0));
		d.add(new Drinks("Apple Juice", 6.0));
		d.add(new Drinks("Water", 4.5));
		d.add(new Drinks("Sparkled water", 5.5));
		d.add(new Drinks("Coffee", 7.5));
		d.add(new Drinks("Tea", 5.0));
		d.add(new Drinks("Wine", 10.0));

		return d;

	}

	public static List<Lunch> loadLunchMenu() {
		List<Lunch> l = new ArrayList<Lunch>();

		l.add(new Lunch("Polish", "Bigos", 15));
		l.add(new Lunch("Polish", "Zurek", 12));
		l.add(new Lunch("Polish", "Golonka", 20));
		l.add(new Lunch("Italian", "Spaghetti", 14));
		l.add(new Lunch("Italian", "Pizza", 20));
		l.add(new Lunch("Italian", "Pasta", 15));
		l.add(new Lunch("Mexican", "Tortilla", 12));
		l.add(new Lunch("Mexican", "Bistec", 17));
		l.add(new Lunch("Mexican", "Tacos", 20));

		return l;
	}

}
