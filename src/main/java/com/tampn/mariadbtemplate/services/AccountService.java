package com.tampn.mariadbtemplate.services;

import com.tampn.mariadbtemplate.entities.Account;
import com.tampn.mariadbtemplate.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void generateData() {

    }

    public List<Account> findUserByCodeAndName(String code, String name, int page, int size) {
        return accountRepository.listByCodeOrName(code, name, page, size);
    }
}
