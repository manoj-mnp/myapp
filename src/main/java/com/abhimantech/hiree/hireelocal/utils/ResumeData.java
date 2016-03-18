package com.abhimantech.hiree.hireelocal.utils;

public class ResumeData {
	private String keywordFound;
	private String type;
	private int position;
	
	
	
	
	public ResumeData(String keywordFound, String type, int position) {
		super();
		this.keywordFound = keywordFound;
		this.type = type;
		this.position = position;
	}
	public String getKeywordFound() {
		return keywordFound;
	}
	public void setKeywordFound(String keywordFound) {
		this.keywordFound = keywordFound;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String printString() {
		return "[keywordFound=" + keywordFound + ", type=" + type + ", position=" + position + "]";
	}
	
	
}
