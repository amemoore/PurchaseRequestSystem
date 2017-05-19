package prs.db;

import prs.lineitem.db.LineItemDAO;
import prs.lineitem.db.LineItemDB;
import prs.product.db.ProductDAO;
import prs.product.db.ProductDB;
import prs.request.db.RequestDAO;
import prs.request.db.RequestDB;
import prs.user.db.UserDAO;
import prs.user.db.UserDB;
import prs.vendor.db.VendorDAO;
import prs.vendor.db.VendorDB;

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

