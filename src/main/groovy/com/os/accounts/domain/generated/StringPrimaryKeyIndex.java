package com.os.accounts.domain.generated;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class StringPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<AccountAPI, HString> {

    public StringPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, false);    }

    public StringPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah) {
        this(consumer, isListenToDataRefreah, ((HollowObjectSchema)consumer.getStateEngine().getSchema("String")).getPrimaryKey().getFieldPaths());
    }

    public StringPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public StringPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah, String... fieldPaths) {
        super(consumer, "String", isListenToDataRefreah, fieldPaths);
    }

    public HString findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getHString(ordinal);
    }

}