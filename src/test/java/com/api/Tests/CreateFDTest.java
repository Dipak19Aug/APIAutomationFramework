package com.api.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.DepositService;
import com.api.models.request.CreateFDRequest;
import com.api.models.response.CreateFDResponse;
import com.api.testdata.TestDataFactory;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class CreateFDTest {
	
	@Test(description="Verify the create fd API is working",groups = {"postLogin","e2e"})
	
	public void createFd() {
		 SoftAssert soft = new SoftAssert();
		 CreateFDRequest createFDRequest = TestDataFactory.getFDData();
		//CreateFDRequest createFDRequest = new CreateFDRequest("Fixed", 60000, 15, "Runali", "Ghadge","Pune", "Sister") ;
		DepositService depositservice = new DepositService();
		//String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI5MjY2MDQ1ODgiLCJ1c2VybmFtZSI6IktyaXNobmExMiIsImlhdCI6MTc3MDgzMzU1NCwiZXhwIjoxNzcwODM3MTU0fQ.1mtIWnI9uKItWtTzKMg-QUf4r2ANeJ0GpaUL_hry-xE";
		// Above used when enter token manually
		//Response response= depositservice.createFd(createFDRequest, token); // Used when enter token manually
		Response response= depositservice.createFd(createFDRequest, TestDataStore.token);
		CreateFDResponse createFdResponse = response.as(CreateFDResponse.class);
		
		TestDataStore.fdId = createFdResponse.getFdId();
		TestDataStore.durationMonths=createFDRequest.getDurationMonths();
		TestDataStore.amount=createFDRequest.getAmount();
		TestDataStore.nominee_firstname=createFDRequest.getNominee_firstname();
		TestDataStore.nominee_lastname=createFDRequest.getNominee_lastname();
		TestDataStore.nominee_address=createFDRequest.getNominee_address();
		TestDataStore.nominee_relationship = createFDRequest.getNominee_relationship();
		TestDataStore.requestPayload = createFDRequest;
		TestDataStore.response = response;
		
		//System.out.println(response.asPrettyString()); //Hum loger use kr rahe hai to ab ye use nahi karenge.
		
		//Assert.assertEquals(response.getStatusCode(),200);
		//Assert.assertTrue(createFdResponse.getSuccess());
		//Assert.assertEquals(createFdResponse.getMessage(),"FD created successfully");
		//Assert.assertTrue(createFdResponse.getFdId()!=null);
		//String fdId = response.jsonPath().getString("fdId");
		//Assert.assertTrue(fdId.startsWith("52"));
		
		soft.assertEquals(response.getStatusCode(),201);
		soft.assertTrue(createFdResponse.getSuccess());
		soft.assertEquals(createFdResponse.getMessage(),"FD created successfully");
		soft.assertTrue(createFdResponse.getFdId()!=null);
		String fdId = response.jsonPath().getString("fdId");
		soft.assertTrue(fdId.startsWith("52"));
		
		
		
		
        soft.assertAll();
		
	}
}
