package com.samuelekman.tegdub.utils.OldClasses;

import com.samuelekman.tegdub.Interfaces.TransactionStore;

/**
 * Created by samuel on 2017-12-05.
 */

public class TransactionStoreFactory {
    public static TransactionStore transactionStore(){
        return new FakeTransactionStorage();
    }
}
