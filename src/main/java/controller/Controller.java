package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.Product;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	Product prod = new Product();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
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
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Product> lista = dao.listarProdutos();

		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
		rd.forward(request, response);

	}

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

		prod.setIdprod(request.getParameter("idprod"));

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

		prod.setIdprod(request.getParameter("idprod"));

		dao.deletarProduto(prod);

		response.sendRedirect("main");

	}

	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();

		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "produtos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());

			documento.open();
			documento.add(new Paragraph("Lista de Produtos: "));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);

			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Valor Unitario"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			ArrayList<Product> lista = dao.listarProdutos();

			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getQuantidade());
				tabela.addCell(lista.get(i).getValor());
			}

			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
