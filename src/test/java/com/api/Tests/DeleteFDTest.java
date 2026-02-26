package com.api.Tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.DepositService;
import com.api.models.request.DeleteFDRequest;
import com.api.models.response.DeleteFDResponse;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class DeleteFDTest {
	
	@Test(description="Verify the delete FD API is working",groups = {"postLogin","e2e"})
	
	public void DeleteFD() {
		
		SoftAssert soft = new SoftAssert();
		
		//DeleteFDRequest deleteFDRequest = new DeleteFDRequest("525376788639"); // What are the we are sending from body that data comes in POJO class.
		//Above line used when fdId send manually
		DeleteFDRequest deleteFDRequest = new DeleteFDRequest(TestDataStore.fdId);
		DepositService depositservice = new DepositService();
		//String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI5MjY2MDQ1ODgiLCJ1c2VybmFtZSI6IktyaXNobmExMiIsImlhdCI6MTc3MDkxMTE3NSwiZXhwIjoxNzcwOTE0Nzc1fQ.jfHVGQplZZZm4zVRIoRSmz5wHkz8asUdAQoQx7AcnG0";
		//Response response= depositservice.deleteFd(deleteFDRequest, token); // Used when send token manually
		Response response= depositservice.deleteFd(deleteFDRequest, TestDataStore.token); 
		//TestDataStore.token
		DeleteFDResponse deleteFDResponse = response.as(DeleteFDResponse.class);
		
		TestDataStore.requestPayload = deleteFDRequest;
		TestDataStore.response = response;
		//System.out.println(response.asPrettyString()); // Hum logger use kar rahe hai to ye abhi use nahi karenge.
		
		soft.assertEquals(response.getStatusCode(), 200);
		soft.assertTrue(deleteFDResponse.getSuccess());
		soft.assertEquals(deleteFDResponse.getMessage(),"FD deleted successfully");
		
		soft.assertAll();
		
	}	
}