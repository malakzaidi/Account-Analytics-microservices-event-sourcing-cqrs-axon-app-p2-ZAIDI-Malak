package org.axon.accountanalyticscqrsaxon.query.repositories;

import org.axon.accountanalyticscqrsaxon.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
}
