package com.wip.digitalbanking.query.mappers;

import com.wip.digitalbanking.query.dto.AccountOperationDTO;
import com.wip.digitalbanking.query.entities.AccountOperation;
import org.mapstruct.Mapper;


@Mapper( componentModel = "SPRING")
public interface AccountOperationMapper {

    public AccountOperationDTO fromAccountOperation (AccountOperation accountOperation);

    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO);


}
