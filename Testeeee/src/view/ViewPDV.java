package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasProdutos;
import model.ModelProdutos;
import model.ModelSessaoUsuario;
import model.ModelVendas;
import model.ModelVendasProdutos;
import util.BLDatas;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ViewPDV extends JInternalFrame {
	private JTable tbProd;
	private JTextField txtCodProduto;
	private JTextField txtSubTotal;
	
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();
	
	ModelVendas modelVendas = new ModelVendas();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
	
	ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<ModelProdutos>();
	ControllerVendas controllerVendas = new ControllerVendas();
	
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	BLDatas blDatas = new BLDatas();
	
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
	
	int quantidade;
	private viewPagamentoPDV formaPagamento = new viewPagamentoPDV();
	private JTextField txtDescPDV;
	private JTextField txtPagarPDV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPDV frame = new ViewPDV();
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
	public ViewPDV() {
		setClosable(true);
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setIconifiable(true);
		setMaximizable(true);
		setTitle("PDV");
		setBounds(10, 11, 733, 441);
		getContentPane().setLayout(null);
		
		JOptionPane.showMessageDialog(null, "Informar a quantidade de itens");
		//setarOperador();
		
		quantidade = 1;
		
		
		
		
		
		
		tbProd = new JTable();
		tbProd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "C\u00F3digo", "Nome", "Qtd", "Valor Uni.", "Valor Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbProd.getColumnModel().getColumn(0).setPreferredWidth(36);
		tbProd.getColumnModel().getColumn(1).setPreferredWidth(55);
		tbProd.getColumnModel().getColumn(2).setPreferredWidth(140);
		tbProd.getColumnModel().getColumn(3).setPreferredWidth(35);
		tbProd.getColumnModel().getColumn(4).setPreferredWidth(65);
		tbProd.getColumnModel().getColumn(5).setPreferredWidth(71);
		tbProd.setBounds(78, 84, 298, 108);
		getContentPane().add(tbProd);
		
		JScrollPane sp = new JScrollPane(tbProd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(5, 84, 508, 246);
		getContentPane().add(sp);
		sp.setBackground(SystemColor.scrollbar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(525, 4, 190, 58);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(6, 34, 79, 16);
		panel.add(lblStatus);
		
		JLabel lblNewLabel_1_2 = new JLabel("Status:");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(6, 6, 79, 16);
		panel.add(lblNewLabel_1_2);
		
		txtCodProduto = new JTextField();
		txtCodProduto.setToolTipText("Insira o codigo do produto");
		txtCodProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pegarConteudo(e);
			}
		});
	
		txtCodProduto.setBounds(6, 342, 501, 28);
		getContentPane().add(txtCodProduto);
		txtCodProduto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ML SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblNewLabel.setBounds(37, 13, 431, 58);
		getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 204, 204));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(525, 63, 190, 318);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Valor Bruto");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(59, 6, 101, 16);
		panel_1.add(lblNewLabel_1_3);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubTotal.setText("0.0");
		txtSubTotal.setBounds(38, 34, 122, 28);
		panel_1.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Comandos");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(48, 203, 80, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("F4 Finalizar Venda");
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(6, 266, 140, 16);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("F5 Pesquisar Produto");
		lblNewLabel_3_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_1.setBounds(6, 289, 122, 16);
		panel_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("F9 Sair");
		lblNewLabel_3_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_2.setBounds(91, 221, 55, 16);
		panel_1.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("F3 Quantidade");
		lblNewLabel_3_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_2.setBounds(6, 245, 122, 16);
		panel_1.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3 = new JLabel("F2 Excluir");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setBounds(6, 221, 76, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Desconto");
		lblNewLabel_1_3_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_3_1.setBounds(59, 74, 101, 16);
		panel_1.add(lblNewLabel_1_3_1);
		
		txtDescPDV = new JTextField();
		txtDescPDV.setEditable(false);
		txtDescPDV.setText("0.0");
		txtDescPDV.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDescPDV.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtDescPDV.setColumns(10);
		txtDescPDV.setBounds(38, 102, 122, 28);
		panel_1.add(txtDescPDV);
		
		txtPagarPDV = new JTextField();
		txtPagarPDV.setEditable(false);
		txtPagarPDV.setForeground(new Color(204, 51, 0));
		txtPagarPDV.setText("0.0");
		txtPagarPDV.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPagarPDV.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtPagarPDV.setColumns(10);
		txtPagarPDV.setBounds(38, 168, 122, 28);
		panel_1.add(txtPagarPDV);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("SubTotal");
		lblNewLabel_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_3_1_1.setBounds(38, 140, 122, 16);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuSair = new JMenuItem("Sair");
		menuSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		mnNewMenu.add(menuSair);
		
		JMenu mnNewMenu_1 = new JMenu("Comandos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuAlterarQtd = new JMenuItem("Alterar quantidade");
		menuAlterarQtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chama uma janela para inserir a quantidade
				quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade "));
				System.out.println("quantidade é" + quantidade);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Excluir");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// exclui os itens da venda 
				int qtdLinha = tbProd.getRowCount();
				if(qtdLinha < 1) {
					JOptionPane.showMessageDialog(null, "Não existe intens para excluir!!");
				} else {
					DefaultTableModel model = (DefaultTableModel) tbProd.getModel();
					int linha = Integer.parseInt(JOptionPane.showInputDialog( "Informe o item que deseja excluir"));
					model.removeRow(linha - 1);
					txtSubTotal.setText(String.valueOf(subTotal()));
					
					for(int i = 0; i < qtdLinha; i ++) {
						model.setValueAt(i + 1,  i, 0);
				}
			
				}
				
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		menuAlterarQtd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnNewMenu_1.add(menuAlterarQtd);
		
		JMenuItem menuFinalizar = new JMenuItem("Finalizar Venda");
		menuFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				formaPagamento.setValorTotalPdv(Float.parseFloat(txtSubTotal.getText()));
				
				formaPagamento.setTextFieldValorTotal();
				formaPagamento.setVisible(true);
				
				txtDescPDV.setText(String.valueOf(formaPagamento.getDesconto()));
				txtPagarPDV.setText(String.valueOf(formaPagamento.getValorTotalPdv()));
				
				
				
				System.out.println(formaPagamento.getFormaPagamento());
				
				
				
				/*
				 * if (formaPagamento.isPago()) { salvarVenda(); }else {
				 * 
				 * JOptionPane.showMessageDialog(null, "Pagamento cancelado pelo usuário",
				 * "Atenção", JOptionPane.INFORMATION_MESSAGE); }
				 */
				
				
				
			}
		});
		
		menuFinalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnNewMenu_1.add(menuFinalizar);
		
		JMenuItem menuPesquisaProd = new JMenuItem("Pesquisar Produto");
		menuPesquisaProd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu_1.add(menuPesquisaProd);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		mnNewMenu_1.add(mntmNewMenuItem);

	}
	
	private void pegarConteudo(java.awt.event.KeyEvent e) {

		DefaultTableModel modelo = (DefaultTableModel) tbProd.getModel();
		if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			try {
				modelProdutos = controllerProdutos.retornarProdutoCOntroller(Integer.parseInt(txtCodProduto.getText()));
				modelo.addRow(new Object[] {
						modelo.getRowCount() +1,
						modelProdutos.getIdProduto(),
						modelProdutos.getProNome(),
						quantidade,
						modelProdutos.getProValor(),
						modelProdutos.getProValor() * quantidade
				
				});
				txtSubTotal.setText(subTotal() + "");
				txtCodProduto.setText("");
				quantidade = 1;
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Informe um numero do tipo inteiro", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}
	
	private float subTotal() {
		float soma = 0;
		float valor = 0;
		int cont = tbProd.getRowCount();
		
		for (int i = 0; i <cont; i++) {
			valor = Float.parseFloat(String.valueOf(tbProd.getValueAt(i, 5)));
			soma += valor;
		}
		return soma;
	}
	
	private void salvarVenda() {
		int cont;
		int codigoProduto = 0;
		int codigoVenda = 0;
		
		modelVendas = new ModelVendas();
		modelVendas.setCliente(1);
		try {
			modelVendas.setVenDataVenda(blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
		} catch (Exception e1) {
			Logger.getLogger(ViewPDV.class.getName()).log(Level.SEVERE, null, e1);
		}
		
		
		//salvar a vendaPDV
		
		
		modelVendas.setVenValorBruto(Float.parseFloat(txtSubTotal.getText().replace(",", ".")));
		modelVendas.setVenDesconto(Float.parseFloat(txtDescPDV.getText().replace(",", ".")));
		modelVendas.setVenValorLiquido(Float.parseFloat(txtPagarPDV.getText().replace(",", ".")));
		codigoVenda = controllerVendas.salvarVendasController(modelVendas);
		
		if (codigoVenda > 0) {
			JOptionPane.showMessageDialog(null, "Venda concluida!", "Concluído", JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao concluir a venda!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
		cont = tbProd.getRowCount();
		
		for (int i = 0; i <cont; i++) {
			
			codigoProduto = (int ) tbProd.getValueAt(i, 1);
			modelVendasProdutos = new ModelVendasProdutos();
			modelProdutos = new ModelProdutos();
			
			modelVendasProdutos.setProduto(codigoProduto);
			modelVendasProdutos.setVendas(codigoVenda);
			modelVendasProdutos.setVenProValor((float) tbProd.getValueAt(i, 4) );
			modelVendasProdutos.setVenProQuantidade(Integer.parseInt(tbProd.getValueAt(i, 3).toString()));
			/////
			
			
			//////////
			
			modelProdutos.setIdProduto(codigoProduto);
			modelProdutos.setproEstoque(controllerProdutos.retornarProdutoCOntroller(codigoProduto).getproEstoque() - 
					Integer.parseInt(tbProd.getValueAt(i, 3).toString()));
			
			listaModelVendasProdutos.add(modelVendasProdutos);
			listaModelProdutos.add(modelProdutos);		
		}
		// finaliza a venda
		if(controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)){
			controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
			JOptionPane.showMessageDialog(null, "Salvo!!","Atençao", JOptionPane.INFORMATION_MESSAGE);
		
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao salvar produtoto", "Error", JOptionPane.WARNING_MESSAGE);
		}
	
	}
	
	
}
