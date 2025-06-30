package com.example.demo.testservice;

import com.example.demo.exception.InvalidTransactionException;
import com.example.demo.model.RewardResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.RewardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for RewardService without involving Spring context.
 */
public class TestRewardServiceUnitTesting {

    private RewardService rewardService;

    /**
     * Sets up the RewardService before each test.
     * Uses reflection to clear and reset the static `transactions` list to ensure test isolation.
     */
    @BeforeEach
    void setup() {
        rewardService = new RewardService();
        try {
            var field = RewardService.class.getDeclaredField("transactions");
            field.setAccessible(true);
            List<Transaction> list = (List<Transaction>) field.get(null);
            list.clear();
            list.addAll(List.of(
                new Transaction("cust1", 120, "2025-01-15"), // Should get 90 points
                new Transaction("cust1", 80, "2025-02-10")   // Should get 30 points
            ));
        } catch (Exception e) {
            throw new RuntimeException(e); // Fails setup if reflection fails
        }
    }

    /**
     * Tests reward calculation for all customers.
     * Verifies correct customer ID and that points exist for both Jan and Feb.
     */
    @Test
    void testCalculateAllRewards() {
        List<RewardResponse> rewards = rewardService.calculateAllRewards();
        assertNotNull(rewards);
        assertEquals(1, rewards.size());
        RewardResponse cust1 = rewards.get(0);
        assertEquals("cust1", cust1.getCustomerId());
        assertTrue(cust1.getTotalRewards() > 0);
        assertTrue(cust1.getMonthlyRewards().containsKey("January"));
        assertTrue(cust1.getMonthlyRewards().containsKey("February"));
    }

    /**
     * Verifies that rewards are correctly fetched for a specific valid customer ID.
     */
    @Test
    void testGetRewardsByCustomerId_valid() {
        RewardResponse response = rewardService.getRewardsByCustomerId("cust1");

        assertEquals("cust1", response.getCustomerId());
        assertTrue(response.getTotalRewards() > 0);
        assertFalse(response.getMonthlyRewards().isEmpty());
    }

    /**
     * Adds a valid transaction and verifies that rewards are correctly computed.
     * 110 should yield 70 points: (110 - 100 = 10 → 2*10 = 20) + 50 (for amount > 50)
     */
    @Test
    void testAddTransaction_valid() {
        Transaction transaction = new Transaction("cust3", 110, "2025-03-20");
        rewardService.addTransaction(transaction);
        RewardResponse response = rewardService.getRewardsByCustomerId("cust3");
        assertEquals("cust3", response.getCustomerId());
        assertEquals(70, response.getTotalRewards()); // Expected points from 110
    }

    /**
     * Tests that an exception is thrown when adding a transaction with a negative amount.
     */
    @Test
    void testAddTransaction_negativeAmount() {
        Transaction transaction = new Transaction("cust3", -50, "2025-03-20");

        assertThrows(InvalidTransactionException.class, () -> {
            rewardService.addTransaction(transaction);
        });
    }

}
