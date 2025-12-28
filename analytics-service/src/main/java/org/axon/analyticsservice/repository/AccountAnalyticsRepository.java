package org.axon.analyticsservice.repository;

import org.axon.analyticsservice.entities.AccountAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAnalyticsRepository extends JpaRepository<AccountAnalytics, Long> {
    AccountAnalytics findByAccountId(String accountId);

}




