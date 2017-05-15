package prs.console;

import java.util.ArrayList;
import java.util.Scanner;
import lineitem.db.LineItemDAO;
import product.db.ProductDAO;
import prs.business.Product;
import prs.business.User;
import prs.business.Vendor;
import prs.db.DAOFactory;
import prs.util.Validator;
import request.db.RequestDAO;
import user.db.UserDAO;
import vendor.db.VendorDAO;

public class PRSMainApp {
	
	private static VendorDAO vendorsDAO = null;
	private static ProductDAO productsDAO = null;
	private static RequestDAO requestsDAO = null;
	private static LineItemDAO lineitemsDAO = null;
	private static UserDAO usersDAO = null;
	private static Scanner sc;
	private static ArrayList<Vendor> vendors;
	private static ArrayList<Product> products;
	private static ArrayList<User> users;
	private static Vendor v;
	
		public static void main(String[] args) {
			int menuOption;
			vendorsDAO = DAOFactory.getVendorDAO();
			productsDAO = DAOFactory.getProductDAO();
			requestsDAO = DAOFactory.getRequestDAO();
			lineitemsDAO = DAOFactory.getLineItemDAO();
			usersDAO = DAOFactory.getUserDAO();
			sc = new Scanner(System.in);
			String choice = "y";
		
			while (choice.equalsIgnoreCase("y")){
				displayMenu();
				menuOption = Validator.getInt(sc, "Please choose a menu option:  ");
				
				switch (menuOption){
					case 1:
						loginRegister();
						break;
					case 2:
						//Welcome page
						break;
					case 3:
						//new purchase request
						break;
					case 4:
						//Purchase Request status
						break;
					case 5:
						listVendors();
						getVendorsByNameOrState();
						break;
					case 6:
						listProducts();
						//listTopProducts();
				}
				choice = Validator.getString(sc, "Continue? (y/n)");
			}
	}
		public static void displayMenu(){
			String menu = "1 - Login/Register\n"
						+ "2 - Welcome Page\n"
						+ "3 - New Purchase Request\n"
						+ "4 - Status of Purchase Requests\n"
						+ "5 - Vendors\n"
						+ "6 - Products\n"
						+ "7 - Manager Approval\n";
			System.out.println(menu);
		}

		public static void loginRegister(){
				String inputtedUsername = "";
				boolean isValid = false;
				while (isValid== false){
					int choice = Validator.getInt(sc, "Login (1) or Register (2)", 0, 3);
					if (choice==1){
						inputtedUsername = Validator.getString(sc, "Enter username:  ");
						users = usersDAO.getUser(inputtedUsername);
							if (users!=null) {
								for (User u : users){
									System.out.println("Welcome " + u.getUserName());
								}
								isValid = true;
							}
							else {
								System.out.println("Invalid username.");
							}
					}
					else if (choice==2){
						registerUser();
						isValid = true;
					}
				}
		}		
		
		public static void registerUser(){
			String userName = Validator.getString(sc, "Enter user name:  ");
			String password = Validator.getString(sc, "Enter password:  ");
			String firstName = Validator.getString(sc, "Enter first name:  ");
			String lastName = Validator.getString(sc, "Enter last name:  ");
			String phone = Validator.getString(sc, "Enter phone:  ");
			String email = Validator.getString(sc, "Enter email:  ");
			Boolean manager = managerYesNo();
			User u = new User(userName, password, firstName, lastName, phone, email, manager);
			usersDAO.addUser(u);
		}
		///Change this to a generic boolean method to re-use	
		public static boolean managerYesNo(){
			boolean manager = false;
			String choice = Validator.getString(sc, "Are you a manager? (y/n):  ");
				if (choice.equalsIgnoreCase("y"))
					manager = true;
				return manager;
		}
//		public static void newPurchaseRequest(){
//			String description = Validator.getString(sc, "Enter a short description:  ");
//			String justification = Validator.getString(sc, "Enter a justification for the purchase:  ");
//			String dateNeeded = Validator.getString(sc, "Enter the date needed:  ");
//			String userName = Validator.getString(sc, "Enter userName:  ");
//			String deliveryMode = Validator.getString(sc, "Enter mode of delivery (pickup or mail):  ");
//			//boolean docAttached = Validator.getString(sc, "Supporting documents? (yes/no):  ");
//			Boolean manager = managerYesNo();
//			String status = "new";
//			double total = Validator.getDouble(sc, "Enter the  price:  ");
//			
//			User u = new User(userName, password, firstName, lastName, phone, email, manager);
//			usersDAO.addUser(u);
//		}
		
		
		public static void listVendors(){
			vendors = vendorsDAO.getAllVendors();
			System.out.println("Name\t\t\tAddress\\City\\State\\Zip\t\t\tPhone\t\t\tEmail\t");
			for (Vendor v : vendors){
					System.out.println(v.getName()+"\t"+ v.getAddress() +"\\"+ v.getCity()+"\\"+ v.getState() 
													+"\\"+ v.getZip() +"\t"+ v.getPhone() +"\t"+ v.getEmail()+"\n");
			}
		}
		
		public static void getVendorsByNameOrState(){
			String searchName = "";
			String searchState = "";
			String choice = "y";
			
			while (choice.equalsIgnoreCase("y")){
				String userInput = Validator.getString(sc, "Would you like to search by name or state? (name/state/neither)");
					
					if (userInput.equalsIgnoreCase("name")){	
							searchName = Validator.getLine(sc, "Enter the vendor name you would like to search for: ");
							System.out.println("Code\tName\t\t\tPre-approval\tPhone\t\tEmail\t\t\tAddress\\City\\State\\Zip");
							v = vendorsDAO.getVendorByName(searchName);
								System.out.println(v.getCode() +"\t"+ v.getName() +"\t"+ v.isPreapproved()+"\t\t"+ v.getPhone() 
													+"\t" + v.getEmail() +"\t" +  v.getAddress() +"\\"+
													v.getCity() +"\\"+ v.getState() +"\\"+ v.getZip());
								choice = Validator.getString(sc, "Continue? (y/n)");
						}	
					else if (userInput.equalsIgnoreCase("state")){
							searchState = Validator.getString(sc, "Enter the state you would like to search for: ");
							
							vendors = vendorsDAO.getVendorByState(searchState);
							if (vendors.isEmpty())
								System.out.println("There are no vendors available in that state.");
							else if (userInput.equalsIgnoreCase("neither")){
								choice = "n";
								break;
							}
							else{
								System.out.println("Code\tName\t\t\tPre-approval\tPhone\t\tEmail\t\t\tAddress\\City\\State\\Zip");
									for (Vendor v: vendors){
										System.out.println(v.getCode() +"\t"+ v.getName() +"\t"+ v.isPreapproved()+"\t\ts"+ v.getPhone() 
															+"\t" + v.getEmail() +"\t" +  v.getAddress() +"\\"+
															v.getCity() +"\\"+ v.getState() +"\\"+ v.getZip());
									}
									choice = Validator.getString(sc, "Continue? (y/n)");		
							}
					}
					
			}
		}
		
		public static void listProducts(){
			products = productsDAO.getAllProducts();
			System.out.println("Vendor#\tPart#\tProduct\t\tPrice\tUnit\tPhoto Link");
				for (Product p : products){
						System.out.println(p.getVendorID() +"\t"+ p.getPartNumber() +" \t"+ p.getName() 
														   +"\t"+ p.getPrice() +"\t"+ p.getUnit() +"\t"+ p.getPhotoPath());
				}
		}
		//LIST TOP 5 PRODUCTS BASED ON ???Join tables?? Most requested? or cheapest?
		public static void listTopProducts(){
			
		}
		
}
