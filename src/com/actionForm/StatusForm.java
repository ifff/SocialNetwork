package com.actionForm;

public class StatusForm {
	private String text = "";
	private String date = "";
	private String picturePath = "";
	private String userId = "";
	private String userName = "";
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
