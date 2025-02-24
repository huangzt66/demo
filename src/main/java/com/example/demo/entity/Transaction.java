package com.example.demo.entity;
public class Transaction {
    private String id;
    private double amount;
    private String timestamp;
    private String type;

    // 构造函数、getter和setter方法
    public String getId() {
        return id; 
    }
    public void setId(String id) {
        this.id = id; 
    }
    public double getAmount() {
        return amount; 
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type; 
    }
}