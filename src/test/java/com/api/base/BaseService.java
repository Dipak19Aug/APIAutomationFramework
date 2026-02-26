package com.api.base;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.api.filters.LogingFilter;

public class BaseService { // Wrapper for RestAssured (Har test mein given().baseUri().contentType().body().post() likhne ki zarurat nahi)
	
//	Isko bolte hain:

	//	⭐ Service Layer Abstraction
	//	⭐ Reusable API Wrapper
	//baseURI
	// Creating the request
	// Handling the response
	
	private static final String BASE_URL="http://localhost:3000";  //Agar ye line nahi hoti, toh tumhe har test case mein given().baseUri("http://localhost:3000") likhna padta.Agar kal server change ho:localhost:3000 → qa.server.com,Toh sirf ek line change hogi.
	private RequestSpecification requestSpecification;  //Ye ek Rest Assured ka object hai jo request setup store karega.Isme tum base URI, headers, body, etc. set kar sakte ho.Agar ye nahi hota, toh tumhe har test mein alag se given() likhna padta aur code repeat hota.
    
	static {
		RestAssured.filters(new LogingFilter());
	}

	public BaseService() {                                              //Ye constructor hai jo BaseService object banate hi request specification initialize kar deta hai.Matlab ab tumhe har test mein given().baseUri(...) likhne ki zarurat nahi.
		requestSpecification = given().baseUri(BASE_URL);        //Agar ye constructor nahi hota, toh tumhe manually har test mein requestspecification = given().baseUri(...) likhna padta.
	}
	
	protected void setAuthToken(String token) {  
		requestSpecification.header("Authorization","Bearer "+token);// "Bearer " yaa pe Bearer word ke bad space dena jaruri hai.
	}
	
	protected Response postRequest(Object payload,String endpoint) { //Tumne ek method banaya jo POST request bhejega.Ye method payload (JSON body) aur endpoint (API ka path, jaise /api/users/register) accept karta hai.Return type hai Response, matlab API ka pura output (status code + body) tumhe milega.
		return requestSpecification   // ye tumne constructor mein initialize kiya tha with base URI.
				.contentType(ContentType.JSON) //  batata hai ki request ka body JSON format mein hai.
				.body(payload) //tumhara actual data (user details, etc.) request ke andar attach ho gaya.
				.post(endpoint); //inally POST call execute hoti hai given endpoint pe.

                                //Object return hota hai jisme status code, headers, aur JSON body hoti hai.
	}
	//Method overloading
	protected Response postRequest(String baseUrl,String payload,String endpoint) {
		return requestSpecification
				.baseUri(baseUrl)
				.contentType(ContentType.JSON)
				.body(payload)
				.post(endpoint);
	}
	
	protected Response getRequest(String endpoint) { // Get Method
		return requestSpecification.get(endpoint);
	}
	
	protected Response deleteRequest(Object payload, String endpoint) { // Delete Method
		return requestSpecification
				.contentType(ContentType.JSON)
				.body(payload)
				.delete(endpoint);
	}
	
}

//🎯 Simple Real-Life Example

//Socho:

//🚗 BaseService = Car Engine
//👨‍ AuthService = Driver
//🧪 Test Class = Passenger


//Agar interviewer puche:

//👉 Why did you create BaseService?

//Tum bolna:

//I created BaseService to centralize Rest Assured configuration like base URI and request specification.
//It reduces code duplication, improves maintainability, and allows reusable API request methods like POST.
