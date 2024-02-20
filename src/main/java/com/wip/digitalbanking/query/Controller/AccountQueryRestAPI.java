package com.wip.digitalbanking.query.Controller;

import com.wip.digitalbanking.query.dto.AccountDTO;
import com.wip.digitalbanking.query.dto.HistoryDTO;
import com.wip.digitalbanking.query.queries.GetAccountByIdQuery;
import com.wip.digitalbanking.query.queries.GetHistoryQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("query/accounts")
public class AccountQueryRestAPI {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{accountId}")
    public CompletableFuture<AccountDTO> getaccount(@PathVariable String accountId) {

        CompletableFuture<AccountDTO> query = queryGateway.query(new GetAccountByIdQuery(accountId), AccountDTO.class);

        return query;
    }

    @GetMapping("/history/{accountId}")
    public CompletableFuture<HistoryDTO> getHistory(@PathVariable String accountId) {

        CompletableFuture<HistoryDTO> query = queryGateway.query(new GetHistoryQuery(accountId), HistoryDTO.class);

        return query;
    }


}
