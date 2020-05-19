package controller;

import java.util.ArrayList;

//import java.util.ArrayList;

import DAO.DaoProdutos;
import model.ModelProdutos;

public class ControllerProdutos {
	
	private DaoProdutos daoProdutos = new DaoProdutos();
	
	public int salvarProdutoController(ModelProdutos pModelProdutos) {
		return this.daoProdutos.salvarProdutosDAO(pModelProdutos);
		
	}
//	
	public boolean exvluirProdutosController(int pCodigo) {
		return this.daoProdutos.excluirProdutosDAO(pCodigo);
	}
	
	public boolean alterarProdutoController(ModelProdutos pModelProdutos) {
		return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
	}

	public ModelProdutos retornarProdutoCOntroller(int pCodigo) {
		return this.daoProdutos.retornarProdutosDAO(pCodigo);
	}
	
	public ModelProdutos retornarProdutoCOntroller(String pNome) {
		return this.daoProdutos.retornarProdutosDAO(pNome);
	}
	
	public ArrayList<ModelProdutos> retornarListaProdutoController(){
		return this.daoProdutos.retonarListaProdutosDAO();
	}
	public boolean alterarEstoqueProdutoController(ArrayList<ModelProdutos> plistaModelProduto) {
		return this.daoProdutos.alterarEstoqueProdutosDAO(plistaModelProduto);
		
	}
}
