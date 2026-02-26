package com.api.base;

import com.api.models.request.CreatePwdRequest;
import com.api.models.request.LoginRequest;
import com.api.models.request.RegisterRequest;

import io.restassured.response.Response;

public class AuthService extends BaseService { //BaseService inherit kiya
	
	private static final String BASE_PATH = "/api/users/"; ///api/users/ ko base path banaya
	
	// public Response register(String payload) { // This for execute some class in package com.api.Tests
	public Response register(RegisterRequest payload) { //register() method create ki
		return postRequest(payload,BASE_PATH+"register"); //Endpoint assemble kiya.
	}
	
	public Response CreatePwd(CreatePwdRequest payload) {  //CreatePwd() method create ki
		return postRequest(payload,BASE_PATH+"create-password"); //Endpoint assemble kiya.
	}
	
	public Response login(LoginRequest payload) { //login() method create ki
		return postRequest(payload, BASE_PATH+"login"); //Endpoint assemble kiya.
	}

}
