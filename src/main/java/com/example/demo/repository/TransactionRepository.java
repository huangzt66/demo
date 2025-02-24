package com.example.demo.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {
    private static Map<String, Transaction> transactionMap = new HashMap<>();

    /**
     * 保存一个事务到事务映射中。
     * 如果事务已经存在于映射中，它将被更新。
     * 如果事务不存在，它将被添加到映射中。
     *
     * @param transaction 要保存的事务对象。
     * @return 保存后的事务对象。
     */
    public Transaction save(Transaction transaction) {
        // 将事务对象存入transactionMap，键为事务的ID，值为事务对象本身
        transactionMap.put(transaction.getId(), transaction);
        // 返回保存后的事务对象
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