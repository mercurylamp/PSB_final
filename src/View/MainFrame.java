package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.AccountManager;

public class MainFrame extends JFrame implements ActionListener {
	private JTable table;
	private JTextField findaTf;
	private JTextField findbTf;
	private JTextArea expensestA;
	private AccountManager am;
	public MainFrame(AccountManager am) {
		this.am = am;
		setSize(650, 500);
		setTitle("PSB 은행");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);
		getContentPane().setLayout(null);
		
		JButton findaBtn = new JButton("검색");
		findaBtn.setBounds(225, 155, 62, 29);
		getContentPane().add(findaBtn);
		
		JButton findbBtn = new JButton("검색");
		findbBtn.setBounds(532, 155, 75, 29);
		getContentPane().add(findbBtn);
		
		findaTf = new JTextField();
		findaTf.setBounds(313, 155, 212, 28);
		getContentPane().add(findaTf);
		findaTf.setColumns(10);
		
		findbTf = new JTextField();
		findbTf.setBounds(41, 155, 179, 28);
		getContentPane().add(findbTf);
		findbTf.setColumns(10);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new TitledBorder(null, "\uBA54\uB274", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		menuPanel.setBounds(125, 31, 390, 96);
		getContentPane().add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton newaccountBtn = new JButton("계좌 개설");
		newaccountBtn.setBounds(16, 26, 117, 29);
		menuPanel.add(newaccountBtn);
		
		JButton removeaccountBtn = new JButton("계좌 폐쇄");
		removeaccountBtn.setBounds(135, 26, 117, 29);
		menuPanel.add(removeaccountBtn);
		
		JButton depositBtn = new JButton("입금");
		depositBtn.setBounds(259, 26, 117, 29);
		menuPanel.add(depositBtn);
		
		JButton drawingBtn = new JButton("출금");
		drawingBtn.setBounds(16, 57, 117, 29);
		menuPanel.add(drawingBtn);
		
		JButton transferBtn = new JButton("송금");
		transferBtn.setBounds(135, 57, 117, 29);
		menuPanel.add(transferBtn);
		
		JButton interestBtn = new JButton("이자 발급");
		interestBtn.setBounds(259, 57, 117, 29);
		menuPanel.add(interestBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 191, 249, 249);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(313, 192, 294, 248);
		getContentPane().add(scrollPane_1);
		
		expensestA = new JTextArea();
		scrollPane_1.setViewportView(expensestA);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String text = btn.getText();
	}
}