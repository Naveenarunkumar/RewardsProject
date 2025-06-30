package com.example.demo.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.RewardController;
import com.example.demo.model.RewardResponse;
import com.example.demo.model.Transaction;

@SpringBootTest // Bootstraps the full Spring application context for integration testing
public class TestRewardControllerIntegrationTesting {

	@Autowired
	private RewardController controller;// Injects the actual controller bean for testing

	/**
	 * Test to verify that getAllRewards() returns a non-empty list. Ensures that
	 * rewards data is present.
	 */

	@Test
	void testGetAllRewards() {
		List<RewardResponse> responses = controller.getAllRewards();
		assertNotNull(responses);
		assertTrue(responses.size() > 0);
	}

	/**
	 * Test to fetch rewards for a specific customer ID. Validate that the response
	 * contains the correct customer information and reward points.
	 */
	@Test
	void testGetRewardsByCustomerId() {
		String testCustomerId = "cust1"; // this ID should exists in our test data
		RewardResponse response = controller.getRewardsByCustomer(testCustomerId).getBody();
		assertNotNull(response, "RewardResponse should not be null");
		assertEquals(testCustomerId, response.getCustomerId(), "Customer ID should match");
		assertTrue(response.getTotalRewards() >= 0, "Total rewards should be non-negative");
	}

	/**
	 * Test to add a new transaction for a customer. Verifies that the transaction
	 * is accepted and a success message is returned.
	 */

	@Test
	void testAddTransaction() {
		Transaction tx = new Transaction("cust2", 90, "2025-03-10");
		String response = controller.addTransaction(tx).getBody();
		assertNotNull(response);
		assertEquals("Transaction added successfully", response);
	}

}