package entity;

public class Account {

    private long id;
    private long balance;
    private long userId;

    public Account() {
    }

    public Account(long id, long balance, long userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account1 = (Account) o;

        if (getId() != account1.getId()) return false;
        if (getBalance() != account1.getBalance()) return false;
        return getUserId() == account1.getUserId();

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getBalance() ^ (getBalance() >>> 32));
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
