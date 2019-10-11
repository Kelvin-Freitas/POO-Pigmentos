package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.PigmentoException;
import session.RegistroCompra;

public class Formulario extends JFrame implements ActionListener {

	private RegistroCompra rc;
	
	private String corUser;
	private double litrosUser;
	
    private JLabel lblCorRequerida,lblQuantidadeRequerida,lblCorDisponivel,lblValorTotal;
    //private JLabel lblPadraoCor;
    private JTextField txtCorRequerida, txtQuantidadeRequerida,txtCorDisponivel,txtValorTotal;
    //private JRadioButton rdbRGB, rdbCMYK;
    //private ButtonGroup grpPadraoCor;
    //private JTextArea txtMensagem;
    private JButton btnVerificar, btnCancelar, btnFechar, btnComprar;
    private JPanel pnlCorRequerida,pnlFormulario,pnlFundo;
    //private JPanel pnlPadraoCor;

    public Formulario() {
        setTitle("Formulário de Compra");

        lblCorRequerida = new JLabel("Cor Requerida");
        lblQuantidadeRequerida = new JLabel("Quantidade Requerida");
        //lblPadraoCor = new JLabel("PadraoCor");
        lblCorDisponivel = new JLabel("Cor Disponivel");
        lblValorTotal = new JLabel("Valor Total R$");
         
        txtCorRequerida = new JTextField(20);
        txtQuantidadeRequerida = new JTextField(20);
        txtCorDisponivel = new JTextField(20);
        txtCorDisponivel.setEditable(false);
        txtValorTotal = new JTextField(20);
        txtValorTotal.setEditable(false);
        
        //rdbRGB = new JRadioButton("RGB");
        //rdbCMYK = new JRadioButton("CMYK");
        //grpPadraoCor = new ButtonGroup();
        //grpPadraoCor.add(rdbRGB);
        //grpPadraoCor.add(rdbCMYK);

        //txtMensagem = new JTextArea(10, 40);
        btnVerificar = new JButton("Verificar");
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnFechar = new JButton("Fechar");
        btnComprar = new JButton("Comprar");
        btnComprar.setBackground(new java.awt.Color(0, 251, 127));

        pnlCorRequerida = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlCorRequerida.setLayout(new GridLayout(4, 2));
        pnlCorRequerida.add(lblCorRequerida);
        pnlCorRequerida.add(txtCorRequerida);
        pnlCorRequerida.add(lblQuantidadeRequerida);
        pnlCorRequerida.add(txtQuantidadeRequerida);
        pnlCorRequerida.add(lblCorDisponivel);
        pnlCorRequerida.add(txtCorDisponivel);
        pnlCorRequerida.add(lblValorTotal);
        pnlCorRequerida.add(txtValorTotal);

        //pnlPadraoCor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //pnlPadraoCor.add(lblPadraoCor);
        //pnlPadraoCor.add(rdbRGB);
        //pnlPadraoCor.add(rdbCMYK);


        pnlFormulario = new JPanel(new GridLayout(5, 1));
        pnlFormulario.add(pnlCorRequerida);

        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(btnVerificar);
        pnlBotoes.add(btnCancelar);
        pnlBotoes.add(btnComprar);
        pnlBotoes.add(btnFechar);
        
        pnlFundo = new JPanel();
        pnlFundo.add(pnlFormulario);

        Container c = getContentPane();
        c.add(pnlFormulario, BorderLayout.NORTH);
        c.add(pnlBotoes, BorderLayout.CENTER);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnVerificar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnFechar.addActionListener(this);
        btnComprar.addActionListener(this);
    }
    
    private void limparCampos() {
    	this.txtCorRequerida.setText("");
        this.txtQuantidadeRequerida.setText("");
        this.txtCorDisponivel.setText("");
        this.txtValorTotal.setText("");
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        Object botao = e.getSource();

        if (botao == btnFechar) {
            System.exit(0);
        } else if (botao == btnCancelar) {
            this.limparCampos();
            //grpPadraoCor.clearSelection();
            //txtMensagem.setText("");
        } else if (botao == btnVerificar) {
            //String mensagem = "";
            //mensagem += "CorRequerida: " + txtCorRequerida.getText() + "\n";
            //mensagem += "Cor_Requerida: " + (rdbRGB.isSelected() ? "RGB" : (rdbCMYK.isSelected() ? "CMYK" : "")) + "\n";
            //txtMensagem.setText(mensagem);
        	if(!(txtCorRequerida.getText()==null) || !(txtQuantidadeRequerida.getText()==null)) {
        		this.litrosUser = Double.parseDouble(txtQuantidadeRequerida.getText());
            	try {
    				this.corUser = this.rc.buscarCor(txtCorRequerida.getText(), this.litrosUser);
    				double valor = this.rc.getValor(corUser, this.litrosUser);
    				this.txtCorDisponivel.setText(corUser);
    				this.txtValorTotal.setText(Double.toString(valor));
    			} catch (PigmentoException e1) {
    				System.err.println("Não encontrado!" + e1);
    			}
        	}
        } else if (botao == btnComprar) {
        	if(!(txtCorRequerida.getText()==null) || !(txtQuantidadeRequerida.getText()==null)) {
        		try {
    				this.rc.registrarCompra(corUser, litrosUser);
    				JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
    				this.limparCampos();
    			} catch (PigmentoException e1) {
    				System.err.println("Erro na ordem de compra!" + e1);
    			}
        	}
        }
    }
    
    public void run() throws ClassNotFoundException, IOException {
    	this.setVisible(true);
    	this.setLocationRelativeTo(null);
    	this.rc = new RegistroCompra ();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	(new Formulario()).run();
    }
}