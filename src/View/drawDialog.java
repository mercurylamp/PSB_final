package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Account;
import Model.AccountManager;

public class drawDialog extends JDialog implements ActionListener {
	private JTextField numTf;
	private JTextField nameTf;
	private JTextField amountTf;
	private JTextField gradeTf;
	private JTextField moneyTf;
	private AccountManager am;
	private DefaultTableModel model;
	private int index;
	private int amount;
	private int grade;
	
	public drawDialog(AccountManager am, int index, DefaultTableModel model) {
		setTitle("출금");
		this.am = am;
		this.model = model;
		this.index = index;
		Account tmp = am.getAccounts().get(index);
		String num = tmp.getNum();
		String name = tmp.getName();
		this.amount = tmp.getAmount();
		this.grade = tmp.getGrade();
		
		setSize(300, 220);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("계좌번호");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 20, 85, 16);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("예금주");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 48, 85, 16);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("잔액");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(31, 76, 85, 16);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("등급");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(31, 104, 85, 16);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("출금하실 금액");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(31, 132, 85, 16);
		getContentPane().add(label_4);
		
		numTf = new JTextField();
		numTf.setEditable(false);
		numTf.setBounds(128, 14, 134, 28);
		getContentPane().add(numTf);
		numTf.setColumns(10);
		numTf.setText(num);
		
		nameTf = new JTextField();
		nameTf.setEditable(false);
		nameTf.setBounds(128, 42, 134, 28);
		getContentPane().add(nameTf);
		nameTf.setColumns(10);
		nameTf.setText(name);
		
		amountTf = new JTextField();
		amountTf.setEditable(false);
		amountTf.setBounds(128, 70, 134, 28);
		getContentPane().add(amountTf);
		amountTf.setColumns(10);
		amountTf.setText(String.valueOf(amount));
		
		gradeTf = new JTextField();
		gradeTf.setEditable(false);
		gradeTf.setBounds(128, 98, 134, 28);
		getContentPane().add(gradeTf);
		gradeTf.setColumns(10);
		gradeTf.setText(String.valueOf(grade));
		
		moneyTf = new JTextField();
		moneyTf.setBounds(128, 126, 134, 28);
		getContentPane().add(moneyTf);
		moneyTf.setColumns(10);
		
		JButton addmoneyBtn = new JButton("출금");
		addmoneyBtn.addActionListener(this);
		addmoneyBtn.setBounds(90, 160, 117, 29);
		getContentPane().add(addmoneyBtn);
		
		Dimension dialogSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - dialogSize.width)/2, (screenSize.height - dialogSize.height)/2);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (am.isDigit(moneyTf.getText()) && Integer.parseInt(moneyTf.getText()) >= 0) {
			if (amount >= Integer.parseInt(moneyTf.getText())
					|| (amount < Integer.parseInt(moneyTf.getText())
							&& grade == 1 && amount
							- Integer.parseInt(moneyTf.getText()) >= -10000000)) {
				am.draw(index, Integer.parseInt(moneyTf.getText()));

				model.setRowCount(0);
				for (int i = 0; i < am.getAccounts().size(); i++) {
					model.addRow(am.getAccounts().get(i).toModel());
				}
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "잔액이 부족합니다.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "입력이 잘못됐습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
}