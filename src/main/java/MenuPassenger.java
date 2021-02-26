import dao.StationsDAO;
import service.ServiceAboutStations;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuPassenger {
    public static void menuPassenger() {
        String name;
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
                    ServiceAboutStations serviceAboutStations = new ServiceAboutStations();
                    serviceAboutStations.getListOfStations();
                    break;
//                case "2":
//                    System.out.println("Введите имя");
//                    name = scanner.nextLine();
//                    System.out.println("Введите фамилию");
//                    surname = scanner.nextLine();
//                    System.out.println("Введите логин");
//                    login = scanner.nextLine();
//                    System.out.println("Введите пароль");
//                    password = scanner.nextLine();
//                    Passenger newPassenger = passengerService.registration(name, surname, login, password);
//                    if (newPassenger != null) {
//                        System.out.println("Пассажир " + name + " " + surname + " успешно зарегистрирован, нажмите \"3\" для входа в систему");
//                    }else {
//                        System.exit(0);
//                    }
//                    break;
//                case "3":
//                    System.out.print("Авторизация  ");
//                    System.out.println("введите логин"); // aa
//                    login = scanner.nextLine();
//                    System.out.println("введите пароль"); // bb
//                    password = scanner.nextLine();
//                    Passenger current = passengerService.authorisation(passId, login, password);
//                    if (current != null) {
//                        name = current.getName();
//                        surname = current.getSurname();
//                        System.out.println("Добро пожаловать, " + name + " " + surname + ", вы успешно авторизованы.\n" +
//                                "Нажмите \"4\" для оформления заявки");
//                        currentPassenger = current;
//                    } else {
//                        System.out.println("Авторизация не удалась, пожалуйста, попробуйте снова"); // добавить проверку, что именно не работает в авторизации
//                    }
//                    break;
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