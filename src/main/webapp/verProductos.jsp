<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>			
<body class="d-flex justify-content-center align-items-center" style="width: 100vw; min-height: 100vh">
	<section class="container">
		<p> Carrito ${ carrito.size() }  <a class="btn btn-primary" href="Comprar">Comprar</a></p>
		<div class="row container">
		 <input type="text" class="form-control" id="codigoJS" name="codigo">
			<form action="VerProductos" method="POST">
				<div class="mb-3 d-flex gap-2">
					<label for="min" class="form-label">Precio Minimo</label>
				    <input type="number" class="form-control" id="min" name="min">
				    <label for="max" class="form-label">Precio Maximo</label>
				    <input type="number" class="form-control" id="max" name="max">
				    <input type="submit" class="btn btn-primary" value="precio" name="enviar">
		  		</div>
			</form>
			<form action="EliminarProducto" method="POST">
				<div class="mb-3 d-flex gap-2">
					<label for="min" class="form-label">Eliminar Varios</label>
				    <textarea class="form-control" id="exampleFormControlTextarea1" name="eliminar" placeholder="Escribe los elementos a eliminar" rows="3"></textarea>
				    <input type="submit" class="btn btn-primary" value="Eliminar" name="enviar">
		  		</div>
			</form>
			<form action="VerProductos" method="POST">
				<div class="mb-3 d-flex gap-2">
					<label for="codigo" class="form-label">Codigo</label>
				    <input type="text" class="form-control" id="codigo" name="codigo">
				    <input type="submit" class="btn btn-primary" value="nombre" name="enviar">
		  		</div>
			</form>
			<a class="btn btn-primary" href="InsertarProducto">Insertar</a>
		</div>
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Codigo <br><a href="VerProductos?orden=1">A - Z</a> <br><a href="VerProductos?orden=2">Z - A</a></th>
		      <th scope="col">Nombre</th>
		      <th scope="col">Cantidad</th>
		      <th scope="col">Precio</th>
		      <th scope="col">Caducidad</th>
		      <th scope="col">Seccion</th>
		      <th scope="col">Comprar</th>
		      <th scope="col">Modificar</th>
		      <th scope="col">Eliminar</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${ productos }" var="producto">
		  		<tr>
			      <th scope="row">${ producto.id }</th>
			      <td>${ producto.codigo }</td>
			      <td>${ producto.nombre }</td>
			      <td>${ producto.cantidad }</td>
			      <td>${ producto.precio }</td>
			      <td>${ producto.caducidad }</td>
			      <td>${ producto.seccion.nombre }</td>
			      <td><a class="btn btn-success" href="AgregarCompra?id=${ producto.id }">Comprar</a></td>
			      <td><a class="btn btn-primary" href="ModificarProducto?id=${ producto.id }">Modificar</a></td>
			      <td><a class="btn btn-danger" href="EliminarProducto?id=${ producto.id }">Eliminar</a></td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
		<form action="VerProductos" method="POST">
		<c:forEach items="${ productos }" var="producto">
		  <div class="form-check">
		  	  <input class="form-check-input" type="checkbox" value="${ producto.id }" id="${ producto.id }" name="productos">
			  <label class="form-check-label" for="${ producto.id }">
			    ${ producto.nombre }
			  </label>
		  </div>		  	  
		 </c:forEach>
		 <input type="submit" value="Eliminar" name="enviar" class="btn btn-danger">
	</form>
	</section>
	<script type="text/javascript">
		const buscador = document.getElementById('codigoJS')
		const productos = document.querySelectorAll('tbody tr')
		
		
		buscador.addEventListener('input', () => {
			
			productos.forEach(producto => {
				if(producto.children[1].innerText.includes(buscador.value)){
					producto.classList.remove('d-none')
				}else {
					producto.classList.add('d-none')
				} 
			})
		})
	</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>