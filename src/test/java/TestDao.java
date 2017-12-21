import connection.ConnectionJDBC;
import dao.impl.AccountDaoImpl;
import dao.impl.UserDaoImpl;
import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDao {

    private Connection connection;
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void testInitDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = ConnectionJDBC.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testGetBuyerByID() throws SQLException {
        User user = userDao.getById(5L);
        Assert.assertEquals("Tom", user.getName());
    }

    @Test
    public void testGetAll() {
        Assert.assertNotNull(accountDao.getAll());
        Assert.assertTrue(accountDao.getAll().size() > 0);
    }

    @Test
    public void testMaxAccount() throws SQLException {
        User user = userDao.getUserWithMaxAccount();
        Assert.assertEquals("Riggs", user.getSurname());
        Assert.assertEquals(80000, user.getAccount().getBalance());
    }

    @Test
    public void testSumAllAccount() {
        Assert.assertEquals(155042, accountDao.sumAllAccounts());

    }

}
