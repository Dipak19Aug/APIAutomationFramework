package com.api.models.response;

public class CreateFDResponse {
	
	private Boolean success;
    private String message;
    private String fdId;
	/**
	 * @param success
	 * @param message
	 * @param fdId
	 */
	public CreateFDResponse(Boolean success, String message, String fdId) {
		super();
		this.success = success;
		this.message = message;
		this.fdId = fdId;
	}
	public boolean getSuccess() {
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
	public String getFdId() {
		return fdId;
	}
	public void setFdId(String fdId) {
		this.fdId = fdId;
	}
	@Override
	public String toString() {
		return "CreateFDResponse [success=" + success + ", message=" + message + ", fdId=" + fdId + "]";
	}
    
    
}
