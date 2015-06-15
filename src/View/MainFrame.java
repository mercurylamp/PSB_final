package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Model.AccountManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;

public class MainFrame extends JFrame implements ActionListener {
	private JTable table;
	private DefaultTableModel model;
	private DefaultTableModel model_1;
	private TableRowSorter sorter;
	private TableRowSorter sorter_1;
	private String[] columnNames = { "계좌번호", "예금주", "잔액", "등급" };
	private String[] columnNames_1 = { "날짜", "내역", "잔액" };
	private JTextField findbTf;
	private JTextField findaTf;
	private AccountManager am;
	private JTable table_1;

	public MainFrame(final AccountManager am) {
		this.am = am;
		setSize(800, 500);
		setTitle("PSB 은행");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				am.saveFile();
				dispose();
			}
		});
		setResizable(false);
		getContentPane().setLayout(null);

		findbTf = new JTextField();
		findbTf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String filter = findbTf.getText();
				if (filter.length() == 0) {
					sorter_1.setRowFilter(null);
				} else {
					try {
						sorter_1.setRowFilter(RowFilter.regexFilter(filter));
					} catch (PatternSyntaxException pse) {
						return;
					}
				}
			}
		});
		findbTf.setBounds(451, 157, 303, 28);
		getContentPane().add(findbTf);
		findbTf.setColumns(10);

		findaTf = new JTextField();
		findaTf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String filter = findaTf.getText();
				if (filter.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(filter));
					} catch (PatternSyntaxException pse) {
						return;
					}
				}
			}
		});
		findaTf.setBounds(92, 157, 283, 28);
		getContentPane().add(findaTf);
		findaTf.setColumns(10);

		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new TitledBorder(null, "\uBA54\uB274",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		menuPanel.setBounds(204, 28, 390, 96);
		getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		JButton newaccountBtn = new JButton("계좌 개설");
		newaccountBtn.addActionListener(this);
		newaccountBtn.setBounds(16, 26, 117, 29);
		menuPanel.add(newaccountBtn);

		JButton removeaccountBtn = new JButton("계좌 폐쇄");
		removeaccountBtn.addActionListener(this);
		removeaccountBtn.setBounds(135, 26, 117, 29);
		menuPanel.add(removeaccountBtn);

		JButton depositBtn = new JButton("입금");
		depositBtn.addActionListener(this);
		depositBtn.setBounds(259, 26, 117, 29);
		menuPanel.add(depositBtn);

		JButton drawingBtn = new JButton("출금");
		drawingBtn.addActionListener(this);
		drawingBtn.setBounds(16, 57, 117, 29);
		menuPanel.add(drawingBtn);

		JButton transferBtn = new JButton("송금");
		transferBtn.addActionListener(this);
		transferBtn.setBounds(135, 57, 117, 29);
		menuPanel.add(transferBtn);

		JButton interestBtn = new JButton("이자 발급");
		interestBtn.addActionListener(this);
		interestBtn.setBounds(259, 57, 117, 29);
		menuPanel.add(interestBtn);

		model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		sorter = new TableRowSorter(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selection = table.getSelectedRow();
				int real_selection = table.convertRowIndexToModel(selection);
				model_1.setRowCount(0);
				for (int i = 0; i < am.getStatement().get(am.getAccounts().get(real_selection))
						.getList().size(); i++) {
					model_1.addRow(am.getStatement().get(am.getAccounts().get(real_selection))
							.toModel(i));
				}
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);

		model_1 = new DefaultTableModel(columnNames_1, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		sorter_1 = new TableRowSorter(model_1);
		table_1 = new JTable(model_1);
		table_1.setRowSorter(sorter_1);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(110);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(15);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(38, 191, 340, 249);
		getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(400, 192, 361, 248);
		getContentPane().add(scrollPane_1);

		JLabel label = new JLabel("검색");
		label.setBorder(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(38, 159, 51, 24);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("검색");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBorder(null);
		label_1.setBounds(400, 159, 51, 24);
		getContentPane().add(label_1);

		resetTable();

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("메뉴");
		menuBar.add(menu);

		JMenuItem Idmenuitem = new JMenuItem("ID 변경");
		Idmenuitem.addActionListener(this);
		menu.add(Idmenuitem);

		JMenuItem passwdmenuitem = new JMenuItem("비밀번호 변경");
		passwdmenuitem.addActionListener(this);
		menu.add(passwdmenuitem);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand();
		if (text.equals("ID 변경"))
			new idDialog(am);
		else if (text.equals("비밀번호 변경"))
			new passwdDialog(am);
		else if (text.equals("계좌 개설")) {
			if (am.getNums().size() == 1000000000000l) {
				JOptionPane.showMessageDialog(null, "계좌를 개설할 수 없습니다.",
						"오류", JOptionPane.ERROR_MESSAGE);
			} else
				new addAccountDialog(am, model);
		}
		else if (text.equals("이자 발급")) {
			am.interest();
			resetTable();
		} else {
			int selection = table.getSelectedRow();
			if (selection != -1) {
				int real_selection = table.convertRowIndexToModel(selection);
				if (text.equals("계좌 폐쇄")) {
					am.removeAccount(real_selection);
					model.removeRow(real_selection);
				} else if (text.equals("입금")) {
					new depositDialog(am, real_selection, model);
				} else if (text.equals("출금")) {
					new drawDialog(am, real_selection, model);
				} else if (text.equals("송금")) {
					new transferDialog(am, real_selection, model);
				}
			} else {
				JOptionPane.showMessageDialog(null, "계좌를 선택해주세요.", "오류",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void resetTable() {
		model.setRowCount(0);
		for (int i = 0; i < am.getAccounts().size(); i++) {
			model.addRow(am.getAccounts().get(i).toModel());
		}
	}
}