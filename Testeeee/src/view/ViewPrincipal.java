package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktop;
	private JLabel lblData;
	private JLabel lblHora;
	public JLabel lblUsu;
	public JLabel lblNivel;
	public JMenuItem itemProd;
	public JMenuItem itemUsu;
	public JMenuItem itemCliente;
	public JMenuItem mnVenda;
	public JMenuItem mnVPDV;
	public JTextPane txtAMsg;
	public JMenu menuVenda;
	public JMenu menuOpcoes;
	public JMenu menuCadastro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
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
	public ViewPrincipal() {
		setTitle("ML SYSTEM");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				setarHora();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 580);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(204, 51, 102));
		setJMenuBar(menuBar);
		
		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setFont(new Font("SansSerif", Font.BOLD, 12));
		menuCadastro.setForeground(new Color(255, 51, 102));
		menuCadastro.setBackground(new Color(204, 51, 102));
		menuBar.add(menuCadastro);
		
		itemCliente = new JMenuItem("Cliente");
		itemCliente.setEnabled(false);
		itemCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewJClientes clientes = new ViewJClientes();
				clientes.setVisible(true);
				desktop.add(clientes);
			}
		});
		itemCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/users_clients_group_16774.png")));
		menuCadastro.add(itemCliente);
		
		itemUsu = new JMenuItem("Usu\u00E1rio");
		itemUsu.setEnabled(false);
		itemUsu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUsuario usuario = new ViewUsuario();
				usuario.setVisible(true);
				desktop.add(usuario);
			}
		});
		itemUsu.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/user_accounts_15362.png")));
		menuCadastro.add(itemUsu);
		
		itemProd = new JMenuItem("Produto");
		itemProd.setEnabled(false);
		itemProd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewJProdutos produtos = new ViewJProdutos();
				produtos.setVisible(true);
				desktop.add(produtos);
			}
		});
		itemProd.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/PRODUTO.png")));
		menuCadastro.add(itemProd);
		
		menuVenda = new JMenu("Venda");
		menuVenda.setFont(new Font("SansSerif", Font.BOLD, 12));
		menuVenda.setForeground(new Color(255, 51, 102));
		menuBar.add(menuVenda);
		
		mnVenda = new JMenuItem("Nova Venda");
		mnVenda.setEnabled(false);
		mnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewVendas venda = new ViewVendas();
				venda.setVisible(true);
				desktop.add(venda);
			}
		});
		mnVenda.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/shopping-cart_25343.png")));
		menuVenda.add(mnVenda);
		
		mnVPDV = new JMenuItem("PDV");
		mnVPDV.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/shopping-cart_25343.png")));
		mnVPDV.setEnabled(false);
		mnVPDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPDV pdv = new ViewPDV();
				pdv.setVisible(true);
				desktop.add(pdv);
			}
		});
		menuVenda.add(mnVPDV);
		
		menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuOpcoes.setFont(new Font("SansSerif", Font.BOLD, 12));
		menuOpcoes.setForeground(new Color(255, 51, 102));
		menuBar.add(menuOpcoes);
		
		JMenuItem itemTrocarUser = new JMenuItem("Trocar Usuario");
		itemTrocarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewLogin login = new ViewLogin();
				login.setVisible(true);
				dispose();
			}
		});
		menuOpcoes.add(itemTrocarUser);
		
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuOpcoes.add(itemSair);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(255, 204, 204));
		desktop.setForeground(new Color(255, 204, 204));
		desktop.setBounds(10, 11, 750, 491);
		contentPane.add(desktop);
		
		lblData = new JLabel("DATA");
		lblData.setBounds(772, 366, 110, 16);
		contentPane.add(lblData);
		
		lblHora = new JLabel("Hora");
		lblHora.setBounds(772, 400, 55, 16);
		contentPane.add(lblHora);
		
		lblUsu = new JLabel("Usuario");
		lblUsu.setForeground(new Color(0, 0, 0));
		lblUsu.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblUsu.setBounds(786, 39, 96, 16);
		contentPane.add(lblUsu);
		
		lblNivel = new JLabel("Nivel");
		lblNivel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNivel.setForeground(new Color(0, 0, 0));
		lblNivel.setBounds(786, 67, 96, 16);
		contentPane.add(lblNivel);
		
		txtAMsg = new JTextPane();
		txtAMsg.setEditable(false);
		txtAMsg.setBackground(new Color(204, 153, 153));
		txtAMsg.setText("Sua Mensagem aqui");
		txtAMsg.setBounds(787, 122, 161, 107);
		contentPane.add(txtAMsg);
	}
	
	public void setarHora() {
		 lblHora.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}
	
	public void setarData() {
		lblData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
	}
}
