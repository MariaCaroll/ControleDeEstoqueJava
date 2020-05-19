package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.ControllerCliente;
import controller.ControllerUsuario;
import model.ModelCliente;
import model.ModelUsuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class ViewUsuario extends JInternalFrame {
	private JTextField txtCod;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField psSenha;
	private JTable tbUsu;
	private JTextField txtPesquisa;
	private JButton btnAdd;
	
	ControllerUsuario controllerUsuario = new ControllerUsuario();
	ModelUsuario modelUsuario = new ModelUsuario();
	ArrayList<ModelUsuario> listaModelUsuarios = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUsuario frame = new ViewUsuario();
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
	public ViewUsuario() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				carregarUsuario();
				habilitarButtons(false);
			}
		});
		setPreferredSize(new Dimension(722, 413));
		setBounds(new Rectangle(10, 11, 722, 413));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Usuarios");
		setBounds(10, 11, 731, 413);
		getContentPane().setLayout(null);
		
		 
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 715, 383);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 6, 55, 16);
		panel.add(lblNewLabel);
		
		txtCod = new JTextField();
		txtCod.setBounds(20, 31, 122, 22);
		panel.add(txtCod);
		txtCod.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(190, 6, 55, 16);
		panel.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(190, 31, 426, 22);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 65, 55, 16);
		panel.add(lblNewLabel_2);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(20, 88, 237, 22);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(292, 65, 55, 16);
		panel.add(lblNewLabel_3);
		
		psSenha = new JPasswordField();
		psSenha.setBounds(292, 88, 237, 22);
		panel.add(psSenha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(30, 122, 634, 142);
		panel_1.setBackground(new Color(0,0,0,10));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		tbUsu = new JTable();
		tbUsu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setarUsuario();

			}
		});
		tbUsu.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Login", "Senha"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbUsu.getColumnModel().getColumn(0).setPreferredWidth(47);
		tbUsu.getColumnModel().getColumn(1).setPreferredWidth(201);
		tbUsu.getColumnModel().getColumn(2).setPreferredWidth(136);
		tbUsu.getColumnModel().getColumn(3).setPreferredWidth(123);
		tbUsu.setBounds(23, 44, 578, 77);
		panel_1.add(tbUsu);
		JScrollPane sp = new JScrollPane(tbUsu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 34, 606, 95);
		sp.setBackground(new Color(0,0,0,10));
		panel_1.add(sp);
		sp.setBackground(SystemColor.scrollbar);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ViewUsuario.class.getResource("/icones/lupa.png")));
		lblNewLabel_4.setBounds(387, 6, 55, 16);
		panel_1.add(lblNewLabel_4);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pesquisaUsuario();
			}
		});
		txtPesquisa.setBounds(62, 6, 310, 22);
		panel_1.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JButton btnExlcuir = new JButton("");
		btnExlcuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExlcuir.setIcon(new ImageIcon(ViewUsuario.class.getResource("/icones/male-user-remove_25351.png")));
		btnExlcuir.setToolTipText("Cancelar");
		btnExlcuir.setBounds(25, 286, 80, 80);
		panel.add(btnExlcuir);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
			
		});
		btnAlterar.setIcon(new ImageIcon(ViewUsuario.class.getResource("/icones/male-user-edit_25348.png")));
		btnAlterar.setToolTipText("Alterar\r\n");
		btnAlterar.setBounds(132, 286, 80, 80);
		panel.add(btnAlterar);
		
		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarButtons(true);

			}
		});
		btnNovo.setIcon(new ImageIcon(ViewUsuario.class.getResource("/icones/male-user-add_25347.png")));
		btnNovo.setToolTipText("Novo\r\n\r\n");
		btnNovo.setBounds(236, 286, 80, 80);
		panel.add(btnNovo);
		
		btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirUsuarios();
				limparCampos();
				carregarUsuario();
			}
		});
		btnAdd.setIcon(new ImageIcon(ViewUsuario.class.getResource("/icones/male-user-accept_25361 (1).png")));
		btnAdd.setToolTipText("Adicionar\r\n\r\n");
		btnAdd.setBounds(605, 286, 80, 80);
		panel.add(btnAdd);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(ViewUsuario.class.getResource("/background/Alive.jpg")));
		lblNewLabel_5.setBounds(0, 0, 715, 383);
		panel.add(lblNewLabel_5);

	}
	private void habilitarButtons(boolean condicao) {
		txtNome.setEnabled(condicao);
		txtCod.setEnabled(condicao);
		txtLogin.setEnabled(condicao);
		btnAdd.setEnabled(condicao);

	}
	
	private void limparCampos() {
		txtNome.setText(null);
		txtCod.setText(null);
		txtLogin.setText(null);
		psSenha.setText(null);
	}
	
	private void carregarUsuario() {
		
		listaModelUsuarios = controllerUsuario.getListaUsuarioController();
		DefaultTableModel modelo = (DefaultTableModel) tbUsu.getModel();
		modelo.setNumRows(0);

		// inserir os produtos na tabela

		int cont = listaModelUsuarios.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] { listaModelUsuarios.get(i).getIdUsuario(), listaModelUsuarios.get(i).getUsuNome(),
					listaModelUsuarios.get(i).getUsuLogin(), listaModelUsuarios.get(i).getUsuSenha(), });

		}
		
	}
	
	  private void setarUsuario() {
		  
	  habilitarButtons(true); 
	  int linha = tbUsu.getSelectedRow();
	  
	  
	  try { int codigoUsuario = (int) tbUsu.getValueAt(linha, 0);
	  modelUsuario = controllerUsuario.getUsuarioController(codigoUsuario);
	 
	  txtCod.setText(String.valueOf(modelUsuario.getIdUsuario()));
	  txtNome.setText(modelUsuario.getUsuNome());
	  txtLogin.setText(modelUsuario.getUsuLogin());
	  psSenha.setText(modelUsuario.getUsuSenha());

	  btnAdd.setEnabled(false);
	 
	  } catch (Exception e) { 
		  JOptionPane.showMessageDialog(null, "Código inválido ou nenhum registro selecionado", "Aviso",JOptionPane.ERROR_MESSAGE); 
		  } 
	  }
	
	private void inserirUsuarios() {
		
		modelUsuario.setUsuNome(txtNome.getText());
		modelUsuario.setUsuLogin(txtLogin.getText());
		modelUsuario.setUsuSenha(String.valueOf(psSenha.getPassword()));

		if (controllerUsuario.salvarUsuarioController(modelUsuario) > 0) {
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Error", JOptionPane.ERROR_MESSAGE);
			

		} else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário ", "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void alterarUsuario() {
		int linha = tbUsu.getSelectedRow();
		int codigoUsuario = (int) tbUsu.getValueAt(linha, 0);
		

		modelUsuario = controllerUsuario.getUsuarioController(codigoUsuario);
		modelUsuario.setUsuNome(txtNome.getText());
		modelUsuario.setUsuLogin(txtLogin.getText());
		modelUsuario.setUsuSenha(String.valueOf(psSenha.getPassword()));
		

		if (controllerUsuario.atualizarUsuarioController(modelUsuario)) {
			JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			habilitarButtons(false);
			carregarUsuario();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao alterar o usuário", "ERRO", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	private void excluirUsuario() {
		int linha = tbUsu.getSelectedRow();
		int codigoUsuario = (int) tbUsu.getValueAt(linha, 0);

		if (controllerUsuario.excluirUsuarioController(codigoUsuario)) {
			JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso", "Atenção", JOptionPane.WARNING_MESSAGE);
			carregarUsuario();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao excluir o usuario!!", "Error", JOptionPane.ERROR_MESSAGE);
			habilitarButtons(false);
		}
	}
	
	private void pesquisaUsuario() {
		DefaultTableModel modelo = (DefaultTableModel) tbUsu.getModel();
		final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
		tbUsu.setRowSorter(classificador);
		String texto = txtPesquisa.getText();
		classificador.setRowFilter(RowFilter.regexFilter(texto, 1));
	}
}
