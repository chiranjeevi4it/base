package com.wipro.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bankapp.model.AccountRequest;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
