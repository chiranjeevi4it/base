package com.wipro.bankapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bankapp.model.AccountStatus;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
}
