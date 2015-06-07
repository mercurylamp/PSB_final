package View;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class loginDialog extends JDialog {
	private JTextField idTf;
	private JPasswordField passwdTf;
	private JButton LoginBtn;
	public loginDialog() {
		setSize(250, 140);
		setTitle("PSB 은행");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(31, 20, 85, 16);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(31, 48, 85, 16);
		getContentPane().add(lblPassword);
		
		idTf = new JTextField();
		idTf.setBounds(128, 14, 105, 28);
		getContentPane().add(idTf);
		idTf.setColumns(10);
		
		LoginBtn = new JButton("Login");
		LoginBtn.setBounds(64, 76, 117, 29);
		getContentPane().add(LoginBtn);
		
		passwdTf = new JPasswordField();
		passwdTf.setBounds(128, 42, 105, 28);
		getContentPane().add(passwdTf);
	}
}