package com.wip.digitalbanking.query.dto;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wip.digitalbanking.coreapi.enums.OperationType;
import com.wip.digitalbanking.query.entities.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private BigDecimal amount;
    @JsonIgnore
private Account account;
    private OperationType type;




}
