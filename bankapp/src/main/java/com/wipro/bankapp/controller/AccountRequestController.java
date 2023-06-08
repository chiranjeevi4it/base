package com.wipro.bankapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankapp.model.AccountRequest;
import com.wipro.bankapp.service.AccountRequestService;

@RestController
@RequestMapping("/api/account-requests")
public class AccountRequestController {

    private final AccountRequestService accountRequestService;

    public AccountRequestController(AccountRequestService accountRequestService) {
        this.accountRequestService = accountRequestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRequest> getAccountRequest(@PathVariable Long id) {
        AccountRequest accountRequest = accountRequestService.getAccountRequestById(id);
        return ResponseEntity.ok(accountRequest);
    }

    @GetMapping
    public ResponseEntity<List<AccountRequest>> getAllAccountRequests() {
        List<AccountRequest> accountRequests = accountRequestService.getAllAccountRequests();
        return ResponseEntity.ok(accountRequests);
    }

    @PostMapping
    public ResponseEntity<AccountRequest> addAccountRequest(@RequestBody AccountRequest accountRequest) {
        AccountRequest createdAccountRequest = accountRequestService.addAccountRequest(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRequest> updateAccountRequestStatus(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        AccountRequest updatedAccountRequest = accountRequestService.updateAccountRequestStatus(id, accountRequest);
        return ResponseEntity.ok(updatedAccountRequest);
    }

}

