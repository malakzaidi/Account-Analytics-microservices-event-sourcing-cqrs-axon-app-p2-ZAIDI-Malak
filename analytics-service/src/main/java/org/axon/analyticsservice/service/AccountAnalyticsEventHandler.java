package org.axon.analyticsservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axon.accountanalyticscqrsaxon.commonapi.events.AccountCreatedEvent;
import org.axon.accountanalyticscqrsaxon.commonapi.events.AccountCreditedEvent;
import org.axon.accountanalyticscqrsaxon.commonapi.events.AccountDebitedEvent;
import org.axon.analyticsservice.entities.AccountAnalytics;
import org.axon.analyticsservice.queries.GetAllAccountAnalytics;
import org.axon.analyticsservice.queries.GetAllAccountAnalyticsByAccountId;
import org.axon.analyticsservice.repository.AccountAnalyticsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AccountAnalyticsEventHandler {
    private AccountAnalyticsRepository accountAnalyticsRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("===================");
        log.info("AccountCreatedEvent received");
        AccountAnalytics accountAnalytics = AccountAnalytics.builder()
                .accountId(event.getId())
                .totalCredit(0)
                .totalDebit(0)
                .balance(event.getInitialBalance())
                .totalNumberofCredits(0)
                .totalNumberofDebits(0)
                .build();
    accountAnalyticsRepository.save(accountAnalytics);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("===================");
        log.info("AccountDebitedEvent received");
        AccountAnalytics accountAnalytics= accountAnalyticsRepository.findByAccountId(event.getId().toString());
        accountAnalytics.setBalance(accountAnalytics.getBalance()-event.getAmount());
        accountAnalytics.setTotalDebit(accountAnalytics.getTotalDebit()+event.getAmount());
        accountAnalytics.setTotalNumberofDebits(accountAnalytics.getTotalNumberofDebits()+1);
        accountAnalyticsRepository.save(accountAnalytics);

    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("===================");
        log.info("AccountCreditedEvent received");
        AccountAnalytics accountAnalytics= accountAnalyticsRepository.findByAccountId(event.getId().toString());
        accountAnalytics.setBalance(accountAnalytics.getBalance()+event.getAmount());
        accountAnalytics.setTotalCredit(accountAnalytics.getTotalDebit()+event.getAmount());
        accountAnalytics.setTotalNumberofCredits(accountAnalytics.getTotalNumberofCredits()+1);
        accountAnalyticsRepository.save(accountAnalytics);

    }

    @QueryHandler
    public List<AccountAnalytics>on(GetAllAccountAnalytics query){
        return accountAnalyticsRepository.findAll();
    }

    @QueryHandler
    public AccountAnalytics on(GetAllAccountAnalyticsByAccountId query) {
        return accountAnalyticsRepository.findByAccountId(query.getAccountId());
    }


}


