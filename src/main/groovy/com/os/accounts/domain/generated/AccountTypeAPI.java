package com.os.accounts.domain.generated;

import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;

@SuppressWarnings("all")
public class AccountTypeAPI extends HollowObjectTypeAPI {

    private final AccountDelegateLookupImpl delegateLookupImpl;

    public AccountTypeAPI(AccountAPI api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[] {
            "accountId",
            "accountDesc",
            "userId",
            "passwordHint",
            "pinIdHint",
            "url"
        });
        this.delegateLookupImpl = new AccountDelegateLookupImpl(this);
    }

    public int getAccountIdOrdinal(int ordinal) {
        if(fieldIndex[0] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "accountId");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[0]);
    }

    public StringTypeAPI getAccountIdTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getAccountDescOrdinal(int ordinal) {
        if(fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "accountDesc");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public StringTypeAPI getAccountDescTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getUserIdOrdinal(int ordinal) {
        if(fieldIndex[2] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "userId");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[2]);
    }

    public StringTypeAPI getUserIdTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getPasswordHintOrdinal(int ordinal) {
        if(fieldIndex[3] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "passwordHint");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[3]);
    }

    public StringTypeAPI getPasswordHintTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getPinIdHintOrdinal(int ordinal) {
        if(fieldIndex[4] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "pinIdHint");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[4]);
    }

    public StringTypeAPI getPinIdHintTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public int getUrlOrdinal(int ordinal) {
        if(fieldIndex[5] == -1)
            return missingDataHandler().handleReferencedOrdinal("Account", ordinal, "url");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[5]);
    }

    public StringTypeAPI getUrlTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public AccountDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public AccountAPI getAPI() {
        return (AccountAPI) api;
    }

}