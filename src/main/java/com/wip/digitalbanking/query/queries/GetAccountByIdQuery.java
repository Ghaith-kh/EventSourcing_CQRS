package com.wip.digitalbanking.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class GetAccountByIdQuery {
    private String accountId;
    public GetAccountByIdQuery(String accountId) {

        this.accountId=accountId;
    }
}
