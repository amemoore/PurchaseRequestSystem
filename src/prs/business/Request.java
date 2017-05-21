package prs.business;

import java.sql.Date;

public class Request {

	private String description;
	private String justification;
	private Date dateNeeded;
	private int userID;
	private String deliveryMode;
	private boolean docAttached;
	private String status;
	private double total;
	private Date dateSubmitted;
	
public Request (String d, String j, Date dtNd, int u, String dM, boolean dA, String s, double t, Date dS){
	description = d;
	justification = j;
	dateNeeded = dtNd;
	userID = u;
	deliveryMode = dM;
	docAttached = dA;
	status = s;
	total = t;
	dateSubmitted = dS;
}

public Date getDateSubmitted() {
	return dateSubmitted;
}

public void setDateSubmitted(Date dateSubmitted) {
	this.dateSubmitted = dateSubmitted;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getJustification() {
	return justification;
}

public void setJustification(String justification) {
	this.justification = justification;
}

public Date getDateNeeded() {
	return dateNeeded;
}

public void setDateNeeded(Date dateNeeded) {
	this.dateNeeded = dateNeeded;
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}

public String getDeliveryMode() {
	return deliveryMode;
}

public void setDeliveryMode(String deliveryMode) {
	this.deliveryMode = deliveryMode;
}

public boolean isDocAttached() {
	return docAttached;
}

public void setDocAttached(boolean docAttached) {
	this.docAttached = docAttached;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}
public String toString(){
	return dateNeeded + " " + description;
	
}

}

