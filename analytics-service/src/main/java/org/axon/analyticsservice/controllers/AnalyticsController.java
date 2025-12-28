package org.axon.analyticsservice.controllers;

import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.axon.analyticsservice.entities.AccountAnalytics;
import org.axon.analyticsservice.queries.GetAllAccountAnalytics;
import org.axon.analyticsservice.queries.GetAllAccountAnalyticsByAccountId;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AnalyticsController {
    private QueryGateway queryGateway;

    public AnalyticsController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/query/accountAnalytics")
    public CompletableFuture<List<AccountAnalytics>> accountAnalytics(){
        return queryGateway.query(new GetAllAccountAnalytics(), ResponseTypes.multipleInstancesOf(AccountAnalytics.class));
    }

    @GetMapping("/query/accountAnalytics/{accountId}")
    public CompletableFuture<AccountAnalytics> getAccountAnalyticsById(@PathVariable String accountId) {
        return queryGateway.query(new GetAllAccountAnalyticsByAccountId(accountId), ResponseTypes.instanceOf(AccountAnalytics.class));
    }


    @GetMapping(value = "/query/accountAnalytics/{accountId}/watch",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountAnalytics> watchAccountAnalyticsById(@PathVariable String accountId) {
        SubscriptionQueryResult<AccountAnalytics, AccountAnalytics> subscriptionQueryResult = queryGateway.subscriptionQuery(
                new GetAllAccountAnalyticsByAccountId(accountId),
                ResponseTypes.instanceOf(AccountAnalytics.class),
                ResponseTypes.instanceOf(AccountAnalytics.class));
        return subscriptionQueryResult.initialResult().concatWith(subscriptionQueryResult.updates());
    }


}
