package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexaoMsql {
	private boolean status = false;
	private String mensagem = "";
	private Connection con = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public void conectar() {
		String servidor = "jdbc:mysql://localhost/dbvendasbl?verifyServerCertificate=false&useTimezone=true&serverTimezone=UTC";
		String usuario = "root";
		String senha = "MariaLima";
		String driver = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.con.createStatement();
			;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}

	}

	public boolean estaconectado() {
		if (this.con != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Executa consultas SQL
	 * 
	 * @param pSQL
	 * @return int
	 */
	public boolean executarSQL(String pSQL) {
		try {
			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			this.setResultSet(getStatement().executeQuery(pSQL));

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean executarUpdateDeleteSQL(String pSQL) {
		try {

			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			getStatement().executeUpdate(pSQL);

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Executa insert SQL
	 * 
	 * @param pSQL
	 * @return boolean
	 */
	public int insertSQL(String pSQL) {
		int status = 0;
		try {
			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			this.getStatement().executeUpdate(pSQL);

			// consulta o ultimo id inserido
			this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));

			// recupera o ultimo id inserido
			while (this.resultSet.next()) {
				status = this.resultSet.getInt(1);
			}

			// retorna o ultimo id inserido
			return status;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return status;
		}
	}

	/**
	 * encerra a conexão corrente
	 * 
	 * @return boolean
	 */
	public boolean fecharConexao() {
		try {
			if ((this.getResultSet() != null) && (this.statement != null)) {
				this.getResultSet().close();
				this.statement.close();
			}
			this.getCon().close();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return this.status;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * @return the resultSet
	 */
	public ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the con
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @param con the con to set
	 */
	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * @param statement the statement to set
	 */
	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	/**
	 * @param resultSet the resultSet to set
	 */
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	/*    *//**
			 * @return the servidor
			 */
	/*
	 * public String getServidor() { return servidor; }
	 * 
	 *//**
		 * @param servidor the servidor to set
		 */
	/*
	 * public void setServidor(String servidor) { this.servidor = servidor; }
	 * 
	 *//**
		 * @return the nomeDoBanco
		 */
	/*
	 * public String getNomeDoBanco() { return nomeDoBanco; }
	 * 
	 *//**
		 * @param nomeDoBanco the nomeDoBanco to set
		 */
	/*
	 * public void setNomeDoBanco(String nomeDoBanco) { this.nomeDoBanco =
	 * nomeDoBanco; }
	 * 
	 *//**
		 * @return the usuario
		 */
	/*
	 * public String getUsuario() { return usuario; }
	 * 
	 *//**
		 * @param usuario the usuario to set
		 */
	/*
	 * public void setUsuario(String usuario) { this.usuario = usuario; }
	 * 
	 *//**
		 * @return the senha
		 */
	/*
	 * public String getSenha() { return senha; }
	 * 
	 *//**
		 * @param senha the senha to set
		 *//*
			 * public void setSenha(String senha) { this.senha = senha; }
			 */

}
