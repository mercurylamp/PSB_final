package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import Model.AccountManager;

public class passwdDialog extends JDialog implements ActionListener {
	private JPasswordField passwdTf;
	private JButton InputBtn;
	private AccountManager am;
	
	public passwdDialog(AccountManager am) {
		this.am = am;
		setSize(250, 110);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("비밀번호");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(31, 20, 85, 16);
		getContentPane().add(lblId);
		
		passwdTf = new JPasswordField();
		passwdTf.setBounds(128, 14, 105, 28);
		getContentPane().add(passwdTf);
		
		InputBtn = new JButton("변경");
		InputBtn.addActionListener(this);
		InputBtn.setBounds(65, 48, 117, 29);
		getContentPane().add(InputBtn);
		
		Dimension dialogSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - dialogSize.width)/2, (screenSize.height - dialogSize.height)/2);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		am.getId().setPasswd(am.encrypt(String.valueOf(passwdTf.getPassword())));
		dispose();
	}
}
