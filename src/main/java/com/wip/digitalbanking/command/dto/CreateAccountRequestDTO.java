package com.wip.digitalbanking.command.dto;

import lombok.*;

import java.math.BigDecimal;
@Data @NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class CreateAccountRequestDTO {
     public BigDecimal initialBalance;

    public  String currency ;

}
