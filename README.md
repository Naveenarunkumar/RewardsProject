
# Reward Points Calculation Service

This Spring Boot application calculates monthly and total reward points for customers based on their transaction amounts. It provides RESTful APIs to fetch rewards and add new transactions.

## Problem Statement

Retailers want to reward customers based on their purchase amounts:

- For every dollar spent **over $50**, the customer receives **1 point**.
- For every dollar spent **over $100**, the customer receives an **additional 1 point** (i.e., 2 points for every dollar over $100).

## 🎯 Example Calculation

If a customer makes a transaction of **$120**:
- The first $50 gets **0 points**.
- The next $50 (from $51 to $100) gets **1 point per dollar → 50 points**.
- The last $20 (from $101 to $120) gets **2 points per dollar → 40 points**.

**Total = 50 + 40 = 90 points**

---
##💻 Tech Stack

- Java 17
- Spring Boot 3.x
- Maven
- JUnit 5 (unit & integration testing)
- Mockito
- RESTful Web Services
- Git

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
     https://github.com/muskan11709298/java_Assignment_2025.git
     cd reward-points-service
2. Build the project:
   ```bash
    mvn clean install
3. Run the application:
   ```bash
   mvn spring-boot:run
4. Access the APIs:
   ```bash
   http://localhost:8080/api/rewards
API Endpoints
1. GET /api/rewards
- Description: Fetch total and monthly reward points for all customers.
```bash
Response:
[
  {
    "customerId": "cust1",
    "monthlyRewards": {
      "March": 90,
      "February": 30,
      "January": 90
    },
    "totalRewards": 210
  },
  {
    "customerId": "cust2",
    "monthlyRewards": {
      "March": 30,
      "February": 90,
      "January": 30
    },
    "totalRewards": 150
  }
]
```
2. GET /api/rewards/{customerId}
- Description: Fetch total and monthly reward points for a specific customer.
Example:
```bash
GET /api/rewards/cust1
```
Response:
```bash
{
  "customerId": "cust1",
  "monthlyRewards": {
    "March": 90,
    "February": 30,
    "January": 90
  },
  "totalRewards": 210
}
```
3. POST /api/rewards/add-transaction
- Description: Add a new transaction.
Request Body:
```bash
{
  "customerId": "cust3",
  "amount": 120,
  "date": "2025-03-15"
}
```
Response:
```bash
"Transaction added successfully"
```


##Tests
This project includes:

-Unit tests using Mockito and JUnit
-Integration tests for controllers and services
-Tests cover both valid and invalid transactions
To run tests:
```bash
mvn test
```

## 📂 Project Structure
```bash
com.rewards.rewards_points_service
├── controller
│   └── RewardController.java
├── model
│   ├── Transaction.java
│   └── RewardResponse.java
├── service
│   └── RewardService.java
├── exception
│   ├── InvalidTransactionException.java
│   └── GlobalExceptionHandler.java
└── test
    ├── TestRewardControllerUnitTesting.java
    ├── TestRewardControllerIntegrationTesting.java
    ├── TestRewardServiceIntegrationTesting.java
    └── TestRewardServiceUnitTesting.java
```
Error Handling
- InvalidTransactionException: Thrown for negative transaction amounts.
- Global exception handler maps this to a 400 Bad Request.
  
##👨‍💻 Author

S. Naveenarunkumar


Java Backend Developer












