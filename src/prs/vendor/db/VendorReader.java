package prs.vendor.db;

import java.util.ArrayList;
import prs.business.Vendor;

public interface VendorReader {
	
	public ArrayList<Vendor> getAllVendors();
	public Vendor getVendorByName(String nm);
	public ArrayList<Vendor> getVendorByState(String st);
}
