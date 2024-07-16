package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.Product;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	Product prod = new Product();

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/insert")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// listar prods
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando objeto que recebe javabeans

		ArrayList<Product> lista = dao.listarProdutos();

		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
		rd.forward(request, response);

	}

	// novo prod
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		prod.setNome(request.getParameter("nome"));
		prod.setQuantidade(request.getParameter("quantidade"));
		prod.setValor(request.getParameter("valor"));

		dao.insertProduto(prod);

		response.sendRedirect("main");
	}

	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idprod = request.getParameter("idprod");

		prod.setIdprod(idprod);

		dao.selecionarContato(prod);
		request.setAttribute("idprod", prod.getIdprod());
		request.setAttribute("nome", prod.getNome());
		request.setAttribute("quantidade", prod.getQuantidade());
		request.setAttribute("valor", prod.getValor());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		prod.setIdprod(request.getParameter("idprod"));
		prod.setNome(request.getParameter("nome"));
		prod.setQuantidade(request.getParameter("quantidade"));
		prod.setValor(request.getParameter("valor"));
		dao.alterarProduto(prod);

		response.sendRedirect("main");
	}

	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idprod = request.getParameter("idprod");
		prod.setIdprod(idprod);

		System.out.println(idprod);
		dao.deletarProduto(prod);

		response.sendRedirect("main");
		
	}

}
