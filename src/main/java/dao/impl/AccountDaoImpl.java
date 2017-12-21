package dao.impl;

import connection.ConnectionJDBC;
import dao.AccountDao;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static final String SQL_SELECT_ALL_ACCOUNT_QUERY = "SELECT * FROM account";
    private static final String SQL_SELECT_SUM_ALL_ACCOUNT_QUERY = "SELECT SUM(account) FROM account";
    private static final String SQL_SELECT_ACCOUNT_BY_USER_ID_QUERY = "SELECT * FROM account WHERE userId = ?";


    public Account getAccountByUserId(long id) {
        Connection connection = ConnectionJDBC.getConnection();
        Account account = new Account();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                account.setId(resultSet.getLong(1));
                account.setBalance(resultSet.getLong(2));
                account.setUserId(resultSet.getLong(3));
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }

    public List<Account> getAll() {
        List<Account> resultList = new LinkedList<>();
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ACCOUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Account account = new Account(resultSet.getLong(1), resultSet.getLong(2), resultSet.getLong(3));
                resultList.add(account);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public long sumAllAccounts() {
        long sumAccounts = 0;
        Connection connection = ConnectionJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_SUM_ALL_ACCOUNT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                sumAccounts = resultSet.getInt(1);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumAccounts;
    }
}
