package com.wip.digitalbanking.query.Services;

import com.wip.digitalbanking.coreapi.enums.AccountStatus;
import com.wip.digitalbanking.coreapi.enums.OperationType;
import com.wip.digitalbanking.coreapi.events.AccountActivatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreditedEvent;
import com.wip.digitalbanking.coreapi.events.AccountDebitedEvent;
import com.wip.digitalbanking.query.entities.Account;
import com.wip.digitalbanking.query.entities.AccountOperation;
import com.wip.digitalbanking.query.repository.AccountOperationRepository;
import com.wip.digitalbanking.query.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class EventHandlerService {
    private AccountRepository accountRepository;
    private AccountOperationRepository accountOperationRepository;

@EventHandler
    public  void on(AccountCreatedEvent event){
        log.info("*****QUERY ACCOUNT CREATION****");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(AccountStatus.CREATED);
        accountRepository.save(account);
    saveAccountOpperation(account,OperationType.DEBIT,event.getInitialBalance());


    }

    @EventHandler
    public  void on(AccountActivatedEvent event){
        log.info("*****QUERY ACCOUNT Activated ****");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if (Objects.nonNull(account)) {


            account.setStatus(event.getStatus());
            accountRepository.save(account);
            saveAccountOpperation(account,OperationType.DEBIT);

        }

    }



    @ResetHandler
    public String initDataBase(){
    log.info("**********reset db **********");

    accountRepository.deleteAll();
    accountOperationRepository.deleteAll();

    return "sucsss reset ";
    }


    @EventHandler
    @Transactional
    public  void on(AccountCreditedEvent event){
        log.info("*****QUERY ACCOUNT Credited ****");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if (Objects.nonNull(account)) {
            BigDecimal newBalance = account.getBalance().add(event.getAmount());
            account.setBalance( newBalance );

            accountRepository.save(account);
            saveAccountOpperation(account,OperationType.CREDIT,event.getAmount());
        }

    }
    @EventHandler
    @Transactional
    public  void on(AccountDebitedEvent  event){
        log.info("*****QUERY ACCOUNT Debited ****");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if (Objects.nonNull(account)) {
            BigDecimal newBalance = account.getBalance().subtract(event.getAmount());
            account.setBalance( newBalance );

            accountRepository.save(account);

            saveAccountOpperation(account,OperationType.DEBIT,event.getAmount());
        }

    }

    public void saveAccountOpperation(Account account,OperationType operationType) {

        AccountOperation accountOperation= new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(operationType);
        accountOperation.setAccount(account);
        accountOperationRepository.save(accountOperation);

    }
    public void saveAccountOpperation(Account account,OperationType operationType,BigDecimal amount) {

        AccountOperation accountOperation= new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(operationType);
        accountOperation.setAccount(account);
        accountOperationRepository.save(accountOperation);
        accountOperation.setAmount(amount);
    }







}



