<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrarse</title>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/registerController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) {
	messageNextPage = "";
}

if (customerBean != null && !customerBean.getCorreoUser().equals("") && !customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<%= messageNextPage %><br/><br/>
<form method="post" action="../controller/registerController.jsp">
	<label for="nombre">Nombre: </label>
	<input type="text" name="nombre" value="">
	<br/>
	<label for="apellidos">Apellidos: </label>
	<input type="text" name="apellidos" value="">
	<br/>
	<label for="nacimiento">Fecha de Nacimiento: </label>
	<input type="date" name="nacimiento" value="">
	<br/>
	<label for="correo">Correo: </label>
	<input type="email" name="correo" value="" placeholder="correo@dominio">
	<br/>
	<label for="password">Contraseña: </label>
	<input type="password" name="password" value="">	
	<br/><br/>
	<input type="submit" value="Registrarse">
</form>
<%
}
%>

</body>
</html>