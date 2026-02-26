package com.api.models.response;

public class GetFDDetailsResponse { // Outer Class of FDDetails
	
	private Boolean success;
    private FDDetails fdDetails;
	/**
	 * @param success
	 * @param fdDetails
	 */
	public GetFDDetailsResponse(Boolean success, FDDetails fdDetails) {
		super();
		this.success = success;
		this.fdDetails = fdDetails;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public FDDetails getFdDetails() {
		return fdDetails;
	}
	public void setFdDetails(FDDetails fdDetails) {
		this.fdDetails = fdDetails;
	}
	@Override
	public String toString() {
		return "GetFDDetailsResponse [success=" + success + ", fdDetails=" + fdDetails + "]";
	}
    
}
