package com.wipro.bankapp.service.impl;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bankapp.model.AccountRequest;
import com.wipro.bankapp.model.AccountStatus;
import com.wipro.bankapp.repository.AccountRequestRepository;
import com.wipro.bankapp.service.AccountRequestService;

@Service
public class AccountRequestServiceImpl implements AccountRequestService {
	

	@Autowired
    private AccountRequestRepository accountRequestRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(AccountRequestServiceImpl.class);
	    

	 public AccountRequest addAccountRequest(AccountRequest accountRequest) {
		 logger.info("Creating a new account request: {}", accountRequest);
		 return accountRequestRepository.save(accountRequest);
	 }

    public AccountRequest getAccountRequestById(Long id) {
    	logger.info("Fetching account request with ID: {}", id);
        return accountRequestRepository.findById(id).orElse(null);
    }

    public List<AccountRequest> getAllAccountRequests() {
    	logger.info("Fetching all account requests");
        return accountRequestRepository.findAll();
    }

    
    
    public AccountRequest updateAccountRequestStatus(Long id, AccountRequest accountRequest) {
        AccountRequest existingAccountRequest = accountRequestRepository.findById(id).orElse(null);
        if (existingAccountRequest != null) {
            AccountStatus newStatus = accountRequest.getStatus();
            existingAccountRequest.setStatus(newStatus);
            return accountRequestRepository.save(existingAccountRequest);
        }
        return null;
    }
}

