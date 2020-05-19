package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMsql;
import model.ModelProdutos;

public class DAOProdutosnew extends ConexaoMsql{
public ArrayList<ModelProdutos> retonarListaProdutosDAO(){
		
		ArrayList<ModelProdutos> listaModelProduto = new ArrayList<>();
		ModelProdutos modelProdutos = new ModelProdutos();
		String sql = "selct * from tbl_produto";
	
		try {
			this.conectar();
			this.executarSQL(sql);
			/*
			 * this.executarSQL(" SELECT " + "pk_id_produto, " + "pro_nome, " +
			 * "pro_valor, " + "pro_estoque, " + "FROM tbl_produto;");
			 */
			
			while ( this.getResultSet().next()) {
				modelProdutos = new ModelProdutos();
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProNome(this.getResultSet().getString(2));
				modelProdutos.setProValor(this.getResultSet().getDouble(3));
				modelProdutos.setproEstoque(this.getResultSet().getInt(4));
				
				listaModelProduto.add(modelProdutos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			this.fecharConexao();
		}
		return listaModelProduto;
	}

}
