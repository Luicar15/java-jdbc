package org.example.repositorio;

import org.example.modelo.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T>{

    void setCoon(Connection coon);
    List<T> listar() throws SQLException;
    T porId(Long id) throws SQLException;
    T guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;

    void setConn(Connection conn);
}
