<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Lista de Produtos</title>
<link rel="icon" href="imagens/boxIcon.png">
<link rel="stylesheet" href="style.css">

</head>
<body>
	<h1>Editar Produto</h1>
	<form name="frmProduto" class="form-prod" action="update">
		<table >

			<tr>
				<input type="text" name="idprod" id="idprod" readonly value="<%out.print(request.getAttribute("idprod")); %>">
			</tr>
			<tr>
				<input type="text" name="nome" value="<%out.print(request.getAttribute("nome")); %>">
			</tr>
			<tr>
				<input type="text" name="quantidade" value="<%out.print(request.getAttribute("quantidade")); %>">
			</tr>
			<tr>
				<input type="text" name="valor" value="<%out.print(request.getAttribute("valor")); %>">
			</tr>
		</table>
		<input type="button" class="adicionar-button" onclick="validar()" value="Adicionar">
	</form>

    <script src="scripts/validador.js"></script>
</body>
</html>