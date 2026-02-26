package com.api.models.request;

public class DeleteFDRequest {
	
	private String fdId;

	/**
	 */
	public DeleteFDRequest(String fdId) {
		super();
		this.fdId = fdId;
	}

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	@Override
	public String toString() {
		return "DeleteFDRequest [fdId=" + fdId + "]";
	}
	
	
}
