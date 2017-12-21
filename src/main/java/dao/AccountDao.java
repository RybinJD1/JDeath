package dao;

import entity.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getAll();

    long sumAllAccounts();

    Account getAccountByUserId(long id);
}
