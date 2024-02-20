package com.wip.digitalbanking.query.repository;

import com.wip.digitalbanking.query.entities.Account;
import com.wip.digitalbanking.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,String> {
    List<AccountOperation> findByAccountId (String accountId);

}
