package com.example.demo.model;

//import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
  private String customerId;
  private double amount;
  private String date;
//  public Transaction() {}

  /**
   * Constructor to initialize a transaction with customer ID, amount, and date.
   *
   * @param customerId ID of the customer
   * @param amount     Amount spent in the transaction
   * @param date       Date of transaction in "yyyy-MM-dd" format
   */


  public Transaction(String customerId, double amount, String date) {
      this.customerId = customerId;
      this.amount = amount;
      this.date = date;
  }

  public String getCustomerId() {
      return customerId;
  }

  public double getAmount() {
      return amount;
  }

  public String getDate() {
      return date;
  }

  public void setTransactionDate(String date) {
      this.date = date;
  }
  
  /**
   * Converts the stored date String into a LocalDate object using the format "yyyy-MM-dd".
   *
   * @return LocalDate representation of the transaction date
   */

  public LocalDate getTransactionDate() {
      return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
