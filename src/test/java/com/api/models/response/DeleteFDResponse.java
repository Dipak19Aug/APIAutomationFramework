package com.api.models.response;

public class DeleteFDResponse {
	

	    private Boolean success;
	    private String message;
		/**
		 * @param success
		 * @param message
		 */
		public DeleteFDResponse(Boolean success, String message) {
			super();
			this.success = success;
			this.message = message;
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
		@Override
		public String toString() {
			return "DeleteFDResponse [success=" + success + ", message=" + message + "]";
		}
	

}
