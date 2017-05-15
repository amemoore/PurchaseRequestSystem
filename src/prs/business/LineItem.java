package prs.business;

public class LineItem {

	private int requestID;
	private int productID;
	private int quantity;
	
	public LineItem(int rID, int pID, int q){
		requestID = rID;
		productID = pID;
		quantity = q;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
