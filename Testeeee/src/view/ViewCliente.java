package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class ViewCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodClie;
	private JTextField txtNomeCli;
	private JTextField txtEndeCli;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTable jtCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCliente frame = new ViewCliente();
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
	public ViewCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 646, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		txtCodClie = new JTextField();
		txtCodClie.setEditable(false);
		txtCodClie.setBounds(10, 36, 58, 20);
		panel.add(txtCodClie);
		txtCodClie.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(89, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(88, 36, 536, 20);
		panel.add(txtNomeCli);
		txtNomeCli.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o");
		lblNewLabel_2.setBounds(10, 67, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtEndeCli = new JTextField();
		txtEndeCli.setBounds(10, 90, 276, 20);
		panel.add(txtEndeCli);
		txtEndeCli.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Bairro");
		lblNewLabel_3.setBounds(308, 67, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(308, 90, 316, 20);
		panel.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cidade");
		lblNewLabel_4.setBounds(10, 131, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(10, 156, 211, 20);
		panel.add(txtCidade);
		txtCidade.setColumns(10);
		
		JComboBox cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"MG", "RJ"}));
		cbEstado.setBounds(231, 155, 46, 22);
		panel.add(cbEstado);
		
		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setBounds(231, 131, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("CEP");
		lblNewLabel_6.setBounds(291, 131, 46, 14);
		panel.add(lblNewLabel_6);
		
		txtCep = new JTextField();
		txtCep.setBounds(287, 156, 133, 20);
		panel.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fone");
		lblNewLabel_7.setBounds(430, 131, 46, 14);
		panel.add(lblNewLabel_7);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(430, 155, 194, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		jtCliente = new JTable();
		jtCliente.setBackground(SystemColor.scrollbar);
		jtCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Cidade", "Telefone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtCliente.setBounds(16, 58, 623, 79);
		panel.add(jtCliente);
		
		JScrollPane sp = new JScrollPane(jtCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBackground(SystemColor.scrollbar);
	
		sp.setBounds(6, 186, 630, 109);
		panel.add(sp);
		
		JButton btnAdd = new JButton("");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Adicionar\r\n");
		btnAdd.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-add_25347.png")));
		btnAdd.setBounds(102, 335, 80, 80);
		panel.add(btnAdd);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setToolTipText("Editar\r\n");
		btnNewButton_1.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-edit_25348.png")));
		btnNewButton_1.setBounds(216, 335, 80, 80);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setToolTipText("Pesquisar\r\n");
		btnNewButton_2.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-search_25352.png")));
		btnNewButton_2.setBounds(330, 335, 80, 80);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setToolTipText("Cancelar");
		btnNewButton_3.setIcon(new ImageIcon(ViewCliente.class.getResource("/icones/male-user-remove_25351.png")));
		btnNewButton_3.setBounds(438, 335, 80, 80);
		panel.add(btnNewButton_3);
	}
}
