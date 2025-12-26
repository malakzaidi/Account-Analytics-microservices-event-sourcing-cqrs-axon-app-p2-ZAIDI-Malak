package org.axon.accountanalyticscqrsaxon.commands.aggregate;


import lombok.extern.slf4j.Slf4j;
import org.axon.accountanalyticscqrsaxon.commonapi.commands.CreateAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.enums.AccountStatus;
import org.axon.accountanalyticscqrsaxon.commonapi.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
    }
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        log.info("CreatedAccountCommand received");
        log.info(command.getId());
        if(command.getInitialBalance()<0) throw new RuntimeException("Balance negative exception");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED

        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();
    }
}
