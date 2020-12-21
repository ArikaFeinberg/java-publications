package lab.model.dao;

import lab.model.dao.connection.ConnectionPool;

public class DAOAbstractFactory {
    public static AbstractDAO getDAO(String table) {
        Tables table_en = Tables.valueOf(table.toUpperCase());

        if (table_en == Tables.USERS)
            return new UsersDAO(ConnectionPool.getConnection());
        else if (table_en == Tables.ACCOUNTS)
            return new AccountsDAO(ConnectionPool.getConnection());
        else if (table_en == Tables.PUBLICATIONS)
            return new PublicationsDAO(ConnectionPool.getConnection());
        else if (table_en == Tables.SUBSCRIPTION)
            return new SubscriptionDAO(ConnectionPool.getConnection());

        else throw new RuntimeException("Enum not found");
    }
}
