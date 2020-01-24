package com.optum.navigatorapp.intakerequest.databeans;

public class Login {

public String testCaseID;
public String testDescription;
public String UserName;
public String Password;

@Override
public String toString() {
	return String.format("%s, %s ,%s, %s" ,testCaseID, testDescription,UserName,Password);
}
}
