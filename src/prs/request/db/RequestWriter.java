package prs.request.db;

import prs.business.Request;

	public interface RequestWriter {
	
	public void addRequest(Request r);
	
	public int approveRequest(int requestId);
	
	public int approveRequestUnderFifty(int rId);

}
