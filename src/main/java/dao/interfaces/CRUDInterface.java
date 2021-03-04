package dao.interfaces;

import java.sql.SQLException;

public interface CRUDInterface <T, R>{
    T create(T obj) throws SQLException;
    T read(R someId) throws SQLException;
    void update(T obj) throws SQLException;
    void delete (T obj) throws SQLException;
}
