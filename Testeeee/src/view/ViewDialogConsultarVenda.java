package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerCliente;
import controller.ControllerProdutos;
import controller.ControllerProdutosVendasProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasCliente;
import controller.ControllerVendasProdutos;
import model.ModelCliente;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendas;
import model.ModelVendasCliente;
import model.ModelVendasProdutos;
import util.BLDatas;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;

public class ViewDialogConsultarVenda extends JDialog {
	ControllerCliente controllerCliente = new ControllerCliente();
	ModelCliente modelCliente = new ModelCliente();
	ArrayList<ModelCliente> listaModelCliente = new ArrayList<ModelCliente>();

	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProduto = new ModelProdutos();
	ArrayList<ModelProdutos> listaModelProduto = new ArrayList<ModelProdutos>();

	ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList<ModelVendasCliente>();
	ControllerVendasCliente controllerVendasCliente = new ControllerVendasCliente();
	ControllerVendas controllerVendas = new ControllerVendas();

	ModelVendas modelVendas = new ModelVendas();
	BLDatas blDatas = new BLDatas();
	
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<ModelVendasProdutos>();
	
	ControllerProdutosVendasProdutos controllerProdutosVendasProdutos = new ControllerProdutosVendasProdutos();
	ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
	ArrayList<ModelProdutosVendasProdutos> listaProdutosVendasProdutos = new ArrayList<ModelProdutosVendasProdutos>();
	

	private final JPanel contentPanel = new JPanel();
	public JTable tbConsulta;
	private JScrollPane sp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewDialogConsultarVenda dialog = new ViewDialogConsultarVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewDialogConsultarVenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				carregarVendas();
			}
			
			
		});
		setBounds(100, 100, 593, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Consultar Venda");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(183, 11, 154, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			tbConsulta = new JTable();
			tbConsulta.setBackground(new Color(211, 211, 211));
			tbConsulta.setFont(new Font("Tahoma", Font.PLAIN, 11));
			tbConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			tbConsulta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00F3digo", "Id Ciente", "Data", "SubTotal"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tbConsulta.getColumnModel().getColumn(1).setPreferredWidth(72);
			tbConsulta.getColumnModel().getColumn(2).setPreferredWidth(119);
			tbConsulta.setBounds(20, 112, 523, 103);
			contentPanel.add(tbConsulta);
			
		}
		{
			sp = new JScrollPane(tbConsulta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp.setBounds(28, 55, 512, 193);
			contentPanel.add(sp);
			sp.setBackground(SystemColor.scrollbar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 304, 577, 43);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			
			JButton btnNewButton = new JButton("Excluir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					excluirVenda();
					
				}
			});
			btnNewButton.setBounds(10, 5, 89, 23);
			buttonPane.add(btnNewButton);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ViewPDV pdv = new ViewPDV();
						pdv.setVisible(true);
						dispose();
					}
				});
				okButton.setBounds(455, 5, 79, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
		}
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
