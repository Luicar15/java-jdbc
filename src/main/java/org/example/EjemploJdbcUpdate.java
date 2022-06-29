package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositoriolmpl;
import org.example.repositorio.Repositorio;
import org.example.util.Conexion_BasedeDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {
        try (Connection coon = Conexion_BasedeDatos.getConnection()){

            Repositorio<Producto> repositorio = new ProductoRepositoriolmpl();
            System.out.println("============ listar ============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============ obtener por id ============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============ editar producto ============");
            Producto producto = new Producto();
            producto.setId(3L);
            producto.setNombre("Teclado Razer mecanico");
            producto.setPrecio(700);
            repositorio.guardar(producto);
            System.out.println("Producto editado con exito");
            repositorio.listar().forEach(System.out::println);
        }

        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

