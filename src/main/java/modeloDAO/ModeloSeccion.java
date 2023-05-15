package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modeloDTO.Seccion;

public class ModeloSeccion extends Conector{
	public Seccion getSeccion(int id) {
		String st = "select * from secciones where id=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			Seccion seccion = new Seccion();
			
			seccion.setId(rs.getInt("id"));
			seccion.setNombre(rs.getString("nombre"));
			
			return seccion;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
