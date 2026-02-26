package com.api.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.CreatePwdRequest;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class CreatePwdTest {
	
	@Test(description="Verify the createpwd API is working",groups = {"setup","e2e"})
	
	public void CreatePwd() {
		CreatePwdRequest createPwdRequest = new CreatePwdRequest.Builder()
		        .applicationId(TestDataStore.applicationId)
		        .userId(TestDataStore.userId)
		        .username(TestDataStore.username)
		       // .applicationId("APP788400")
		       // .userId("926604588")
		       // .username("Krishna12")
		        .password("Test@123")
		      .build(); // Dont miss this 
		
		AuthService authService = new AuthService();
		Response response=authService.CreatePwd(createPwdRequest);
		
		TestDataStore.password =createPwdRequest.getPassword();
		
		TestDataStore.requestPayload = createPwdRequest;
		TestDataStore.response = response;
		
		// Hum abhi logger use kr rahe hai is liya 'System.out.println' ek professional framework mein use nahi karenge.
		//System.out.println(response.asPrettyString());
		// hamne yaha pe createPwdResponse nahi banaya kyunki hum response ka koi bhe value age leke nahi ja rahe hai
		
		//System.out.println("AppId : "+TestDataStore.applicationId);
		//System.out.println("UserId : "+TestDataStore.userId);
		//System.out.println("Username : "+TestDataStore.username);
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(response.jsonPath().getBoolean("success"));
		Assert.assertEquals(response.jsonPath().getString("message"),"Password set successfully");
		
		//Interviewer को कैसे explain करेगा (Exact Line)
		//I created response POJO only where response data is required for
		//further API chaining. For simple validation responses, I prefer
		//JsonPath to keep the framework lightweight.
		
		
		
		
	}
}
