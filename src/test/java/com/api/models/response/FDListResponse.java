package com.api.models.response;

import java.util.List;

public class FDListResponse {
	
 private Boolean success;
 private List<FDDetails> fdList;
 /**
 * @param success
 * @param fdList
 */
 public FDListResponse(Boolean success, List<FDDetails> fdList) {
	super();
	this.success = success;
	this.fdList = fdList;
 }
 public Boolean getSuccess() {
	return success;
 }
 public void setSuccess(Boolean success) {
	this.success = success;
 }
 public List<FDDetails> getFdList() {
	return fdList;
 }
 public void setFdList(List<FDDetails> fdList) {
	this.fdList = fdList;
 }
 @Override
 public String toString() {
	return "FDListResponse [success=" + success + ", fdList=" + fdList + "]";
 }
 
 

}
