package com.wip.digitalbanking.query.Controller;

import com.wip.digitalbanking.query.dto.AccountOperationDTO;
import com.wip.digitalbanking.query.entities.AccountOperation;
import com.wip.digitalbanking.query.queries.GetOprationByAccount;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/AccountOperation")
public class AccountOperationQueryRestAPI {
    @Autowired
    QueryGateway queryGateway;
    @GetMapping("/{accountId}")
    public CompletableFuture<List<AccountOperationDTO>> getopration(  @PathVariable  String accountId){


        CompletableFuture<List<AccountOperationDTO>> response = queryGateway.query(new GetOprationByAccount(accountId)
        , ResponseTypes.multipleInstancesOf(AccountOperationDTO.class)
        )
        ;
        return response;
    }


}
