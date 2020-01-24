package com.optum.navigatorapp.enterpriseStorageServices.databeans;

public class NasShareAllocation {
	public String testCaseID;
	public String testDescription;
	public String RequestTitle;
	public String RequestType;
	public String InfrastructureService;
	public String PPMOProjectNumber;
	public String ApplicationOptions;
	public String ASKApplicationName;
	public String ListOfASK;
	public String AdminGroup;
	public String Backup;
	public String Storage;
	public String DataCenter;
	public String EnvironmentZone;
	public String NetworkZone;
	public String InterfaceZone;
	public String Protocol;
	public String ShareName;
	public String GlobalGroupsForModify;
	public String GlobalGroupsForReadOnly;
	public String ServiceAccountForModify;
	public String ServiceAccountForReadOnly;
	public String MountPoint;
	public String ServerName;
	public String DMZGlobalGroups;
	public String DMZServiceAccount;
	public String AdditionalUsers;
	public String MSID;
	public String role;
	public String ManagerName;
	
	@Override
	public String toString() {
		return "NasShareAllocation [testCaseID=" + testCaseID + ", testDescription=" + testDescription
				+ ", RequestTitle=" + RequestTitle + ", RequestType=" + RequestType + ", InfrastructureService="
				+ InfrastructureService + ", PPMOProjectNumber=" + PPMOProjectNumber + ", ApplicationOptions="
				+ ApplicationOptions + ", ASKApplicationName=" + ASKApplicationName + ", ListOfASK=" + ListOfASK
				+ ", AdminGroup=" + AdminGroup + ", Backup=" + Backup + ", Storage=" + Storage + ", DataCenter="
				+ DataCenter + ", EnvironmentZone=" + EnvironmentZone + ", NetworkZone=" + NetworkZone
				+ ", InterfaceZone=" + InterfaceZone + ", Protocol=" + Protocol + ", ShareName=" + ShareName
				+ ", GlobalGroupsForModify=" + GlobalGroupsForModify + ", GlobalGroupsForReadOnly="
				+ GlobalGroupsForReadOnly + ", ServiceAccountForModify=" + ServiceAccountForModify
				+ ", ServiceAccountForReadOnly=" + ServiceAccountForReadOnly + ", MountPoint=" + MountPoint
				+ ", ServerName=" + ServerName + ", DMZGlobalGroups=" + DMZGlobalGroups + ", DMZServiceAccount="
				+ DMZServiceAccount + ", AdditionalUsers=" + AdditionalUsers + ", MSID=" + MSID + ", role=" + role
				+ ", ManagerName=" + ManagerName + "]";
	}
	
	

}
