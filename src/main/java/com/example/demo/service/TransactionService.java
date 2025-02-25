package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * 创建交易
     *
     * @param transaction 交易对象
     * @return 保存后的交易对象
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction createTransaction(Transaction transaction) {
        // 简单的金额验证
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("交易金额必须大于 0");
        }
        return transactionRepository.save(transaction);
    }

    /**
     * 根据 ID 获取交易
     *
     * @param id 交易 ID
     * @return 交易对象，如果未找到则返回 null
     */
    @Cacheable(value = "transactions", key = "#id")
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    /**
     * 获取所有交易
     *
     * @return 所有交易的列表
     */
    @Cacheable(value = "transactions", key = "'all'")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * 根据 ID 更新交易
     *
     * @param id          交易 ID
     * @param transaction 新的交易对象
     * @return 更新后的交易对象，如果未找到则返回 null
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction updateTransaction(String id, Transaction transaction) {
        // 简单的金额验证
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("交易金额必须大于 0");
        }
        Transaction existingTransaction = transactionRepository.findById(id);
        if (existingTransaction != null) {
            // transaction.setId(id);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    /**
     * 根据 ID 删除交易
     *
     * @param id 交易 ID
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }
}