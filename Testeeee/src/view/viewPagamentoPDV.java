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
	private float valorTotalPdv;
	private float desconto;
	private float valorRecebido;
	private float troco;
	private String formaPagamento;
	private boolean pago;
	
	public float getValorTotalPdv() {
		return valorTotalPdv;
	}

	public void setValorTotalPdv(float valorTotalPdv) {
		this.valorTotalPdv = valorTotalPdv;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
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


	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}




	private JComboBox cbFormaPagamento;
	private JTextField txtSub;
	private JTextField txtDesconto;
	private JTextField txtValorRecebido;
	private JTextField txtTroco;
	private JLabel lblValorPagar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			viewPagamentoPDV dialog = new viewPagamentoPDV();
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
				
				listarFormaPagamento();
				calcularPagamento();
				pago = false;
			}
		});
		setBounds(100, 100, 492, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null); 
		
		
		
		lblValorPagar = new JLabel("0.0");
	
		lblValorPagar.setBackground(new Color(204, 204, 204));
		lblValorPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorPagar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Total a Pagar", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblValorPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValorPagar.setBounds(131, 198, 261, 82);
		contentPanel.add(lblValorPagar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Troco");
		lblNewLabel_1_1.setBounds(20, 307, 46, 14);
		contentPanel.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
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
		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"", "Dinheiro", "Cart\u00E3o de cr\u00E9dito  1x", "Cart\u00E3o de cr\u00E9dito  2x", "Cart\u00E3o de cr\u00E9dito  3x", "Cart\u00E3o de cr\u00E9dito  4x", "Cart\u00E3o de d\u00E9bito", "", ""}));
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
		
		txtDesconto = new JTextField();
		txtDesconto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularPagamento();
			}
		});
		txtDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDesconto.setColumns(10);
		txtDesconto.setBounds(131, 102, 116, 22);
		panel.add(txtDesconto);
		
		txtValorRecebido = new JTextField();
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
			buttonPane.setBounds(6, 324, 476, 38);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						desconto = Float.parseFloat(txtDesconto.getText().replace(",", "."));
						valorRecebido = Float.parseFloat(txtValorRecebido.getText());
						troco = Float.parseFloat(txtTroco.getText());
						valorTotalPdv = Float.parseFloat(lblValorPagar.getText());
						formaPagamento = cbFormaPagamento.getSelectedItem().toString();
						pago = true;
						
						dispose();
					
					}
		
					
				});
				okButton.setBounds(315, 6, 52, 28);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(372, 6, 80, 28);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		lblData = new JLabel("");
		lblData.setBounds(6, 346, 96, 16);
		contentPanel.add(lblData);
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		lblData.setText(formato.format(data));
		
		txtTroco = new JTextField();
		
		txtTroco.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTroco.setEditable(false);
		txtTroco.setColumns(10);
		txtTroco.setBounds(56, 303, 61, 22);
		contentPanel.add(txtTroco);
	}



	
	private void listarFormaPagamento() {
		listaModelFormaPagamentos = controllerFormaPagamento.getListaFormaPagamentoController();
		cbFormaPagamento.removeAllItems();
		for( int i = 0; i< listaModelFormaPagamentos.size(); i++) {
			cbFormaPagamento.addItem(listaModelFormaPagamentos.get(i).getDescricaoForPag());
		}
	}
	
	private void calcularPagamento() {
		float subtTotal;
		float desconto;
		float valorRecebido;
		float valorPagar;
		float troco;
		
		subtTotal = Float.parseFloat(txtSub.getText());
		if(!txtDesconto.getText().isEmpty()) {
			desconto = Float.parseFloat(txtDesconto.getText().replace(",", "."));
		}else {
			desconto = 0;
		}
		if(!txtValorRecebido.getText().isEmpty()) {
			valorRecebido = Float.parseFloat(txtValorRecebido.getText().replace(",", "."));
		}else {
			valorRecebido = 0;
		}
	
		
		// calcular valor a pagar
		valorPagar = subtTotal - desconto;
		lblValorPagar.setText(String.valueOf(valorPagar));
		//calcular troco
		troco = valorRecebido - valorPagar;
		txtTroco.setText(String.valueOf(troco));
	}
}
