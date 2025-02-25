package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    /**
     * 测试创建交易的 API
     * @throws Exception 可能的异常
     */
    @Test
    public void testCreateTransaction() throws Exception {
        // 模拟服务层返回的创建后的交易对象
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        Mockito.when(transactionService.createTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);

        // 发送 POST 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(100.0)));
    }

    /**
     * 测试获取所有交易的 API
     * @throws Exception 可能的异常
     */
    @Test
    public void testGetAllTransactions() throws Exception {
        // 模拟服务层返回的交易列表
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(100.0);
        transactions.add(transaction1);
        Mockito.when(transactionService.getAllTransactions()).thenReturn(transactions);

        // 发送 GET 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount", is(100.0)));
    }

    /**
     * 测试根据 ID 获取交易的 API
     * @throws Exception 可能的异常
     */
    @Test
    public void testGetTransactionById() throws Exception {
        // 模拟服务层返回的交易对象
        String id = "123";
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(200.0);
        Mockito.when(transactionService.getTransactionById(id)).thenReturn(transaction);

        // 发送 GET 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(200.0)));
    }

    /**
     * 测试根据 ID 获取交易，交易不存在的情况
     * @throws Exception 可能的异常
     */
    @Test
    public void testGetTransactionByIdNotFound() throws Exception {
        // 模拟服务层返回 null，表示未找到交易
        String id = "456";
        Mockito.when(transactionService.getTransactionById(id)).thenReturn(null);

        // 发送 GET 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * 测试根据 ID 更新交易，交易不存在的情况
     * @throws Exception 可能的异常
     */
    @Test
    public void testUpdateTransactionNotFound() throws Exception {
        // 模拟服务层返回 null，表示未找到交易
        String id = "999";
        //Mockito.when(transactionService.updateTransaction(id, Mockito.any(Transaction.class))).thenReturn(null);

        // 发送 PUT 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.put("/transactions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * 测试根据 ID 删除交易的 API
     * @throws Exception 可能的异常
     */
    @Test
    public void testDeleteTransaction() throws Exception {
        // 模拟服务层返回的交易对象，表示交易存在
        String id = "123";
        Transaction transaction = new Transaction();
        transaction.setId(id);
        Mockito.when(transactionService.getTransactionById(id)).thenReturn(transaction);

        // 发送 DELETE 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * 测试根据 ID 删除交易，交易不存在的情况
     * @throws Exception 可能的异常
     */
    @Test
    public void testDeleteTransactionNotFound() throws Exception {
        // 模拟服务层返回 null，表示未找到交易
        String id = "456";
        Mockito.when(transactionService.getTransactionById(id)).thenReturn(null);

        // 发送 DELETE 请求并验证响应
        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}