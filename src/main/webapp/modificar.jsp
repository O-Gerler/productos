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
	<c:if test="${ requestScope.mensaje != null }">
		<div class="alert alert-danger position-fixed top-0" style="width: 100vw" role="alert">
  			${ requestScope.mensaje }
		</div>
	</c:if>
	<section class="container">
		<form method="POST" action="ModificarProducto">
			<input type="hidden" name="id" value="${ requestScope.producto.id }">
		  <div class="mb-3">
		    <label for="codigo" class="form-label">Codigo</label>
		    <input type="text" class="form-control" id="codigo" name="codigo" value="${ requestScope.producto.codigo }">
		  </div>
		  <div class="mb-3">
		    <label for="nombre" class="form-label">Nombre</label>
		    <input type="text" class="form-control" id="nombre" name="nombre" value="${ requestScope.producto.nombre }">
		  </div>
		  <div class="mb-3">
		    <label for="cantidad" class="form-label">Cantidad</label>
		    <input type="number" class="form-control" id="cantidad" name="cantidad" value="${ requestScope.producto.cantidad }">
		  </div>
		  <div class="mb-3">
		    <label for="precio" class="form-label">Precio</label>
		    <input type="text" class="form-control" id="precio" name="precio" value="${ requestScope.producto.precio }">
		  </div>
		  <div class="mb-3">
		    <label for="caducidad" class="form-label">Caducidad</label>
		    <input type="date" class="form-control" id="caducidad" name="caducidad" value="${ requestScope.producto.caducidad }">
		  </div>
		  <div class="mb-3">
		    <label for="caducidad" class="form-label">Seccion</label>
		    <select id="seccion" name="seccion" class="form-select">
		    	<c:forEach items="${ secciones }" var="seccion">
		    		<c:if test="${ requestScope.producto.seccion.id == seccion.id}">
		    			<option value="${ seccion.id }" selected="selected">${ seccion.nombre }</option>
		    		</c:if>
		    		<c:if test="${ requestScope.producto.seccion.id != seccion.id}">
		    			<option value="${ seccion.id }" >${ seccion.nombre }</option>
		    		</c:if>
		    	</c:forEach>
		    </select>
		  </div>
		  <div class="w-100 d-flex justify-content-between">
			<a class="btn btn-primary" href="VerProductos">Volver</a>
		  	<button type="submit" class="btn btn-primary">Enviar</button>
		  </div>
		</form>
	</section>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>