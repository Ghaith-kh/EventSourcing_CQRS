package com.wip.digitalbanking.query.Services;

import com.wip.digitalbanking.coreapi.enums.AccountStatus;
import com.wip.digitalbanking.coreapi.enums.OperationType;
import com.wip.digitalbanking.coreapi.events.AccountActivatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreditedEvent;
import com.wip.digitalbanking.coreapi.events.AccountDebitedEvent;
import com.wip.digitalbanking.query.dto.AccountDTO;
import com.wip.digitalbanking.query.dto.AccountOperationDTO;
import com.wip.digitalbanking.query.dto.HistoryDTO;
import com.wip.digitalbanking.query.entities.Account;
import com.wip.digitalbanking.query.entities.AccountOperation;
import com.wip.digitalbanking.query.mappers.AccountMapper;
import com.wip.digitalbanking.query.mappers.AccountOperationMapper;
import com.wip.digitalbanking.query.queries.GetAccountByIdQuery;
import com.wip.digitalbanking.query.queries.GetHistoryQuery;
import com.wip.digitalbanking.query.queries.GetOprationByAccount;
import com.wip.digitalbanking.query.repository.AccountOperationRepository;
import com.wip.digitalbanking.query.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QueryHandlerService {
    private AccountRepository accountRepository;
    private AccountOperationRepository accountOperationRepository;
    private AccountMapper accountMapper;
    private AccountOperationMapper accountOperationMapper;

    @QueryHandler
    public AccountDTO handle(GetAccountByIdQuery query) {
        Account account = accountRepository.findById(query.getAccountId()).get();
        log.info(account.toString());


        return accountMapper.fromaccount(account);
    }

    @QueryHandler
    public List<AccountOperationDTO> handle(GetOprationByAccount query) {
        log.info(query.getAccountId());
        List<AccountOperation> accountOperation = accountOperationRepository.findByAccountId(query.getAccountId());
        log.info(accountOperation.toString());
        List<AccountOperationDTO> accountOperationDTOS = new ArrayList<>();
        if (!accountOperation.isEmpty()) {
            accountOperationDTOS =
                    accountOperation
                            .stream()
                            .map(op -> accountOperationMapper.fromAccountOperation(op))
                            .toList();
        }


        return accountOperationDTOS;
    }

    @QueryHandler
    public HistoryDTO handle(GetHistoryQuery query) {
        String accountId = query.getAccountId();
        log.info(accountId);
        List<AccountOperation> accountOperation = accountOperationRepository.findByAccountId(accountId);
        AccountDTO account = accountMapper.fromaccount(accountRepository.findById(accountId).get());
        List<AccountOperationDTO> accountOperationDTOS =

                accountOperation
                        .stream()
                        .map(op -> accountOperationMapper.fromAccountOperation(op))
                        .toList();

        return new HistoryDTO(account, accountOperationDTOS);
    }

}



