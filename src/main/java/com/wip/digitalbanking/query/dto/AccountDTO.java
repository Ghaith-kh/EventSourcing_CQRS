package com.wip.digitalbanking.query.dto;

import com.wip.digitalbanking.coreapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountDTO {
    private String id;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;

}
