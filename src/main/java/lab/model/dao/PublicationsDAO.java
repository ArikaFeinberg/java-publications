package lab.model.dao;

import lab.model.dao.entities.Publication;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.dao.entities.enums.Theme;

import java.sql.*;
import java.util.ArrayList;

public class PublicationsDAO extends AbstractDAO<Publication> {
    PublicationsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Publication getByID(int id) {
        Statement statement = null;
        Publication publication = null;
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Publications where publication_id = " + id);
            if (resultSet.next()) {
                publication = new Publication.PublicationBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setTheme(Theme.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setPrice(resultSet.getDouble(4))
                        .build();
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return publication;
        }
    }

    @Override
    public void insert(Publication publication) {

        try {
            Class.forName("org.postgresql.Driver");
            String sql = "INSERT INTO publications (theme,name,price) VALUES (?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, publication.getTheme().toString());
            preparedStatement.setString(2, publication.getName());
            preparedStatement.setDouble(3, publication.getPrice());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Publication publication) {
        try {
            Class.forName("org.postgresql.Driver");
            String sql = "UPDATE publications " +
                    "SET theme = ?," +
                    "name = ?," +
                    "price = ?" +
                    "WHERE Publication_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, publication.getTheme().toString());
            preparedStatement.setString(2, publication.getName());
            preparedStatement.setDouble(3, publication.getPrice());
            preparedStatement.setInt(4, publication.getPublication_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public ArrayList<Publication> getAll() {
        Statement statement = null;
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from publications ");
            while (resultSet.next()) {
                publications.add(new Publication.PublicationBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setTheme(Theme.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setPrice(resultSet.getDouble(4))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return publications;
        }
    }

    @Override
    public ArrayList<Publication> getWhere(String str) {
        Statement statement = null;
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Publications where " + str);
            while (resultSet.next()) {
                publications.add(new Publication.PublicationBuilder()
                        .setPublication_id(resultSet.getInt(1))
                        .setTheme(Theme.valueOf(resultSet.getString(2)))
                        .setName(resultSet.getString(3))
                        .setPrice(resultSet.getDouble(4))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            closeStatement(statement);
            return publications;
        }
    }

    public void delete(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            String sql = "DELETE FROM Publications where Publication_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
