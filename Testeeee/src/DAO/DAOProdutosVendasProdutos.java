package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMsql;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendasProdutos;

public class DAOProdutosVendasProdutos extends ConexaoMsql{
	
	public ArrayList<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosDAO(int pCodigoVenda){
		
		ArrayList<ModelProdutosVendasProdutos> lisModelProdutosVendasProdutos = new ArrayList<ModelProdutosVendasProdutos>();
		ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
		ModelProdutos modelProdutos = new ModelProdutos();
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
		try {
			this.conectar();
			this.executarSQL(
					"SELECT "
		                    + "tbl_produto.pk_id_produto,"
		                    + "tbl_produto.pro_nome,"
		                    + "tbl_produto.pro_valor,"
		                    + "tbl_produto.pro_estoque,"
		              
		                    + "tbl_vendas_produtos.fk_produto,"
		                    + "tbl_vendas_produtos.fk_venda,"
		                    + "tbl_vendas_produtos.pk_id_vendas_produtos,"
		                    + "tbl_vendas_produtos.venda_pro_quantidade,"
		                    + "tbl_vendas_produtos.venda_prod_valor"
		                   
		                 + " FROM"
		                     + " tbl_vendas_produtos "
		                     + "INNER JOIN tbl_produto ON tbl_produto.pk_id_produto = tbl_vendas_produtos.fk_produto "
		                     +"WHERE tbl_vendas_produtos.fk_venda = '"+pCodigoVenda+"';");
			
			while (this.getResultSet().next()) {
				modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
				modelProdutos = new ModelProdutos();
				modelVendasProdutos = new ModelVendasProdutos();
				
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProNome(this.getResultSet().getString(2));
				modelProdutos.setProValor(this.getResultSet().getDouble(3));
				modelProdutos.setproEstoque(this.getResultSet().getInt(4));
		
				
				modelVendasProdutos.setProduto(this.getResultSet().getInt(5));
				modelVendasProdutos.setVendas(this.getResultSet().getInt(6));
				modelVendasProdutos.setIdVendaProduto(this.getResultSet().getInt(7));
				modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(8));
				modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(9));
			
				modelProdutosVendasProdutos.setModelProdutos(modelProdutos);
				modelProdutosVendasProdutos.setModelVendasProdutos(modelVendasProdutos);
				
				lisModelProdutosVendasProdutos.add(modelProdutosVendasProdutos);
		
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return lisModelProdutosVendasProdutos;
		
	}

}
