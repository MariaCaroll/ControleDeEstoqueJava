package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import componentes.UJTextField;
import controller.ControllerFormaPagamento;
import model.ModelFormaPagamento;
import util.Mascaras;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class viewPagamentoPDV extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JLabel lblData;
	
	ArrayList<ModelFormaPagamento> listaModelFormaPagamentos = new ArrayList<ModelFormaPagamento>();
	ControllerFormaPagamento controllerFormaPagamento = new ControllerFormaPagamento();
	Mascaras mascaras = new Mascaras();
	private float valorTotalPdv;
	private float descontoPDV;
	private float valorRecebido;
	public float valorAPagar;
	private float troco;
	
	private String formaPagamento;
	
	
	public float getValorTotalPdv() {
		return valorTotalPdv;
	}

	public float getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(float valorAPagar) {
		this.valorAPagar = valorAPagar;
	}

	public void setValorTotalPdv(float valorTotalPdv) {
		this.valorTotalPdv = valorTotalPdv;
	}

	public float getDescontoPDV() {
		return descontoPDV;
	}

	public void setDescontoPDV(float descontoPDV) {
		this.descontoPDV = descontoPDV;
	}

	public float getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public float getTroco() {
		return troco;
	}

	public void setTroco(float troco) {
		this.troco = troco;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public void setTextFieldValorTotal() {
		txtSub.setText(String.valueOf(this.valorTotalPdv));
	}
	



	private JComboBox cbFormaPagamento;
	private JTextField txtSub;
	public JTextField txtDesPaga;
	private JTextField txtValorRecebido;
	private JTextField txtTroco;
	private JLabel lblValorPagar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			viewPagamentoPDV dialog = new viewPagamentoPDV();
			dialog.getContentPane().setBackground(new Color(180,22,51));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public viewPagamentoPDV() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				
				calcularPagamento();
			}
		});
		setBounds(100, 100, 492, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().setBackground(new Color(180,22,51));
		contentPanel.setLayout(null);
		setLocationRelativeTo(null); 
		
		
		
		lblValorPagar = new JLabel("0.0");
	
		lblValorPagar.setBackground(new Color(204, 204, 204));
		lblValorPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorPagar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Total a Pagar", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblValorPagar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorPagar.setBounds(131, 198, 261, 82);
		contentPanel.add(lblValorPagar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Troco");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(185, 304, 46, 14);
		contentPanel.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(180,22,51));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(56, 11, 388, 176);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Desconto");
		lblNewLabel_2.setBounds(59, 109, 60, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Valor Recebido");
		lblNewLabel_3.setBounds(29, 145, 90, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Pagamento");
		lblNewLabel.setBounds(55, 68, 64, 14);
		panel.add(lblNewLabel);
		
		cbFormaPagamento = new JComboBox();
		cbFormaPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDesPaga.requestFocus();
			}
		});
		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"", "Dinheiro", "Cart\u00E3o de cr\u00E9dito  1x", "Cart\u00E3o de cr\u00E9dito  2x", "Cart\u00E3o de cr\u00E9dito  3x", "Cart\u00E3o de cr\u00E9dito  4x", "Cart\u00E3o de d\u00E9bito", ""}));
		cbFormaPagamento.setBounds(131, 64, 145, 22);
		panel.add(cbFormaPagamento);
		
		JLabel lblNewLabel_1 = new JLabel("SubTotal");
		lblNewLabel_1.setBounds(59, 31, 60, 16);
		panel.add(lblNewLabel_1);
		
		txtSub = new JTextField();
		txtSub.setEditable(false);
		txtSub.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSub.setBounds(133, 28, 116, 22);
		panel.add(txtSub);
		txtSub.setColumns(10);
		
		txtDesPaga = new JTextField();
		txtDesPaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtValorRecebido.requestFocus();
			}
		});
		txtDesPaga.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				calcularPagamento();
			}
		});
		txtDesPaga.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDesPaga.setColumns(10);
		txtDesPaga.setBounds(131, 102, 116, 22);
		panel.add(txtDesPaga);
		
		txtValorRecebido = new JTextField();
		txtValorRecebido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descontoPDV = Float.parseFloat(txtDesPaga.getText().replace(",", "."));
				valorRecebido = Float.parseFloat(txtValorRecebido.getText());
				troco = Float.parseFloat(txtTroco.getText());
				valorTotalPdv = Float.parseFloat(lblValorPagar.getText());
				formaPagamento = cbFormaPagamento.getSelectedItem().toString();
				limparPagamento();
				dispose();
			
			}
		});
		txtValorRecebido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			
				calcularPagamento();
			}
		});
		txtValorRecebido.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValorRecebido.setColumns(10);
		txtValorRecebido.setBounds(131, 138, 116, 22);
		panel.add(txtValorRecebido);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(6, 372, 464, 38);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limparPagamento();
						dispose();
					}
				});
				cancelButton.setBounds(370, 6, 80, 28);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			JButton btnNewButton = new JButton("finalizar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					descontoPDV = Float.parseFloat(txtDesPaga.getText().replace(",", "."));
					valorRecebido = Float.parseFloat(txtValorRecebido.getText());
					troco = Float.parseFloat(txtTroco.getText());
					valorTotalPdv = Float.parseFloat(lblValorPagar.getText());
					formaPagamento = cbFormaPagamento.getSelectedItem().toString();
					limparPagamento();
					dispose();
				}
			});
			btnNewButton.setBounds(268, 6, 90, 28);
			buttonPane.add(btnNewButton);
		}
		
		lblData = new JLabel("");
		lblData.setBounds(6, 346, 96, 16);
		contentPanel.add(lblData);
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		lblData.setText(formato.format(data));
		
		txtTroco = new JTextField();
		txtTroco.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		txtTroco.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTroco.setEditable(false);
		txtTroco.setColumns(10);
		txtTroco.setBounds(235, 292, 81, 38);
		contentPanel.add(txtTroco);
	}



	
//	private void listarFormaPagamento() {
//		listaModelFormaPagamentos = controllerFormaPagamento.getListaFormaPagamentoController();
//		cbFormaPagamento.removeAllItems();
//		for( int i = 0; i< listaModelFormaPagamentos.size(); i++) {
//			cbFormaPagamento.addItem(listaModelFormaPagamentos.get(i).getDescricaoForPag());
//		}
//	}
	

	private void calcularPagamento() {
	
		valorTotalPdv = Float.parseFloat(txtSub.getText());
		
		try {
			descontoPDV = Float.parseFloat(txtDesPaga.getText().replace(",", "."));
			
		} catch (Exception e) {
			descontoPDV = 0;
			txtDesPaga.setText("0");
		}
	
			
		try {
			valorRecebido = Float.parseFloat(txtValorRecebido.getText().replace(",", "."));
		} catch (Exception e) {
			valorRecebido = 0;
		}
		
		try {
			valorRecebido = Float.parseFloat(txtValorRecebido.getText().replace(",", "."));
		} catch (Exception e) {
			valorRecebido = 0;
			txtValorRecebido.setText("0");
		}
		valorAPagar = valorTotalPdv - descontoPDV;
		lblValorPagar.setText(mascaras.arredondamentoComPontoDuasCasasString(valorAPagar));
		
		troco = valorRecebido - valorAPagar;
		txtTroco.setText(mascaras.arredondamentoComPontoDuasCasasString(troco));
	}
	
	
	
	
	private void limparPagamento() {
		txtSub.setText(null);
		txtDesPaga.setText(null);
		txtValorRecebido.setText(null);
		txtTroco.setText(null);
	}
}
