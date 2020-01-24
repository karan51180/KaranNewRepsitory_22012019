package com.optum.automation.coreframework.utils;
/**
 * @author Manoj Sharma
 *
 */
/*
 * This enumerator is used to represent the content types in HTTP post requests
 */
public enum CustomContentType {

	Text,
	TextPlain("text/plain"),
	JSON("application/json"),
	Javascript("application/javascript"),
	XML("application/xml"),
	TextXml("text/xml"),
	HTML("text/html");

	private String contentType;

	private CustomContentType(){}

	private CustomContentType(String type)
	{
		this.contentType = type;
	}
	public static String getContentType (CustomContentType type){
		if(type.getValue()!=null)
			return type.getValue();
		else
			return type.name().toLowerCase();
	}

	private String getValue() {
		return contentType;
	}
}