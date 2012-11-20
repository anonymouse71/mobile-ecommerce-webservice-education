package com.google.mcommerce.sample.chaper13.server.entity;

import java.util.Date;

/**
 * �û�ʵ����
 * @author Administrator
 *
 */
public class UserInfo {
	private String UserID;
	private String UserPW;
	private String Email;
	private String UserName;
	private String Country;
	private String Vip;
	private String RegDate;
	private String RegTime;
	
	public UserInfo() {

	}

	public UserInfo(String UserID, String UserPW, String Email) {
		this.UserID = UserID;
		this.UserPW = UserPW;
		this.Email = Email;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserPW() {
		return UserPW;
	}

	public void setUserPW(String userPW) {
		UserPW = userPW;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getVip() {
		return Vip;
	}

	public void setVip(String vip) {
		Vip = vip;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	public String getRegTime() {
		return RegTime;
	}

	public void setRegTime(String regTime) {
		RegTime = regTime;
	}
}