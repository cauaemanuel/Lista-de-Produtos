package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {

	/** conexao **/
	// parametros para a conexao

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbproduto?useTimezone=true&serverTimesone=UTC";
	private String user = "root";
	private String password = "vegeta30";

	// metodos de conexao

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

	// crud create

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
			//encerrar conexao
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
