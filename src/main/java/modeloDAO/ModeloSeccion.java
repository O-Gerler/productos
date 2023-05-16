package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeloDTO.Seccion;

public class ModeloSeccion extends Conector{
	public Seccion getSeccion(int id) {
		String st = "select * from secciones where id=?";
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			return rellenarSeccion(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private Seccion rellenarSeccion(ResultSet rs) throws SQLException {
		Seccion seccion = new Seccion();
		
		seccion.setId(rs.getInt("id"));
		seccion.setNombre(rs.getString("nombre"));
		
		return seccion;
	}
	
	public ArrayList<Seccion> getAllSecciones() {
		String st = "select * from secciones";
		ArrayList<Seccion> secciones = new ArrayList<>();
		
		try {
			PreparedStatement pst = super.con.prepareStatement(st);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				secciones.add(rellenarSeccion(rs));
			}
			
			return secciones;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
