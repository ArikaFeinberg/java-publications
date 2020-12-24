package lab.model.dao;

import lab.model.dao.connection.ConnectionPool;

public class DAOFactory {

    public static final DAOFactory FACTORY = new DAOFactory();

    public UsersDAO createUsersDAO() {
        return new UsersDAO(ConnectionPool.getConnection());
    }

    public SubscriptionDAO createSubscriptionDAO() {
        return new SubscriptionDAO(ConnectionPool.getConnection());
    }

    public PublicationsDAO createPublicationsDAO() {
        return new PublicationsDAO(ConnectionPool.getConnection());
    }

    public AccountsDAO createAccountsDAO() {
        return new AccountsDAO(ConnectionPool.getConnection());
    }
}
