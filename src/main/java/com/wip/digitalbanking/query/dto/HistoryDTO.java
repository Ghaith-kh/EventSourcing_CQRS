package com.wip.digitalbanking.query.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wip.digitalbanking.query.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class HistoryDTO {
    @JsonProperty("account")
    public AccountDTO accountDTO;
    @JsonProperty(" les Operations ")
    public List<AccountOperationDTO> accountOperationDTO;
}

