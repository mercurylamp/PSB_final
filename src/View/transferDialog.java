package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Account;
import Model.AccountManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class transferDialog extends JDialog implements ActionListener {
	private JTextField numTf;
	private JTextField nameTf;
	private JTextField amountTf;
	private JTextField gradeTf;
	private JTextField moneyTf;
	private AccountManager am;
	private int index;
	private JTable table;
	
	public transferDialog(AccountManager am, int index) {
		setTitle("송금");
		this.am = am;
		this.index = index;
		Account tmp = am.getAccounts().get(index);
		String num = tmp.getNum();
		String name = tmp.getName();
		int amount = tmp.getAmount();
		int grade = tmp.getGrade();
		
		setSize(550, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("계좌번호");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 49, 85, 16);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("계좌주");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 77, 85, 16);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("잔액");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(31, 105, 85, 16);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("등급");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(31, 133, 85, 16);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("송금하실 금액");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(31, 161, 85, 16);
		getContentPane().add(label_4);
		
		numTf = new JTextField();
		numTf.setEditable(false);
		numTf.setBounds(128, 43, 134, 28);
		getContentPane().add(numTf);
		numTf.setColumns(10);
		numTf.setText(num);
		
		nameTf = new JTextField();
		nameTf.setEditable(false);
		nameTf.setBounds(128, 71, 134, 28);
		getContentPane().add(nameTf);
		nameTf.setColumns(10);
		nameTf.setText(name);
		
		amountTf = new JTextField();
		amountTf.setEditable(false);
		amountTf.setBounds(128, 99, 134, 28);
		getContentPane().add(amountTf);
		amountTf.setColumns(10);
		amountTf.setText(String.valueOf(amount));
		
		gradeTf = new JTextField();
		gradeTf.setEditable(false);
		gradeTf.setBounds(128, 127, 134, 28);
		getContentPane().add(gradeTf);
		gradeTf.setColumns(10);
		gradeTf.setText(String.valueOf(grade));
		
		moneyTf = new JTextField();
		moneyTf.setBounds(128, 155, 134, 28);
		getContentPane().add(moneyTf);
		moneyTf.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 25, 244, 226);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton transferBtn = new JButton("송금");
		transferBtn.addActionListener(this);
		transferBtn.setBounds(84, 211, 117, 29);
		getContentPane().add(transferBtn);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}