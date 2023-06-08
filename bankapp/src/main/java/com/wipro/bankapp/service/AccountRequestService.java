package com.wipro.bankapp.service;

import java.util.List;

import com.wipro.bankapp.model.AccountRequest;

public interface AccountRequestService {

	AccountRequest getAccountRequestById(Long id);

	List<AccountRequest> getAllAccountRequests();

	AccountRequest addAccountRequest(AccountRequest accountRequest);

	AccountRequest updateAccountRequestStatus(Long id, AccountRequest accountRequest);

}
