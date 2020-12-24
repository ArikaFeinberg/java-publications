package lab.model.dao;

import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO extends AbstractDAO<User> {
    UsersDAO(Connection connection) {
        super(connection);
    }

    @Override
    public User getByID(int id) {
        Statement statement = null;
        User user = null;
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where user_id = " + id);
            if (resultSet.next()) {
                user = new User.UserBuilder().setUser_id(resultSet.getInt(1))
                        .setEmail(resultSet.getString(2))
                        .setPassword(resultSet.getString(3))
                        .setUserName(resultSet.getString(4))
                        .setRole(Role.valueOf(resultSet.getString(5)))
                        .setBlocked(!resultSet.getString(6).equals("N"))
                        .build();
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return user;
        }
    }

    @Override
    public void insert(User user) {
        try {
            Class.forName("org.postgresql.Driver");
            String sql = "INSERT INTO Users(userName," +
                    "password," +
                    "email) " +
                    "VALUES( ? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            Class.forName("org.postgresql.Driver");
            String sql = " UPDATE Users SET blocked = ? WHERE USER_ID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getBlocked() ? "Y" : "N");
            preparedStatement.setInt(2, user.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public ArrayList<User> getAll() {
        Statement statement = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where role = 'USER' ");
            while (resultSet.next()) {
                users.add(new User.UserBuilder()
                        .setUser_id(resultSet.getInt(1))
                        .setEmail(resultSet.getString(2))
                        .setPassword(resultSet.getString(3))
                        .setUserName(resultSet.getString(4))
                        .setRole(Role.valueOf(resultSet.getString(5)))
                        .setBlocked(!resultSet.getString(6).equals("N"))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return users;
        }
    }

    @Override
    public ArrayList<User> getWhere(String str) {
        System.out.println(str);
        Statement statement = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where " + str);
            while (resultSet.next()) {
                users.add(new User.UserBuilder()
                        .setUser_id(resultSet.getInt(1))
                        .setEmail(resultSet.getString(2))
                        .setPassword(resultSet.getString(3))
                        .setUserName(resultSet.getString(4))
                        .setRole(Role.valueOf(resultSet.getString(5)))
                        .setBlocked(!resultSet.getString(6).equals("N"))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return users;
        }
    }
}
