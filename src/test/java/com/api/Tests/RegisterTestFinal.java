package com.api.Tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.RegisterRequest;
import com.api.models.response.RegisterResponse;
import com.api.testdata.TestDataFactory;
import com.api.utils.TestDataStore;

import io.restassured.response.Response;

@Listeners(com.api.listeners.TestListener.class)
public class RegisterTestFinal {
	
	@Test(description="Verify the register API is working",groups = {"setup","e2e"})
	
	public void registerTest() {
		RegisterRequest registerRequest = TestDataFactory.getRegisterData();
		//RegisterRequest registerRequest = new RegisterRequest("Vinayak", "VG123", "Vithhal","Gawade", "BRGPG7685U", "423412341097", "98865432095","1993-05-14", "male");
		//JSON body manually string mein nahi likhi,Java object banaya, Isko bolte hain:Serialization Friendly Design
		AuthService authService = new AuthService();
		Response response=authService.register(registerRequest);
		//Kya kiya?
		//Test ne directly RestAssured use nahi kiya, AuthService ko call kiya
		
//Kyun?  : Test readable rahe, Endpoint details hide rahe, Layered architecture follow ho
		RegisterResponse registerResponse = response.as(RegisterResponse.class); //ye register response me response ke value store krti hai 
		//Kya kiya?  : JSON response → Java object
		//Kyun?   : Taaki tum likh sako: registerResponse.getUserId() , instead of: response.jsonPath().get("userId")
		
		//Ye ek API Automation Test Case hai jiska goal hai:

		//	✅ Register API ko call karna
		//	✅ Response ko object mein convert karna
		//	✅ Response ko validate karna (assertions)
		
		
		// Hum abhi logger use kr rahe hai is liya 'System.out.println' ek professional framework mein use nahi karenge.
		//System.out.println(response.asPrettyString());
		//System.out.println("Status Code: "+response.getStatusCode());
		//System.out.println("ApplicationId: "+registerResponse.getApplicationId());
		//System.out.println("UserId: "+registerResponse.getUserId());
		//System.out.println("success: "+registerResponse.getSuccess());
		//System.out.println("message: "+registerResponse.getMessage());
		
		TestDataStore.applicationId = registerResponse.getApplicationId(); // Applicationid ko utility class mein store karega
		TestDataStore.userId = registerResponse.getUserId(); //userid ko utility class mein store karega
		TestDataStore.username = registerRequest.getUsername(); //username ko utility class mein store karega
		
		TestDataStore.requestPayload = registerRequest; //Request body globally save karega taki listner isko access kar sake.
		TestDataStore.response = response; // Response object globally save karega taki listenesr isko access kr sake
	
		Assert.assertEquals(response.getStatusCode(), 201); // humne yaha pe registerResponse nahi use kiya becaus status code field nahi ai o response pojo mein bhe mention  nahi hai
		//Assert.assertTrue(response.jsonPath().getBoolean("success"));
		Assert.assertTrue(registerResponse.getSuccess());
		Assert.assertTrue(registerResponse.getUserId()!=null);
		Assert.assertTrue(registerResponse.getApplicationId()!=null);
		Assert.assertEquals(registerResponse.getMessage(),"User registered successfully");
		Assert.assertTrue(registerResponse.getMessage()!=null);	
			
	}

}
//
//Q1: Ye test kya verify kar raha hai ?
//Expected answer:
//Register API ko hit kar rahe ho
//Payload send kar rahe ho
//Response deserialize kar rahe ho
//Status code + fields validate kar rahe ho
//

//: Tumne AuthService kyu banaya ? direct RestAssured kyu nahi use kiya ?
//Ye VERY IMPORTANT question hai.
//Expected logic:
//Separation of concern
//Reusable service layer
//Code duplication avoid
//Clean framework design


//Q: RegisterRequest class kyu banayi ?
//Answer:
//JSON payload ko object form mein maintain karne ke liye
//Hardcoded JSON avoid karne ke liye
//Type safety
//Reusable data model

//Q: response.as(RegisterResponse.class) kya karta hai ?
//Answer:
//JSON response ko Java object mein convert karta hai
//Deserialization

//Q:Tumne JSONPath aur POJO dono use kiya — kyu ?
//response.jsonPath().getBoolean("success")
//Strong answer:
//Quick field validation = JSONPath
//Structured validation = POJO

//4️⃣ Framework Design Questions (Senior level)
//👉 Q: Agar kal 50 APIs add ho gayi toh structure kaise help karega ?
//Expected:

//BaseService = common HTTP methods
//AuthService = endpoint layer
//Test layer = validation only


//👉 Q: Agar baseURL change ho gaya toh kya karoge ?
//Expected:

//Sirf BaseService update
//Baaki sab unaffected

//✅ 5️⃣ Hidden Deep Questions (Jo strong interviewer puchta hai)

//👉 Q: Tum new AuthService object har test mein kyu bana rahe ho ?
//Purpose:

//Check OOP understanding.

//👉 Q: Agar response null aa gaya toh kya hoga ?
//Check:

//Error handling awareness.

//👉 Q: Hardcoded test data problem kya hai ?
//Best answer:

//Test flaky ho sakta hai
//Duplicate user error
//Test data management needed

//👉 Tumne Service layer kyu banayi ? direct TestNG mein request kyu nahi likhi ?
//Best killer answer:

//Test class should only contain validation.
//API calling logic should be reusable and centralized.
