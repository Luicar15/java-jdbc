package org.example.util;

import com.sun.source.tree.UsesTree;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_BasedeDatos {
    private static String url ="jdbc:mysql://localhost:3306/java_curso";    //url de conexion
    private static String username="root";
    private static String password="DL1082898550";

    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException{
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}
