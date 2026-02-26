package com.api.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class LoginTest {
	
	@Test(description="Verify the Login API is working",groups = {"postLogin","e2e"})
	
	public void loginTest() {
		
		LoginRequest loginRequest = new LoginRequest("Luigi632", "Test@123"); // Used when manually data entered.& when run postlogin test suit.
		//LoginRequest loginRequest = new LoginRequest(TestDataStore.username, TestDataStore.password);//used when run e2e test suit
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		LoginResponse loginResponse = response.as(LoginResponse.class);
		
		TestDataStore.token=loginResponse.getToken();
		TestDataStore.requestPayload = loginRequest;
		TestDataStore.response = response;
		// Hum abhi logger use kr rahe hai is liya 'System.out.println' ek professional framework mein use nahi karenge.
		//System.out.println(response.asPrettyString());		
		//System.out.println("Status Code: "+ response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(loginResponse.getSuccess()); // jaha par flag vaha pe uski value mat mention kr like "success"
		Assert.assertEquals(loginResponse.getMessage(), "Login successful");
		Assert.assertTrue(loginResponse.getToken()!=null);
		
		
		
	}	

}
