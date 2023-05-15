package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeloDTO.Producto;

public class ModeloProducto extends Conector{
	public ArrayList<Producto> getAllProductos() {
		String st = "select * from productos";
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Producto producto = new Producto();
				
				producto.setId(rs.getInt("id"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setCaducidad(rs.getDate("caducidad"));
				
				productos.add(producto);
			}
			
			return productos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
