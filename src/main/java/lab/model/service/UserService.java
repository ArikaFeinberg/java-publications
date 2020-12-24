package lab.model.service;

import lab.model.dao.DAOFactory;
import lab.model.dao.Tables;
import lab.model.dao.UsersDAO;

import lab.model.dao.entities.User;

import java.util.ArrayList;

public class UserService {

    private final UsersDAO usersDAO;

    public UserService(DAOFactory factory) {
        usersDAO = factory.createUsersDAO();
    }

    public User getUser(String username, String password) {
        ArrayList<User> users = usersDAO.getWhere(" username  = '"
                + username + "' AND password = '"
                + password + "' ");
        if (users == null || users.size() == 0) return null;
        User user = users.get(0);
        usersDAO.close();
        return user;
    }

    public void registration(User user) {
        usersDAO.insert(user);
        usersDAO.close();
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = usersDAO.getAll();
        usersDAO.close();
        return users;
    }

    public void block(int id) {
        User user = usersDAO.getByID(id);
        user.setBlocked(true);
        usersDAO.update(user);
        usersDAO.close();
    }

    public void unblock(int id) {
        User user = usersDAO.getByID(id);
        user.setBlocked(false);
        usersDAO.update(user);
        usersDAO.close();
    }

}
