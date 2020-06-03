package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexoes.ConexaoMsql;
import controller.ControllerUsuario;
import model.ModelSessaoUsuario;
import model.ModelUsuario;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JLabel lblNewLabel_1;
	private JPasswordField psSenha;
	
	ControllerUsuario controllerUsuario = new ControllerUsuario();
	ModelUsuario modelUsuario = new ModelUsuario();
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
	ConexaoMsql conexao = new ConexaoMsql();
	
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
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
	public ViewLogin() {
		setForeground(new Color(199, 21, 133));
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);  
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 409, 338);
		panel.setBackground(new Color(151,21,52));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(215, 74, 72, 14);
		panel.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Serif", Font.BOLD, 14));
		txtLogin.setBounds(215, 99, 140, 23);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(215, 143, 46, 14);
		panel.add(lblNewLabel_1);
		
		psSenha = new JPasswordField();
		psSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					logar();
				}
			}
		});
		psSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		psSenha.setBounds(215, 168, 140, 23);
		panel.add(psSenha);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ViewLogin.class.getResource("/icones/Login_37128.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnNewButton.setBounds(273, 220, 80, 80);
		btnNewButton.setBackground(new Color(0, 0,0,10));
		panel.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Autentique-se");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 17));
		lblNewLabel_2.setBounds(215, 34, 140, 28);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("SYSTEM");
		lblNewLabel_3.setForeground(Color.PINK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(23, 61, 103, 59);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("ML");
		lblNewLabel_4.setForeground(Color.PINK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setBounds(55, 25, 46, 40);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Write once, ");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setForeground(Color.PINK);
		lblNewLabel_6.setBounds(23, 143, 72, 28);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("run anywhere");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.PINK);
		lblNewLabel_5.setBounds(23, 175, 92, 16);
		panel.add(lblNewLabel_5);
	}
	
	public void logar() {
		modelUsuario.setUsuLogin(txtLogin.getText());
		modelUsuario.setUsuSenha(String.valueOf(psSenha.getPassword()));
		
		if(controllerUsuario.getValidarUsuarioController(modelUsuario)) {
			modelUsuario = controllerUsuario.getUsuarioController(txtLogin.getText());
		
			//nivel de acesso para os tipos de usuarios 
			if(modelUsuario.getUsuNivel().equals("ADMIN")) {
				ViewPrincipal principal = new ViewPrincipal();
				
				principal.setVisible(true);
				//pega o nome do usuário e seta no menu principal
				principal.lblUsu.setText(txtLogin.getText());
				
				principal.lblUsu.setForeground(new Color(189, 42, 11));;
				principal.lblNivel.setText(modelUsuario.getUsuNivel());
				principal.lblNivel.setForeground(new Color(189, 42, 11));
				principal.txtAMsg.setText("Acesso Total");
				principal.txtAMsg.setForeground(new Color(189, 42, 11));
				principal.menuCadastro.setForeground(new Color(189, 42, 11));
				principal.menuVenda.setForeground(new Color(189, 42, 11));
				principal.menuOpcoes.setForeground(new Color(189, 42, 11));
				
				//habilitar os campos
				principal.itemCliente.setEnabled(true);
				principal.itemProd.setEnabled(true);
				principal.itemUsu.setEnabled(true);
				principal.mnVenda.setEnabled(true);
				principal.mnVPDV.setEnabled(true);
				this.dispose();
				
			} else {
				if(modelUsuario.getUsuNivel().equals("ESTOQUE")) {
					ViewPrincipal principal = new ViewPrincipal();
					
					principal.setVisible(true);
					
					//pega o nome do usuário e seta no menu principal
					principal.lblUsu.setText(txtLogin.getText());
					
					principal.lblUsu.setForeground(new Color(11, 23, 137));;
					principal.lblNivel.setText(modelUsuario.getUsuNivel());
					principal.lblNivel.setForeground(new Color(11, 23, 137));
					principal.txtAMsg.setText("Acesso a penas para cadastro de produto, Certo!!   Para Mais informações contate o Gerente");
					principal.txtAMsg.setForeground(new Color(11, 23, 137));
					principal.menuCadastro.setForeground(new Color(11, 23, 137));
					principal.menuVenda.setForeground(new Color(11, 23, 137));
					principal.menuOpcoes.setForeground(new Color(11, 23, 137));
					
					//habilitar os campos
					principal.itemProd.setEnabled(true);
					dispose();
				} else {
					ViewPrincipal principal = new ViewPrincipal();
					
					principal.setVisible(true);
					
					//pega o nome do usuário e seta no menu principal
					principal.lblUsu.setText(txtLogin.getText());
					
					principal.lblUsu.setForeground(new Color(9, 131, 15));;
					principal.lblNivel.setText(modelUsuario.getUsuNivel());
					principal.lblNivel.setForeground(new Color(9, 131, 15));
					principal.txtAMsg.setText("Acesso a penas para cadastro de cliente e efeturar vendas, Certo!!   Para Mais informações contate o Gerente");
					principal.txtAMsg.setForeground(new Color(9, 131, 15));
					principal.menuCadastro.setForeground(new Color(9, 131, 15));
					principal.menuVenda.setForeground(new Color(9, 131, 15));
					principal.menuOpcoes.setForeground(new Color(9, 131, 15));
					
					//habilitar os campos
					principal.mnVenda.setEnabled(true);
					principal.mnVPDV.setEnabled(true);
					principal.itemCliente.setEnabled(true);
					dispose();
				}
			}
	
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
