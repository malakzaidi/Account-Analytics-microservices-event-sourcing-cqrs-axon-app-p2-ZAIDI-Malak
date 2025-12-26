package org.axon.accountanalyticscqrsaxon.commands.controllers;


import org.axon.accountanalyticscqrsaxon.commonapi.commands.CreateAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.dto.CreateAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {
    private CommandGateway commandGateway;
    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler (Exception ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

}
