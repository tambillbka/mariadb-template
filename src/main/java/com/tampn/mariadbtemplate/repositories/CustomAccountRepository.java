package com.tampn.mariadbtemplate.repositories;

import com.tampn.mariadbtemplate.entities.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomAccountRepository {

    public List<Account> listByCodeOrName(String code, String name);
}
