package lab.model.dao;

import lab.model.dao.entities.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountsDAO extends AbstractDAO<Account> {
    AccountsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Account getByID(int id) {
        Statement statement = null;
        Account account = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from accounts_table where userNumber = " + id);
            if (resultSet.next()) {
                account = new Account.AccountBuilder()
                        .setUser_id(resultSet.getInt(1))
                        .setScore(resultSet.getDouble(2))
                        .build();
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return account;
        }
    }

    @Override
    public void insert(Account account) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "INSERT INTO accounts_table(userNumber,score) VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(2, account.getScore());
            preparedStatement.setInt(1, account.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Account account) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "UPDATE accounts_table " +
                    "SET score = ? WHERE userNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getScore());
            preparedStatement.setInt(2, account.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public ArrayList<Account> getAll() {
        Statement statement = null;
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from accounts_table");
            while (resultSet.next()) {
                accounts.add(new Account.AccountBuilder()
                        .setUser_id(resultSet.getInt(1))
                        .setScore(resultSet.getDouble(2))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return accounts;
        }
    }

    @Override
    public ArrayList<Account> getWhere(String str) {
        Statement statement = null;
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from accounts_table where " + str);
            while (resultSet.next()) {
                accounts.add(new Account.AccountBuilder()
                        .setUser_id(resultSet.getInt(1))
                        .setScore(resultSet.getDouble(2))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return accounts;
        }
    }

}
