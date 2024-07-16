package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {


	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbproduto?useTimezone=true&serverTimesone=UTC";
	private String user = "root";
	private String password = "vegeta30";

	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	

	public void insertProduto(Product produto) {
		String create = "insert into produtos(nome, quantidade,valor) values (?,?,?)";

		try {
			// abrir a conexao
			Connection con = conectar();
			// preparar a query para executar
			PreparedStatement pst = con.prepareStatement(create);
			// substituir parametros (?) pelo conteudo
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getQuantidade());
			pst.setString(3, produto.getValor());
			// executar a query

			pst.executeUpdate();
			// encerrar conexao
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	

	public ArrayList<Product> listarProdutos() {
		String read = "select * from produtos order by nome";
		ArrayList<Product> produtos = new ArrayList<>();

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idprod = rs.getString(1);
				String nome = rs.getString(2);
				String quantidade = rs.getString(3);
				String valor = rs.getString(4);

				produtos.add(new Product(idprod, nome, quantidade, valor));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	
	public void selecionarContato(Product prod) {
		String read2 = "select * from produtos where idprod = ?";
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(read2);
			ps.setString(1, prod.getIdprod());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				prod.setIdprod(rs.getString(1));
				prod.setNome(rs.getString(2));
				prod.setQuantidade(rs.getString(3));
				prod.setValor(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void alterarProduto(Product prod) {
		String create = "update produtos set nome=?, quantidade=?, valor=? where idprod=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, prod.getNome());
			pst.setString(2, prod.getQuantidade());
			pst.setString(3, prod.getValor());
			pst.setString(4, prod.getIdprod());
			
			pst.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void deletarProduto(Product prod) {
		String delete = "delete from produtos where idprod=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, prod.getIdprod());
			pst.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
