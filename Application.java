import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Thelma Ofoegbu
 */


public class Application {
	static ArrayList<Products> allProducts = new ArrayList<Products>();
	static ArrayList<Skincare> skincareProducts = new ArrayList<Skincare>();
	static ArrayList<Haircare> haircareProducts = new ArrayList<Haircare>();
	static ArrayList<Fragrance> allFragrances = new ArrayList<Fragrance>();
	static ArrayList<String> categories = new ArrayList<String>();
	static ArrayList<Products> cart = new ArrayList<Products>();
	static ArrayList<Products> productsSold = new ArrayList<Products>();
	static Scanner input = new Scanner(System.in);
	static InputStreamReader textInput = new InputStreamReader(System.in); 
	static BufferedReader reader = new BufferedReader(textInput);
	static String capName;
	static int numTransactions;
	static double totalTakings;
	static int cancelledTransaction;
	static String adminPassword = "Admin";
	static int centralProductID = 116;

	/**
	 * Standard main method. Starting point of my program.
	 * it will call prePopulate method first before calling the start method which begins the program.
	 * 
	 */
	public static void main(String[] args) {

		prePopulate();
		start();

	}
	/**
	 * Displays a Welcome message to the User
	 * Prompts user to Enter their name then calls the mainMenu method which is the core program
	 * 
	 */
	private static void start() {
		// Prints a welcome message and prompts the user to enter their name
		System.out.println("Welcome to ShopMate (Thelma's Shopping Cart System)");
		System.out.println("Please enter ur name");
		// Reads the user's name and capitalizes the first letter
		String name = input.next();
		capName = name.substring(0,1).toUpperCase() + name.substring(1);
		// Greets the user by their capitalized name and prints a blank line
		System.out.println("Hi " + capName);
		System.out.println(" ");

		mainMenu();

	}

	/**
	 * Displays the main menu for the system and allows the user to choose between
	 * 1. View all Products
	 * 2. View Your Cart
	x. Exit
	 * It also has a hidden choice which leads to the Admin menu method, it is password protected which means you'll be sent to the enterPassword method first.
	 */

	private static void mainMenu() {
		System.out.println("Press 1 View all Products");
		System.out.println("Press 2 View Your Cart");
		System.out.println("Press x Exit");

		String choice = input.next();
		choice = choice.toLowerCase();//converts choice  to lowercase

		switch(choice) {
		case "1":{
			viewAllProducts();
			break;
		}
		case "2":{
			viewCart();
			break;
		}
		case "x":{
			System.out.println("System Shutting down");
			System.exit(0);// Terminates the running program
			break;
		}
		case "a":{
			enterPassword();
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}
		}

		mainMenu();
	}

	/**
	 * Method checks if there is any product in the cart, displays your cart is empty if there is nothing in the cart
	 * Else it Displays the products in the cart
	 * It allows users to select between proceeding to checkout,adding more items to cart, editing cart or canceling the transaction
	 * 
	 */
	private static void viewCart() {
		if (cart.isEmpty()) {// Check if cart is empty
			System.out.println("Your cart is empty.");
		} 
		else {//print the contents of the cart if not empty
			System.out.println("*************Your Cart***************");
			System.out.println();
			for (Products p: cart) {
				System.out.println(p.getProductID() + ": " +p.getProductName() +" " +"("+ p.getProductQty()+")");
			}
			System.out.println("**************************************");
			System.out.println();
			System.out.println("Press 1 to proceed to Checkout");
			System.out.println("Press 2 to Add more Items to Cart");
			System.out.println("Press 3 to Edit Cart");
			System.out.println("Press 4 to Cancel Transaction");



			String choice = input.next();
			choice = choice.toLowerCase();

			switch(choice) {
			case "1":{
				checkOut();
				break;
			}
			case "2":{
				viewAllProducts();
				break;
			}
			case "3":{
				editCart();
				break;
			}
			case "4":{
				cancelTransaction();
				break;
			}

			default:{
				System.out.println("Invalid choice. Please try again");
				viewCart();
				break;
			}
			}
		}

	}
	/**
	 * The Edit Cart method allows users to select between: Editing The quantity of items in the cart or removing an item from the cart or returning to the view cart method
	 */

	private static void editCart() {
		System.out.println("Press 1 to Edit Quantity");
		System.out.println("Press 2 to Remove Item from cart");
		System.out.println("Press 3 to Go back");


		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{

			for (Products p: cart) {
				System.out.println(p.getProductID() + ": " +p.getProductName() +" " +"("+ p.getProductQty()+")");
			}
			System.out.println("Enter the product ID to edit quantity:");
			int chosenID = input.nextInt();
			boolean isFound = false;

			for (Products s : allProducts) {
				if (s.getProductID() == chosenID) {
					isFound=true;
					for (Products p : cart) {
						if (p.getProductID() ==chosenID) {
							isFound=true;
							System.out.println("Enter new quantity :");
							s.setProductQty(s.getProductQty()+ p.getProductQty());// add the current cartItem quantity back to the inventory
							int numUnit = input.nextInt();
							while(numUnit > s.getProductQty()) {//Check if there is enough product in stock to cover user input
								System.out.println("Insufficient quantity. Available quantity:" + s.getProductQty()+ " Please enter a lower quantity");
								numUnit = input.nextInt();

							}

							s.setProductQty(s.getProductQty()-numUnit);// Update the product quantity in the inventory
							p.setProductQty(numUnit); //set the product quantity in the cart
							System.out.println("Quantity updated successfully.");
							break;


						}
					} 

				}

			}
			if(isFound== false) {
				System.out.println("No Product with the ID" +" " + chosenID+" "+ " found");
				System.out.println("Please Try again");
				System.out.println(" ");
				editCart();
			}
			break;
		}

		case "2":{
			for (Products p: cart) {
				System.out.println(p.getProductID() + ": " +p.getProductName() );
			}
			System.out.println("Enter the product ID to remove from Cart");
			int chosenID = input.nextInt();
			boolean isFound = false;

			for (Products s : allProducts) {
				if (s.getProductID() == chosenID) {
					isFound=true;
					for (Products p : cart) {
						if (p.getProductID() == chosenID) { 
							isFound=true;
							s.setProductQty(s.getProductQty()+ p.getProductQty());// add the current cartItem quantity back to the inventory
							cart.remove(p);//remove p from Cart
							System.out.println("Product removed successfully from cart.");
							break;
						}
					}
				}
			}
			if(isFound== false) {
				System.out.println("No Product with the ID" +" " + chosenID+" "+ " found");
				System.out.println("Please Try again");
				System.out.println(" ");
				editCart();
			}
			break;
		}
		case "3":{
			viewCart();
			break;
		}
		default:{
			System.out.println("Invalid choice. Please try again");
			editCart();
			break;
		}
		}
	}


	/**
	 * This method displays All products to users based on their categories
	 * It allows users to select The product they will like to view and sends the users to the viewSelectedProduct Method
	 */
	public static void viewAllProducts() {
		DecimalFormat currency = new DecimalFormat("€0.00");
		// Loop through each category and print a header with the category name
		for (String category : categories) {
			System.out.println("*********** " + category+ " ***********");
			System.out.println();
			// Loop through all products in the specified category and display the product details
			for (Products p : allProducts) {
				if (p.getCategory().equals(category)) {
					System.out.println(p.getProductID()+" "+ p.getProductName() +" \t"+ currency.format(p.getProductPrice()));
				}
			}
			System.out.println();
		}
		System.out.print("Enter the ID of the product you want to view: ");
		int chosenID = input.nextInt();
		boolean isFound=false;
		
		Products selectedProduct = null;// Initialize the selectedProduct variable to null
		for (Products p : allProducts) {
			if (p.getProductID() ==chosenID) {// Find the selected product by ID
				selectedProduct = p;
				isFound=true;
				break;
			}
		}
		if(isFound== false) {
			System.out.println("No Product with the ID" +" " + chosenID+""+ " found");
			System.out.println("Please Try again");
			System.out.println();
			viewAllProducts();

		}
		if(selectedProduct != null) {
		viewSelectedProduct(selectedProduct);// Call the viewSelectedProduct method and pass selectedProduct
	}
	}

	/**
	 * This method checks the selected product's category and prints the product details from the appropriate class
	 * it allows users to select between adding the product to cart, going back to the list of products or viewing cart
	 * @param selectedProduct
	 */
	public static void viewSelectedProduct(Products selectedProduct) {
		if (selectedProduct.getCategory()=="Haircare") {
			Haircare selectedHaircare = (Haircare) selectedProduct;
			selectedHaircare.printProductdetails(selectedHaircare);

		}
		else if (selectedProduct.getCategory()=="Skincare") {
			Skincare selectedSkincare = (Skincare) selectedProduct;
			selectedSkincare.printProductdetails(selectedSkincare);

		}
		else {
			Fragrance selectedFragrance = (Fragrance) selectedProduct;
			selectedFragrance.printProductdetails(selectedFragrance);

		}

		System.out.println("Press 1 to Add Product to Cart");
		System.out.println("Press 2 to Go Back to List of products");
		System.out.println("Press 3 to View Cart");


		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			addToCart(selectedProduct);
			break;
		}
		case "2":{
			System.out.println(" ");
			viewAllProducts();
			break;
		}
		case "3":{
			viewCart();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			viewSelectedProduct(selectedProduct);
			break;
		}
		}
	}


	/**

	*Adds a product to the shopping cart.
	*Creates a new cart item object with the same attributes as the stock item passed in as a parameter,
	except for the quantity, which is set to the amount requested by the user. The stock item's quantity is
	reduced by the number of units added to the cart. If the requested number of units is more than the
	available stock, an error message is displayed and the user is asked to try again.
	*After adding the item to the cart, the user is given a choice to proceed to checkout, add more items
	to the cart, view the cart, or cancel the transaction.
	@param stockItem the product to be added to the cart
	 */
	private static void addToCart(Products stockItem) {
		Products cartItem = new Products();// Create a new Products object and set its properties
		cartItem.setCategory(stockItem.getCategory());
		cartItem.setProductID(stockItem.getProductID());
		cartItem.setProductName(stockItem.getProductName());
		cartItem.setProductPrice(stockItem.getProductPrice());
		System.out.println("How Many Units would you like to add");// Prompt user for number of units to add and ensure there is enough stock
		int numUnits=input.nextInt();
		while(numUnits>stockItem.getProductQty()) {
			System.out.println("Not enough Stock");
			System.out.println("We currently have " + stockItem.getProductQty()+ " In Stock,....Please Try again");
			System.out.println("How Many Units would you like to add");
			numUnits = input.nextInt();
		}
		cartItem.setProductQty(numUnits);// Set the number of units for the cart item and update stock
		stockItem.setProductQty(stockItem.getProductQty()- numUnits);
		cart.add(cartItem);// Add cart item to cart and provide user with feedback
		System.out.println(numUnits +" " +cartItem.getProductName()+ "(s)" +" successfully added");
		System.out.println(" ");

		System.out.println("Press 1 to proceed to Checkout");
		System.out.println("Press 2 to Add more Items to Cart");
		System.out.println("Press 3 to View Cart");
		System.out.println("Press 4 to Cancel Transaction");



		String choice = input.next();

		switch(choice) {
		case "1":{
			checkOut();
			break;
		}
		case "2":{
			viewAllProducts();
			break;
		}
		case "3":{
			viewCart();
			break;
		}
		case "4":{
			cancelTransaction();
			break;
		}

		default:{
			System.out.println("Invalid choice. Returning to main Menu");

			break;
		}
		}



	}
	/**

	 * This method is used to display the list of items in the cart and the total price,
	and prompts the user to choose the next step in the checkout process.
	 * If the user chooses to proceed to payment, the makePayment method is called.
	 * If the user chooses to cancel the transaction, the cancelTransaction method is called.
	 * If the user chooses to return to the main menu, the mainMenu method is called.


	 */
	private static void checkOut() {
		DecimalFormat currency = new DecimalFormat("€0.00");// Create currency format to display price in euros
		System.out.println("*********Checkout:**********");
		System.out.println();
		double total = 0;
		
		// Loop through the items in the cart and print out the name, quantity, and total price for each item
		for (Products p : cart) {
			System.out.println(p.getProductName() + " ( " + p.getProductQty() + ")" +" - " + currency.format(p.getProductPrice() * p.getProductQty()));
			total += p.getProductPrice() * p.getProductQty();//calculates total cost of all products in the cart

		}
		System.out.println(" ");
		System.out.println("Total: " + currency.format(total));
		System.out.println(" ");
		System.out.println("Press 1 to proceed to payment");
		System.out.println("Press 2 to Cancel Transaction");
		System.out.println("Press 3 to return to Main menu");

		String choice = input.next();


		switch(choice) {
		case "1":{
			makePayment(currency, total);//calls makePayment passing currency and total

			break;
		}

		case "2":{
			cancelTransaction();
			break;
		}
		case "3":{
			mainMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			checkOut();
			break;
		}
		}

	}
	/**
	 *This method takes payment from the customer to complete a transaction, updates the number of transactions and total takings,
	 *and adds sold items to the list of products sold. If the customer has entered more money than the total price of the items,
	 *the method calculates the change to be returned and prints it.


	 */
	public static void makePayment(DecimalFormat currency, double total) {
		System.out.println("Amount Due: " + total );
		System.out.println("Please Enter payment to complete Transaction");// Prompt the user to enter payment to complete transaction
		double moneyEntered = input.nextDouble();//Holds money entered into machine

		while (moneyEntered<total){// Check if the money entered is less than the total cost of the items
			double balance = total - moneyEntered;
			System.out.println("Balance Remaining: " + currency.format(balance));
			System.out.println("Please enter remaining balance.");
			moneyEntered = moneyEntered + input.nextDouble();

		}
		System.out.println("Transaction Complete.");// Print a message to indicate that the transaction is complete
		if (moneyEntered>total){// Check if the money entered is more than the total cost of the items
			double change = moneyEntered - total;
			System.out.println("Change due: " + currency.format(change));
			System.out.println("Please take your change.");

		}

		// Add the sold items to the productsSold list
		for (Products p : cart) { 

			Products soldItem = new Products();
			soldItem.setCategory(p.getCategory());
			soldItem.setProductID(p.getProductID());
			soldItem.setProductName(p.getProductName());
			soldItem.setProductPrice(p.getProductPrice());
			soldItem.setProductQty(p.getProductQty());
			productsSold.add(soldItem);


		}


		cart.clear();// Clears the cart 

		System.out.println();
		System.out.println("Thank you for your purchase!");
		System.out.println();

		numTransactions++;// Increments the transaction counter
		totalTakings = totalTakings + total;//Updates the total takings
	}

	/**

	 *Cancels the current transaction, removes all items from the cart, and returns the user to the main menu.
	 *Also increases the number of cancelled transactions and updates the inventory if any items were added to the cart.
	 */
	private static void cancelTransaction() {
		for (Products cartItem : cart) {
			int cartItemID = cartItem.getProductID();
			int cartItemQty = cartItem.getProductQty();
			for (Products p : allProducts) {
				if (p.getProductID() == cartItemID) {
					int originalQty = p.getProductQty();
					p.setProductQty(originalQty + cartItemQty);
					break;
				}
			}
		}

		cart.clear();
		cancelledTransaction++;
		System.out.println("Transaction cancelled. Your Cart has been emptied.");
		mainMenu();

	}

	/**
	 * Prompts the user to enter the admin password 
	 * allows access to the Admin Method if the correct password is entered.
	 * The user has three attempts to enter the correct password, after which access is denied and the main menu is displayed.
	 * @throws Exception if an error occurs while getting input
	 */
	private static void enterPassword() {
		int attempts = 3;// Number of attempts user has to enter password
		System.out.println("Hi " + capName + " Please enter Admin Password. You have " + attempts +  " attempts");
		String password = input.next();// Get password from user
		int passwordCount=0;

		// Keep prompting user until correct password is entered or max attempts reached	
		while (!(password.equalsIgnoreCase(adminPassword))){
			System.out.println("Incorrect Password");
			attempts--;
			passwordCount++;

			if(passwordCount == 3){
				System.out.println("Max Password Entry reached...Access Denied");
				mainMenu();
			}
			System.out.println("Re-enter HR Password. You have " + attempts +  " attempt(s)");
			password = input.next();

		}

		// If correct password entered, allow access to Admin menu	 	
		System.out.println("Correct Password. Opening Admin Mode.");
		adminMenu();
	}

	/**
	 * This method displays the Admin menu options and allows the user to choose between the skincare Menu, fragrance menu, haircare menu, editing a product,viewing all product by category,or viewing stats .
	 */
	private static void adminMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 For Skincare Menu");
		System.out.println("Press 2 For Fragrance Menu");
		System.out.println("Press 3 For Haircare Menu");
		System.out.println("Press 4 to Edit Existing Product Details");
		System.out.println("Press 5 to view all Products by Category");
		System.out.println("Press 6 for Statistics");
		System.out.println("Press M to Return to main menu");
		System.out.println("*********************************************");

		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {

		case "1":{	
			skincareMenu();	
			break;
		}
		case "2":{
			fragranceMenu();
			break;
		}
		case "3":{
			haircareMenu();
			break;
		}
		case "4":{
			try {
				editProduct();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "5":{
			viewAllProductsByCategory();
			break;
		}
		case "6":{
			viewStats();
		}

		case "m":{
			mainMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			adminMenu();
			break;
		}

		}
		adminMenu();


	}
	/**
	 * This method displays the skincare menu which allows the user to choose from a list of options.
	 *The user can add a new skincare product, delete an existing product or view all products in the skincare category.
	 *The user can also return to the admin menu.
	 */

	private static void skincareMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Create a new Skincare Product");
		System.out.println("Press 2 to Delete a Skincare Product");
		System.out.println("Press 3 to view all Skincare Products");
		System.out.println("Press a to Return to Admin menu");
		System.out.println("*********************************************");

		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			try {
				addSkincare();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "2":{
			removeskincare();
			break;
		}
		case "3":{
			viewAllSkincare();
			break;
		}

		case "a":{
			adminMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}

		}


		skincareMenu();



	}
	/**
	 * Method to search for a Skincare product by its ID
	 *@return Skincare object if found, null if not found
	 */

	private static Skincare findS() {
		for(Skincare s: skincareProducts) {// loop through skincare products and print their IDs and names
			System.out.println(s.getProductID()+" " + s.getProductName());
		}
		System.out.println();
		System.out.println("Choose Product by Product ID");// prompt user to choose product by ID
		int chosenID = input.nextInt();
		Skincare foundSkincare = null;
		boolean isFound = false;


		for (Skincare s : skincareProducts) {// loop through skincare products
			if (s.getProductID() == chosenID) {
				foundSkincare = s;
				isFound=true;
				break; 
			}
		}
		if(isFound==false) {
			System.out.println("No ID found. Try again");// print an error message
			return findS();
		}
		return foundSkincare;// return the foundSkincare object
	}

	/**
	 * Method to display all Skincare products in the inventory
	 */

	private static void viewAllSkincare() {

		for (Skincare s: skincareProducts) {
			s.printProductdetails(s);
		}

	}

	/**
	 *
	 *Finds a Skincare product by its ID and removes it from the list of all products and all Skincare products.
	 */
	private static void removeskincare() {
		Skincare toRemove = findS();
		System.out.println(toRemove.getProductName()+" has been Removed.");
		allProducts.remove(toRemove);
		skincareProducts.remove(toRemove);
		System.out.println();


	}

	/**
	 * Allows the user to edit the details of a Skincare product. The user is prompted to choose which detail they want to change
	 * and then enter the new value for that detail.
	 *
	 * @param s the Skincare product to edit
	 * @throws Exception if there is an error reading user input
	 */
	private static void editSkincare(Skincare s) throws Exception{ 
		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			System.out.println("Enter new product name to replace " + s.getProductName() );
			String name = reader.readLine();
			System.out.println( s.getProductName() + " Successfully changed");
			s.setProductName(name.substring(0, 1).toUpperCase() + name.substring(1));
			break;
		}
		case "2":{
			System.out.println("Enter new product brand to replace " + s.getProductBrand());
			String brand = reader.readLine();
			System.out.println( s.getProductBrand() + " Successfully changed");
			s.setProductBrand(brand.substring(0, 1).toUpperCase() + brand.substring(1));
			break;	
		}
		case "3":{
			System.out.println("Enter new product size to replace " + s.getProductSize());
			String size = reader.readLine();
			s.setProductSize(size.substring(0, 1).toUpperCase() + size.substring(1));
			System.out.println("Size Successfully changed");
			break;
		}
		case "4":{
			System.out.println("Enter new product price to replace " + s.getProductPrice());
			double price = input.nextDouble();
			s.setProductPrice(price);
			System.out.println("Price Successfully changed");
			break;

		}
		case "5":{
			System.out.println("Enter new product quantity to replace " + s.getProductQty());
			int qty = input.nextInt();
			s.setProductQty(qty);
			System.out.println("Quantity Successfully changed");
			break;

		}
		case "6":{
			System.out.println("Enter new Product SkinType to replace:" + s.getSkinType());
			String skinType =reader.readLine();
			s.setSkinType(skinType.substring(0,1).toUpperCase() + skinType.substring(1));
			System.out.println("Skin type Successfully changed");
			break;
		}
		case "7":{
			System.out.println("Enter new Product Spf Rating to replace:" +
					s.getSpfRating()); String spf=reader.readLine();
					s.setSpfRating(spf.substring(0,1).toUpperCase() + spf.substring(1));
					System.out.println("SPF rating Successfully changed");
					break;	
		}
		case "8":{
			System.out.println("Is vegan (yes or no )");
			boolean isVegan = reader.readLine().equalsIgnoreCase("yes"); 
			s.setVegan(isVegan);
			System.out.println("Succesfully Changed");
			break;
		}
		case "a":{
			adminMenu();
			break;
		}
		default: {
			System.out.println("Invalid Choice. Try again");
			break;
		}
		}

	}

	/**
	 * Finds a product from the list of all products by searching for the product ID entered by the user.
	 *@return the product found in the list, or null if the product ID entered is not found
	 */

	private static Products findProduct() {
		for(Products p: allProducts) {
			System.out.println(p.getProductID()+" " + p.getProductName());
		}
		System.out.println();
		System.out.println("Enter the product ID to Edit");
		int chosenID = input.nextInt();
		Products foundProduct = null;
		boolean isFound = false;


		for ( Products p: allProducts) {
			if (p.getProductID() == chosenID) {
				foundProduct = p;
				isFound=true;
				break; 
			}
		}
		if(isFound==false) {
			System.out.println("No ID found. Try again");
			return findProduct();
		}
		return foundProduct;

	}


	/**
	 *Allows the admin to edit a product by selecting a product from the list of all products and choosing which attribute to edit.
	 *The method also casts the product as either Skincare, Haircare, or Fragrance depending on the product type.
	 *The method calls the appropriate edit method based on the product type.
	 *The method then calls itself to allow the admin to continue editing products until they choose to return to the admin menu.
	 *@throws Exception if an error occurs while editing the product
	 */
	public static void editProduct() throws Exception {// Method to edit a product's information
		Products toEdit = findProduct();// Find the product to be edited

		System.out.println("Press 1 to edit Product name");
		System.out.println("Press 2 to edit Product Brand");
		System.out.println("Press 3 to edit Size of Product");
		System.out.println("Press 4 to edit Price of Product");
		System.out.println("Press 5 to edit Quantity in Stock");

		// Check the type of product to add further options for editing

		if(Skincare.class.equals(toEdit.getClass())) {
			Skincare s = (Skincare) toEdit; //Cast Product as Skincare
			System.out.println("Press 6 to edit Skin type");
			System.out.println("Press 7 to edit Spf Rating");
			System.out.println("Press 8 to change Vegan Property");
			System.out.println("Press a to to return to admin menu");

			editSkincare(s);// Call method to edit Skincare product and pass s

		}

		else if(Haircare.class.equals(toEdit.getClass())) {
			Haircare h = (Haircare) toEdit; //Cast Product as Haircare
			System.out.println("Press 6 to edit Hair Type");
			System.out.println("Press 7 to edit Longevity");
			System.out.println("Press 8 to change Uv Protection");
			System.out.println("Press a to to return to admin menu");

			editHaircare(h);// Call method to edit Haircare product and pass h
		}

		else {
			Fragrance f = (Fragrance) toEdit; //Cast Product as Fragrance
			System.out.println("Press 6 to edit Gender");
			System.out.println("Press 7 to edit Longevity");
			System.out.println("Press a to to return to admin menu");

			editFragrance(f);// Call method to edit Fragrance product and pass f
		}
		editProduct();//recursively call editProduct()
	}

	/**

	 *Adds a new Skincare product to the inventory.
	 *Prompts the user to enter details such as product name, brand, size, price, quantity in stock, skin type, SPF rating,
	 *and vegan property for the new product. Creates a new Skincare object with the details and adds it to both the list of
	 *all Products and the list of Skincare products.
	 *@throws Exception if there is an error while reading user input
	 */
	private static void addSkincare() throws Exception {
		Skincare newproduct = new Skincare();
		addNewProduct(newproduct);
		newproduct.setCategory("Skincare");
		System.out.println("Enter Product SkinType");
		String skinType=reader.readLine();
		newproduct.setSkinType(skinType.substring(0,1).toUpperCase() + skinType.substring(1));
		System.out.println("Enter Product Spf Rating");
		String spf=reader.readLine();
		newproduct.setSpfRating(spf.substring(0,1).toUpperCase() + spf.substring(1));
		System.out.println("Is vegan (yes or no )");
		boolean isVegan = reader.readLine().equalsIgnoreCase("yes");
		newproduct.setVegan(isVegan);
		System.out.println(newproduct.getProductName()+ " Successfully Created");

		// Add the product to the list of all Products
		skincareProducts.add(newproduct);
		allProducts.add(newproduct);

	}

	/**

	Adds a new product to the inventory. Prompts the user to enter details such as product name, brand, size, price, and
	quantity in stock for the new product. Sets the product ID of the new product to a unique value and returns it.
	@param newproduct the new Product object to be added to the inventory passed from the subclasses
	@return the new product with updated product ID and other details
	@throws Exception if there is an error while reading user input
	 */
	public static Products addNewProduct(Products newproduct) throws Exception {
		newproduct.setProductID(centralProductID);
		centralProductID++;//increment value by 1 so a unique value is given each time this method is called
		System.out.println("Enter Product Name");
		String Name=reader.readLine();
		newproduct.setProductName(Name.substring(0,1).toUpperCase() + Name.substring(1));
		System.out.println("Enter Product Brand");
		String brand=reader.readLine();
		newproduct.setProductBrand(brand.substring(0,1).toUpperCase() + brand.substring(1));
		System.out.println("Enter Product Size (ml)");
		String size =reader.readLine();
		newproduct.setProductSize(size.substring(0,1).toUpperCase() + size.substring(1));
		System.out.println("Enter Product Price");
		newproduct.setProductPrice(input.nextDouble());
		System.out.println("Enter product Quantity");
		newproduct.setProductQty(input.nextInt());
		return newproduct;
	}
	/**
	 * This method displays the Fragrance menu which allows the user to choose from a list of options.
	 *The user can add a new Fragrance product, delete an existing product or view all products in the Fragrance category.
	 *The user can also return to the admin menu.
	 */
	private static void fragranceMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Create a new Fragrance Product");
		System.out.println("Press 2 to Delete a Fragrance Product");
		System.out.println("Press 3 to view all Fragrance Products");
		System.out.println("Press a to Return to Admin menu");
		System.out.println("*********************************************");

		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			try {
				addFragrance();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "2":{
			removeFragrance();
			break;
		}
		case "3":{
			viewAllFragrance();
			break;
		}

		case "a":{
			adminMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}

		}


		fragranceMenu();

	}

	/**

	 *Adds a new Fragrance product to the inventory.
	 *The method creates a new instance of the Fragrance class, sets its category to "Fragrance",
	 *prompts the user to input the product gender and longevity, sets these values to the new product, and adds
	 *the new product to the list of all products and all Fragrances.
	 *@throws Exception if there is an error reading user input
	 */

	private static void addFragrance() throws Exception{
		Fragrance newproduct = new Fragrance();
		addNewProduct(newproduct);
		newproduct.setCategory("Fragrance");
		System.out.println("Enter Product Gender");
		String gender=reader.readLine();
		newproduct.setGender(gender.substring(0,1).toUpperCase() + gender.substring(1));
		System.out.println("Enter Product Longevity");
		String longevity =reader.readLine();
		newproduct.setLongevity(longevity.substring(0,1).toUpperCase() + longevity.substring(1));
		System.out.println(newproduct.getProductName()+ " Successfully Created");


		// Add the product to the list of all Products
		allFragrances.add(newproduct);
		allProducts.add(newproduct);

	}

	/**
	 * Method to search for a Fragrance product by its ID
	 *@return Fragrance object if found, null if not found
	 */
	private static Fragrance findF() {
		for(Fragrance f: allFragrances) {
			System.out.println(f.getProductID()+" " + f.getProductName());
		}
		System.out.println();
		System.out.println("Choose Product By Product ID");
		int chosenID = input.nextInt();
		Fragrance foundFragrance = null;
		boolean isFound = false;


		for (Fragrance f : allFragrances) {
			if (f.getProductID() == chosenID) {
				foundFragrance = f;
				isFound=true;
				break; 
			}
		}
		if(isFound==false) {
			System.out.println("No ID found. Try again");
			return findF();
		}
		return foundFragrance;
	}

	/**

	/**
	 * Allows the user to edit the details of a Fragrance product. The user is prompted to choose which detail they want to change
	 * and then enter the new value for that detail.
	 *
	 * @param f the Fragrance product to edit
	 * @throws Exception if there is an error reading user input
	 */
	private static void editFragrance(Fragrance f) throws Exception{

		String choice =input.next();
		choice = choice.toLowerCase();
		switch(choice) {
		case "1":{
			System.out.println("Enter new product name to replace " + f.getProductName() );
			String name = reader.readLine();
			System.out.println(f.getProductName()+ " Successfully changed");
			f.setProductName(name.substring(0, 1).toUpperCase() + name.substring(1));
			break;
		}
		case "2":{
			System.out.println("Enter new product brand to replace " + f.getProductBrand());
			String brand = reader.readLine();
			System.out.println(f.getProductBrand()+ " Successfully changed");
			f.setProductBrand(brand.substring(0, 1).toUpperCase() + brand.substring(1));
			break;	
		}
		case "3":{
			System.out.println("Enter new product size to replace " + f.getProductSize());
			String size = reader.readLine();
			f.setProductSize(size.substring(0, 1).toUpperCase() + size.substring(1));
			System.out.println("Size Successfully changed");
			break;
		}
		case "4":{
			System.out.println("Enter new product price to replace " + f.getProductPrice());
			double price = input.nextDouble();
			f.setProductPrice(price);
			System.out.println("Price Successfully changed");
			break;

		}
		case "5":{
			System.out.println("Enter new product quantity to replace " + f.getProductQty());
			int qty = input.nextInt();
			f.setProductQty(qty);
			System.out.println("Quantity Successfully changed");
			break;
		}

		case "6":{
			System.out.println("Enter new Product Gender to replace:" + f.getGender());
			String gender=reader.readLine();
			f.setGender(gender.substring(0,1).toUpperCase() + gender.substring(1));
			System.out.println("Gender Successfully changed");
			break;
		}
		case "7":{
			System.out.println("Enter new Product Longevity Rating to raplace:" + f.getLongevity());
			String longevity =reader.readLine();
			f.setLongevity(longevity.substring(0,1).toUpperCase() + longevity.substring(1));
			System.out.println("Longevity Succesfully Changed");
			break;	
		}
		case "a":{
			adminMenu();
			break;
		}
		default: {
			System.out.println("Invalid Choice. Try again");
			break;
		}
		}

	}

	/**
	 *
	 *Finds a Fragrance product by its ID and removes it from the list of all products and all Fragrance products.
	 */
	private static void removeFragrance() {
		Fragrance toRemove = findF();
		allProducts.remove(toRemove);
		allFragrances.remove(toRemove);
		System.out.println(toRemove.getProductName()+" has been Removed.");
		System.out.println();

	}

	/**
	 * Method to display all Fragrance products in the inventory
	 */
	private static void viewAllFragrance() {

		for (Fragrance f: allFragrances) {
			f.printProductdetails(f);
		}

	}
	/**
	 * This method displays the Haircare menu which allows the user to choose from a list of options.
	 *The user can add a new Haircare product, delete an existing product or view all products in the Haircare category.
	 *The user can also return to the admin menu.
	 */
	private static void haircareMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Create a new Haircare Product");
		System.out.println("Press 2 to Delete a Haircare Product");
		System.out.println("Press 3 to view all Haircare Products");
		System.out.println("Press a to Return to Admin menu");
		System.out.println("*********************************************");

		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			try {
				addHaircare();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "2":{
			removeHaircare();
			break;
		}
		case "3":{
			viewAllHaircare();
			break;
		}

		case "a":{
			adminMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}

		}


		haircareMenu();

	}

	/**

	 *Adds a new Haircare product to the inventory.
	 *The method creates a new instance of the Haircare class, sets its category to "Haircare",
	 *prompts the user to input the product Hairtype,HairConcern and UvProtection attribute, sets these values to the new product, and adds
	 *the new product to the list of all products and all Haircare Products.
	 *@throws Exception if there is an error reading user input
	 */
	private static void addHaircare() throws Exception{
		Haircare newproduct = new Haircare();
		addNewProduct(newproduct);
		newproduct.setCategory("Haircare");
		System.out.println("Enter Product HairType");
		String HairType=reader.readLine();
		newproduct.setHairType(HairType.substring(0,1).toUpperCase() + HairType.substring(1));
		System.out.println("Enter Product Hair Concern");
		String concern=reader.readLine();
		newproduct.setHairConcerns(concern.substring(0,1).toUpperCase() + concern.substring(1));
		System.out.println("Is Uv Prptectant (yes or no) ");
		boolean Uv = reader.readLine().equalsIgnoreCase("yes");
		newproduct.setUvProtectant(Uv);
		System.out.println(newproduct.getProductName()+ " Successfully Created");

		// Add the product to the list of all Products
		haircareProducts.add(newproduct);
		allProducts.add(newproduct);

	}

	/**
	 * Method to search for a Haircare product by its ID
	 *@return Haircare object if found, null if not found
	 */
	private static Haircare findH() {
		for(Haircare h: haircareProducts) {
			System.out.println(h.getProductID()+" " + h.getProductName());
		}
		System.out.println();
		System.out.println("Choose Product By Product ID");
		int chosenID = input.nextInt();
		Haircare foundHaircare = null;
		boolean isFound = false;


		for (Haircare h : haircareProducts) {
			if (h.getProductID() == chosenID) {
				foundHaircare = h;
				isFound=true;
				break; 
			}
		}
		if(isFound==false) {
			System.out.println("No ID found. Try again");
			return findH();
		}
		return foundHaircare;
	}

	/**
	 * Allows the user to edit the details of a Haircare product. The user is prompted to choose which detail they want to change
	 * and then enter the new value for that detail.
	 *
	 * @param s the Haircare product to edit
	 * @throws Exception if there is an error reading user input
	 */
	private static void editHaircare(Haircare h) throws Exception{
		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {
		case "1":{
			System.out.println("Enter new product name to replace " + h.getProductName() );
			String name = reader.readLine();
			System.out.println(h.getProductName()+ " Successfully changed");
			h.setProductName(name.substring(0, 1).toUpperCase() + name.substring(1));
			break;
		}
		case "2":{
			System.out.println("Enter new product brand to replace " + h.getProductBrand());
			String brand = reader.readLine();
			System.out.println(h.getProductBrand()+ " Successfully changed");
			h.setProductBrand(brand.substring(0, 1).toUpperCase() + brand.substring(1));
			break;	
		}
		case "3":{
			System.out.println("Enter new product size to replace " + h.getProductSize());
			String size = reader.readLine();
			h.setProductSize(size.substring(0, 1).toUpperCase() + size.substring(1));
			System.out.println("Size Successfully changed");
			break;
		}
		case "4":{
			System.out.println("Enter new product price to replace " + h.getProductPrice());
			double price = input.nextDouble();
			h.setProductPrice(price);
			System.out.println("Price Successfully changed");
			break;

		}
		case "5":{
			System.out.println("Enter new product quantity to replace " + h.getProductQty());
			int qty = input.nextInt();
			h.setProductQty(qty);
			System.out.println("Quantity Successfully changed");
			break;

		}
		case "6":{
			System.out.println("Enter new Product SkinType to replace:" + h.getHairType());
			String hairType=reader.readLine();
			h.setHairType(hairType.substring(0,1).toUpperCase() + hairType.substring(1));
			System.out.println("Hairtype Successfully changed");
			break;
		}
		case "7":{
			System.out.println("Enter new Product Hair Concern:" + h.getHairConcerns());
			String concern=reader.readLine();
			h.setHairConcerns(concern.substring(0,1).toUpperCase() + concern.substring(1));
			System.out.println("Hair Concern Successfully changed");
			break;	
		}
		case "8":{
			System.out.println("Is UvProtectant (yes or no )");
			boolean isUv = reader.readLine().equalsIgnoreCase("yes");
			h.setUvProtectant(isUv);
			System.out.println("Uv Protection Succesfully Changed");
			break;
		}
		case "a":{
			adminMenu();
			break;
		}
		default: {
			System.out.println("Invalid Choice. Try again");
			break;
		}
		}


	}

	/**
	 *
	 *Finds a Haircare product by its ID and removes it from the list of all products and all Haircare products.
	 */
	private static void removeHaircare() {
		Haircare toRemove = findH();
		allProducts.remove(toRemove);
		haircareProducts.remove(toRemove);
		System.out.println(toRemove.getProductName()+" has been Removed.");
		System.out.println();
	}

	/**
	 * Method to display all Haircare products in the inventory
	 */
	private static void viewAllHaircare() {

		for (Haircare h: haircareProducts) {
			h.printProductdetails(h);
		}

	}

	/**

	 *This method displays all products in the inventory by category.
	 *It loops through each category and displays the products in that category.
	 *If no products are found in a category, it moves to the next category.
	 */
	private static void viewAllProductsByCategory() {
		for (String category : categories) {
			System.out.println("****** Category: " + category+ " *******");
			System.out.println();
			for (Products p : allProducts) {
				if (p.getCategory().equals(category)) {
					System.out.println(p.getProductID()+" \t "+ p.getProductName());
				}
			}
			System.out.println("***********************************");
			System.out.println();
		}
	}

	/**
	 * Displays various statistics about the transactions and products sold, and allows the user to return to the Admin menu.

	 */
	private static void viewStats() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to view Total Number of transactions");
		System.out.println("Press 2 to view Total Takings");
		System.out.println("Press 3 to View Total Number of Cancelled Transactions");
		System.out.println("Press 4 To view the total number of products sold both collectively and by category");
		System.out.println("Press a to Return to Admin menu");
		System.out.println("*********************************************");

		String choice = input.next();
		choice = choice.toLowerCase();

		switch(choice) {

		case "1":{// Display the total number of transactions	
			System.out.println("Total number of transactions: " + numTransactions);
			break;
		}
		case "2":{// Display the total takings
			DecimalFormat currency = new DecimalFormat("€0.00");
			System.out.println("Total Takings: " + currency.format(totalTakings));
			break;
		}
		case "3":{// Display the total number of cancelled transactions
			System.out.println("Total number of Cancelled transactions: " + cancelledTransaction);

			break;
		}
		case "4":{// Display the total number of products sold in each category and all categories
			int productSold;
			int totalProductSold = 0;
			System.out.println();
			for (String category : categories) {
				productSold =0;

				for (Products p : productsSold) { 

					if (p.getCategory().equals(category)) { 
						productSold = productSold += p.getProductQty(); 
					} 
				} 
				System.out.println("Products sold in " + category + " category:\t"+ productSold );

				totalProductSold = totalProductSold += productSold;
			}
			System.out.println();
			System.out.println("Total Products sold in all categories:\t"+ totalProductSold );
			break;

		}


		case "a":{
			adminMenu();
			break;
		}

		default:{
			System.out.println("Invalid choice. Please try again");
			viewStats();
			break;
		}

		}
		viewStats();


	}

	/**

	 *This method pre-populates the inventory with some initial products including skincare, haircare and fragrance products.
	 *It creates instances of each product and adds them to their respective lists: skincareProducts, haircareProducts and allFragrances.
	 *It also adds all the products to the allProducts list and adds the categories to the categories list.
	 */
	private static void prePopulate() {

		Skincare moist = new Skincare(101,"Moisturizer", "Neutrogena", "30ml", 12.99, 30,"Skincare", "Dry", "SPF 15", true);
		Skincare scrub = new Skincare(102,"Cocoa Scrub","Cetaphil","100ml",13.20,15,"Skincare", "Sensitive","N/A",false);
		Skincare toner = new Skincare(103,"Inkey Toner", "Mario Badescu", "50ml", 18.00, 25,"Skincare", "Oily", "N/A", false);
		Skincare serum = new Skincare(104,"Byoma Serum", "The Ordinary", "40ml", 9.99, 20,"Skincare", "Combination", "N/A", true);
		Skincare cleanser = new Skincare(105, "Face Cleanser", "Cetaphil", "150ml", 10.99, 50,"Skincare", "Normal", "SPF 30", false);

		skincareProducts.add(moist);
		skincareProducts.add(scrub);
		skincareProducts.add(toner);
		skincareProducts.add(serum);
		skincareProducts.add(cleanser);

		Haircare dCond = new Haircare(106,"Conditioner","Cantu","300ml",13.20,20,"Haircare","Curly","Frizz",false);
		Haircare shamp = new Haircare(107,"Shampoo    ","Shea Moisture","25ml",8.99,40,"Haircare","All","Frizz",false);
		Haircare oil = new Haircare(108,"Hair Oil    ","Shea Moisture","100ml",15.99,10,"Haircare","Straight","Frizz",false);
		Haircare leaveIn = new Haircare(109,"Leave-In    ","Tresemme","200ml",12.50,25,"Haircare","All","Frizz",true);
		Haircare butter = new Haircare(110,"Hair Gel    ","Garnier","500ml",20.99,30,"Haircare","Curly","Frizz",true);

		haircareProducts.add(dCond);
		haircareProducts.add(shamp);
		haircareProducts.add(oil);
		haircareProducts.add(leaveIn);
		haircareProducts.add(butter);

		Fragrance dior = new Fragrance(111,"Sauvage Dior ", "Dior", "60ml", 119.99, 15,"Fragrance", "Men", "Moderate");
		Fragrance chanel = new Fragrance(112,"Coco Amour  ", "Chanel", "50ml", 129.99, 20,"Fragrance", "Women", "Long-lasting");
		Fragrance armani = new Fragrance(113,"Acqua di Gio  ", "Giorgio Armani", "100ml", 149.99, 10,"Fragrance", "Men", "Moderate");
		Fragrance Ysl = new Fragrance(114,"Black Opium", "Yves Saint Laurent", "90ml", 129.99, 12,"Fragrance", "Women", "Moderate");
		Fragrance cloud = new Fragrance(115, "Cloud       ", "Ariana Grande", "100ml", 99.99, 5,"Fragrance", "Women", "Long-lasting");

		allFragrances.add(dior);
		allFragrances.add(chanel);
		allFragrances.add(armani);
		allFragrances.add(Ysl);
		allFragrances.add(cloud);


		allProducts.add(moist);
		allProducts.add(scrub);
		allProducts.add(toner);
		allProducts.add(serum);
		allProducts.add(cleanser);
		allProducts.add(dCond);
		allProducts.add(shamp);
		allProducts.add(oil);
		allProducts.add(leaveIn);
		allProducts.add(butter);
		allProducts.add(dior);
		allProducts.add(chanel);
		allProducts.add(armani);
		allProducts.add(Ysl);
		allProducts.add(cloud);

		categories.add("Skincare");
		categories.add("Haircare");
		categories.add("Fragrance");


	}

}
