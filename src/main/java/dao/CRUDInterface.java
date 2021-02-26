package dao;

import java.sql.SQLException;

public interface CRUDInterface <T>{
    T create(T obj) throws SQLException;
    T read() throws SQLException;
    void update(T obj, Integer id) throws SQLException;
    void delete (T obj) throws SQLException;
}
