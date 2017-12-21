package services.impl;

import dao.impl.UserDaoImpl;
import entity.Account;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static volatile UserDaoImpl instance;

    public UserService() {

    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    public User getById(long id) {
        return getInstance().getById(id);
    }

    public List<User> getAll() {
        return getInstance().getAll();
    }

    public List<User> getAllUserWithAccount() {
        AccountService accountService = new AccountService();
        List<User> list = getInstance().getAll();
        List<User> userList = new ArrayList<>();
        for (User user : list) {
            Account account = accountService.getAccountByUserId(user.getId());
            user.setAccount(account);
            userList.add(user);
        }
        return userList;
    }

    public User getUserWithMaxAccount() {
        return getInstance().getUserWithMaxAccount();
    }

}
