<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comprar</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>			
<body class="d-flex justify-content-center align-items-center" style="width: 100vw; min-height: 100vh">
	<section class="container">
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
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
		<div>
			<p> Monto total : ${ monto } </p>
			<p> <a class="btn btn-primary" href="Pagar" >Comprar</a> </p>
		</div>
	</section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>