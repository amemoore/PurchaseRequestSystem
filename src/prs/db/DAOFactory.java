package prs.db;

import lineitem.db.LineItemDAO;
import lineitem.db.LineItemDB;
import product.db.ProductDAO;
import product.db.ProductDB;
import request.db.RequestDAO;
import request.db.RequestDB;
import user.db.UserDAO;
import user.db.UserDB;
import vendor.db.VendorDAO;
import vendor.db.VendorDB;

public class DAOFactory {
		public static LineItemDAO getLineItemDAO(){
			LineItemDAO lineitemsDAO = new LineItemDB();
				return lineitemsDAO;
			}
		public static ProductDAO getProductDAO(){
				ProductDAO productsDAO = new ProductDB();
				return productsDAO;
			}
		public static RequestDAO getRequestDAO(){
			RequestDAO requestsDAO = new RequestDB();
				return requestsDAO;
			}	
		public static UserDAO getUserDAO(){
			UserDAO usersDAO = new UserDB();
				return usersDAO;
			}	
		public static VendorDAO getVendorDAO(){
			VendorDAO vendorsDAO = new VendorDB();
				return vendorsDAO;
			}	
	}

