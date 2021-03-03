package dao.interfaces;

import entity.User;

import java.sql.SQLException;
import java.util.Map;

public interface UserDaoInterface extends CRUDInterface<User, String> {
     Map<String,String> readAllPassNames() throws SQLException;

}
