package com.wip.digitalbanking.query.repository;

import com.wip.digitalbanking.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {

}
