package org.axon.accountanalyticscqrsaxon.query.controllers;

import org.axon.accountanalyticscqrsaxon.query.entities.Account;
import org.axon.accountanalyticscqrsaxon.query.queries.GetAllAccounts;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {
    private QueryGateway queryGateway;


    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/list")
    public CompletableFuture<List<Account>> accountList(){
        CompletableFuture<List<Account>> result = queryGateway.query
                (new GetAllAccounts(),
                ResponseTypes.multipleInstancesOf(Account.class));
        return result;

}}
