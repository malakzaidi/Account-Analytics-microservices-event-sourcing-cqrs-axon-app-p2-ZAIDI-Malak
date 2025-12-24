package org.axon.accountanalyticscqrsaxon.commands.controllers;


import org.axon.accountanalyticscqrsaxon.commonapi.commands.CreateAccountCommand;
import org.axon.accountanalyticscqrsaxon.commonapi.dto.CreateAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {
    private CommandGateway commandGateway;
    @PostMapping("/create")
    public CompletableFuture<String> createdAccount(@RequestBody CreateAccountDTO request){

        CompletableFuture<String> result = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return result;

    }
}
