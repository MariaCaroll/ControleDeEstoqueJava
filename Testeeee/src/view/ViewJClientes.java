package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controller.ControllerCliente;
import model.ModelCliente;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class ViewJClientes extends JInternalFrame {
	private JTextField txtCodCli;
	private JTextField txtNomeCli;
	private JTextField txtEndCli;
	private JTextField txtBairro;
	private JTable tbClientes;

	ControllerCliente controllerCliente = new ControllerCliente();
	ModelCliente modelCliente = new ModelCliente();
	ArrayList<ModelCliente> listaModelCliente = new ArrayList<>();
	private JComboBox cbUf;
	private JButton btnAddd;
	private JFormattedTextField txtCep;
	private JTextField txtCidade;
	private JFormattedTextField txtTelefone;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewJClientes frame = new ViewJClientes();
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
	public ViewJClientes() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				carregarCliente();
				 habilitarButtons(false);
			}
		});
		setFrameIcon(new ImageIcon(ViewJClientes.class.getResource("/icones/users_clients_group_16774.png")));
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Clientes");
		setBounds(10, 11, 731, 413);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 731, 383);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		txtCodCli = new JTextField();
		txtCodCli.setEditable(false);
		txtCodCli.setBounds(10, 36, 86, 22);
		panel.add(txtCodCli);
		txtCodCli.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(126, 11, 46, 14);
		panel.add(lblNewLabel_1);

		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(126, 36, 365, 22);
		panel.add(txtNomeCli);
		txtNomeCli.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 67, 86, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Bairro");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(277, 70, 46, 14);
		panel.add(lblNewLabel_3);

		txtEndCli = new JTextField();
		txtEndCli.setBounds(10, 95, 231, 22);
		panel.add(txtEndCli);
		txtEndCli.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(277, 95, 214, 22);
		panel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cidade");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setBounds(524, 67, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(161, 122, 46, 14);
		panel.add(lblNewLabel_5);

		cbUf = new JComboBox();
		cbUf.setModel(new DefaultComboBoxModel(new String[] { "MG" }));
		cbUf.setBounds(161, 146, 59, 22);
		panel.add(cbUf);

		JLabel lblNewLabel_6 = new JLabel("CEP");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 126, 46, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Telefone");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setBounds(524, 11, 80, 14);
		panel.add(lblNewLabel_7);

		JPanel painelPesquisa = new JPanel();
		painelPesquisa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelPesquisa.setBounds(237, 126, 472, 149);
		painelPesquisa.setBackground(new Color(0,0,0,10));
		panel.add(painelPesquisa);
		painelPesquisa.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewJClientes.class.getResource("/icones/lupa.png")));
		label.setBounds(253, 11, 46, 14);
		painelPesquisa.add(label);

		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pesquisaCLiente();
			}
		});
		txtPesquisa.setBounds(23, 11, 220, 20);
		painelPesquisa.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		tbClientes = new JTable();
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setarCliente();

			}
		});
		tbClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cod", "Nome", "Cidade", "Fone" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tbClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbClientes.getColumnModel().getColumn(1).setPreferredWidth(142);
		tbClientes.getColumnModel().getColumn(2).setPreferredWidth(92);
		tbClientes.getColumnModel().getColumn(3).setResizable(false);
		tbClientes.setBounds(10, 45, 309, 105);
		painelPesquisa.add(tbClientes);
		JScrollPane sp = new JScrollPane(tbClientes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 36, 456, 95);
		painelPesquisa.add(sp);
		sp.setBackground(SystemColor.scrollbar);

		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarButtons(true);
			}
		});
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNovo.setToolTipText("Novo\r\n\r\n");
		btnNovo.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-add_25347.png")));
		btnNovo.setBounds(237, 292, 80, 80);
		panel.add(btnNovo);

		btnAddd = new JButton("");
		btnAddd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirClientes();
				limparCampos();
				carregarCliente();
			}
		});
		btnAddd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddd.setToolTipText("Adicionar\r\n\r\n");
		btnAddd.setIcon(new ImageIcon(ViewJClientes.class.getResource("/icones/male-user-accept_25361 (1).png")));
		btnAddd.setBounds(606, 292, 80, 80);
		panel.add(btnAddd);

		JButton btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
				
			}
		});
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setToolTipText("Alterar\r\n");
		btnAlterar.setIcon(new ImageIcon(ViewJClientes.class.getResource("/icones/male-user-edit_25348.png")));
		btnAlterar.setBounds(133, 292, 80, 80);
		panel.add(btnAlterar);

		JButton btnExlcuir = new JButton("");
		btnExlcuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirClinte();
				carregarCliente();
				limparCampos();

			}
		});
		btnExlcuir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExlcuir.setToolTipText("Cancelar");
		btnExlcuir.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-remove_25351.png")));
		btnExlcuir.setBounds(26, 292, 80, 80);
		panel.add(btnExlcuir);
		
		txtCep = new JFormattedTextField();
		txtCep.setBounds(10, 146, 139, 22);
		  try {
              txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
          } catch (java.text.ParseException ex) {
              ex.printStackTrace();
          }
		
		panel.add(txtCep);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(524, 95, 162, 22);
		panel.add(txtCidade);
		txtCidade.setColumns(10);
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setBounds(524, 36, 162, 22);
		/*
		 * try { txtTelefone.setFormatterFactory(new
		 * javax.swing.text.DefaultFormatterFactory(new
		 * javax.swing.text.MaskFormatter("#####-###"))); } catch
		 * (java.text.ParseException ex) { ex.printStackTrace(); }
		 */
		panel.add(txtTelefone);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(ViewJClientes.class.getResource("/background/Dracula.jpg")));
		lblNewLabel_8.setBounds(0, 0, 722, 383);
		panel.add(lblNewLabel_8);

	}

	private void habilitarButtons(boolean condicao) {
		txtNomeCli.setEnabled(condicao);
		txtEndCli.setEnabled(condicao);
		txtBairro.setEnabled(condicao);
		txtCep.setEnabled(condicao);
		txtTelefone.setEnabled(condicao);
		cbUf.setEnabled(condicao);
		txtCidade.setEnabled(condicao);
		btnAddd.setEnabled(condicao);

	}

	private void limparCampos() {

		txtNomeCli.setText(null);
		txtEndCli.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtCep.setText(null);
		txtTelefone.setText(null);

	}
	/**
	 * esse metodo setar o dado da table nos jtextfield
	 */
	
	  private void setarCliente() {
	  
	  habilitarButtons(true); 
	  int linha = tbClientes.getSelectedRow();
	  
	  
	  try { int codigoCliente = (int) tbClientes.getValueAt(linha, 0);
	  modelCliente = controllerCliente.getClienteController(codigoCliente);
	  
	  txtCodCli.setText(String.valueOf(modelCliente.getIdCliente()));
	  txtNomeCli.setText(modelCliente.getCliNome());
	  txtEndCli.setText(modelCliente.getCliEndereco());
	  txtBairro.setText(modelCliente.getCliBairro());
	  txtCep.setText(modelCliente.getCliCep());
	  txtCidade.setText(modelCliente.getCliCidade());
	  txtTelefone.setText(modelCliente.getCliTelefone());
	  cbUf.setSelectedItem(modelCliente.getCliUf());
	  btnAddd.setEnabled(false);
	 
	  } catch (Exception e) { 
		  JOptionPane.showMessageDialog(null, "Código inválido ou nenhum registro selecionado", "Aviso",JOptionPane.ERROR_MESSAGE); 
		  } 
	  }

	private void inserirClientes() {
		

		modelCliente.setCliNome(txtNomeCli.getText());
		modelCliente.setCliEndereco(txtEndCli.getText());
		modelCliente.setCliBairro(txtBairro.getText());
		modelCliente.setCliCidade(txtCidade.getText());
		modelCliente.setCliUf(cbUf.getSelectedItem().toString());
		modelCliente.setCliCep(txtCep.getText());
		modelCliente.setCliTelefone(txtTelefone.getText());

		if (controllerCliente.salvarClienteController(modelCliente) > 0) {
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Error", JOptionPane.ERROR_MESSAGE);
			carregarCliente();
			limparCampos();

		} else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente ", "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void carregarCliente() {
		listaModelCliente = controllerCliente.getListaClienteController();
		DefaultTableModel modelo = (DefaultTableModel) tbClientes.getModel();
		modelo.setNumRows(0);

		// inserir os produtos na tabela

		int cont = listaModelCliente.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelCliente.get(i).getIdCliente(), listaModelCliente.get(i).getCliNome(),
					listaModelCliente.get(i).getCliCidade(), listaModelCliente.get(i).getCliTelefone(), });

		}
	}


	 

	private void excluirClinte() {
		int linha = tbClientes.getSelectedRow();
		int codigoCliente = (int) tbClientes.getValueAt(linha, 0);

		if (controllerCliente.excluirClienteController(codigoCliente)) {
			JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Atenção", JOptionPane.WARNING_MESSAGE);
			carregarCliente();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao excluir o Cliente!!", "Error", JOptionPane.ERROR_MESSAGE);
			habilitarButtons(false);
		}
	}

	private void alterarCliente() {
		int linha = tbClientes.getSelectedRow();
		int codigoCliente = (int) tbClientes.getValueAt(linha, 0);
		

		modelCliente = controllerCliente.getClienteController(codigoCliente);
		modelCliente.setCliNome(txtNomeCli.getText());
		modelCliente.setCliEndereco(txtEndCli.getText());
		modelCliente.setCliBairro(txtBairro.getText());
		modelCliente.setCliCidade(txtCidade.getText());
		modelCliente.setCliUf(cbUf.getSelectedItem().toString());
		modelCliente.setCliCep(txtCep.getText());
		modelCliente.setCliTelefone(txtTelefone.getText());
		

		if (controllerCliente.atualizarClienteController(modelCliente)) {
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			habilitarButtons(false);
			carregarCliente();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente", "ERRO", JOptionPane.ERROR_MESSAGE);

		}
	}
	private void pesquisaCLiente() {
		DefaultTableModel modelo = (DefaultTableModel) tbClientes.getModel();
		final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
		tbClientes.setRowSorter(classificador);
		String texto = txtPesquisa.getText();
		classificador.setRowFilter(RowFilter.regexFilter(texto, 1));
	}
	
}
