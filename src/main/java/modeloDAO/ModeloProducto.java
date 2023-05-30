package modeloDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.EliminarProducto;
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
	
	public void eliminarProducto(Producto producto) {
		String st = "DELETE FROM productos WHERE id=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, producto.getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modificarProducto(Producto producto) {
		String st = "update productos set codigo=?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion=? where id=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			pst.setInt(6, producto.getSeccion().getId());
			pst.setInt(7, producto.getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int ultimoId() {
		String st = "SELECT max(id) FROM productos";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			return rs.getInt("max(id)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
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
	
	public Producto getProductoPorCoigo(String codigo) {
		String st = "SELECT * FROM productos WHERE codigo=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			modeloSeccion.setCon(this.getCon());
			
			pst.setString(1, codigo);;
			
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
			
			return rs.getString("codigo").equals(codigo) ? true : false;
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

	private Producto rellenarProducto(ModeloSeccion modeloSeccion, ResultSet rs){
		Producto producto = new Producto();
		try {
			producto.setId(rs.getInt("id"));
			producto.setCodigo(rs.getString("codigo"));
			producto.setNombre(rs.getString("nombre"));
			producto.setCantidad(rs.getInt("cantidad"));
			producto.setPrecio(rs.getDouble("precio"));
			producto.setCaducidad(rs.getDate("caducidad"));
			producto.setSeccion(modeloSeccion.getSeccion(rs.getInt("id_seccion")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			producto = null;
		}
		
		return producto;
	}
}
