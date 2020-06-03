package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.ControllerProdutos;
import model.ModelProdutos;
import util.Formatador;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;

public class ViewJProdutos extends JInternalFrame {
	private JTextField txtCodPro;
	private JTable tbProd;

	
	ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();
	
	Formatador formatador = new Formatador();
	
	String  salvarAlterar;
	
	private JTextField txtNomePro;
	private JTextField txtPesquisa;
	private JFormattedTextField txtEstoquPro;
	private JFormattedTextField txtValorProd;
	private JButton btnAdd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewJProdutos frame = new ViewJProdutos();
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
	public ViewJProdutos() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				carregarProduto();
				habilitarButtons(false);
			}
		});
		setFrameIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/PRODUTO.png")));
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Produto");
		
		setBounds(10, 11, 731, 413);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 715, 383);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		txtCodPro = new JTextField();
		txtCodPro.setEditable(false);
		txtCodPro.setBounds(10, 36, 86, 20);
		panel.add(txtCodPro);
		txtCodPro.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(120, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtNomePro = new JTextField();
		txtNomePro.setBounds(120, 36, 341, 20);
		panel.add(txtNomePro);
		txtNomePro.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Estoque");
		lblNewLabel_2.setBounds(509, 11, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Valor");
		lblNewLabel_3.setBounds(619, 11, 46, 14);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(24, 82, 656, 178);
		panel.add(panel_1);
		panel.setBackground(new Color(0,0,0,10));
		panel_1.setLayout(null);
		
		tbProd = new JTable();
		tbProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setarProduto();
			}
		});
		tbProd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Qtd", "Valor"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tbProd.getColumnModel().getColumn(1).setPreferredWidth(251);
		tbProd.getColumnModel().getColumn(1).setMinWidth(16);
		tbProd.setBounds(10, 67, 313, 190);
		panel_1.add(tbProd);
		
		JScrollPane sp = new JScrollPane(tbProd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 42, 636, 125);
		panel_1.add(sp);
		sp.setBackground(SystemColor.scrollbar);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pesquisaProduto();
			}
		});
		txtPesquisa.setBounds(21, 11, 362, 20);
		panel_1.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/lupa.png")));
		lblNewLabel_4.setBounds(399, 11, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				excluirProudto();
				carregarProduto();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/Package-delete_25333.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(37, 292, 80, 80);
		panel.add(btnExcluir);
		
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/checkprod.png")));
		btnAdd.setToolTipText("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirProduto();
				carregarProduto();
				habilitarButtons(false);
			}
		});
		btnAdd.setBounds(600, 292, 80, 80);
		panel.add(btnAdd);
		
		JButton btnNovo = new JButton("");
		btnNovo.setToolTipText("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarButtons(true);
				
			}
			
		});
		btnNovo.setIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/Package-add_25346.png")));
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNovo.setBounds(265, 292, 80, 80);
		panel.add(btnNovo);
		
		JButton btnEditar = new JButton("");
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
				
			}
		});
		btnEditar.setIcon(new ImageIcon(ViewJProdutos.class.getResource("/icones/Item-configuration_25375 (1).png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(150, 292, 80, 80);
		panel.add(btnEditar);
		
		txtEstoquPro = new JFormattedTextField();
		txtEstoquPro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		txtEstoquPro.setBounds(509, 36, 80, 20);
		panel.add(txtEstoquPro);
		
		txtValorProd = new JFormattedTextField();
		txtValorProd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
		txtValorProd.setBounds(619, 36, 71, 20);
		panel.add(txtValorProd);

	}
	
	private void habilitarButtons(boolean condicao) {
		txtNomePro.setEnabled(condicao);
		txtEstoquPro.setEnabled(condicao);
		txtValorProd.setEnabled(condicao);
		btnAdd.setEnabled(condicao);
	}
	
	/**
	 * Preenche a tabela de produtos
	 */
//	
	private void carregarProduto() {
		listaModelProdutos = controllerProdutos.retornarListaProdutoController();
		DefaultTableModel modelo = (DefaultTableModel) tbProd.getModel();
		modelo.setNumRows(0);

		//inserir os produtos na tabela
		
		int cont = listaModelProdutos.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] {
					listaModelProdutos.get(i).getIdProduto(),
					listaModelProdutos.get(i).getProNome(),
					listaModelProdutos.get(i).getproEstoque(),
					listaModelProdutos.get(i).getProValor()
			});
			
		}
		
	}
	
	private void inserirProduto() {
		
		modelProdutos.setProNome(txtNomePro.getText());
		modelProdutos.setproEstoque(Integer.parseInt(txtEstoquPro.getText()));
		modelProdutos.setProValor(Float.parseFloat(txtValorProd.getText().replace(",", ".")));
		
			if(controllerProdutos.salvarProdutoController(modelProdutos) > 0 ) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar produtos", "Atenção", JOptionPane.WARNING_MESSAGE);
				
				
			}else {
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Error", JOptionPane.ERROR_MESSAGE);
				limparCampos();
			}
	}
	
	private void excluirProudto() {
		int linha = tbProd.getSelectedRow();
		int codigoProduto = (int) tbProd.getValueAt(linha, 0);
		
		
		if(controllerProdutos.exvluirProdutosController(codigoProduto)) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir produto!!", "Atenção", JOptionPane.WARNING_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Produto Excluido com sucesso!!", "Error", JOptionPane.ERROR_MESSAGE);
			habilitarButtons(false);
		}
	}
	
	private void limparCampos() {
		
		txtNomePro.setText(null);
		txtEstoquPro.setText(null);
		txtValorProd.setText(null);
	}
	
	private void setarProduto() {
		
		habilitarButtons(true);
		int linha = tbProd.getSelectedRow();
		
		
		try {
			int codigoProduto = (int) tbProd.getValueAt(linha, 0);
			modelProdutos = controllerProdutos.retornarProdutoCOntroller(codigoProduto);
			
			txtCodPro.setText(String.valueOf(modelProdutos.getIdProduto()));
			txtNomePro.setText(modelProdutos.getProNome());
			txtEstoquPro.setText(String.valueOf(modelProdutos.getproEstoque()));
			txtValorProd.setText(String.valueOf(modelProdutos.getProValor()));
			btnAdd.setEnabled(false);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Código inválido ou nenhum registro selecionado", "Aviso", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void alterarProduto() {
		
		modelProdutos.setProNome(txtNomePro.getText());
		modelProdutos.setproEstoque(Integer.parseInt(txtEstoquPro.getText()));
		modelProdutos.setProValor(formatador.converterVirgulaParaPonto(txtValorProd.getText()));
		
		if(controllerProdutos.alterarProdutoController(modelProdutos)) {
			JOptionPane.showMessageDialog(null, "Produto alterado com sucesso", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			habilitarButtons(false);
			carregarProduto();
			limparCampos();
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao alterar o produto", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void pesquisaProduto() {
		DefaultTableModel modelo = (DefaultTableModel) tbProd.getModel();
		final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
		tbProd.setRowSorter(classificador);
		String texto = txtPesquisa.getText();
		classificador.setRowFilter(RowFilter.regexFilter(texto, 1));
	}
	

}
