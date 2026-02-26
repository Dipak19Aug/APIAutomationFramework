package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utils.TestDataStore;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private long startTime;

    @Override
    public void onTestStart(ITestResult result) {
        startTime = System.currentTimeMillis();
        logger.info("========== TEST STARTED: " + result.getName() + " ==========");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("TEST PASSED: " + result.getName());
        logger.info("Execution Time: " + executionTime + " ms");
        logger.info("===============================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.error("TEST FAILED: " + result.getName());
        logger.error("Execution Time: " + executionTime + " ms");

        // Print Request Payload
        if (TestDataStore.requestPayload != null) {
            logger.error("----- REQUEST PAYLOAD -----");
            logger.error(TestDataStore.requestPayload.toString());
        }

        // Print Full Response
        if (TestDataStore.response != null) {
            logger.error("----- FULL RESPONSE -----");
            logger.error(TestDataStore.response.asPrettyString());
            logger.error("Status Code: " + TestDataStore.response.getStatusCode());
        }

        logger.error("Failure Reason: ", result.getThrowable());
        logger.error("===============================================");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("TEST SKIPPED: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("========== TEST SUITE STARTED ==========");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========== TEST SUITE FINISHED ==========");
    }
}
