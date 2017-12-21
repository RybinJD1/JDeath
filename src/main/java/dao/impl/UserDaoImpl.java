package dao.impl;

import connection.ConnectionJDBC;
import dao.UserDao;
import entity.Account;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String SQL_FIND_BY_ID_USER_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_GET_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String SQL_SELECT_MAX_ACCOUNT_QUERY = "SELECT name, surname, account FROM user INNER JOIN account ON user.id = account.userId ORDER BY account DESC LIMIT 1;";


    public User getById(long id) {
        User result = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_USER_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getAll() {
        List<User> resultList = new LinkedList<>();
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_USER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                resultList.add(user);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public User getUserWithMaxAccount() {
        User user = null;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MAX_ACCOUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                Account account = new Account(resultSet.getLong(3));
                user = new User(resultSet.getString(1), resultSet.getString(2), account);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
