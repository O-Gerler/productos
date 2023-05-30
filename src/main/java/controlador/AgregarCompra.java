package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeloDAO.ModeloProducto;
import modeloDTO.Producto;

/**
 * Servlet implementation class AgregarCompra
 */
@WebServlet("/AgregarCompra")
public class AgregarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Producto> productos = (ArrayList<Producto>) (session.getAttribute("carrito") == null ? new ArrayList<Producto>() : session.getAttribute("carrito"));
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		Producto producto = modeloProducto.getProducto(id);
		
		if (producto != null) {
			productos.add(producto);
		}
		
		session.setAttribute("carrito", productos);
		
		response.sendRedirect("VerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
