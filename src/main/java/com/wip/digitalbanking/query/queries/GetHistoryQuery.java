package com.wip.digitalbanking.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@Data  @NoArgsConstructor
public class GetHistoryQuery {

    private String accountId;
    public GetHistoryQuery(String accountId) {
        this.accountId=accountId;
    }
}
