package service;

import dao.UserDAO;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestUserService extends TestService {
    UserDAO userDAO = new UserDAO();
    UserService userService = new UserService(userDAO);

    final String name = generateRandomString(10);
    final String surname = generateRandomString(5);
    final String login = generateRandomString(5);
    final String password = generateRandomString(10);

    User testUser = new User(name, surname, login, password);

    @BeforeSuite
    public final User generateUser() {
        testUser.setLogin(login);
        testUser.setPassword(password);
        testUser.setName(name);
        testUser.setSurname(surname);
        return testUser;
    }

    @Test
    public void createNewPassengerTest () {
        Assert.assertTrue(userService.registration(testUser));
    }

    @Test
    public void deleteUserTest() {
        Assert.assertTrue(userService.deleteUser(generateUser()));
    }
}
