package com.os.accounts.domain.generated;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface AccountDelegate extends HollowObjectDelegate {

    public int getAccountIdOrdinal(int ordinal);

    public int getAccountDescOrdinal(int ordinal);

    public int getUserIdOrdinal(int ordinal);

    public int getPasswordHintOrdinal(int ordinal);

    public int getPinIdHintOrdinal(int ordinal);

    public int getUrlOrdinal(int ordinal);

    public AccountTypeAPI getTypeAPI();

}