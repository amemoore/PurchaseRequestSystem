package prs.console;

import java.util.ArrayList;
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
	private static ArrayList<LineItem> lineitems;
	private static Vendor v;
	private static Product p;
	private static LineItem li;
	private static User u;
	private static Request r;
	private static Boolean b;
	
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
//FOR SERVLET START SEPARATE EMPLOYEE AND MANAGER LOGINS - THE MANAGER APPROVE REQUEST METHOD INCORPORATES
// THE CHECKREQUEST METHOD.
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
						checkRequest();
						break;
					case 5:
						listAllVendors();
						getVendorsByNameOrState();
						break;
					case 6:
						listAllProducts();
						listPreapprovedVendors();
						break;
					case 7:
						approveRequest();
				}
				choice = Validator.getString(sc, "Continue application? (y/n)");
				if (choice.equalsIgnoreCase("n"))
					System.out.println("Good-Bye");
			}
	}
		public static void displayMenu(){
			String menu = "1 - Login or Register\n"
						+ "2 - Welcome Page\n"
						+ "3 - Create New Purchase Request\n"
						+ "4 - Status of Purchase Requests\n"
						+ "5 - Lists of Vendors\n"
						+ "6 - Lists of Products\n"
						+ "7 - Manager Approval\n";
			System.out.println(menu);
		}

//Login or Register Choice	
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
	
//Register User
		public static void registerUser(){
			String userName = Validator.getString(sc, "Enter user name:  ");
			String password = Validator.getString(sc, "Enter password:  ");
			String firstName = Validator.getString(sc, "Enter first name:  ");
			String lastName = Validator.getString(sc, "Enter last name:  ");
			String phone = Validator.getString(sc, "Enter phone:  ");
			String email = Validator.getString(sc, "Enter email:  ");
			Boolean manager = Validator.getBoolean(sc, "Are you a manager? (y/n):  ", b );
			User u = new User(userName, password, firstName, lastName, phone, email, manager);
			usersDAO.addUser(u);
		}

//Creating Purchase Request
		public static Request createRequest(){
			String description = Validator.getLine(sc, "Enter a short description:  ");
			String justification = Validator.getLine(sc, "Enter a justification for the purchase:  ");
			String dateNeeded = Validator.getString(sc, "Enter the date needed (yyyy-mm-dd):  ");
				java.sql.Date javaSqlDateNeeded = java.sql.Date.valueOf(dateNeeded);
			String userName = Validator.getString(sc, "Enter userName:  ");
			String deliveryMode = Validator.getString(sc, "Enter mode of delivery (pickup or mail):  ");
			boolean docAttached = Validator.getBoolean(sc, "Supporting documents? (yes/no):  ", b);
			String status = "new";
			double total = Validator.getDouble(sc, "Enter the  estimated price:  ");
			String dateSubmitted = Validator.getString(sc, "Enter the date of submission, today's date (yyyy-mm-dd):  ");
				java.sql.Date javaSqlDateSubmitted = java.sql.Date.valueOf(dateSubmitted);
			
			User u = usersDAO.getUser(userName);	//Extract userId in order to make the Request object r
			int userID = u.getuID();				//Request object has userId, not userName
			Request r = new Request(description, justification,javaSqlDateNeeded, userID, deliveryMode, 
				docAttached, status, total, javaSqlDateSubmitted);
			return r;
		}	
		
//Creating a Lineitem - User Choosing From Vendor and Products
		public static ArrayList<LineItem> getLineItemsForRequest(){
			lineitems = new ArrayList<>();
			listAllVendors();
			int pickVendor = Validator.getInt(sc, "Pick a Vendor No. from the above list:  ");
			v = vendorsDAO.getVendorByVendorId(pickVendor);
			System.out.println(v.getName() + " has the following products available: \n");
			products = productsDAO.getProductsForVendor(v.getvId());
			System.out.println("Price\tName\t\tUnit");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
			String choice = "y";
			while (choice.equalsIgnoreCase("y")){
				for (Product p: products){
					System.out.println(p.getProductID() +"\t"+ p.getPrice() +"\t"+ p.getName() +"\t"+ p.getUnit() );
				}
				int pickProduct = Validator.getInt(sc, "Please enter the product by Product No.: ");
				int pickQuantity = Validator.getInt(sc, "Please enter the quantity: ", 0, 20);
				p = productsDAO.getProductByProductNo(pickProduct);
				System.out.println(p);
				int pi = p.getProductID();
				li = new LineItem(pi, pickQuantity);
				lineitems.add(li);
				choice = Validator.getString(sc, "Continue adding products? (y/n):  ");
			}
			return lineitems;
		}
//Pulling Together All Purchase Request Methods Into One Transaction
		public static void doTransaction(){
			LineItem finalLi; 
			lineitems = getLineItemsForRequest();
			r = createRequest();
			requestsDAO.addRequest(r);
			int rId = requestsDAO.getRequestId();
			System.out.println(rId);
				
			for (LineItem li:lineitems){
				finalLi = new LineItem (rId, li.getProductID(), li.getQuantity());
				System.out.println(finalLi);
				lineitemsDAO.addLineItem(finalLi);
				}
			if (r.getTotal() < 50.0){
				requestsDAO.approveRequestUnderFifty(rId);
				System.out.println("Request has been created and approved.");
				}
			else if (r.getTotal() > 49.99){
				System.out.println("Request has not been approved.  Please wait for manager approval.");
			}
		}

//Employee - Check Status of Purchase Request
		public static void checkRequest(){
			ArrayList<String> requestStatus = new ArrayList<>();
			requestStatus = requestsDAO.checkRequest();
			System.out.println("Employee Name\tProduct Name\t\tPrice\t\tQuantity\tDate Needed\tDate Submiteed\tStatus");
			System.out.println("----------------------------------------------------------------------------------------");
			for (int i=0; i<requestStatus.size(); i++){
				System.out.print(requestStatus + "\t");
			}
			System.out.println();
		}
		
//Manager - Check Status of Purchase Request and Approval 
		public static void approveRequest(){
			int requestId = 0;
			checkRequest();
			requestId = Validator.getInt(sc, "Please enter the first Request No. from the above list for "
					+ "approval (or enter 0 to exit):  ");
			if (requestId!=0 && requestsDAO.approveRequest(requestId)==1){
				System.out.println("Request No. " + requestId + " was updated.");
			}
		}
		
//Providing Complete Vendor List		
		public static void listAllVendors(){
			vendors = vendorsDAO.getAllVendors();
			System.out.println("Vendor No.\\Name\t\tPhone\t\tEmail\tPre-approval");
			System.out.println("---------------------------------------------------------------------");
			for (Vendor v : vendors){
					System.out.println(v);
			}
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Please Note:  It is preferable to pick a pre-approved vendor.\n");
		}

//Searching By Vendor Name Or Vendor State	
		public static void getVendorsByNameOrState(){
			String searchName = "";
			String searchState = "";
			String choice = "y";
			
			while (choice.equalsIgnoreCase("y")){
				String userInput = Validator.getString(sc, "Would you like to search by name or state? (name/state/no)");
					
					if (userInput.equalsIgnoreCase("name")){	
							searchName = Validator.getLine(sc, "Enter the vendor name you would like to search for: ");
							v = vendorsDAO.getVendorByName(searchName);
							if (vendors.isEmpty())
								System.out.println("There are no vendors available with that name.");
							else{
								System.out.println("Vendor No.\\Name\t\tPhone\t\tEmail\t\t\tPre-approval");
								System.out.println("---------------------------------------------------------------------");
										System.out.println(v);
								System.out.println("---------------------------------------------------------------------");
								System.out.println("Please Note:  It is preferable to pick a pre-approved vendor.\n");
							}
							choice = Validator.getString(sc, "Another search? (y/n)");	
					}
					else if (userInput.equalsIgnoreCase("state")){
							searchState = Validator.getString(sc, "Enter the state you would like to search for: ");
							vendors = vendorsDAO.getVendorByState(searchState);
							if (vendors.isEmpty())
								System.out.println("There are no vendors available in that state.");
							
							else {
								System.out.println("Vendor No.\\Name\t\tPhone\t\tEmail\t\t\tPre-approval");
								System.out.println("---------------------------------------------------------------------");
									for (Vendor v: vendors){
										System.out.println(v);
									}
								System.out.println("---------------------------------------------------------------------");
								System.out.println("Please Note:  It is preferable to pick a pre-approved vendor.\n");
							}
							choice = Validator.getString(sc, "Another search? (y/n)");	
					}
					else if (userInput.equalsIgnoreCase("no")){
						choice = "n";
						break;
					}
			}
		}
		
//Providing Complete Product List (For All Vendors)
		public static void listAllProducts(){
			products = productsDAO.getAllProducts();
			System.out.println("COMPLETE PRODUCT LIST");
			System.out.println("Vendor#\tPart#\tProduct\t\tPrice\tUnit\tPhoto Link");
				for (Product p : products){
						System.out.println(p.getVendorID() +"\t"+ p.getPartNumber() +" \t"+ p.getName() 
														   +"\t"+ p.getPrice() +"\t"+ p.getUnit() +"\t"+ p.getPhotoPath());
				}
		}
		
//List Pre-approved Vendors
		public static void listPreapprovedVendors(){
			vendors = vendorsDAO.listPreapprovedVendors();
			System.out.println("Pre-approved Vendors");
			System.out.println("---------------------");
			for (Vendor v : vendors){
					System.out.println(v.getName());
			}
			System.out.println("----------------------");
		}
}
