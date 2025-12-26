package org.axon.accountanalyticscqrsaxon.commonapi.events;

import lombok.Getter;
import org.axon.accountanalyticscqrsaxon.commonapi.enums.AccountStatus;

public class AccountCreditedEvent extends BaseEvent {
    @Getter
    private double amount;
    @Getter private String currency;

    public AccountCreditedEvent(String id,double amount,String currency){
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
