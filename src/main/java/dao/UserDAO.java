package dao;

import entity.User;

import entity.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDAO extends ConnectionToDB  implements UserDaoInterface{


    @Override
    public User create(User obj) throws SQLException {

//        if (newPassenger != null) {
//            System.out.println("Пассажир " + name + " " + surname + " успешно зарегистрирован, нажмите \"3\" для входа в систему");
//        }else {
//            System.exit(0);
//        }
        return null;
    }

    @Override
    public User read() throws SQLException {
        try  {
            conn = DriverManager.getConnection(url, properties);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from stations");
            while (result.next()) {
                String dataBasePrintByColumnName = result.getString("station_name");
                System.out.println(dataBasePrintByColumnName);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void update(User obj, Integer id) throws SQLException {

    }

    @Override
    public void delete(User obj) throws SQLException {

    }


}
