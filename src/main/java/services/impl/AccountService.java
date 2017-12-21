package services.impl;

import dao.impl.AccountDaoImpl;
import entity.Account;

import java.util.List;

public class AccountService {

    private static volatile AccountDaoImpl instance;

    public AccountService() {
    }

    public static AccountDaoImpl getInstance() {
        if (instance == null) {
            synchronized (AccountDaoImpl.class) {
                if (instance == null) {
                    instance = new AccountDaoImpl();
                }
            }
        }
        return instance;
    }

    public Account getAccountByUserId(long id) {
        return getInstance().getAccountByUserId(id);
    }

    public long sumAllAccounts() {
        return getInstance().sumAllAccounts();
    }

    public List<Account> getAll() {
        return getInstance().getAll();
    }

}

