package com.os.accounts.domain.generated;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class AccountDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, AccountDelegate {

    private final int accountIdOrdinal;
    private final int accountDescOrdinal;
    private final int userIdOrdinal;
    private final int passwordHintOrdinal;
    private final int pinIdHintOrdinal;
    private final int urlOrdinal;
    private AccountTypeAPI typeAPI;

    public AccountDelegateCachedImpl(AccountTypeAPI typeAPI, int ordinal) {
        this.accountIdOrdinal = typeAPI.getAccountIdOrdinal(ordinal);
        this.accountDescOrdinal = typeAPI.getAccountDescOrdinal(ordinal);
        this.userIdOrdinal = typeAPI.getUserIdOrdinal(ordinal);
        this.passwordHintOrdinal = typeAPI.getPasswordHintOrdinal(ordinal);
        this.pinIdHintOrdinal = typeAPI.getPinIdHintOrdinal(ordinal);
        this.urlOrdinal = typeAPI.getUrlOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public int getAccountIdOrdinal(int ordinal) {
        return accountIdOrdinal;
    }

    public int getAccountDescOrdinal(int ordinal) {
        return accountDescOrdinal;
    }

    public int getUserIdOrdinal(int ordinal) {
        return userIdOrdinal;
    }

    public int getPasswordHintOrdinal(int ordinal) {
        return passwordHintOrdinal;
    }

    public int getPinIdHintOrdinal(int ordinal) {
        return pinIdHintOrdinal;
    }

    public int getUrlOrdinal(int ordinal) {
        return urlOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public AccountTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (AccountTypeAPI) typeAPI;
    }

}