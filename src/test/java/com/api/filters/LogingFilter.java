package com.api.filters;

import org.apache.logging.log4j.LogManager;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LogingFilter implements Filter {
	
	private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(LogingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		logRequest(requestSpec);
		Response response=ctx.next(requestSpec, responseSpec);//Request is going to execute
		logResponse(response);
		return response; //Test for assertion
	}
	
	public void logRequest(FilterableRequestSpecification requestSpec) {
		Logger.info("BASE URI:"+requestSpec.getBaseUri());
		Logger.info("Request Header:"+requestSpec.getHeaders());
		Logger.info("Request Payload:"+requestSpec.getBody());
		
	}
	
	public void logResponse (Response response) {
		Logger.info("STATUS CODE:" +response.getStatusCode());
		Logger.info("Response Header:"+ response.headers());
		//Logger.info("Request Body:"+ response.getBody().prettyPrint()); isase response double print ho raha tha filter class se aut listner se
		Logger.info("Response Body:"+ response.getBody().asPrettyString());
		
	}

}
