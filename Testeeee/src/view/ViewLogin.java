package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerUsuario;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 616, 369);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(331, 105, 72, 14);
		panel.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Serif", Font.BOLD, 14));
		txtLogin.setBounds(331, 130, 140, 20);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(331, 174, 46, 14);
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
		psSenha.setBounds(331, 199, 140, 20);
		panel.add(psSenha);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ViewLogin.class.getResource("/icones/Login_37128.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnNewButton.setBounds(401, 261, 80, 80);
		btnNewButton.setBackground(new Color(0, 0,0,10));
		panel.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Autentique-se");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_2.setBounds(331, 65, 140, 28);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Vendas");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(62, 72, 161, 59);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("BL");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setBounds(62, 145, 161, 59);
		panel.add(lblNewLabel_4);
	}
	
	public void logar() {
		modelUsuario.setUsuLogin(txtLogin.getText());
		modelUsuario.setUsuSenha(String.valueOf(psSenha.getPassword()));
		
		if(controllerUsuario.getValidarUsuarioController(modelUsuario)) {
			ViewPrincipal principal = new ViewPrincipal();
			principal.setVisible(true);
			//principal.lblUsuario.setText();
			this.dispose();
			
		}else {
			JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
