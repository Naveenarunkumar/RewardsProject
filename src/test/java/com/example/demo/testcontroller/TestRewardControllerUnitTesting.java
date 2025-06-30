package com.example.demo.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.*;
import com.example.demo.controller.RewardController;
import com.example.demo.model.RewardResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.RewardService;
/**
 * Unit test class for RewardController.
 * 
 * This test focuses on testing the controller's logic in by mocking the RewardService.
 * 
 * No actual service logic is called
 * the controller's behavior and its response to mocked service outputs.
 */

@ExtendWith(MockitoExtension.class)// Enables Mockito support in JUnit 5
public class TestRewardControllerUnitTesting {

       // Injects the controller we want to test
	    @InjectMocks
	    private RewardController rewardController;
     
	    // Mocks the service used by the controller
	    @Mock
	    private RewardService rewardService;

	    /**
	     * Test the getAllRewards() method of the controller.
	     * Ensures the controller returns the mocked list of reward responses correctly.
	     */

	    @Test
	    void testGetAllRewards() {
	        // Mock reward data for cust1 and cust2
	        Map<String, Integer> cust1Rewards = Map.of(
	                "2025-03-10", 50,
	                "2025-04-10", 100
	        );

	        Map<String, Integer> cust2Rewards = Map.of(
	                "2025-04-11", 90
	        );
	        // Expected mock response from the service
	        List<RewardResponse> mockResponse = List.of(
	                new RewardResponse("cust1", cust1Rewards, 150),
	                new RewardResponse("cust2", cust2Rewards, 90)
	        );
	        // Define behavior of the mocked service
	        Mockito.when(rewardService.calculateAllRewards()).thenReturn(mockResponse);
	        // Call controller method
	        List<RewardResponse> result = rewardController.getAllRewards();
	        // Validate the controller returns the expected result
	        assertEquals(2, result.size());
	        assertEquals("cust1", result.get(0).getCustomerId());
	        assertEquals(150, result.get(0).getTotalRewards());
	        assertEquals(2, result.get(0).getMonthlyRewards().size());
	        assertEquals(90, result.get(1).getTotalRewards());
	    }

    @Test
    void testGetRewardsByCustomer() {
        // Prepare mock response
        Map<String, Integer> monthlyRewards = Map.of("2025-03-10", 90);
        RewardResponse mockResponse = new RewardResponse("cust3", monthlyRewards, 90);
        // Mock the service call
        Mockito.when(rewardService.getRewardsByCustomerId("cust3")).thenReturn(mockResponse);
        // Call controller method
        ResponseEntity<RewardResponse> response = rewardController.getRewardsByCustomer("cust3");
        // Validate response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("cust3", response.getBody().getCustomerId());
    }
    
    /**
     * Test the addTransaction() method.
     * Validates that the controller accepts a transaction and returns the correct success message.
     */
    @Test
    void testAddTransaction() {
        // Create a transaction
        Transaction transaction = new Transaction("cust2", 90.0, "2025-03-10");
        // Call controller method (method returns a static string)
        ResponseEntity<String> response = rewardController.addTransaction(transaction);
       //validates response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transaction added successfully", response.getBody());

    }
    }