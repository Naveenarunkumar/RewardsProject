package com.example.demo.model;

import java.util.Map;

/**
 * Represents the reward response data for a customer.
 * 
 * This class contains:
 * - the customer's ID,
 * - reward points earned per month,
 * - and the total reward points.
 * 
 */

public class RewardResponse {
    private String customerId;
    /**
     * A map containing reward points earned by the customer each month.
     * Key: Month name (e.g., "January", "February","March")
     * Value: Reward points for that month
     */
    private Map<String, Integer> monthlyRewards;
    private int totalRewards;

    /**
     * Constructor to create a RewardResponse object with all necessary fields.
     *
     * @paramcustomerId     ID of the customer
     * @parammonthlyRewards Monthly Reward Summary
     * @paramtotalRewards   Total reward points
     */

    public RewardResponse(String customerId, Map<String, Integer> monthlyRewards, int totalRewards) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }
}