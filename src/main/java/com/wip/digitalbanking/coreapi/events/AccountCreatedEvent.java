package com.wip.digitalbanking.coreapi.events;

import com.wip.digitalbanking.coreapi.commands.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;

public class AccountCreatedEvent extends BaseCommand<String> {
   @Getter
   private BigDecimal initialBalance;
   @Getter private String currency;


    public AccountCreatedEvent(String id, BigDecimal initialBalance, String currency) {
        super(id);
        this.initialBalance=initialBalance;
        this.currency=currency;
    }
}
