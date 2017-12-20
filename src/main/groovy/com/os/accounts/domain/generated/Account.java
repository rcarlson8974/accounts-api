package com.os.accounts.domain.generated;

import com.netflix.hollow.api.objects.HollowObject;
import com.netflix.hollow.core.schema.HollowObjectSchema;

import com.netflix.hollow.tools.stringifier.HollowRecordStringifier;

@SuppressWarnings("all")
public class Account extends HollowObject {

    public Account(AccountDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    public HString getAccountId() {
        int refOrdinal = delegate().getAccountIdOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getAccountDesc() {
        int refOrdinal = delegate().getAccountDescOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getUserId() {
        int refOrdinal = delegate().getUserIdOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getPasswordHint() {
        int refOrdinal = delegate().getPasswordHintOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getPinIdHint() {
        int refOrdinal = delegate().getPinIdHintOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public HString getUrl() {
        int refOrdinal = delegate().getUrlOrdinal(ordinal);
        if(refOrdinal == -1)
            return null;
        return  api().getHString(refOrdinal);
    }

    public AccountAPI api() {
        return typeApi().getAPI();
    }

    public AccountTypeAPI typeApi() {
        return delegate().getTypeAPI();
    }

    protected AccountDelegate delegate() {
        return (AccountDelegate)delegate;
    }

}