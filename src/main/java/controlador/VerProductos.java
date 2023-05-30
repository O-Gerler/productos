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
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int FILTRAR_POR_ABC_ASC = 1;
		final int FILTRAR_POR_ABC_DSC = 2;
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Producto> productosCarrito = (session.getAttribute("carrito") == null ? new ArrayList<Producto>() : (ArrayList<Producto>) session.getAttribute("carrito"));
		
		int filtrar = 0;
		
		try {
			filtrar = Integer.parseInt(request.getParameter("orden"));
		} catch (Exception e) {
			filtrar = 0;
		}
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		ArrayList<Producto> productos = modeloProducto.getAllProductos();
		
		modeloProducto.cerrar();
		
		switch (filtrar) {
			case FILTRAR_POR_ABC_ASC:
				productos.sort((p1, p2) -> p1.getCodigo().compareTo(p2.getCodigo()));
				break;
			case FILTRAR_POR_ABC_DSC:
				productos.sort((p1, p2) -> p2.getCodigo().compareTo(p1.getCodigo()));
		}
		
		request.setAttribute("carrito", productosCarrito);
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String FILTRAR_POR_NOMBRE = "nombre";
		final String FILTRAR_POR_PRECIO = "precio";
		final String ELIMINAR_MULTIPLE = "Eliminar";
		
		String enviar = request.getParameter("enviar");
		String[] productosArray = null;
		try {
			productosArray = request.getParameterValues("productos");
		} catch (Exception e) {
			productosArray = null;
		}
		
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		ArrayList<Producto> productos = modeloProducto.getAllProductos();
		
		ArrayList<Producto> productosFiltrados = new ArrayList<>();
		
		if (enviar.equals(FILTRAR_POR_NOMBRE)) {
			String buscado = request.getParameter("codigo");
			
			for (Producto producto : productos) {
				if (producto.getCodigo().contains(buscado)) {
					productosFiltrados.add(producto);
					System.out.println(producto);
				}
			}
			request.setAttribute("productos", productosFiltrados);
		}else if (enviar.equals(FILTRAR_POR_PRECIO)) {
			int min = Integer.parseInt(request.getParameter("min"));
			int max = Integer.parseInt(request.getParameter("max"));
			
			for (Producto producto : productos) {
				if (producto.getPrecio() > min && producto.getPrecio() < max) {
					productosFiltrados.add(producto);
					System.out.println(producto);
				}
			}
			
			productosFiltrados.sort((p1, p2) -> p1.getPrecio() - p2.getPrecio() > 0 ? 1 : -1);
			request.setAttribute("productos", productosFiltrados);
		}else if (enviar.equals(ELIMINAR_MULTIPLE) && productosArray != null) {
			for (String idProductoString : productosArray) {
				System.out.println("a");
				Producto producto = modeloProducto.getProducto(Integer.parseInt(idProductoString));
				modeloProducto.eliminarProducto(producto);
			}
			request.setAttribute("productos", modeloProducto.getAllProductos());
		}else {
			request.setAttribute("productos", productos);
		}
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Producto> productosCarrito = (ArrayList<Producto>) (session.getAttribute("carrito") == null ? new ArrayList<Producto>() : session.getAttribute("carrito"));
		
		
		request.setAttribute("carrito", productosCarrito);
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

}
