package com.api.models.response;

public class RegisterResponse {
	
	  private Boolean success;
	  private String message;
	  private String userId;
	  private String applicationId;
	  
	  public RegisterResponse() {
		  
	  }
	 
	  public RegisterResponse(Boolean success, String message, String userId, String applicationId) {
		super();
		this.success = success;
		this.message = message;
		this.userId = userId;
		this.applicationId = applicationId;
	  }

	  public Boolean getSuccess() {
		  return success;
	  }

	  public void setSuccess(Boolean success) {
		  this.success = success;
	  }

	  public String getMessage() {
		  return message;
	  }

	  public void setMessage(String message) {
		  this.message = message;
	  }

	  public String getUserId() {
		  return userId;
	  }

	  public void setUserId(String userId) {
		  this.userId = userId;
	  }

	  public String getApplicationId() {
		  return applicationId;
	  }

	  public void setApplicationId(String applicationId) {
		  this.applicationId = applicationId;
	  }

	  @Override
	  public String toString() {
		return "RegisterResponse [success=" + success + ", message=" + message + ", userId=" + userId
				+ ", applicationId=" + applicationId + "]";
	  }
	  

}
