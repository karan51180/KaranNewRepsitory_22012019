package com.optum.navigatorapp.intakerequest.testDataProviders;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import com.optum.automation.coreframework.dataprovider.TestData;
import com.optum.navigatorapp.intakerequest.databeans.EngineeringAndSupport;

public class EngSupportTestDataProvider {
	@DataProvider(name = "ValidRequestData")
	public static Iterator<Object[]> ValidData() {

		return TestData.getData(EngineeringAndSupport.class, "ValidRequestData");

	}

	@DataProvider(name = "InValidData")
	public static Iterator<Object[]> InValidData() {

		return TestData.getData(EngineeringAndSupport.class, "InValidData");

	}
	@DataProvider(name = "NtwMngrAssignment")
	public static Iterator<Object[]> NtwMngrAssignment() {

		return TestData.getData(EngineeringAndSupport.class, "NtwMngrAssignment");

	}
	@DataProvider(name = "ReturnFlowByNWEngr")
	public static Iterator<Object[]> ReturnFlowByNWEngr() {

		return TestData.getData(EngineeringAndSupport.class, "ReturnFlowByNWEngr");

	}
	@DataProvider(name = "ReviewFlowByBSRqstr")
	public static Iterator<Object[]> ReviewFlowByBSRqstr() {

		return TestData.getData(EngineeringAndSupport.class, "ReviewFlowByBSRqstr");

	}
	@DataProvider(name = "CancelFlowByBSReq")
	public static Iterator<Object[]> CancelFlowByBSReq() {

		return TestData.getData(EngineeringAndSupport.class, "CancelFlowByBSReq");

	}
	@DataProvider(name = "CancelFlowByNWEng")
	public static Iterator<Object[]> CancelFlowByNWEng() {

		return TestData.getData(EngineeringAndSupport.class, "CancelFlowByNWEng");

	}
	
	@DataProvider(name = "CompleteFlowByNWEng")
	public static Iterator<Object[]> CompleteFlowByNWEng() {

		return TestData.getData(EngineeringAndSupport.class, "CompleteFlowByNWEng");

	}
	@DataProvider(name = "PartialCompleteFlowByNWEng")
	public static Iterator<Object[]> PartialCompleteFlowByNWEng() {

		return TestData.getData(EngineeringAndSupport.class, "PartialCompleteFlowByNWEng");

	}
	@DataProvider(name = "DCFFlowByNWEng")
	public static Iterator<Object[]> DCFFlowByNWEng() {

		return TestData.getData(EngineeringAndSupport.class, "DCFFlowByNWEng");

	}
	
	@DataProvider(name = "DCFFlowByDCFMngr")
	public static Iterator<Object[]> DCFFlowByDCFMngr() {

		return TestData.getData(EngineeringAndSupport.class, "DCFFlowByDCFMngr");

	}
	
	@DataProvider(name = "ReturnDCFFlowByDCFEngr")
	public static Iterator<Object[]> ReturnDCFFlowByDCFEngr() {

		return TestData.getData(EngineeringAndSupport.class, "ReturnDCFFlowByDCFEngr");

	}
	
	@DataProvider(name = "CompleteDCFFlowByDCFEngr")
	public static Iterator<Object[]> CompleteDCFFlowByDCFEngr() {

		return TestData.getData(EngineeringAndSupport.class, "CompleteDCFFlowByDCFEngr");

	}
	
}
