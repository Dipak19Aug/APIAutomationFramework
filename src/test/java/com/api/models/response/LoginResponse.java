package com.api.models.response;

public class LoginResponse {
	
	private Boolean success;
    private String message;
    private String token;
	/**
	 * @param success
	 * @param message
	 * @param token
	 */
	public LoginResponse(Boolean success, String message, String token) {
		super();
		this.success = success;
		this.message = message;
		this.token = token;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "LoginResponse [success=" + success + ", message=" + message + ", token=" + token + "]";
	}
	
	
    

}
