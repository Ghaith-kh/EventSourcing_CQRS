package com.wip.digitalbanking.coreapi.events;

import com.wip.digitalbanking.coreapi.enums.AccountStatus;
import com.wip.digitalbanking.coreapi.commands.BaseCommand;
import lombok.Getter;

public class AccountActivatedEvent extends BaseCommand<String> {
    @Getter
    private AccountStatus status;

    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.status = status;
    }
}
