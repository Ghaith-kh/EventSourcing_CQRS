package com.wip.digitalbanking.query.mappers;

import com.wip.digitalbanking.query.dto.AccountDTO;
import com.wip.digitalbanking.query.entities.Account;
import org.mapstruct.Mapper;

@Mapper( componentModel = "SPRING")
public interface AccountMapper {
    public AccountDTO fromaccount(Account account);
    public Account fromaccountDTO(AccountDTO accountDTO );
}

