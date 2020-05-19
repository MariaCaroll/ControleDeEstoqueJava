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

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktop;
	public JLabel lblUsuario;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 560);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_3 = new JMenu("Cadastro");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem itemCliente = new JMenuItem("Cliente");
		itemCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewJClientes clientes = new ViewJClientes();
				clientes.setVisible(true);
				desktop.add(clientes);
			}
		});
		itemCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/users_clients_group_16774.png")));
		mnNewMenu_3.add(itemCliente);
		
		JMenuItem itemUsu = new JMenuItem("Usu\u00E1rio");
		itemUsu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUsuario usuario = new ViewUsuario();
				usuario.setVisible(true);
				desktop.add(usuario);
			}
		});
		itemUsu.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/user_accounts_15362.png")));
		mnNewMenu_3.add(itemUsu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Produto");
		mntmNewMenuItem_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewJProdutos produtos = new ViewJProdutos();
				produtos.setVisible(true);
				desktop.add(produtos);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/PRODUTO.png")));
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu = new JMenu("Venda");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nova Venda");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewVendas venda = new ViewVendas();
				venda.setVisible(true);
				desktop.add(venda);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/icones/shopping-cart_25343.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("PDV");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPDV pdv = new ViewPDV();
				pdv.setVisible(true);
				desktop.add(pdv);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBounds(10, 11, 750, 456);
		contentPane.add(desktop);
		
		lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Serif", Font.BOLD, 16));
		lblUsuario.setBounds(810, 34, 82, 23);
		contentPane.add(lblUsuario);
	}
}
