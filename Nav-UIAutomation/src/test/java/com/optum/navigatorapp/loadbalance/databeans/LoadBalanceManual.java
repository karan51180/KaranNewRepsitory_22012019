package com.optum.navigatorapp.loadbalance.databeans;

public class LoadBalanceManual {

	    
	    public String	TestCaseId;
		public String	TestDescription;
		public String	RequestTitle;
		public String	dateAndTime;
		public String	WorkedInUnitedStates;
		public String	ESD;
		public String	VIPAction;
		public String	EnvironmentZone;
		public String	ChangeTicket;
		public String	TesterName;
		public String	TesterEmail;
		public String	TesterPhone;
		public String   SpecialInstruction;
		public String   CustomerNotes;
		public String	MSID;
		public String	role;
		public String	managerName;
		public String	attachments;
		public String   expectedErrorText;
		public String   confirmation;
		public String   UserImpact;
		public String   offshoreNote;
		public String   userimpactNote;

		@Override
		public String toString()
        {
			return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", TestCaseId,  RequestTitle, dateAndTime, WorkedInUnitedStates, ESD, VIPAction, UserImpact, EnvironmentZone, 
					ChangeTicket, TesterName, TesterEmail, TesterPhone, SpecialInstruction, CustomerNotes, MSID, role, confirmation, offshoreNote, userimpactNote);
		}


}

