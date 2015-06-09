package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class AccountManager {
	private String id = "admin";
	private char[] passwd = { '1', '1', '1', '1' };
	private ArrayList<Account> account = new ArrayList<Account>();
	private HashSet<String> nums = new HashSet<String>();

	public AccountManager() {
		openFile();
	}

	public void addAccount(String name, int grade) {
		Random rand = new Random();
		String num = "110-";
		num += rand.nextInt(999);
		num += "-";
		num += rand.nextInt(999999);
		while (nums.contains(num)) {
			num = "110-";
			num += rand.nextInt(999);
			num += "-";
			num += rand.nextInt(999999);
		}
		nums.add(num);
		account.add(new Account(num, name, 0, grade));
	}

	public void removeAccount(int index) {
		nums.remove(account.get(index).getNum());
		account.remove(index);
	}

	public void deposit(int index, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(index).addAmount(money);
		account.get(index).addList(today + money + "원 입금");
	}

	public void draw(int index, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(index).drawAmount(money);
		account.get(index).addList(today + money + "원 출금");
	}

	public void transfer(int from, int to, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(from).drawAmount(money);
		account.get(to).addAmount(money);
		account.get(from).addList(
				today + account.get(to).getName() + "님께 " + money + "원 송금");
		account.get(to).addList(
				today + account.get(from).getName() + "님께서 " + money + "원 송금");
	}

	public void interest() {
		for (int i = 0; i < account.size(); i++) {
			int amount = account.get(i).getAmount();
			int grade = account.get(i).getGrade();
			if (amount < 0)
				continue;
			if (grade == 1) {
				account.get(i).addAmount((int) (amount * 0.05));
			} else if (grade == 2) {
				account.get(i).addAmount((int) (amount * 0.03));
			} else if (grade == 3) {
				account.get(i).addAmount((int) (amount * 0.01));
			}
			SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
					Locale.KOREA);
			String today = date.format(new Date());
			account.get(i).addList(today + (int) (amount * 0.05) + "원 이자 발급");
		}
	}

	public void findAccount(int select, String s, DefaultTableModel model) {
		ArrayList<Vector> top = new ArrayList<Vector>();
		ArrayList<Vector> bottom = new ArrayList<Vector>();
		for (int i = 0; i < account.size(); i++) {
			Vector tmp = new Vector();
			tmp.addElement(account.get(i).getNum());
			tmp.addElement(account.get(i).getName());
			tmp.addElement(account.get(i).getAmount());
			tmp.addElement(account.get(i).getGrade());
			if (select == 0) {
				if (account.get(i).getNum().equals(s))
					top.add(tmp);
				else
					bottom.add(tmp);
			} else if (select == 1) {
				if (account.get(i).getName().equals(s))
					top.add(tmp);
				else
					bottom.add(tmp);
			} else if (select == 2) {
				if (account.get(i).getAmount() == Integer.parseInt(s))
					top.add(tmp);
				else
					bottom.add(tmp);
			} else if (select == 3) {
				if (account.get(i).getGrade() == Integer.parseInt(s))
					top.add(tmp);
				else
					bottom.add(tmp);
			}
		}
		model.setRowCount(0);
		for (int i = 0; i < top.size(); i++) {
			model.addRow(top.get(i));
		}
		for (int i = 0; i < bottom.size(); i++) {
			model.addRow(bottom.get(i));
		}
	}

	public boolean loginCheck(String id, char[] passwd) {
		if (this.id.equals(id)) {
			int length = passwd.length > this.passwd.length ? this.passwd.length
					: passwd.length;
			for (int i = 0; i < length; i++) {
				if (this.passwd[i] != passwd[i])
					return false;
			}
			return true;
		} else
			return false;
	}

	public ArrayList<Account> getAccounts() {
		return account;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPasswd(char[] passwd) {
		this.passwd = passwd;
	}

	public boolean isDigit(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void saveFile() {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream("bank.dat");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(id);
			oos.writeObject(passwd);
			oos.writeObject(account);
			oos.writeObject(nums);
			oos.reset();
			oos.close();
			fout.close();
		} catch (Exception e) {

		}
	}

	public void openFile() {
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = new FileInputStream("bank.dat");
			ois = new ObjectInputStream(fin);
			id = (String) ois.readObject();
			passwd = (char[]) ois.readObject();
			account = (ArrayList<Account>) ois.readObject();
			nums = (HashSet<String>) ois.readObject();
			ois.close();
			fin.close();
		} catch (Exception e) {

		}
	}
}