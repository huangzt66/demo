package com.example.demo.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.Transaction;

public class TransactionRepositoryTest {

    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        // 在每个测试方法执行前初始化 TransactionRepository
        transactionRepository = new TransactionRepository();
    }

    @Test
    public void testSave() {
        // 创建一个 Transaction 实例
        Transaction transaction = new Transaction();
        transaction.setId("1");

        // 调用 save 方法保存Transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // 验证保存的Transaction是否正确
        assertEquals(transaction, savedTransaction);
        assertEquals(transaction, transactionRepository.findById("1"));
    }

    @Test
    public void testFindById() {
        // 创建一个 Transaction 实例
        Transaction transaction = new Transaction();
        transaction.setId("2");

        // 保存Transaction
        transactionRepository.save(transaction);

        // 根据 ID 查找Transaction
        Transaction foundTransaction = transactionRepository.findById("2");

        // 验证找到的Transaction是否正确
        assertEquals(transaction, foundTransaction);

        // 查找不存在的 ID，验证返回 null
        assertNull(transactionRepository.findById("3"));
    }

    @Test
    public void testFindAll() {
        // 创建多个 Transaction 实例
        Transaction transaction1 = new Transaction();
        transaction1.setId("4");
        Transaction transaction2 = new Transaction();
        transaction2.setId("5");

        // 保存Transaction
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        // 获取所有Transaction
        List<Transaction> transactions = transactionRepository.findAll();

        // 验证返回的Transaction列表大小是否正确
        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(transaction1));
        assertTrue(transactions.contains(transaction2));
    }

    @Test
    public void testDeleteById() {
        // 创建一个 Transaction 实例
        Transaction transaction = new Transaction();
        transaction.setId("6");

        // 保存Transaction
        transactionRepository.save(transaction);

        // 删除Transaction
        transactionRepository.deleteById("6");

        // 验证Transaction是否已被删除
        assertNull(transactionRepository.findById("6"));
    }
}