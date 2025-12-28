package org.axon.accountanalyticscqrsaxon.query.service;

import lombok.AllArgsConstructor;
import org.axon.accountanalyticscqrsaxon.query.entities.Account;
import org.axon.accountanalyticscqrsaxon.query.queries.GetAllAccounts;
import org.axon.accountanalyticscqrsaxon.query.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountQueryHandler {
    private AccountRepository accountRepository;

    @QueryHandler
    public List<Account> on (GetAllAccounts query){
        return accountRepository.findAll();
    }
}
