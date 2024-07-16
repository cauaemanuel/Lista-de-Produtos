<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Product"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<Product> lista = (ArrayList<Product>) request.getAttribute("produtos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
<link rel="icon" href="imagens/boxIcon.png">
<link rel="stylesheet" href="style.css">

</head>
<body>
	<h1>Lista de Produtos</h1>
	<a href="novo.html" class="Botao1">Novo Produto</a>
	<a href="report" class="Botao2 h1-a">Relatorio</a>

	<table class="table-novo">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdprod()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getQuantidade()%></td>
				<td><%=lista.get(i).getValor()%></td>
				<td><a href="select?idprod=<%=lista.get(i).getIdprod()%>"
					class="Botao1">Editar</a> <a
					href="javascript:confirmar(<%=lista.get(i).getIdprod()%>)"
					class="Botao2">Excluir</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>