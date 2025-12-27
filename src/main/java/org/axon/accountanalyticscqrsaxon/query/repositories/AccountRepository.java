package org.axon.accountanalyticscqrsaxon.query.repositories;

import org.axon.accountanalyticscqrsaxon.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
