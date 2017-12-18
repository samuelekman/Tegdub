package com.samuelekman.tegdub.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.samuelekman.tegdub.model.Transaction;

import java.util.List;


/**
 * Created by samuel on 2017-12-14.
 */

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTransaction(Transaction transaction);

    @Query("SELECT * FROM transactions")
    List<Transaction> getAllTransactions();

    @Query("select * from transactions where t_id = :transactionId")
    List<Transaction> getTransaction(int transactionId);

    @Query("select * from transactions where strftime('%m', date) = :month")
    List<Transaction> getTransactionMonth(String month);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTransaction(Transaction transaction);

    @Query("delete from transactions where t_id = :transactionId")
    void deleteTransaction(int transactionId);
}
