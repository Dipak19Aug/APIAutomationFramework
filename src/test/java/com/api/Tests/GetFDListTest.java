package com.api.Tests;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.DepositService;
import com.api.models.response.FDDetails;
import com.api.models.response.FDListResponse;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

public class GetFDListTest {
	
	@Test(description="verify the get FD List API is working",groups = {"postLogin","e2e"})
	
	public void FdListTest() {
		
		SoftAssert soft = new SoftAssert();
		
		DepositService depositService = new DepositService();
		// String token= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI5MjY2MDQ1ODgiLCJ1c2VybmFtZSI6IktyaXNobmExMiIsImlhdCI6MTc3MDg5NzU4OCwiZXhwIjoxNzcwOTAxMTg4fQ.FYv9CVfPOuBHqnLDqUhUDpNb1G6W_vfu1PE_v7V_gaQ";
		//above used when send token manually.
		// Response response=depositService.getFdList(token);// Used when enter token manually
		 Response response=depositService.getFdList(TestDataStore.token);
		 FDListResponse fdListResponse = response.as(FDListResponse.class);
		 
		 TestDataStore.response = response;
		 
		// System.out.println(response.asPrettyString()); // Hum Logger use kar rahe hai to ye abhi use nahi kar rahe hai.
		 
		 soft.assertEquals(response.getStatusCode(),200);
		 soft.assertTrue(fdListResponse.getSuccess());
		 
		 List <FDDetails> list = fdListResponse.getFdList();
		 soft.assertTrue(list.size()>0);
		 System.out.println("Count of FD:"+list.size());
		 
		 for(FDDetails fd : list) {
			 soft.assertTrue(fd.getFd_id()!=null);
			 soft.assertEquals(fd.getDeposit_type(), "Fixed");
		 }
		 
		 boolean found = list.stream()
			        .anyMatch(fd -> fd.getFd_id().equals(TestDataStore.fdId));

			soft.assertTrue(found,"FD id not present in list");
			
			soft.assertAll();
	}

}
