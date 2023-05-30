package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeloDTO.Producto;

/**
 * Servlet implementation class Comprar
 */
@WebServlet("/Comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comprar() {
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
		
		int montoTotal = 0;
		
		if (productos.size() > 0) {
			for (Producto producto : productos) {
				montoTotal += producto.getPrecio();
			}
		}
		
		
		request.setAttribute("productos", productos);
		request.setAttribute("monto", montoTotal);
		request.getRequestDispatcher("comprar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
