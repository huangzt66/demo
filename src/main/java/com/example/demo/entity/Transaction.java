package com.example.demo.entity;

public class Transaction {
    private String id;
    private double amount;
    private String timestamp;
    private String type;
    // 新增账号 ID 属性
    private String accountId;

    public Transaction() {
        // 无参构造函数,自动生成 id
        this.id = java.util.UUID.randomUUID().toString();
    }

    public Transaction(String id, double amount, String timestamp, String type, String accountId) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
        this.accountId = accountId;
    }

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

    // 新增的获取账号 ID 方法
    public String getAccountId() {
        return accountId;
    }

    // 新增的设置账号 ID 方法
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}