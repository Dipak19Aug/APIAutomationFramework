package com.api.base;

import com.api.models.request.CreateFDRequest;
import com.api.models.request.DeleteFDRequest;

import io.restassured.response.Response;

public class DepositService extends BaseService {
	
	private static final String BASE_PATH ="/api/deposits/";
	
	public Response createFd(CreateFDRequest payload, String token) {
		setAuthToken(token);
		return postRequest(payload,BASE_PATH+"create");
	}
	
	public Response getFDDetails(String fdId,String token) {
		setAuthToken(token);
		return getRequest (BASE_PATH+"details/"+fdId);
	}
	
	public Response getFdList(String token) {
		setAuthToken(token);
		return getRequest(BASE_PATH+"list");
	}
	
	public Response deleteFd(DeleteFDRequest payload,String token) { // Method
		setAuthToken(token);
		return deleteRequest(payload,BASE_PATH+"delete");
	}

}
