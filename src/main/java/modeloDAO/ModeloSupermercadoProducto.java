package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeloDTO.Producto;
import modeloDTO.SuperProducto;
import modeloDTO.Supermercado;

public class ModeloSupermercadoProducto extends Conector{
	public void insertar(Producto producto, Supermercado supermercado) {
		String st = "INSERT INTO productos_supermercados(id_producto, id_supermercado) VALUES (?, ?)";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, producto.getId());
			pst.setInt(2, supermercado.getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminar(Producto producto) {
		String st = "DELETE FROM productos_supermercados WHERE id_producto=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, producto.getId());
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<SuperProducto> getSuperProductosPorIdProducto(int id) {
		String st = "SELECT * FROM productos_supermercados WHERE id_producto=?";
		ArrayList<SuperProducto> superProductos = new ArrayList<>();
		
		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
		
		modeloProducto.setCon(super.con);
		modeloSupermercado.setCon(super.con);
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SuperProducto superProducto = new SuperProducto();
				
				superProducto.setProducto(modeloProducto.getProducto(rs.getInt("id_producto")));
				superProducto.setSupermercado(modeloSupermercado.getSupermercado(rs.getInt("id_supermercado")));
				
				superProductos.add(superProducto);
			}
			
			return superProductos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
