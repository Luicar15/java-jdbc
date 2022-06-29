package org.example.repositorio;

import org.example.modelo.Categoria;
import org.example.modelo.Producto;
import org.example.util.Conexion_BasedeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoriolmpl implements Repositorio<Producto>{

    private Connection coon;

    public ProductoRepositoriolmpl(Connection coon){
        this.coon = coon;
    }

    public ProductoRepositoriolmpl(){

    }

    @Override
    public void setCoon(Connection coon) {
        this.coon = coon;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = coon.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " + "inner join categorias as c ON (p.categoria_id = c.id)")) {
            while (rs.next()) {
                Producto p = crearProducto(rs);
                productos.add(p);
            }
        }
        return productos;

}

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try (PreparedStatement stmt = coon.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " + "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    producto = crearProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, sku, fecha_registro) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());
            stmt.setString(4, producto.getSku());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5, producto.getId());
            } else {
                stmt.setDate(5, new Date(producto.getFecha_registro().getTime()));
            }
            stmt.executeUpdate();
                if (producto.getId() == null){
                    try (ResultSet rs = stmt.getGeneratedKeys()){
                        if (rs.next()){
                            producto.setId(rs.getLong(1));
                        }

                    }
                }
                return producto;
        }
    }

        @Override
        public void eliminar(Long id) throws SQLException {
            try (PreparedStatement stmt = coon.prepareStatement("DELETE FROM productos WHERE id=?")){
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }

        }

    @Override
    public void setConn(Connection conn) {

    }

    private Producto crearProducto(ResultSet rs) throws SQLException {
            Producto p = new Producto();
            p.setId(rs.getLong("id"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecio(rs.getInt("precio"));
            p.setFecha_registro(rs.getDate("fecha_registro"));
            p.setSku(rs.getString("sku"));
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("categoria_id"));
            categoria.setNombre(rs.getString("categoria"));
            p.setCategoria(categoria);
            return p;
        }
    }


