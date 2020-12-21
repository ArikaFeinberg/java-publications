package lab.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class AbstractDAO<T> {
    protected Connection connection;

    AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    abstract public T getByID(int id);

    abstract public void insert(T obj);

    abstract public void update(T obj);

    abstract public ArrayList<T> getAll();

    abstract public ArrayList<T> getWhere(String str);

    public void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;

    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
