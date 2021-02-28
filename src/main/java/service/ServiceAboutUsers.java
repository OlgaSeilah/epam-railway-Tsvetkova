package service;
import dao.StationsDAO;
import dao.UserDAO;
import entity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class ServiceAboutUsers {
    UserDAO userDAO;

    public ServiceAboutUsers(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ServiceAboutUsers() {

    }


    public boolean registration (User newUser) {
        try {
            userDAO.create(newUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Ползователь с таким логином уже существует, пожалуйста, попробуйте заново");
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


}
