package com.os.accounts.domain.generated;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class AccountPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<AccountAPI, Account> {

    public AccountPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, false);    }

    public AccountPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah) {
        this(consumer, isListenToDataRefreah, ((HollowObjectSchema)consumer.getStateEngine().getSchema("Account")).getPrimaryKey().getFieldPaths());
    }

    public AccountPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public AccountPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah, String... fieldPaths) {
        super(consumer, "Account", isListenToDataRefreah, fieldPaths);
    }

    public Account findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getAccount(ordinal);
    }

}