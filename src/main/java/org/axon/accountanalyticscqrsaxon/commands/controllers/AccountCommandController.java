package org.axon.accountanalyticscqrsaxon.commands.controllers;


import org.axon.accountanalyticscqrsaxon.commonapi.commands.CreateAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.commands.CreditAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.commands.DebitAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.dto.CreateAccountDTO;
import org.axon.accountanalyticscqrsaxon.commonapi.dto.CreditAccountDTO;
import org.axon.accountanalyticscqrsaxon.commonapi.dto.DebitAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;


    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @PostMapping("/create")
    public CompletableFuture<String> createdAccount(@RequestBody CreateAccountDTO request){

        CompletableFuture<String> result = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return result;
    }
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDTO request){
        CompletableFuture<String> result = commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()

        ));
        return result;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler (Exception ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountDTO request){
        CompletableFuture<String> result = commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()

        ));
        return result;
    }


}















