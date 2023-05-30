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
import modeloDAO.ModeloSupermercadoProducto;
import modeloDTO.Producto;
import modeloDTO.SuperProducto;

/**
 * Servlet implementation class Pagar
 */
@WebServlet("/Pagar")
public class Pagar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Producto> productos = (ArrayList<Producto>) session.getAttribute("carrito");
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		ModeloSupermercadoProducto modeloSupermercadoProducto = new ModeloSupermercadoProducto();
		modeloSupermercadoProducto.setCon(modeloProducto.getCon());
		
		
		for (Producto producto : productos) {
			ArrayList<SuperProducto> superProductos = modeloSupermercadoProducto.getSuperProductosPorIdProducto(producto.getId());
			
			Producto producto2 = modeloProducto.getProducto(producto.getId());
			
			if(producto2.getCantidad() > 0) {
				producto2.setCantidad(producto2.getCantidad() - 1);
				modeloProducto.modificarProducto(producto2);
			}else if(superProductos.size() > 0 && producto2.getCantidad() == 0) {
				modeloSupermercadoProducto.eliminar(producto2);
			}else if (superProductos.size() == 0 && producto2.getCantidad() == 0) {
				modeloProducto.eliminarProducto(producto2);
			}
		}
		
		session.setAttribute("carrito",new ArrayList<Producto>());
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
