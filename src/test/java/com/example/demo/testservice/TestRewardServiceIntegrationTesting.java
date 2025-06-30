package com.example.demo.testservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.InvalidTransactionException;
import com.example.demo.model.RewardResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.RewardService;

/**
 * Integration test for RewardService.
 *
 * It checks correct reward calculations, error handling, and transaction
 * validation.
 */

@SpringBootTest
public class TestRewardServiceIntegrationTesting {

	@Autowired
	private RewardService rewardService;

	/**
	 * Verifies that reward points are correctly calculated for all customers.
	 * Ensures that "cust1" exists and has valid rewards for at least January.
	 */
	@Test
	void testCalculateAllRewards() {
		List<RewardResponse> rewards = rewardService.calculateAllRewards();
		assertFalse(rewards.isEmpty());
		RewardResponse cust1 = rewards.stream().filter(r -> r.getCustomerId().equals("cust1")).findFirst().orElse(null);
		assertNotNull(cust1);
		assertTrue(cust1.getTotalRewards() > 0);
		assertTrue(cust1.getMonthlyRewards().containsKey("January"));
	}

	/**
	 * Adds a valid transaction and ensures the rewards are correctly reflected.
	 */
	@Test
	void testAddTransaction_validTransaction() {
		Transaction transaction = new Transaction("custNew", 120, "2025-06-01");
		rewardService.addTransaction(transaction);
		RewardResponse response = rewardService.getRewardsByCustomerId("custNew");
		assertNotNull(response);
		assertEquals("custNew", response.getCustomerId());
		assertEquals(90, response.getTotalRewards()); // 2*(120-100) + 50 = 90
		assertTrue(response.getMonthlyRewards().containsKey("June"));
	}

	/**
	 * Ensures that trying to add a transaction with a negative amount triggers a
	 * custom InvalidTransactionException.
	 */
	@Test
	void testAddTransaction_invalidAmount() {
		Transaction transaction = new Transaction("cust5", -100.0, "2025-03-10");
		assertThrows(InvalidTransactionException.class, () -> {
			rewardService.addTransaction(transaction);
		});
	}
}
