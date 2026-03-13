package com.demo.models;

import java.sql.Date;

public class PaymentItem
{
   private String checkNumber;
   private Date paymentDate;
   private double amount;
   public PaymentItem(String checkNumber, Date paymentDate, double amount)
   {
       this.checkNumber = checkNumber;
       this.paymentDate = paymentDate;
       this.amount = amount;
   }
   public String getCheckNumber() { return checkNumber; }
   public Date getPaymentDate() { return paymentDate; }
   public double getAmount() { return amount; }
}
