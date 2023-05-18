package modeloDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeloDTO.Producto;

public class ModeloProducto extends Conector{
	public void insertarProducto(Producto producto) {
		String st = "insert into productos (codigo, nombre, cantidad, precio, caducidad, id_seccion) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			pst.setInt(6, producto.getSeccion().getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Producto getProducto(int id) {
		String st = "SELECT * FROM productos WHERE id=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			modeloSeccion.setCon(this.getCon());
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			return rellenarProducto(modeloSeccion, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean codigoExiste(String codigo) {
		String st = "select codigo from productos where codigo=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setString(1, codigo);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Producto> getAllProductos() {
		String st = "select * from productos";
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			modeloSeccion.conectar();
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productos.add(rellenarProducto(modeloSeccion, rs));
			}
			
			modeloSeccion.cerrar();
			
			return productos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private Producto rellenarProducto(ModeloSeccion modeloSeccion, ResultSet rs) throws SQLException {
		Producto producto = new Producto();
		
		producto.setId(rs.getInt("id"));
		producto.setCodigo(rs.getString("codigo"));
		producto.setNombre(rs.getString("nombre"));
		producto.setCantidad(rs.getInt("cantidad"));
		producto.setPrecio(rs.getDouble("precio"));
		producto.setCaducidad(rs.getDate("caducidad"));
		producto.setSeccion(modeloSeccion.getSeccion(rs.getInt("id_seccion")));
		return producto;
	}
}
