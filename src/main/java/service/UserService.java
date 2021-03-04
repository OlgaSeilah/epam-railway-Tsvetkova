package service;

import dao.UserDAO;
import entity.User;

import java.sql.SQLException;
import java.util.Map;

public class UserService {
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registration(User newUser) {
        try {
            userDAO.create(newUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public User authorisation(String login, String password) throws UserDoesNotExistException, IncorrectPasswordException {
        User current = null;
        try {
            current = userDAO.read(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (current == null) {
            throw new UserDoesNotExistException();
        } else if (current.getPassword().equals(password)) {
            return current;
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public Map<String, String> getListOfPassNames() {
        try {
            return userDAO.readAllPassNames();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(User deleteUser) {
        try {
            userDAO.delete(deleteUser);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
