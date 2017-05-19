package prs.business;

public class Product {
	private int productID;
	private String name;
	private String partNumber;
	private double price;
	private String unit;
	private int vendorID;
	private String photoPath;
	
	public Product(int pI, String n,String pN, double p, String u, int vID, String pP){
		productID = pI;
		name = n;
		partNumber = pN;
		price = p;
		unit = u;
		vendorID = vID;
		photoPath = pP;
	}

	public int getProductID() {
		return productID;
	}

	public void setpID(int pID) {
		this.productID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String toString(){
		return name + " " + unit + " " + price;
	}
}
