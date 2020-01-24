package com.optum.navigatorapp.intakerequest.databeans;

public class VendorAndThridParty {

	public String	MSID;
	public String   role;
	public String	testCaseID;
	public String	testDescription;
	public String	RequestAction;
	public String	RequestTitle;
	public String	WorkedinUnitedStates;
	public String   ConnectingEntity;
	public String   SUI;
	public String   PHIInvolved;
	public String	ThridPartyName;
	public String	SecureShareRequired;
	public String	ReasonForConnection;
	public String	DuringPeakSeason;
	public String	AfterPeakSeason;
	public String	PerformedasScheduled;
	public String	BusinessName;
	public String	ContactName;
	public String	ContactEmail;
	public String	CustomerNotes;
	public String   attachments;
	public String   ContractIDNumber;
	public String   VendorId;
	public String   EngagementId;
	public String   TrackingId;
	public String   RequestSubmission;
	public String   expRequestorInformation;

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", testCaseID, testDescription, RequestAction, RequestTitle, WorkedinUnitedStates, ConnectingEntity, SUI, PHIInvolved, ThridPartyName, SecureShareRequired, ReasonForConnection, DuringPeakSeason, AfterPeakSeason, PerformedasScheduled, BusinessName, ContactName, ContactEmail, CustomerNotes, attachments, ContractIDNumber, VendorId, EngagementId, TrackingId, RequestSubmission, expRequestorInformation );
	}
}
