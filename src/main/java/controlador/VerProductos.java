package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		final int SIN_FILTRAR = 0;
		final int FILTRAR_POR_ABC_ASC = 1;
		final int FILTRAR_POR_ABC_DSC = 2;
		
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
			case SIN_FILTRAR: 
				request.setAttribute("productos", productos);
				break;
			case FILTRAR_POR_ABC_ASC:
				productos.sort((p1, p2) -> p1.getCodigo().compareTo(p2.getCodigo()));
				request.setAttribute("productos", productos);
				break;
			case FILTRAR_POR_ABC_DSC:
				productos.sort((p1, p2) -> p2.getCodigo().compareTo(p1.getCodigo()));
				request.setAttribute("productos", productos);
		}
		
		
		
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String FILTRAR_POR_NOMBRE = "nombre";
		final String FILTRAR_POR_PRECIO = "precio";
		
		String enviar = request.getParameter("enviar");
		
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
		}
		
		
		
		request.setAttribute("productos", productosFiltrados);
		request.getRequestDispatcher("verProductos.jsp").forward(request, response);
	}

}
