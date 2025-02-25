package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void testCreateTransaction() {
        // 创建一个测试用的交易对象
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);

        // 模拟保存操作
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // 调用创建交易方法
        Transaction createdTransaction = transactionService.createTransaction(transaction);

        // 验证结果
        assertEquals(transaction, createdTransaction);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testCreateTransactionWithInvalidAmount() {
        // 创建一个金额无效的交易对象
        Transaction transaction = new Transaction();
        transaction.setAmount(-100.0);

        // 验证是否抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.createTransaction(transaction);
        });

        // 验证没有调用保存操作
        verify(transactionRepository, never()).save(transaction);
    }

    @Test
    public void testGetTransactionById() {
        // 测试用的交易 ID
        String id = "123";
        Transaction transaction = new Transaction();
        transaction.setId(id);

        // 模拟根据 ID 查询操作
        when(transactionRepository.findById(id)).thenReturn(transaction);

        // 调用根据 ID 获取交易方法
        Transaction retrievedTransaction = transactionService.getTransactionById(id);

        // 验证结果
        assertEquals(transaction, retrievedTransaction);
        verify(transactionRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllTransactions() {
        // 创建一个测试用的交易列表
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        transactions.add(transaction1);
        transactions.add(transaction2);

        // 模拟查询所有交易操作
        when(transactionRepository.findAll()).thenReturn(transactions);

        // 调用获取所有交易方法
        List<Transaction> retrievedTransactions = transactionService.getAllTransactions();

        // 验证结果
        assertEquals(transactions, retrievedTransactions);
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateTransaction() {
        // 测试用的交易 ID 和交易对象
        String id = "123";
        Transaction existingTransaction = new Transaction();
        existingTransaction.setId(id);
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(200.0);

        // 模拟根据 ID 查询操作
        when(transactionRepository.findById(id)).thenReturn(existingTransaction);
        // 模拟保存操作
        when(transactionRepository.save(newTransaction)).thenReturn(newTransaction);

        // 调用更新交易方法
        Transaction updatedTransaction = transactionService.updateTransaction(id, newTransaction);

        // 验证结果
        assertEquals(newTransaction, updatedTransaction);
        verify(transactionRepository, times(1)).findById(id);
        verify(transactionRepository, times(1)).save(newTransaction);
    }

    @Test
    public void testUpdateTransactionWithInvalidAmount() {
        // 测试用的交易 ID 和金额无效的交易对象
        String id = "123";
        Transaction existingTransaction = new Transaction();
        existingTransaction.setId(id);
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(-200.0);

        // 模拟根据 ID 查询操作
        when(transactionRepository.findById(id)).thenReturn(existingTransaction);

        // 验证是否抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.updateTransaction(id, newTransaction);
        });

        // 验证没有调用保存操作
        verify(transactionRepository, never()).save(newTransaction);
    }

    @Test
    public void testDeleteTransaction() {
        // 测试用的交易 ID
        String id = "123";

        // 调用删除交易方法
        transactionService.deleteTransaction(id);

        // 验证删除操作被调用
        verify(transactionRepository, times(1)).deleteById(id);
    }
}