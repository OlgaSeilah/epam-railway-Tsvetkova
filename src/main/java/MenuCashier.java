import dao.RequestDAO;
import dao.StationsDAO;
import dao.UserDAO;
import entity.Request;
import entity.User;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCashier {
    public void menuCashier() {
        UserDAO userDAO = new UserDAO();
        ServiceAboutUsers serviceAboutUsers = new ServiceAboutUsers(userDAO);
        StationsDAO stationsDAO = new StationsDAO();
        ServiceAboutStations serviceAboutStations = new ServiceAboutStations();
        RequestDAO requestDAO = new RequestDAO();
        ServiceAboutRequest serviceAboutRequest = new ServiceAboutRequest(requestDAO, stationsDAO);

        User cashier = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("        Меню кассира");

        System.out.println("Список доступных функций:\n" +
                "1 - Просмотр списка доступных станций\n" +
                "2 - Авторизоваться в роли кассира\n" +
                "3 - Просмотреть список зарегистрированных пассажиров\n" +
                "4 - Добавить станцию\n" +
                "5 - Удалить станцию\n" +
                "6 - Вывести три наиболее посещаемые станции\n" +
                "7 - Удалить пассажира\n" +
                "8 - Удалить заявку на билет\n" +
                "9 - Завершить выполнение программы\n" +
                "Введите номер искомой функции:");
        while (true) {
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":                                                         // ПРОСМОТР СПИСКА СТАНЦИЙ
                    System.out.println("Список станций в бд:");
                    serviceAboutStations.getListOfStationNames().forEach(System.out::println);
                    ;
                    break;
                case "3":                                                      // Просмотреть список зарегистрированных пассажиров
                    System.out.println("Введите имя");
                    name = scanner.nextLine();
                    System.out.println("Введите фамилию");
                    surname = scanner.nextLine();
                    System.out.println("Введите логин");
                    login = scanner.nextLine();
                    System.out.println("Введите пароль");
                    password = scanner.nextLine();
                    User newUser = new User(name, surname, login, password);
                    newUser.setName(name);
                    newUser.setSurname(surname);
                    newUser.setLogin(login);
                    newUser.setPassword(password);
                    if (serviceAboutUsers.registration(newUser)) {
                        System.out.println("Пассажир " + name + " " + surname + " успешно зарегистрирован, нажмите \"3\" для входа в систему");
                    } else {
                        System.out.println("all is bad");
                    }
                    break;
                case "2":                                                         // АВТОРИЗОВАТЬ кассира
                    System.out.print("Авторизация  ");
                    System.out.println("введите логин"); // admin
                    String login = scanner.nextLine();
                    System.out.println("введите пароль"); // admin
                    String password = scanner.nextLine();
                    try {
                        cashier = serviceAboutUsers.authorisation(login, password);
                        System.out.println("Добро пожаловать, " + cashier.getName() + " " + cashier.getSurname() + ", вы успешно авторизованы.\n" +
                                "Нажмите \"4\" для оформления заявки"); //TODO тут что-то изменить?
                    } catch (UserDoesNotExistException e) {
                        System.out.println("нет юзера");
                    } catch (IncorrectPasswordException ex) {
                        System.out.println("пароль не тот");
                    }
                    break;

//                case "4":                                                               // СОЗДАТЬ ЗАЯВКУ
//                    if (current == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 3");
//                    else {
//                        System.out.println("Введите станцию отправления");
//                        String startStation = scanner.nextLine();
//                        System.out.println("Введите станцию назначения");
//                        String destinationStation = scanner.nextLine();
//                        String userLogin = current.getLogin();
//                        Request request = new Request(userLogin, startStation, destinationStation);
//
//                        try {
//                            if (serviceAboutRequest.createRequest(request)) {
//                                System.out.println("Пассажир " + current.getName() + " " + current.getSurname() +
//                                        " успешно оформил заявку на билет от станции " + request.getStartStation() +
//                                        " до станции " + request.getDestinationStation());
//                            }
//                        }catch (SuchStationDoesNotExistException e ) {
//                            System.out.println("нет одной ли двух станций");
//                        }
//                        catch (SQLException e) {
//                            System.out.println("что-т еще не так");
//                        }
//
//
//                        break;
//                    }
//                case "5":
//                    current = null;
//                    System.out.println("Вы успешно вышли из профиля");
//
//                    break;
//                case "6":
//                    System.out.println("До свидания!");
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println("Вы запросили несуществующую функцию: " + choose);
//                    System.out.println("Работа программы завершена");
//                    System.exit(0);
//
//            }


            }
        }
    }
}