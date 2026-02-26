package com.api.models.request;

public class RegisterRequest{
	
	private String firstname;
	private String username;
	private String middleName;
	private String sirname;
	private String pan;
	private String aadhaar;
	private String mobile;
	private String dob;
	private String gender;
	
	public RegisterRequest(String firstname, String username, String middleName, String sirname, String pan,
			String aadhaar, String mobile, String dob, String gender) {
		super();
		this.firstname = firstname;
		this.username = username;
		this.middleName = middleName;
		this.sirname = sirname;
		this.pan = pan;
		this.aadhaar = aadhaar;
		this.mobile = mobile;
		this.dob = dob;
		this.gender = gender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSirname() {
		return sirname;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "RegisterResponse [firstname=" + firstname + ", username=" + username + ", middleName=" + middleName
				+ ", sirname=" + sirname + ", pan=" + pan + ", aadhaar=" + aadhaar + ", mobile=" + mobile + ", dob="
				+ dob + ", gender=" + gender + "]";
	}
	
}
