package com.demo.models;

public class OrderItem
{
   private String productCode;
   private int quantityOrdered;
   private double priceEach;
   public OrderItem(String productCode, int quantityOrdered, double priceEach)
   {
       this.productCode = productCode;
       this.quantityOrdered = quantityOrdered;
       this.priceEach = priceEach;
   }
   public String getProductCode() { return productCode; }
   public int getQuantityOrdered() { return quantityOrdered; }
   public double getPriceEach() { return priceEach; }
}
