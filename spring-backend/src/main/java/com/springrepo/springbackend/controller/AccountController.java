package com.springrepo.springbackend.controller;

import com.springrepo.springbackend.dto.AccountDTO;
import com.springrepo.springbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountDTO accountDTO) {
        boolean success = accountService.login(accountDTO);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountDTO accountDTO) {
        String message = accountService.register(accountDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        accountService.logout();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkLoginStatus() {
        boolean isLoggedIn = accountService.isLoggedIn();
        if (isLoggedIn) {
            return ResponseEntity.ok("User is logged in");
        } else {
            return ResponseEntity.ok("User is not logged in");
        }
    }
}
