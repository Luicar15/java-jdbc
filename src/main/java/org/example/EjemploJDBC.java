package org.example;

import org.example.modelo.Categoria;
import org.example.modelo.Producto;
import org.example.servicio.CatalogoServicio;
import org.example.servicio.Servicio;


import java.sql.*;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {
        Servicio servicio = new CatalogoServicio();
        System.out.println("============ listar ============");
        servicio.listar().forEach(System.out::println);
        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminación");

        Producto producto = new Producto();
        producto.setNombre("Lampara led escritorio");
        producto.setPrecio(990);
        producto.setFecha_registro(new Date());
        producto.setSku("abcdefg12");
        servicio.guardarProductoConCategoria(producto, categoria);
        System.out.println("Producto guardado con exito " + producto.getId());
        servicio.listar().forEach(System.out::println);


    }
}
