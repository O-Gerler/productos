package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeloDAO.ModeloProducto;
import modeloDAO.ModeloSeccion;
import modeloDTO.Producto;
import modeloDTO.Seccion;

/**
 * Servlet implementation class InsertarProducto
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		modeloSeccion.conectar();
		
		ArrayList<Seccion> secciones = modeloSeccion.getAllSecciones();
		
		modeloSeccion.cerrar();
		
		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("insertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidad = request.getParameter("caducidad");
		int id_seccion = Integer.parseInt(request.getParameter("seccion"));
		
		Date fechaCaducidad = null;
		
		try {
			fechaCaducidad = new SimpleDateFormat("yyyy-MM-dd").parse(caducidad);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Producto producto = new Producto();
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(fechaCaducidad);
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		modeloSeccion.setCon(modeloProducto.getCon());
		
		producto.setSeccion(modeloSeccion.getSeccion(id_seccion));
		
		if (!modeloProducto.codigoExiste(producto.getCodigo())) {
			request.setAttribute("incorrecto", true);
			request.setAttribute("mensaje", "El codigo ya existe");
			doGet(request, response);
		}else if (producto.getPrecio() < 0 && producto.getCantidad() < 0) {
			request.setAttribute("incorrecto", true);
			request.setAttribute("mensaje", "El precio o cantidad incorrecta");
			doGet(request, response);
		}else if (producto.getCaducidad().before(new Date())) {
			request.setAttribute("incorrecto", true);
			request.setAttribute("mensaje", "La fecha no puede ser anterior a la actual");
			doGet(request, response);
		}else if (producto.getSeccion().getId() == 0) {
			request.setAttribute("incorrecto", true);
			request.setAttribute("mensaje", "Id seccion incorrecto");
			doGet(request, response);
		}else {
			modeloProducto.insertarProducto(producto);
			
			response.sendRedirect("VerProductos");
		}
		
		modeloProducto.cerrar();
	}
	
}
