package com.api.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.DepositService;

import com.api.models.response.GetFDDetailsResponse;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class GetFDDetails {
	
	@Test(description="Verify the get FD Details API is working",groups = {"postLogin","e2e"})
	
	public void getFDDetails() {
		
		SoftAssert soft = new SoftAssert();
		// So is class ke liye humne Request POJO nahi banaya hai because ye get Details tha
		DepositService depositService = new DepositService();
		// String fdId = "524163136769"; // What are the data sending in URL thats need to declare here (because this is get request so we are not created the Request POJO)
		// String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI5MjY2MDQ1ODgiLCJ1c2VybmFtZSI6IktyaXNobmExMiIsImlhdCI6MTc3MDg5MzQ0NSwiZXhwIjoxNzcwODk3MDQ1fQ.5QcO40w_ETn7268r0fwrLoMNod-RAXjSWjvj1nJtuhs";
		// Above used when enter token manually
		 // Response response = depositService.getFDDetails(fdId, token);// Used when enter token manually
		 Response response = depositService.getFDDetails(TestDataStore.fdId, TestDataStore.token);
		 GetFDDetailsResponse fdDetailsResponse = response.as(GetFDDetailsResponse.class);
		 
		// TestDataStore.requestPayload = createFDRequest; // ye GET request hai koi data nahi bhej rahe hai
			TestDataStore.response = response;
			
	     // Hum abhi logger use kr rahe hai is liya 'System.out.println' ek professional framework mein use nahi karenge.	
		// System.out.println(response.asPrettyString());
		// System.out.println("Status code:-"+response.getStatusCode());
		// System.out.println("success:-"+response.jsonPath().getBoolean("success"));
		 
		 soft.assertEquals(response.getStatusCode(), 200);
		 soft.assertTrue(fdDetailsResponse.getSuccess());
		 
		 soft.assertTrue(fdDetailsResponse.getFdDetails().getFd_id()!=null);
		 soft.assertTrue(fdDetailsResponse.getFdDetails().getUser_id()!=null);
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getDeposit_type(),"Fixed");
		 //soft.assertEquals(fdDetailsResponse.getFdDetails().getAmount(),TestDataStore.amount);// value arahi hai double means 5000.00 and hum verify kr rahehai 5000
		 
		 double actualAmount = Double.parseDouble(
		            fdDetailsResponse.getFdDetails().getAmount().toString()
		    );
		    double expectedAmount = (double) TestDataStore.amount;
		    soft.assertEquals(actualAmount, expectedAmount);
		    
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getDuration_months(),TestDataStore.durationMonths);
		// soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_firstname(),"Runali");
		// soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_lastname(),"Ghadge");
		//soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_address(),"Pune");
		// soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_relationship(),"Sister");
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_firstname(),TestDataStore.nominee_firstname);
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_lastname(),TestDataStore.nominee_lastname);
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_address(),TestDataStore.nominee_address);
		 soft.assertEquals(fdDetailsResponse.getFdDetails().getNominee_relationship(),TestDataStore.nominee_relationship);
		 
		 
		 soft.assertAll();
	}

}
