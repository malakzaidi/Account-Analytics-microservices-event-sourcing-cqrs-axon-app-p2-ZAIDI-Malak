package org.axon.accountanalyticscqrsaxon.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axon.accountanalyticscqrsaxon.commonapi.events.AccountCreatedEvent;
import org.axon.accountanalyticscqrsaxon.query.entities.Account;
import org.axon.accountanalyticscqrsaxon.query.repositories.AccountRepository;
import org.axon.accountanalyticscqrsaxon.query.repositories.AccountTransactionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountEventHandler {
    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;

    @EventHandler
    public void on(AccountCreatedEvent  accountCreatedEvent, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("***********************************");
        log.info("Account Created Event Received");
        Account account = new Account();
        account.setId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getInitialBalance());
        account.setCurrency(accountCreatedEvent.getCurrency());
        account.setStatus(accountCreatedEvent.getStatus());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);

    }
}
