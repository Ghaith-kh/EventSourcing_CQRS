package com.wip.digitalbanking.coreapi.events;

import com.wip.digitalbanking.coreapi.commands.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;

public class AccountDebitedEvent extends BaseCommand<String> {
    @Getter private BigDecimal amount;

   @Getter private String currency;

    public AccountDebitedEvent(String id, BigDecimal amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
