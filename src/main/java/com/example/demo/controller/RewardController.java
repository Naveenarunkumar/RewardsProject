package com.example.demo.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RewardResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.RewardService;



//REST controller to handle reward calculation requests.

@RestController
@RequestMapping("/api/rewards")
public class RewardController{
	private static final Logger logger = LoggerFactory.getLogger(RewardController.class);

	@Autowired
	private RewardService rewardService;
	/*
	 * end point to calculate rewards point for all customer
	 */
	@GetMapping
	public List<RewardResponse> getAllRewards() {
	    logger.info("Fetching rewards for all customers");
		return rewardService.calculateAllRewards();
	}
	/*
	 * end point to calcuate reward point for customer based on customer id
	 */
	@GetMapping("/{customerId}")
	public ResponseEntity<RewardResponse> getRewardsByCustomer(@PathVariable String customerId) {
	    logger.info("Fetching rewards for a particular customers");
		return ResponseEntity.ok(rewardService.getRewardsByCustomerId(customerId));
	}
	/*
	 * Adds a new transaction for rewards calculation
	 */
	@PostMapping("/add-transaction")
	public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
		rewardService.addTransaction(transaction);
	    logger.info("Transaction Added successfully");
		return ResponseEntity.ok("Transaction added successfully");
	}
}
