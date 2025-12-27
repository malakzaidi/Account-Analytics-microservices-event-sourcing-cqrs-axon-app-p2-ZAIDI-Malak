package org.axon.accountanalyticscqrsaxon.query.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axon.accountanalyticscqrsaxon.query.enums.TransactionType;


import java.time.Instant;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class AccountTransaction {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private Instant instant;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    private Account account;
}
