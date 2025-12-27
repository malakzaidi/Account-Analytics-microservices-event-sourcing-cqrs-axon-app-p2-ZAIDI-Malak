package org.axon.accountanalyticscqrsaxon.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axon.accountanalyticscqrsaxon.commonapi.enums.AccountStatus;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    private String id;
    private double balance;
    private Instant createdAt;
    private String currency;
    private double amount;
    private AccountStatus status;
    @OneToMany(mappedBy="account")
    List<AccountTransaction> transactions;
}
