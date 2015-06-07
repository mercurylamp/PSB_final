package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MainFrame extends JFrame {
	private JTable table;
	private JTextField findaTf;
	private JTextField findbTf;
	private JTextArea expensestA;
	private loginDialog login = new loginDialog();
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		login.setVisible(true);
		
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
	}
	
	public static void main(String args[]) {
		new MainFrame();
	}
}