package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.AccountManager;

public class addAccountDialog extends JDialog implements ActionListener {
	private JTextField textField;
	private JComboBox comboBox;
	private JButton openBtn;
	private AccountManager am;
	private DefaultTableModel model;
	private String[] combobox = { "1", "2", "3" };

	public addAccountDialog(AccountManager am, DefaultTableModel model) {
		setTitle("계좌 개설");
		this.am = am;
		this.model = model;
		setSize(250, 140);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("이름");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(31, 20, 85, 16);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("등급");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(31, 48, 85, 16);
		getContentPane().add(label_1);

		comboBox = new JComboBox(combobox);
		comboBox.setBounds(128, 42, 105, 28);
		getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(128, 14, 105, 28);
		getContentPane().add(textField);
		textField.setColumns(10);

		openBtn = new JButton("개설");
		openBtn.addActionListener(this);
		openBtn.setBounds(64, 76, 117, 29);
		getContentPane().add(openBtn);

		Dimension dialogSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - dialogSize.width) / 2,
				(screenSize.height - dialogSize.height) / 2);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		am.addAccount(textField.getText(),
				Integer.parseInt(combobox[comboBox.getSelectedIndex()]));

		model.setRowCount(0);
		for (int i = 0; i < am.getAccounts().size(); i++) {
			model.addRow(am.getAccounts().get(i).toModel());
		}
		dispose();
	}
}