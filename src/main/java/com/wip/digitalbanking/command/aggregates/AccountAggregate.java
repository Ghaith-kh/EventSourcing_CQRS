package com.wip.digitalbanking.command.aggregates;

import com.wip.digitalbanking.command.Exception.balanceNotSufficientException;
import com.wip.digitalbanking.coreapi.commands.CreateAccountCommand;
import com.wip.digitalbanking.coreapi.commands.CreditAccountCommand;
import com.wip.digitalbanking.coreapi.commands.DebitAccountCommand;
import com.wip.digitalbanking.coreapi.enums.AccountStatus;
import com.wip.digitalbanking.coreapi.events.AccountActivatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreatedEvent;
import com.wip.digitalbanking.coreapi.events.AccountCreditedEvent;
import com.wip.digitalbanking.coreapi.events.AccountDebitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
@Slf4j
@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
       // default Constructor required by axon
    }
@CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        log.info("create Account command .!");
        // business Logic //
        AggregateLifecycle.apply((new AccountCreatedEvent(
                command.getId()
                ,command.getInitialBalance(),
                command.getCurrency()

        )));
    }
    @CommandHandler
    public void handel(CreditAccountCommand command) {
        log.info("credit Account command .!");
        // business Logic //
        AggregateLifecycle.apply((new AccountCreditedEvent(
                command.getId()
                ,command.getAmount(),
                command.getCurrency()

        )));
    }



    @EventSourcingHandler
    public void on(AccountCreatedEvent event){

        this.accountId=event.getId();
        this.currency=event.getCurrency();
        this.balance=event.getInitialBalance();
        this.status=AccountStatus.CREATED;

        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){

        this.accountId=event.getId();

        this.status=event.getStatus();


    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){

        this.accountId=event.getId();
        this.currency=event.getCurrency();
        this.balance=(this.balance.subtract(event.getAmount() ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){

        this.accountId=event.getId();
        this.currency=event.getCurrency();
        log.info(event.getId());
        this.balance=this.balance.add(event.getAmount());
    }

    @CommandHandler
    public void handel(DebitAccountCommand  command) {
        log.info("Debit Account command .!");
        log.info(this.balance.toString());
        // business Logic //
        if (this.balance.subtract(command.getAmount()).doubleValue()<0){
            throw new balanceNotSufficientException("wini flousek ya 7aj ");
        }
        AggregateLifecycle.apply((new AccountDebitedEvent(
                command.getId()
                ,command.getAmount(),
                command.getCurrency()

        )));
    }



}
