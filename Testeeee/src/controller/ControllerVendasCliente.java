package controller;

import java.util.ArrayList;

import DAO.DAOVendasCliente;
import model.ModelVendasCliente;

public class ControllerVendasCliente {
	
	private DAOVendasCliente daoVendasCliente = new DAOVendasCliente();

	public ArrayList<ModelVendasCliente> getListaVendasCliente() {
		return this.daoVendasCliente.getListaVendasCClientesDAO();
	}

}
