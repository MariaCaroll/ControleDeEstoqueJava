package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerProdutos;
import controller.ControllerProdutosVendasProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasCliente;
import controller.ControllerVendasProdutos;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendas;
import model.ModelVendasCliente;
import model.ModelVendasProdutos;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ViewConsultarVenda extends JInternalFrame {
	
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProduto = new ModelProdutos();
	ArrayList<ModelProdutos> listaModelProduto = new ArrayList<ModelProdutos>();
	
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<ModelVendasProdutos>();
	
	ControllerProdutosVendasProdutos controllerProdutosVendasProdutos = new ControllerProdutosVendasProdutos();
	ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
	ArrayList<ModelProdutosVendasProdutos> listaProdutosVendasProdutos = new ArrayList<ModelProdutosVendasProdutos>();
	
	ControllerVendas controllerVendas = new ControllerVendas();
	ModelVendas modelVendas = new ModelVendas();
	
	ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList<ModelVendasCliente>();
	ControllerVendasCliente controllerVendasCliente = new ControllerVendasCliente();
	
	private JTable tbConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultarVenda frame = new ViewConsultarVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewConsultarVenda() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				carregarVendas();
			}
		});
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consultar Venda");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblNewLabel.setBounds(283, 22, 146, 14);
		getContentPane().add(lblNewLabel);
		
		tbConsulta = new JTable();
		tbConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome Cliente", "Data", "SubTotal"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbConsulta.getColumnModel().getColumn(1).setPreferredWidth(188);
		tbConsulta.setBounds(105, 121, 408, 77);
		getContentPane().add(tbConsulta);
		
		JScrollPane sp = new JScrollPane(tbConsulta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(sp);
		sp.setBounds(30, 112, 656, 157);
		sp.setBounds(30, 112, 656, 127);
		
		JButton btnNewButton = new JButton("excluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirVenda();
			}
		});
		btnNewButton.setBounds(30, 306, 90, 28);
		getContentPane().add(btnNewButton);
	


	}
	private void excluirVenda() {
		int linha = tbConsulta.getSelectedRow();
		int codigo = (int) tbConsulta.getValueAt(linha, 0);
		
		
		listaModelProduto = new ArrayList<ModelProdutos>();
		listaProdutosVendasProdutos = controllerProdutosVendasProdutos.getListaProdutosVendasProdutosController(codigo);
		
		for (int i = 0; i < listaProdutosVendasProdutos.size(); i++) {
			modelProduto = new ModelProdutos();
			modelProduto.setIdProduto(listaProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto());
			modelProduto.setproEstoque(
					listaProdutosVendasProdutos.get(i).getModelProdutos().getproEstoque()
					+
					listaProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProQuantidade());
			listaModelProduto.add(modelProduto);
	
		}
		
		if(controllerProdutos.alterarEstoqueProdutoController(listaModelProduto)) {
			controllerVendasProdutos.excluirVendasProdutosController(codigo);
			if (controllerVendas.excluirVendasController(codigo)) {
				JOptionPane.showMessageDialog(null, "Venda excluida", "Atenção", JOptionPane.WARNING_MESSAGE);
				carregarVendas();
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao excluir a venda", "ERRO", JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	private void carregarVendas() {

		DefaultTableModel modelo = (DefaultTableModel) tbConsulta.getModel();
		listaModelVendasClientes = controllerVendasCliente.getListaVendasCliente();

		int cont = listaModelVendasClientes.size();
		modelo.setNumRows(0);
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelVendasClientes.get(i).getModelVendas().getIdVenda(),
					listaModelVendasClientes.get(i).getModelCliente().getCliNome(),
					listaModelVendasClientes.get(i).getModelVendas().getVenDataVenda(),
					listaModelVendasClientes.get(i).getModelVendas().getVenValorLiquido()});

		}
	}
}
