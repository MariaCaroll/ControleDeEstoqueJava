package DAO;

import java.util.ArrayList;

//import java.util.ArrayList;

import conexoes.ConexaoMsql;
import model.ModelProdutos;

public class DaoProdutos extends ConexaoMsql {

	/**
	 * Cadastrar um produto
	 * 
	 * @param pModelProdutos
	 * @return boolean
	 */

	public int salvarProdutosDAO(ModelProdutos pModelProdutos) {

		try {
			this.conectar();
			String query = "INSERT INTO tbl_produto ("
					//+"pro_nome,"
					//+"pro_valor,"
					//+"pro_estoque"
					//+") VALUES ("
					//+ "'"+pModelProdutos.getProNome()+"',"
					//+ "'"+pModelProdutos.getProValor()+"',"
					//+ "'"+pModelProdutos.getproEstoque() + "'"
					//+");"
					+ "pro_nome,"				
      + "pro_valor,"
      + "pro_estoque"
      + ") VALUES ("
      + "'" + pModelProdutos.getProNome() + "',"
      + "'" + pModelProdutos.getProValor() + "',"
      + "'" + pModelProdutos.getproEstoque() + "'"
      + ")";

					
			this.getStatement().executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			fecharConexao();
			}
			 
		return 0;

	}

	/**
	 * Excluir um produto
	 * 
	 * @param pIdProduto
	 * @return boolean
	 */

	public boolean excluirProdutosDAO(int pIdProduto) {
//
//		try {
//
//			this.conectar();
//			return this.executarUpdateDeleteSQL("DELETE FROM tbl_produto WHERE pk_id_produto = " + pIdProduto + "; ");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			this.fecharConexao();
//		}

		try {
			this.conectar();
			String query = "DELETE FROM tbl_produto WHERE pk_id_produto = " + pIdProduto + ";";
			this.getStatement().executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
		return false;
//
	}

//	
	public boolean alterarProdutoDAO(ModelProdutos pModelProdutos) {
		try {

			this.conectar();
			return this.executarUpdateDeleteSQL(
					"UPDATE tbl_produto SET " + "pro_nome = '" + pModelProdutos.getProNome() + "'," + "pro_valor = '"
							+ pModelProdutos.getProValor() + "'," + "pro_estoque = '" + pModelProdutos.getproEstoque()
							+ "'" + "WHERE pk_id_produto = '" + pModelProdutos.getIdProduto() + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}

//		try {
//			this.conectar();
//			String query = "UPDATE "
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
	}

//	
	public ModelProdutos retornarProdutosDAO(int pIdProduto) {
		ModelProdutos modelProdutos = new ModelProdutos();
		try {

			this.conectar();

			this.executarSQL(
					"select pk_id_produto, pro_nome, pro_valor, pro_estoque from tbl_produto where pk_id_produto = '"
							+ pIdProduto + "'");

			/*
			 * this.executarSQL(" SELECT " + "pk_id_produto, " + "pro_nome, " +
			 * "pro_valor, " + "pro_estoque, " +
			 * "FROM tbl_produto WHERE pk_id_produto = '"+pIdProduto+"'");
			 */

			while (this.getResultSet().next()) {
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProNome(this.getResultSet().getString(2));
				modelProdutos.setProValor(this.getResultSet().getFloat(3));
				modelProdutos.setproEstoque(this.getResultSet().getInt(4));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.fecharConexao();
		}
		return modelProdutos;

	}
	
	public ModelProdutos retornarProdutosDAO(String pNomeProduto) {
		ModelProdutos modelProdutos = new ModelProdutos();
		try {

			this.conectar();

			this.executarSQL(
					"select pk_id_produto, pro_nome, pro_valor, pro_estoque from tbl_produto where pro_nome = '"
							+ pNomeProduto + "'");

			/*
			 * this.executarSQL(" SELECT " + "pk_id_produto, " + "pro_nome, " +
			 * "pro_valor, " + "pro_estoque, " +
			 * "FROM tbl_produto WHERE pk_id_produto = '"+pIdProduto+"'");
			 */

			while (this.getResultSet().next()) {
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProNome(this.getResultSet().getString(2));
				modelProdutos.setProValor(this.getResultSet().getFloat(3));
				modelProdutos.setproEstoque(this.getResultSet().getInt(4));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.fecharConexao();
		}
		return modelProdutos;

	}


//	/**
//	 * Retornar uma lista de produtos
//	 * @return
//	 */
//	
	public ArrayList<ModelProdutos> retonarListaProdutosDAO() {

		ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
		ModelProdutos modelProdutos = new ModelProdutos();

//		try {
//			this.conectar();
//			this.executarSQL(" SELECT " + "pk_id_produto, " + "pro_nome, " + "pro_valor, " + "pro_estoque, "
//					+ "FROM tbl_produto;");

//			while (this.getResultSet().next()) {
			//	modelProdutos = new ModelProdutos();
//				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
//				modelProdutos.setProNome(this.getResultSet().getString(2));
//				modelProdutos.setProValor(this.getResultSet().getDouble(3));
//				modelProdutos.setproEstoque(this.getResultSet().getInt(4));
//
//				listaModelProdutos.add(modelProdutos);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();

//		} finally {
//			this.fecharConexao();
//		}
//		return listaModelProdutos;
//	}
	
		try {
			this.conectar();
			String query = "SELECT * FROM tbl_produto ORDER BY pk_id_produto";
			this.executarSQL(query);
			
			while (this.getResultSet().next()) {
				modelProdutos = new ModelProdutos();
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProNome(this.getResultSet().getString(2));
				modelProdutos.setProValor(this.getResultSet().getFloat(3));
				modelProdutos.setproEstoque(this.getResultSet().getInt(4));

				listaModelProdutos.add(modelProdutos);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaModelProdutos;
	}

	public boolean alterarEstoqueProdutosDAO(ArrayList<ModelProdutos> plistaModelProduto) {
		try {

			this.conectar();
			for (int i = 0; i < plistaModelProduto.size(); i++) {
				
			
			this.executarUpdateDeleteSQL(
					"UPDATE tbl_produto SET "
							+ "pro_estoque = '" + plistaModelProduto.get(i).getproEstoque()+ "'" 
							+ "WHERE pk_id_produto = '" + plistaModelProduto.get(i).getIdProduto() + "'"
							);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
}
