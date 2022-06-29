package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositoriolmpl;
import org.example.repositorio.Repositorio;
import org.example.util.Conexion_BasedeDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcDelete {
    public static void main(String[] args) {
        try (Connection coon = Conexion_BasedeDatos.getConnection()){

            Repositorio<Producto> repositorio = new ProductoRepositoriolmpl();
            System.out.println("============ listar ============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============ obtener por id ============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============ Eliminar producto ============");
            repositorio.eliminar(3L);
            System.out.println("Producto eliminado con exito");
            repositorio.listar().forEach(System.out::println);
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
