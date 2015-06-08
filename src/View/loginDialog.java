package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.AccountManager;

public class loginDialog extends JDialog implements ActionListener {
	private JTextField idTf;
	private JPasswordField passwdTf;
	private JButton LoginBtn;
	private AccountManager am;
	public loginDialog(AccountManager am) {
		this.am = am;
		setSize(250, 140);
		setTitle("로그인");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		
		LoginBtn = new JButton("로그인");
		LoginBtn.addActionListener(this);
		LoginBtn.setBounds(64, 76, 117, 29);
		getContentPane().add(LoginBtn);
		
		passwdTf = new JPasswordField();
		passwdTf.setBounds(128, 42, 105, 28);
		getContentPane().add(passwdTf);
		
		Dimension dialogSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - dialogSize.width)/2, (screenSize.height - dialogSize.height)/2);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (am.loginCheck(idTf.getText(), passwdTf.getPassword())) {
			new MainFrame(am);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "ID 혹은 PASSWORD가 잘못됐습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
}