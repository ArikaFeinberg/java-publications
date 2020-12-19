package lab.model.dao;

import lab.model.dao.entities.Subscription;

import java.sql.*;
import java.util.ArrayList;

public class SubscriptionDAO extends AbstractDAO<Subscription>{
    SubscriptionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Subscription getByID(int id) {
        Statement statement=null;
        Subscription subscription = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Subscription where user_id = " + id);
            if(resultSet.next()) {
                subscription = new Subscription.SubscriptionBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setUser_id(resultSet.getInt(2))
                        .build();
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return subscription;
        }
    }

    @Override
    public void insert(Subscription subscription) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "INSERT INTO subscription(publication_id," +
                    "user_id) " +
                    "VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,subscription.getPublication_id());
            preparedStatement.setInt(2,subscription.getUser_id());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Subscription obj) {

    }

    @Override
    public ArrayList<Subscription> getAll() {
        Statement statement=null;
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Subscription");
            while(resultSet.next()) {
                subscriptions.add(new Subscription.SubscriptionBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setUser_id(resultSet.getInt(2))
                        .build());
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return subscriptions;
        }
    }

    @Override
    public ArrayList<Subscription> getWhere(String str) {
        Statement statement=null;
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from Subscription where " + str);
            while(resultSet.next()) {
                subscriptions.add(new Subscription.SubscriptionBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setUser_id(resultSet.getInt(2))
                        .build());
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
        finally {
            closeStatement(statement);
            return subscriptions;
        }
    }

    public void delete(int id){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "DELETE FROM Subscription where Publication_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
