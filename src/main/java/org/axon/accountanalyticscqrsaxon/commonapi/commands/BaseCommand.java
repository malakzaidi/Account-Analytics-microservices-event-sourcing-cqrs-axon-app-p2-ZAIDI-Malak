package org.axon.accountanalyticscqrsaxon.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand <T>{
    @TargetAggregateIdentifier
    @Getter
    T id;
    public BaseCommand(T id){
        this.id = id;
    }


}
