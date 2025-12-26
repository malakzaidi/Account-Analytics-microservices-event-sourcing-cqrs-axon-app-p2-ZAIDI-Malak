package org.axon.accountanalyticscqrsaxon.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor  @Data @NoArgsConstructor
public class CreditAccountDTO {
    @Getter private String accountId;
    @Getter private double amount;
    @Getter private String currency;
}
