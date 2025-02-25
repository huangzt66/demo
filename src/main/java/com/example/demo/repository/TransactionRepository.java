package com.example.demo.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;

@Repository
public class TransactionRepository {
    private static Map<String, Transaction> transactionMap = new HashMap<>();

    /**
     * 保存一个Transaction到Transaction MAP中。
     * 如果Transaction已经存在于映射中，它将被更新。
     * 如果Transaction不存在，它将被添加到映射中。
     *
     * @param transaction 要保存的Transaction对象。
     * @return 保存后的Transaction对象。
     */
    public Transaction save(Transaction transaction) {
        // 将Transaction对象存入transactionMap，键为Transaction的ID，值为Transaction对象本身
        transactionMap.put(transaction.getId(), transaction);
        // 返回保存后的Transaction对象
        return transaction;
    }

    public Transaction findById(String id) {
        return transactionMap.get(id);
    }

    public List<Transaction> findAll() {
        return transactionMap.values().stream().collect(Collectors.toList());
    }

    public void deleteById(String id) {
        transactionMap.remove(id);
    }
}