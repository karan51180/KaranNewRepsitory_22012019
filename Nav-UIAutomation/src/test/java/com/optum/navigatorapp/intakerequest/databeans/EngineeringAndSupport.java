package com.optum.navigatorapp.intakerequest.databeans;

public class EngineeringAndSupport {

	public String	testCaseID;
	public String	testDescription;
	public String	RequestTitle;
	public String	WorkedInUnitedStates;
	public String	ActionType;
	public String	DetailedDescription;
	public String	PeakSesaon;
	public String	ImpactIfAfterPeakSeason;
	public String	PerformedScheduled;
	public String	MSID;
	public String	role;
	public String	attachments;
	public String	notes;
	public String	expectedErrorText;
	public String	managerName;
	public String	cortianExtraFields;
	public String	networkPortFields;
	public String expRequestorInformation;
	public String expRequestInformation;
	public String requestStatus;
	public String assignResource;
	public String estimatedHours;
	public String actualHours;
	public String comments;


	@Override
	public String toString() {
		return String.format("%s, %s ,%s, %s,%s,%s,%s, %s ,%s, %s,%s", testCaseID, testDescription, RequestTitle, WorkedInUnitedStates, ActionType, DetailedDescription, PeakSesaon, ImpactIfAfterPeakSeason, PerformedScheduled, MSID, role);
	}
}
