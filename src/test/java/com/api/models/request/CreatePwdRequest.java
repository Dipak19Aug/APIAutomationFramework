package com.api.models.request;

public class CreatePwdRequest {
	
	  private String applicationId;
	  private String userId;
	  private String username;
	  private String password;
	
	  public CreatePwdRequest(String applicationId, String userId, String username, String password) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.username = username;
		this.password = password;
	  }

	  public String getApplicationId() {
		  return applicationId;
	  }

	  public void setApplicationId(String applicationId) {
		  this.applicationId = applicationId;
	  }

	  public String getUserId() {
		  return userId;
	  }

	  public void setUserId(String userId) {
		  this.userId = userId;
	  }

	  public String getUsername() {
		  return username;
	  }

	  public void setUsername(String username) {
		  this.username = username;
	  }

	  public String getPassword() {
		  return password;
	  }

	  public void setPassword(String password) {
		  this.password = password;
	  }

	  @Override
	  public String toString() {
		return "CreatePwdRequest [applicationId=" + applicationId + ", userId=" + userId + ", username=" + username
				+ ", password=" + password + "]";
	  }
	  
	  public static class Builder{
		  
		  private String applicationId;
		  private String userId;
		  private String username;
		  private String password;
		  
		  public Builder applicationId(String applicationId) { // Type 1
			  this.applicationId=applicationId;
			  Builder x= this;
			  return x;
		  }
		  
		  public Builder userId(String userId) {
			  this.userId=userId;
			  return this;
		  }
		  
		  public Builder username(String username) {
			  this.username=username;
			  return this;
		  }
		  public Builder password(String password) {
			  this.password=password;
			  return this;
	  }
		  public CreatePwdRequest build() {
			  CreatePwdRequest createPwdRequest = new  CreatePwdRequest(applicationId, userId, username, password);
			  return createPwdRequest;
		  }
	  
	}
}
