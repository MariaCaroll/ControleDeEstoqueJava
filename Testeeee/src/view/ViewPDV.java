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
		setBounds(10, 11, 733, 434);
		getContentPane().setLayout(null);
		
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
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(525, 4, 190, 169);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Caixa:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1.setBounds(6, 6, 79, 16);
		panel.add(lblNewLabel_1);
		
		JLabel txtCaixa = new JLabel("caixa");
		txtCaixa.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtCaixa.setBounds(6, 34, 79, 16);
		panel.add(txtCaixa);
		
		JLabel lblOperador = new JLabel("operador");
		lblOperador.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblOperador.setBounds(6, 90, 157, 16);
		panel.add(lblOperador);
		
		JLabel lblNewLabel_1_1 = new JLabel("Operador:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(6, 62, 102, 16);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(6, 146, 79, 16);
		panel.add(lblStatus);
		
		JLabel lblNewLabel_1_2 = new JLabel("Status:");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(6, 118, 79, 16);
		panel.add(lblNewLabel_1_2);
		
		txtCodProduto = new JTextField();
		txtCodProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pegarConteudo(e);
			}
		});
	
		txtCodProduto.setBounds(6, 342, 501, 28);
		getContentPane().add(txtCodProduto);
		txtCodProduto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Maria Lima Developer Java");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblNewLabel.setBounds(37, 13, 431, 58);
		getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(525, 185, 190, 195);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("SubTotal");
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(59, 6, 101, 16);
		panel_1.add(lblNewLabel_1_3);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(38, 34, 122, 28);
		panel_1.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Comandos");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setBounds(59, 74, 71, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("F3 Quantidade");
		lblNewLabel_3.setBounds(20, 95, 122, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("F4 Finalizar Venda");
		lblNewLabel_3_1.setBounds(20, 118, 140, 16);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("F5 Pesquisar Produto");
		lblNewLabel_3_1_1.setBounds(20, 141, 122, 16);
		panel_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("F9 Sair");
		lblNewLabel_3_1_2.setBounds(20, 162, 55, 16);
		panel_1.add(lblNewLabel_3_1_2);
		
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
		menuAlterarQtd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnNewMenu_1.add(menuAlterarQtd);
		
		JMenuItem menuFinalizar = new JMenuItem("Finalizar Venda");
		menuFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				codigoVenda = controllerVendas.salvarVendasController(modelVendas);
				modelVendas.setVenValorBruto(Double.parseDouble(txtSubTotal.getText().replace(",", ".")));
				modelVendas.setVenDesconto(0.0);
				modelVendas.setVenValorLiquido(Double.parseDouble(txtSubTotal.getText().replace(",", ".")));
				
				cont = tbProd.getRowCount();
				for (int i = 0; i <cont; i++) {
					codigoProduto = (int ) tbProd.getValueAt(i, 1);
					modelVendasProdutos = new ModelVendasProdutos();
					modelProdutos = new ModelProdutos();
					
					modelVendasProdutos.setProduto(codigoProduto);
					modelVendasProdutos.setVendas(codigoVenda);
					modelVendasProdutos.setVenProValor((double) tbProd.getValueAt(i, 4) );
					modelVendasProdutos.setVenProQuantidade(Integer.parseInt(tbProd.getValueAt(i, 3).toString()));
					
					
					modelProdutos.setIdProduto(codigoProduto);
					modelProdutos.setproEstoque(controllerProdutos.retornarProdutoCOntroller(codigoProduto).getproEstoque() - Integer.parseInt(tbProd.getValueAt(i, 3).toString()));
					
					listaModelVendasProdutos.add(modelVendasProdutos);
					listaModelProdutos.add(modelProdutos);		
				}
				if(controllerVendasProdutos.salvarVendasProdutosController(listaModelVendasProdutos)){
					controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
					JOptionPane.showMessageDialog(null, "Salvo!!","Atençao", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar produtoto", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		menuFinalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnNewMenu_1.add(menuFinalizar);
		
		JMenuItem menuPesquisaProd = new JMenuItem("Pesquisar Produto");
		menuPesquisaProd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu_1.add(menuPesquisaProd);

	}
	
	private void pegarConteudo(java.awt.event.KeyEvent e) {
		
		
		int quantidade = 1;
		DefaultTableModel modelo = (DefaultTableModel) tbProd.getModel();
		if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			
			modelProdutos = controllerProdutos.retornarProdutoCOntroller(Integer.parseInt(txtCodProduto.getText()));
			modelo.addRow(new Object[] {
					modelo.getRowCount() +1,
					modelProdutos.getIdProduto(),
					modelProdutos.getProNome(),
					quantidade,
					modelProdutos.getProValor(),
					modelProdutos.getProValor() * quantidade
			
			});
			txtSubTotal.setText(String.valueOf(subTotal()));
			txtCodProduto.setText(null);
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
}
