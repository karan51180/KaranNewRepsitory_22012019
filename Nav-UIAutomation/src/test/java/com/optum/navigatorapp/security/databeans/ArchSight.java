package com.optum.navigatorapp.security.databeans;

public class ArchSight {

	public String	testCaseID;
	public String	testDescription;
	public String	requestTitle;
	public String	inegrationType;
	public String	sourceEnvironment;
	public String 	actionPlanPex;
	public String 	technicalSME;
	public String	helDeskGroup;
	public String	projectName;
	public String	projectNumber;
	public String	productName;
	public String	manufacturer;
	public String	versionNumber;
	public String	productType;
	public String 	aSKID;
	public String 	productDescription;
	public String 	reasonforIntegration;
	
	public String 	loggingMethod;
	public String	networkZone;
	public String 	flatFileTransportMethod;
	public String 	domain;
	public String 	estimatedEventCount;
	public String 	estimatedEventCountType;
	public String 	hosts;
	public String 	logMessageSamples;
	public String 	actionableEvents;
	public String	attachments;
	public String	notes;
	public String	MSID;
	public String	role;
	public String 	cyberSecurityVerification;
	public String 	contentVerification;
	public String 	cEFSyslog;
	public String   windowsEventsDetails;
	public String	dURequest;
	public String 	requestCategory;
	public String	requestSubCategory;
	public String	requestDepartment;
	public String   details;
	public String	justificationStatement;
	public String 	requestTypeOtherValue;
	public String	zoneOtherValue;
	public String   zone;
	
	

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", testCaseID,testDescription,requestTitle,inegrationType,sourceEnvironment,actionPlanPex,technicalSME,helDeskGroup,projectName,projectNumber,productName,manufacturer,versionNumber,productType,aSKID,productDescription,reasonforIntegration,loggingMethod,networkZone,flatFileTransportMethod,domain,estimatedEventCount,estimatedEventCountType,hosts,logMessageSamples,actionableEvents,attachments,notes,MSID,cyberSecurityVerification,contentVerification,MSID,role,cEFSyslog,windowsEventsDetails,dURequest,requestCategory,requestSubCategory,requestDepartment,details,justificationStatement);
	}
	
	
}
