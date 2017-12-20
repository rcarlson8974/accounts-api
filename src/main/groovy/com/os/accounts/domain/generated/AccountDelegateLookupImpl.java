package com.os.accounts.domain.generated;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class AccountDelegateLookupImpl extends HollowObjectAbstractDelegate implements AccountDelegate {

    private final AccountTypeAPI typeAPI;

    public AccountDelegateLookupImpl(AccountTypeAPI typeAPI) {
        this.typeAPI = typeAPI;
    }

    public int getAccountIdOrdinal(int ordinal) {
        return typeAPI.getAccountIdOrdinal(ordinal);
    }

    public int getAccountDescOrdinal(int ordinal) {
        return typeAPI.getAccountDescOrdinal(ordinal);
    }

    public int getUserIdOrdinal(int ordinal) {
        return typeAPI.getUserIdOrdinal(ordinal);
    }

    public int getPasswordHintOrdinal(int ordinal) {
        return typeAPI.getPasswordHintOrdinal(ordinal);
    }

    public int getPinIdHintOrdinal(int ordinal) {
        return typeAPI.getPinIdHintOrdinal(ordinal);
    }

    public int getUrlOrdinal(int ordinal) {
        return typeAPI.getUrlOrdinal(ordinal);
    }

    public AccountTypeAPI getTypeAPI() {
        return typeAPI;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

}