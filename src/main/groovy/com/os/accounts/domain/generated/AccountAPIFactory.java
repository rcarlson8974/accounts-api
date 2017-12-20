package com.os.accounts.domain.generated;

import com.netflix.hollow.api.client.HollowAPIFactory;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowDataAccess;
import java.util.Collections;
import java.util.Set;

@SuppressWarnings("all")
public class AccountAPIFactory implements HollowAPIFactory {

    private final Set<String> cachedTypes;

    public AccountAPIFactory() {
        this(Collections.<String>emptySet());
    }

    public AccountAPIFactory(Set<String> cachedTypes) {
        this.cachedTypes = cachedTypes;
    }

    @Override
    public HollowAPI createAPI(HollowDataAccess dataAccess) {
        return new AccountAPI(dataAccess, cachedTypes);
    }

    @Override
    public HollowAPI createAPI(HollowDataAccess dataAccess, HollowAPI previousCycleAPI) {
        return new AccountAPI(dataAccess, cachedTypes, Collections.<String, HollowFactory<?>>emptyMap(), (AccountAPI) previousCycleAPI);
    }

}