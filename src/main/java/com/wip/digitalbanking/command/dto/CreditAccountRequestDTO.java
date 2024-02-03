package com.wip.digitalbanking.command.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditAccountRequestDTO {
    public  String accountId ;
    public BigDecimal amount;

    public  String currency ;
}
