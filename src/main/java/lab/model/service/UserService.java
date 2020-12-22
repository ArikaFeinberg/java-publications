package lab.model.service;

import lab.model.dao.DAOAbstractFactory;
import lab.model.dao.UsersDAO;

import lab.model.dao.entities.User;

import java.util.ArrayList;

public class UserService {

    public User getUser(String username, String password) {
        UsersDAO usersDAO = (UsersDAO) DAOAbstractFactory.getDAO("USERS");
        ArrayList<User> users = usersDAO.getWhere(" userName  = '"
                + username + "' AND password = '"
                + password + "' ");
        if (users == null || users.size() == 0) return null;
        User user = users.get(0);
        usersDAO.close();
        return user;
    }

    public void registration(User user) {
        UsersDAO usersDAO = (UsersDAO) DAOAbstractFactory.getDAO("USERS");
        usersDAO.insert(user);
        usersDAO.close();
    }

    public ArrayList<User> getAll() {
        UsersDAO usersDAO = (UsersDAO) DAOAbstractFactory.getDAO("USERS");
        ArrayList<User> users =
                usersDAO.getAll();
        usersDAO.close();
        return users;
    }

    public void block(int id) {
        UsersDAO usersDAO = (UsersDAO) DAOAbstractFactory.getDAO("USERS");
        User user =
                usersDAO.getByID(id);
        user.setBlocked(true);
        usersDAO.update(user);
        usersDAO.close();
    }

    public void unblock(int id) {
        UsersDAO usersDAO = (UsersDAO) DAOAbstractFactory.getDAO("USERS");
        User user =
                usersDAO.getByID(id);
        user.setBlocked(false);
        usersDAO.update(user);
        usersDAO.close();
    }

}
