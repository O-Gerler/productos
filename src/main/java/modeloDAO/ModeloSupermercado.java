package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeloDTO.Supermercado;

public class ModeloSupermercado extends Conector{
	public ArrayList<Supermercado> getAllSupermercados() {
		String st = "SELECT * FROM supermercados";
		ArrayList<Supermercado> supermercados = new ArrayList<>();
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(rs.getInt("id"));
				supermercado.setNombre(rs.getString("nombre"));
				
				supermercados.add(supermercado);
			}
			
			return supermercados;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
