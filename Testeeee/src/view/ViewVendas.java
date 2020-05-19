package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import componentes.UJComboBox;
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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ViewVendas extends JInternalFrame {
	private JTextField txtCodCliente;
	private JTextField txtCodVenda;
	private JTextField txtCodProduto;
	private JTable tbCadastro;
	private JTable tbConsulta;
	private JTextField txtDesconto;
	private JTextField txtSubtotal;
	private JTextField txtVenda;

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
	
	String alterarSalvar;

	private JComboBox comBoxProduto;
	private JTextField txtQuantidade;
	private JComboBox cbCli;
	private JPanel cadastro;
	private JPanel consultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVendas frame = new ViewVendas();
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
	public ViewVendas() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				listarClientes();
				listarProdutos();
				carregarVendas();
				preencherCodClienteComboBox();
				preencherCodProdutoComboBox();
				AutoCompleteDecorator.decorate(comBoxProduto);
				AutoCompleteDecorator.decorate(cbCli);
				
			}
		});
		setFrameIcon(new ImageIcon(ViewJClientes.class.getResource("/icones/users_clients_group_16774.png")));
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Vendas");
		setBounds(10, 11, 733, 434);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 715, 383);
		getContentPane().add(tabbedPane);

		cadastro = new JPanel();
		cadastro.setSize(new Dimension(700, 383));
		cadastro.setPreferredSize(new Dimension(700, 383));
		tabbedPane.addTab("Cadastro", null, cadastro, null);
		tabbedPane.setEnabledAt(0, true);
		cadastro.setBounds(0, 0, 700, 383);
		cadastro.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cod Cliente");
		lblNewLabel.setBounds(20, 6, 85, 16);
		cadastro.add(lblNewLabel);

		txtCodCliente = new JTextField();
		txtCodCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				modelCliente = controllerCliente.getClienteController(Integer.parseInt(txtCodCliente.getText()));
				cbCli.setSelectedItem(modelCliente.getCliNome());
			}
		});
		txtCodCliente.setBounds(20, 25, 108, 22);
		cadastro.add(txtCodCliente);
		txtCodCliente.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(159, 6, 55, 16);
		cadastro.add(lblNewLabel_1);

		txtCodVenda = new JTextField();
		txtCodVenda.setEditable(false);
		txtCodVenda.setBackground(new Color(50, 205, 50));
		txtCodVenda.setForeground(new Color(34, 139, 34));
		txtCodVenda.setBounds(588, 25, 121, 28);
		cadastro.add(txtCodVenda);
		txtCodVenda.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("N\u00FAmero da Venda");
		lblNewLabel_2.setBounds(588, 6, 108, 16);
		cadastro.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cod Prod");
		lblNewLabel_3.setBounds(20, 59, 70, 16);
		cadastro.add(lblNewLabel_3);

		txtCodProduto = new JTextField();
		txtCodProduto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				modelProduto = controllerProdutos.retornarProdutoCOntroller(Integer.parseInt(txtCodCliente.getText()));
				comBoxProduto.setSelectedItem(modelProduto.getProNome());

			}

		});
		txtCodProduto.setBounds(20, 78, 108, 22);
		cadastro.add(txtCodProduto);
		txtCodProduto.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Produto");
		lblNewLabel_4.setBounds(159, 59, 55, 16);
		cadastro.add(lblNewLabel_4);

		JButton btnAddproduto = new JButton("");
		btnAddproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicinarProdutoTable();
			}
		});
		btnAddproduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddproduto.setIcon(new ImageIcon(ViewVendas.class.getResource("/icones/add_icon-icons.com_74429.png")));
		btnAddproduto.setBounds(625, 65, 36, 35);
		cadastro.add(btnAddproduto);

		tbCadastro = new JTable();
		tbCadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbCadastro.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Produto", "Qtd", "Valor Unit\u00E1rio", "Valor Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbCadastro.getColumnModel().getColumn(0).setPreferredWidth(61);
		tbCadastro.getColumnModel().getColumn(1).setPreferredWidth(169);
		tbCadastro.getColumnModel().getColumn(2).setPreferredWidth(60);
		tbCadastro.getColumnModel().getColumn(3).setPreferredWidth(91);
		tbCadastro.setBounds(20, 112, 666, 103);
		cadastro.add(tbCadastro);
		JScrollPane sp = new JScrollPane(tbCadastro, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(30, 112, 656, 127);
		cadastro.add(sp);
		sp.setBackground(SystemColor.scrollbar);

		JLabel lblNewLabel_5 = new JLabel("Desconto");
		lblNewLabel_5.setBounds(491, 288, 70, 16);
		cadastro.add(lblNewLabel_5);

		txtDesconto = new JTextField();
		txtDesconto.setText("0");
		txtDesconto.setHorizontalAlignment(txtDesconto.RIGHT);

		txtDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				subTotal();
			}
		});

		txtDesconto.setBounds(491, 312, 85, 22);
		cadastro.add(txtDesconto);
		txtDesconto.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Subtotal");
		lblNewLabel_6.setBounds(609, 288, 71, 16);
		cadastro.add(lblNewLabel_6);

		txtSubtotal = new JTextField();
		txtSubtotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(609, 312, 100, 22);
		txtSubtotal.setHorizontalAlignment(txtSubtotal.RIGHT);
		cadastro.add(txtSubtotal);
		txtSubtotal.setColumns(10);

		JButton btnSalvar = new JButton("");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addVenda();
			}
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setToolTipText("Salvar\r\n");
		btnSalvar.setIcon(new ImageIcon(ViewVendas.class.getResource("/icones/shopping-cart-accept_25344.png")));
		btnSalvar.setBounds(330, 267, 80, 80);
		cadastro.add(btnSalvar);

		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarSalvar= "novo";
				limparFrame();
				btnSalvar.setEnabled(true);
			}
		});
		btnNovo.setToolTipText("Novo");
		btnNovo.setIcon(new ImageIcon(ViewVendas.class.getResource("/icones/shopping-cart-add_25345.png")));
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNovo.setBounds(112, 267, 80, 80);
		cadastro.add(btnNovo);

		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setIcon(new ImageIcon(ViewVendas.class.getResource("/icones/shopping-cart-remove_25320.png")));
		btnCancelar.setToolTipText("Cancelar\r\n");
		btnCancelar.setBounds(20, 267, 80, 80);
		cadastro.add(btnCancelar);

		consultar = new JPanel();
		tabbedPane.addTab("Consultar/Excluir/Alterar", null, consultar, null);
		consultar.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Pesquisa");
		lblNewLabel_7.setBounds(26, 6, 67, 16);
		consultar.add(lblNewLabel_7);

		txtVenda = new JTextField();
		txtVenda.setBounds(26, 31, 297, 22);
		consultar.add(txtVenda);
		txtVenda.setColumns(10);

		tbConsulta = new JTable();
		tbConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbConsulta
				.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome Cliente", "Data", "SubTotal"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbConsulta.getColumnModel().getColumn(0).setPreferredWidth(61);
		tbConsulta.getColumnModel().getColumn(1).setPreferredWidth(199);
		tbConsulta.getColumnModel().getColumn(2).setPreferredWidth(134);
		tbConsulta.setBounds(20, 112, 666, 103);
		consultar.add(tbConsulta);
		JScrollPane jsp = new JScrollPane(tbConsulta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(26, 67, 665, 158);
		sp.setBounds(30, 112, 656, 127);

		comBoxProduto = new JComboBox();
		comBoxProduto.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (comBoxProduto.isPopupVisible()) {
					preencherCodProdutoComboBox();

				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});

		comBoxProduto.setBounds(159, 76, 325, 22);
		cadastro.add(comBoxProduto);

		JLabel lblNewLabel_8 = new JLabel("Quantidade");
		lblNewLabel_8.setBounds(534, 59, 70, 16);
		cadastro.add(lblNewLabel_8);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(510, 78, 94, 22);
		cadastro.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		cbCli = new JComboBox();
		cbCli.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (cbCli.isPopupVisible()) {
					preencherCodClienteComboBox();
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		cbCli.setBounds(159, 23, 325, 22);
		cadastro.add(cbCli);
		
		JButton btnRemoverproduto = new JButton("");
		btnRemoverproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerProduto();
			}
		});
		btnRemoverproduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoverproduto.setToolTipText("Remover Produto");
		btnRemoverproduto.setIcon(new ImageIcon(ViewVendas.class.getResource("/icones/Errorstopoutput_error_parada_10120.png")));
		btnRemoverproduto.setBounds(673, 65, 36, 35);
		cadastro.add(btnRemoverproduto);

		consultar.add(jsp);
		jsp.setBackground(SystemColor.scrollbar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirVenda();

			}

		});
		btnExcluir.setIcon(new ImageIcon(ViewVendas.class
				.getResource("/icones/1486504830-delete-dustbin-empty-recycle-recycling-remove-trash_81361.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBounds(26, 267, 80, 80);
		consultar.add(btnExcluir);

	}

	/**
	 * preenche o combobox com todos os clientesS
	 */
	@SuppressWarnings("unchecked")
	private void listarClientes() {
		listaModelCliente = controllerCliente.getListaClienteController();
		cbCli.removeAllItems();
		for (int i = 0; i < listaModelCliente.size(); i++) {
			cbCli.addItem(listaModelCliente.get(i).getCliNome());

		}
	}

	/**
	 * preencher o comboboc com os produtos
	 */
	@SuppressWarnings("unchecked")
	private void listarProdutos() {
		listaModelProduto = controllerProdutos.retornarListaProdutoController();
		comBoxProduto.removeAllItems();
		for (int i = 0; i < listaModelProduto.size(); i++) {
			comBoxProduto.addItem(listaModelProduto.get(i).getProNome());

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
				
			/*} else {
				JOptionPane.showMessageDialog(null, "Erro ao excluir a venda", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		

		if (controllerVendas.excluirVendasController(codigo)) {
			//JOptionPane.showMessageDialog(null, "Venda excluida", "Atenção", JOptionPane.WARNING_MESSAGE);
			carregarVendas();
		} else {
			//JOptionPane.showMessageDialog(null, "Erro ao excluir a venda", "ERRO", JOptionPane.ERROR_MESSAGE);
		}*/
		}
	}

	private void adicinarProdutoTable() {

		if (txtQuantidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a quantidade", "Atenção", JOptionPane.WARNING_MESSAGE);
		} else {
			modelProduto = controllerProdutos.retornarProdutoCOntroller(Integer.parseInt(txtCodProduto.getText()));
			// add uma linha na tabela
			DefaultTableModel modelo = (DefaultTableModel) tbCadastro.getModel();
			int cont = 0;
			double quantidade = 0;
			quantidade = Double.parseDouble(txtQuantidade.getText());
			for (int i = 0; i < cont; i++) {
				modelo.setNumRows(0);

			}
			modelo.addRow(new Object[] { modelProduto.getIdProduto(), modelProduto.getProNome(),
					txtQuantidade.getText(), modelProduto.getProValor(), quantidade * modelProduto.getProValor() });
			subTotal();
		}
	}

	private void subTotal() {
		double soma = 0, valor;

		int cont = tbCadastro.getRowCount();
		for (int i = 0; i < cont; i++) {
			valor = (double) tbCadastro.getValueAt(i, 4);
			soma = soma + valor;

		}

		txtSubtotal.setText(String.valueOf(soma));
		desconto();
	}

	private void desconto() {
		try {
			txtSubtotal.setText(
					String.valueOf(Double.parseDouble(txtSubtotal.getText()) - Double.parseDouble(txtDesconto.getText())));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

	}

	private void preencherCodClienteComboBox() {
		modelCliente = controllerCliente.getClienteController(cbCli.getSelectedItem().toString());
		txtCodCliente.setText(String.valueOf(modelCliente.getIdCliente()));

	}

	private void preencherCodProdutoComboBox() {
		modelProduto = controllerProdutos.retornarProdutoCOntroller(comBoxProduto.getSelectedItem().toString());
		txtCodProduto.setText(String.valueOf(modelProduto.getIdProduto()));
	}

	private void limparFrame() {
		txtQuantidade.setText(null);
		txtDesconto.setText("0");
		txtSubtotal.setText(null);

		DefaultTableModel modelo = (DefaultTableModel) tbCadastro.getModel();
		modelo.setNumRows(0);
	}

	//salvar a venda
	private void addVenda() {
		
		
		int codVenda = 0;
		int codigoProduto = 0;
		double desconto = 0;
		
		listaModelVendasClientes = new ArrayList<ModelVendasCliente>();
		
		desconto = Double.parseDouble(txtSubtotal.getText());
	
		
		modelVendas.setCliente(Integer.parseInt(txtCodCliente.getText()));
		try {
			
			modelVendas
					.setVenDataVenda(blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelVendas.setVenValorLiquido(Double.parseDouble(txtSubtotal.getText()));
		modelVendas.setVenValorBruto(Double.parseDouble(txtDesconto.getText()) + desconto);
		modelVendas.setVenDesconto(desconto);
	
	

		codVenda = controllerVendas.salvarVendasController(modelVendas);
		if (codVenda > 0) {
			JOptionPane.showMessageDialog(null, "Venda concluida!", "Concluído", JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao concluir a venda!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
		
		int cont = tbCadastro.getRowCount();
		for (int i = 0; i < cont; i++) {
			codigoProduto = (int) tbCadastro.getValueAt(i, 0);
			modelVendasProdutos = new ModelVendasProdutos();
			modelProduto = new ModelProdutos();
			modelVendasProdutos.setProduto(codigoProduto);
			modelVendasProdutos.setVendas(codVenda);
			modelVendasProdutos.setVenProValor((double) tbCadastro.getValueAt(i, 3));
			modelVendasProdutos.setVenProQuantidade(Integer.parseInt( tbCadastro.getValueAt(i, 2).toString()));
			
			//baixa no estoque
			modelProduto.setIdProduto((int)tbCadastro.getValueAt(i, 0));
			modelProduto.setproEstoque(controllerProdutos.retornarProdutoCOntroller(codigoProduto).getproEstoque() - Integer.parseInt( tbCadastro.getValueAt(i, 2).toString()));
			
			listaModelVendasProdutos.add(modelVendasProdutos);
			listaModelProduto.add(modelProduto);
			
		}
		//salvar os produtos da venda
		if(controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)) {
			//alterar o estoque do produot
			controllerProdutos.alterarEstoqueProdutoController(listaModelProduto);
			//JOptionPane.showMessageDialog(null, "produto da venda salvo com sucesso!", "Concluído", JOptionPane.INFORMATION_MESSAGE);
			carregarVendas();
			limparFrame();
		}else {
			//JOptionPane.showMessageDialog(null, "Erro ao salvar produto da venda!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	

	
	public void removerProduto() {
		int linha = tbCadastro.getSelectedRow();
		DefaultTableModel modelo = (DefaultTableModel) tbCadastro.getModel();
		modelo.removeRow(linha);
		subTotal();
		
	}
}
