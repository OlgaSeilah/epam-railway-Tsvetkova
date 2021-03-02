import dao.RequestDAO;
import dao.StationsDAO;
import dao.UserDAO;
import entity.Request;
import entity.User;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuPassenger {
    public void menuPass(Scanner scanner) {
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        StationsDAO stationsDAO = new StationsDAO();
        StationsService stationsService = new StationsService();
        RequestDAO requestDAO = new RequestDAO();
        RequestService requestService = new RequestService(requestDAO, stationsDAO);

        User current = null;

        System.out.println("        Меню пассажира \n".toUpperCase());
        System.out.println("Необходимо зарегистироваться или авторизоваться для продолжения работы\n" +
                "1 - Регистрация\n" +
                "2 - Авторизация");
        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                System.out.println("Регистрация:\nВведите имя");
                String name = scanner.nextLine();
                System.out.println("Введите фамилию");
                String surname = scanner.nextLine();
                System.out.println("Введите логин");
                String login = scanner.nextLine();
                System.out.println("Введите пароль");
                String password = scanner.nextLine();
                User newUser = new User(name, surname, login, password);
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setLogin(login);
                newUser.setPassword(password);
                if (userService.registration(newUser)) {
                    current = newUser;
                    System.out.println("Пассажир " + name + " " + surname + " успешно зарегистрирован, нажмите \"3\" для входа в систему");
                } else {
                    System.out.println("all is bad");
                }
                break;
            case "2":
                System.out.print("Авторизация  ");
                System.out.println("Авторизация:\nВведите логин"); // aa
                login = scanner.nextLine();
                System.out.println("Введите пароль"); // bb
                password = scanner.nextLine();
                try {
                    current = userService.authorisation(login, password);
                    System.out.println("Добро пожаловать, " + current.getName() + " " + current.getSurname() + ", вы успешно авторизованы.");
                } catch (UserDoesNotExistException e) {
                    System.out.println("пользователь с таким логином не найден в базе");
                } catch (IncorrectPasswordException ex) {
                    System.out.println("Не совпадает пароль");
                }
                break;
        }
        if (current != null) {
            System.out.println("Список доступных функций:\n" +
                    "1 - Просмотр списка доступных станций\n" +
                    "2 - Оформить заявку на билет\n" +
                    "3 - Выйти из системы\n" +
                    "4 - Завершить выполнение программы\n" +
                    "Введите номер искомой функции:");
            while (true) {
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        System.out.println("Список станций на линии:");
                        stationsService.getListOfStationNames().forEach(System.out::println);
                        ;
                        break;
                    case "2":
                        System.out.println("Введите станцию отправления");
                        String startStation = scanner.nextLine();
                        System.out.println("Введите станцию назначения");
                        String destinationStation = scanner.nextLine();
                        assert current != null;
                        String userLogin = current.getLogin();
                        Request request = new Request(userLogin, startStation, destinationStation);
                        try {
                           if (requestService.createRequest(request)) {
                                System.out.println("Пассажир " + current.getName() + " " + current.getSurname() +
                                        " успешно оформил заявку на билет от станции " + request.getStartStation() +
                                        " до станции " + request.getDestinationStation());
                           }
                        } catch (SuchStationDoesNotExistException e) {
                             System.out.println("нет одной ли двух станций");
                        } catch (SQLException e) {
                             System.out.println("что-т еще не так");
                        }
                        break;

                    case "3":
                        current = null;
                        System.out.println("Вы успешно вышли из профиля");
                        break;
                    case "4":
                        System.out.println("До свидания!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Вы запросили несуществующую функцию: " + choose +
                                "\n Работа программы завершена");
                        System.exit(0);
                }
            }
        }
    }
}
