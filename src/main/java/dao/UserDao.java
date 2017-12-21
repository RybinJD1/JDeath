package dao;

import entity.User;

import java.util.List;

public interface UserDao {

    User getById(long id);

    List<User> getAll();

    User getUserWithMaxAccount();
}
