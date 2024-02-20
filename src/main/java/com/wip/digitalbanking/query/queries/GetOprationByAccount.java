package com.wip.digitalbanking.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor
public class GetOprationByAccount {
    String accountId;
    public GetOprationByAccount(String accountId) {
        this.accountId=accountId;
    }
}
