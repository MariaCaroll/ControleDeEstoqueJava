package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.ControllerProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasProdutos;
import model.ModelProdutos;
import model.ModelSessaoUsuario;
import model.ModelVendas;
import model.ModelVendasProdutos;
import util.BLDatas;
import util.Mascaras;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

public class ViewPDV extends JInternalFrame {
	private JTable tbProd;
	private JTextField txtCodProduto;
	private JTextField txtSubTotal;
	
	private int pesquisaProduto;
	
	
	public int getPesquisaProduto() {
		return pesquisaProduto;
	}


	public void setPesquisaProduto(int pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}


	public void setTextFieldPesquisaProd() {
		txtCodProduto.setText(String.valueOf(this.pesquisaProduto));
	}
	
	
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
	
	Mascaras mascaras = new Mascaras();
	
	int quantidade;
	private viewPagamentoPDV formaPagamento = new viewPagamentoPDV();
	public JLabel lblOperador;
	public JLabel lblOpe;
	private JLabel lblStatus;
	private JComboBox cdProduto;
	public JMenuItem mnFinalizar;
	
	

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
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				preencherCBProduto();
				preencherCodProdutoComboBox();
				limparForm();
				AutoCompleteDecorator.decorate(cdProduto);
			}
		});
	
		setClosable(true);
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setIconifiable(true);
		setMaximizable(true);
		setBackground(new Color(160,10,99));
		setTitle("PDV");
		setBounds(10, 11, 733, 464);
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
		tbProd.setBackground(new Color(0, 0, 0, 0));
		tbProd.setGridColor(Color.WHITE);
		tbProd.setForeground(new Color(0, 0, 0));
		tbProd.setShowGrid(true);
		getContentPane().add(tbProd);
		
		JScrollPane sp = new JScrollPane(tbProd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(new LineBorder(new Color(0, 0, 0)));
		sp.setOpaque(false);
		tbProd.setOpaque(false);
		sp.setBounds(5, 84, 508, 246);
		getContentPane().add(sp);
		sp.setBackground(new Color(153, 0, 102));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		
	
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(525, 4, 190, 121);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblStatus = new JLabel("status");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(6, 34, 152, 16);
		panel.add(lblStatus);
		
		JLabel lblNewLabel_1_2 = new JLabel("Status:");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(6, 6, 79, 16);
		panel.add(lblNewLabel_1_2);
		
		lblOperador = new JLabel("Caixa");
		lblOperador.setForeground(Color.BLACK);
		lblOperador.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblOperador.setBounds(6, 62, 99, 16);
		panel.add(lblOperador);
		
		lblOpe = new JLabel("01");
		lblOpe.setForeground(Color.BLACK);
		lblOpe.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblOpe.setBounds(6, 90, 79, 16);
		panel.add(lblOpe);
		
		txtCodProduto = new JTextField();
		txtCodProduto.setBackground(new Color(245, 245, 245));
		txtCodProduto.setToolTipText("Insira o codigo do produto");
		txtCodProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pegarConteudo(e);
			}
		});
	
		txtCodProduto.setBounds(6, 342, 85, 28);
		getContentPane().add(txtCodProduto);
		txtCodProduto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ML SYSTEM");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblNewLabel.setBounds(37, 13, 431, 58);
		getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		
		
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(525, 149, 190, 255);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Valor Bruto");
		lblNewLabel_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(59, 6, 101, 16);
		panel_1.add(lblNewLabel_1_3);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBackground(new Color(245, 245, 245));
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubTotal.setText("0.0");
		txtSubTotal.setBounds(38, 34, 122, 28);
		panel_1.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Comandos");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(59, 84, 80, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("F4 Ir Para Pagamento");
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(38, 168, 130, 16);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("F9 Sair");
		lblNewLabel_3_1_2.setForeground(Color.BLACK);
		lblNewLabel_3_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_2.setBounds(122, 224, 50, 16);
		panel_1.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("F3 Quantidade");
		lblNewLabel_3_2.setForeground(Color.BLACK);
		lblNewLabel_3_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_2.setBounds(80, 140, 92, 16);
		panel_1.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3 = new JLabel("F2 Excluir");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setBounds(98, 112, 62, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblFinalizar = new JLabel("F8 Finalizar Venda");
		lblFinalizar.setForeground(Color.BLACK);
		lblFinalizar.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFinalizar.setBounds(59, 196, 122, 16);
		panel_1.add(lblFinalizar);
		
		cdProduto = new JComboBox();
		cdProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pegarConteudo(e);
			}
		});
		cdProduto.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(cdProduto.isPopupVisible()) {
				preencherCodProdutoComboBox();
				}
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		cdProduto.setBounds(121, 343, 350, 26);
		getContentPane().add(cdProduto);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(0, 0, 721, 410);
		getContentPane().add(panel_2);
		
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
					txtSubTotal.setText(mascaras.arredondamentoComPontoDuasCasasString(subTotal()));
					
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
		
		JMenuItem menuFinalizar = new JMenuItem("Ir Para Pagamento");

		menuFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formaPagamento();
				
			}
		});
		
		menuFinalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnNewMenu_1.add(menuFinalizar);
	
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		mnFinalizar = new JMenuItem("Finalizar Venda");
		mnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null, "deseja realmente Finalizar a venda?", "Atenção", JOptionPane.YES_NO_OPTION);
				if( resposta == JOptionPane.YES_OPTION) {
					salvarVenda();
					limparForm();
				} else {
					dispose();
				}
			
			}
		});
		mnFinalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		mnNewMenu_1.add(mnFinalizar);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Consultar");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consultar Venda");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDialogConsultarVenda consulta = new ViewDialogConsultarVenda();
				consulta.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnNewMenu_2.add(mntmNewMenuItem_2);

	}
	
	private void pegarConteudo(java.awt.event.KeyEvent e) {
		
		lblStatus.setText("Venda em aberto");

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
		modelVendas.setCliente(13);
		try {
			modelVendas.setVenDataVenda(blDatas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
		} catch (Exception e1) {
			Logger.getLogger(ViewPDV.class.getName()).log(Level.SEVERE, null, e1);
		}
		
		
		//salvar a vendaPDV
		
		
		modelVendas.setVenValorBruto(Float.parseFloat(txtSubTotal.getText().replace(",", ".")));
		modelVendas.setVenDesconto(formaPagamento.getDescontoPDV());
		modelVendas.setVenValorLiquido(mascaras.arredondamentoComPontoDuasCasas(formaPagamento.getValorAPagar()));
		codigoVenda = controllerVendas.salvarVendasController(modelVendas);
		
		if (codigoVenda > 0) {
			
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
			limparForm();
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao salvar produtoto", "Error", JOptionPane.WARNING_MESSAGE);
		}
	
	}
	
	public void formaPagamento() {
		try {
			formaPagamento.setVisible(true);
			
			formaPagamento.setValorTotalPdv(Float.parseFloat(txtSubTotal.getText()));
			
			formaPagamento.setTextFieldValorTotal();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Você deve incluir um produto");
		}
		
		
	}
	@SuppressWarnings("unchecked")
	private void preencherCBProduto() {
		listaModelProdutos = controllerProdutos.retornarListaProdutoController();
		cdProduto.removeAllItems();
		for (int i = 0; i < listaModelProdutos.size(); i++) {
			cdProduto.addItem(listaModelProdutos.get(i).getProNome());
		}

	}
	private void preencherCodProdutoComboBox() {
		modelProdutos = controllerProdutos.retornarProdutoCOntroller(cdProduto.getSelectedItem().toString());
		txtCodProduto.setText(String.valueOf(modelProdutos.getIdProduto()));
	}
	
	private void limparForm() {
		
		txtSubTotal.setText(null);
		DefaultTableModel modelo = (DefaultTableModel)tbProd.getModel();
		modelo.setNumRows(0);
		lblStatus.setText("Caixa Livre");
	}
	
	public void codigoPes(int codigoPesquisa) {
		txtCodProduto.setText(String.valueOf(codigoPesquisa));
	}
}
