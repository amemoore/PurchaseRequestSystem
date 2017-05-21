package prs.console;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import prs.business.LineItem;
import prs.business.Product;
import prs.business.Request;
import prs.business.User;
import prs.business.Vendor;
import prs.db.DAOFactory;
import prs.lineitem.db.LineItemDAO;
import prs.product.db.ProductDAO;
import prs.request.db.RequestDAO;
import prs.user.db.UserDAO;
import prs.util.Validator;
import prs.vendor.db.VendorDAO;

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
	private static ArrayList<Request> requests;
	private static ArrayList<LineItem> lineitems;
	private static ArrayList<LineItem> finallineitems;
	private static Vendor v;
	private static Product p;
	private static LineItem li;
	private static User u;
	private static Request r;
	
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
						doTransaction();
						break;
					case 4:
						//Purchase Request status
						break;
					case 5:
						listAllVendors();
						getVendorsByNameOrState();
						break;
					case 6:
						listAllProducts();
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

/////Login or Register User/////////////////////////////////////////////////////////////////////	
		public static void loginRegister(){
				String inputtedUsername = "";
				boolean isValid = false;
				while (isValid== false){
					int choice = Validator.getInt(sc, "Login (1) or Register (2)", 0, 3);
					if (choice==1){
						inputtedUsername = Validator.getString(sc, "Enter username:  ");
						u = usersDAO.getUser(inputtedUsername);
							if (u.getUserName()!=null) {
								System.out.println("Welcome " + u.getUserName());
								isValid = true;	
							}
							else 
								System.out.println("Invalid username.");
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
			Boolean manager = getYesNo(sc, "Are you a manager? (y/n):  " );
			User u = new User(userName, password, firstName, lastName, phone, email, manager);
			usersDAO.addUser(u);
		}
		
		public static boolean getYesNo(Scanner sc, String prompt){
			boolean yesNo = false;
			String choice = Validator.getString(sc, prompt);
				if (choice.equalsIgnoreCase("y"))
					yesNo = true;
				return yesNo;
		}
		
/////Creating purchase request - Holding all db interactions until end//////////////////////////
		public static Request createRequest(){
			String description = Validator.getLine(sc, "Enter a short description:  ");
			String justification = Validator.getLine(sc, "Enter a justification for the purchase:  ");
			String dateNeeded = Validator.getString(sc, "Enter the date needed (yyyy-mm-dd):  ");
				java.sql.Date javaSqlDateNeeded = java.sql.Date.valueOf(dateNeeded);
			String userName = Validator.getString(sc, "Enter userName:  ");
			String deliveryMode = Validator.getString(sc, "Enter mode of delivery (pickup or mail):  ");
			Boolean docAttached = getYesNo(sc, "Supporting documents? (yes/no):  ");
			String status = "new";
			double total = Validator.getDouble(sc, "Enter the  price:  ");
			String dateSubmitted = Validator.getString(sc, "Enter the date of submission (yyyy-mm-dd):  ");
				java.sql.Date javaSqlDateSubmitted = java.sql.Date.valueOf(dateSubmitted);
			//Extract userId in order to make the Request object r.
			User u = usersDAO.getUser(userName);
			int userID = u.getuID();
			Request r = new Request(description, justification,javaSqlDateNeeded, userID, deliveryMode, 
				docAttached, status, total, javaSqlDateSubmitted);
			return r;
		}	
			
		//Creating a lineitem - User choosing from vendor and products.
		public static ArrayList<LineItem> getLineItemsForRequest(){
			lineitems = new ArrayList<>();
			listAllVendors();
			String pickVendor = Validator.getLine(sc, "Please pick a vendor from the above list:  ");
			v = vendorsDAO.getVendorByName(pickVendor);
			System.out.println(v.getName() + " has the following products available: \n");
			products = productsDAO.getProductsForVendor(v.getvId());
			System.out.println("Price\tName\t\tUnit");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
			String choice = "y";
			while (choice.equalsIgnoreCase("y")){
				for (Product p: products){
					System.out.println(p.getPrice() +"\t"+ p.getName() +"\t"+ p.getUnit() );
				}
				String pickProduct = Validator.getLine(sc, "Please enter the product name for purchase request: ");
				int pickQuantity = Validator.getInt(sc, "Please enter the quantity: ");
				p = productsDAO.getProduct(pickProduct);
				System.out.println(p);
				int pi = p.getProductID();
				li = new LineItem(pi, pickQuantity);
				lineitems.add(li);
				System.out.println(li);
				choice = Validator.getString(sc, "Continue? (y/n):  ");
			}
			return lineitems;
		}
		//Pulling together all purchase request methods into one transaction.
		public static void doTransaction(){
			LineItem finalLi; 
				r = createRequest();
				//System.out.println(r);
				lineitems = getLineItemsForRequest();
				//System.out.println(lineitems);
				requestsDAO.addRequest(r);
				int rId = requestsDAO.getRequestId();
				
				for (LineItem li:lineitems){
					finalLi = new LineItem (rId, li.getProductID(), li.getQuantity());
					lineitemsDAO.addLineItem(finalLi);
				}
		}
		
 /////Providing Vendor list and Vendor search./////////////////////////////////////////////////////		
		public static void listAllVendors(){
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
		
/////Providing complete product list (all vendors)./////////////////////////////////////////////////
		public static void listAllProducts(){
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
