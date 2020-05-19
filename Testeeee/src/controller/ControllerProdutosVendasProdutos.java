package controller;

import java.util.ArrayList;

import DAO.DAOProdutosVendasProdutos;
import model.ModelProdutosVendasProdutos;

public class ControllerProdutosVendasProdutos {
	
	private DAOProdutosVendasProdutos dAOProdutosVendasProdutos = new DAOProdutosVendasProdutos();
	
	public ArrayList<ModelProdutosVendasProdutos> getListaProdutosVendasProdutosController(int pCodigoVenda){
		return this.dAOProdutosVendasProdutos.getListaProdutosVendasProdutosDAO(pCodigoVenda);
		
	}

}
