package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeloDAO.ModeloProducto;
import modeloDAO.ModeloSupermercadoProducto;
import modeloDTO.Producto;
import modeloDTO.SuperProducto;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		Producto producto = modeloProducto.getProducto(id);
		
		ModeloSupermercadoProducto modeloSupermercadoProducto = new ModeloSupermercadoProducto();
		modeloSupermercadoProducto.setCon(modeloProducto.getCon());
		
		ArrayList<SuperProducto> superProductos = modeloSupermercadoProducto.getSuperProductosPorIdProducto(id);
		
		if(producto.getCantidad() > 0) {
			producto.setCantidad(producto.getCantidad() - 1);
			modeloProducto.modificarProducto(producto);
		}else if(superProductos.size() > 0 && producto.getCantidad() == 0) {
			modeloSupermercadoProducto.eliminar(producto);
		}else if (superProductos.size() == 0 && producto.getCantidad() == 0) {
			modeloProducto.eliminarProducto(producto);
		}
		
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
