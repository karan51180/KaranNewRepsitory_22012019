package com.optum.navigatorapp.SecurityRequest.databeans;

public class SecurityRequest {

	public String testCaseID;
	public String testDescription;
	public String RequestTitle;
	public String SecurityRequest;
	public String RequestforAE;
	public String ServiceCategory;
	public String Criticalbusinessapplication;
	public String workedinUS;
	public String Lineofbusiness;
	public String DetailedDescription;
	public String Attachments;
	public String Notes;
	public String Rulesforuse;
	public String Apllicationcheckout;
	public String managerName;
	public String MSID;
	public String role;
 public String expRequestorInformation;
 public String expRequestInformation;
 public String requestStatus;
 public String assignResource;
	
	public String ReasonReview;
	@Override
	public String toString() {
		return String.format("%s, %s ,%s, %s,%s,%s,%s, %s ,%s, %s,%s,%s,%s", testCaseID, testDescription, RequestTitle,
				SecurityRequest, RequestforAE, ServiceCategory, Criticalbusinessapplication, workedinUS, Lineofbusiness,
				DetailedDescription, MSID, role,ReasonReview);
	}
}
