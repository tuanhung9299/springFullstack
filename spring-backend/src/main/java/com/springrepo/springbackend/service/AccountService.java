package com.springrepo.springbackend.service;

import com.springrepo.springbackend.dto.AccountDTO;
import com.springrepo.springbackend.model.Account;
import com.springrepo.springbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private boolean loggedIn = false;  // Biến trạng thái để theo dõi người dùng đã đăng nhập hay chưa

    public boolean login(AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findByUsername(accountDTO.getUsername());
        if (account.isPresent() && account.get().getPassword().equals(accountDTO.getPassword())) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String register(AccountDTO accountDTO) {
        Optional<Account> existingAccount = accountRepository.findByUsername(accountDTO.getUsername());
        if (existingAccount.isPresent()) {
            return "Username already exists";
        }

        Account newAccount = new Account(accountDTO.getUsername(), accountDTO.getPassword(), accountDTO.getEmail());
        accountRepository.save(newAccount);
        return "Account registered successfully";
    }

    public boolean logout() {
        loggedIn = false;
        return true;
    }
}
