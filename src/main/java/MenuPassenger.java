import dao.UserDAO;
import entity.User;
import service.IncorrectPasswordException;
import service.ServiceAboutStations;
import service.ServiceAboutUsers;
import service.UserDoesNotExistException;

import java.util.Scanner;

public class MenuPassenger {

    public void menu() {
        UserDAO userDAO = new UserDAO();
        ServiceAboutUsers serviceAboutUsers = new ServiceAboutUsers(userDAO);
        ServiceAboutStations serviceAboutStations = new ServiceAboutStations();

        User current = null;
        String name; // TODO объявить эти переменные заново в каждом кейсе
        String surname;
        String login;
        String password;
        int passId = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("        Меню пассажира");

        System.out.println("Список доступных функций:\n" +
                "1 - Просмотр списка доступных станций\n" +
                "2 - Зарегистрировать нового пассажира\n" +
                "3 - Войти, используя логин и пароль\n" +
                "4 - Оформить заявку на билет\n" +
                "5 - Выйти из системы\n" +
                "6 - Завершить выполнение программы\n" +
                "Введите номер искомой функции:");
        while (true) {
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("Список станций в бд:");
                    serviceAboutStations.getListOfStationNames().forEach(System.out::println);;
                    break;
                case "2":
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
                case "3":
                    System.out.print("Авторизация  ");
                    System.out.println("введите логин"); // aa
                    login = scanner.nextLine();
                    System.out.println("введите пароль"); // bb
                    password = scanner.nextLine();

                    try {
                        current = serviceAboutUsers.authorisation(login, password);
                        System.out.println("Добро пожаловать, " + current.getName() + " " + current.getSurname() + ", вы успешно авторизованы.\n" +
                                "Нажмите \"4\" для оформления заявки");
                    } catch (UserDoesNotExistException e) {
                        System.out.println("нет юзера");
                    } catch (IncorrectPasswordException ex) {
                        System.out.println("пароль не тот");
                    }
                    break;

                case "4":
                    if (current == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 3");
                    else {
                        System.out.println("Введите станцию отправления");
                        String startStation = scanner.nextLine();
                        System.out.println("Введите станцию назначения");
                        String destinationStation = scanner.nextLine();
                        String userLogin = current.getLogin();
                        Re
                    }
//                case "4":
//                    if (currentPassenger == null) System.out.println("Авторизуйтесь, пожалуйста! Нажмите 3");
//                    else {
//                        System.out.println("Введите станцию отправления");
//                        String startStation = scanner.nextLine(); //
//                        System.out.println("Введите станцию назначения");
//                        String destinationStation = scanner.nextLine();
//
//                        passId = currentPassenger.getId();
//                        Request currentReq = requestService.createRequest(passId, startStation, destinationStation);
//                        currentRequest = currentReq;
//                        if (currentReq != null) {
//                            System.out.println("Поздравляем, " + currentPassenger.getName() + " " + currentPassenger.getSurname() +
//                                    ", вы оформили заявку на билет от станции " + currentRequest.getStartStation() + " до станции " +
//                                    currentRequest.getDestinationStation() + "!");
//                        } else {
//                            System.out.println("поломалось где-то здесь");
//                        }
//                    }
//                    break;
//                case "5":
//                    currentPassenger = null;
//                    System.out.println("Вы успешно вышли из системы");
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
//    }
            }
        }
    }
}