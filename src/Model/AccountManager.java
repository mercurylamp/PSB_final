package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class AccountManager {
	private Id id = new Id();
	private ArrayList<Account> account = new ArrayList<Account>();
	private HashSet<String> nums = new HashSet<String>();
	private HashMap<Account, statement> states = new HashMap<Account, statement>();

	public AccountManager() {
		openFile();
	}

	public void addAccount(String name, int grade) {
		Random rand = new Random();
		String num = "110-";
		for (int i=0;i<3;i++)
			num += String.valueOf(rand.nextInt(9));
		num += "-";
		for (int i=0;i<6;i++)
			num += String.valueOf(rand.nextInt(9));
		while (nums.contains(num)) {
			num = "110-";
			for (int i=0;i<3;i++)
				num += String.valueOf(rand.nextInt(9));
			num += "-";
			for (int i=0;i<6;i++)
				num += String.valueOf(rand.nextInt(9));
		}
		nums.add(num);
		Account tmp = new Account(num, name, 0, grade);
		account.add(tmp);
		states.put(tmp, new statement());
	}

	public void removeAccount(int index) {
		nums.remove(account.get(index).getNum());
		states.remove(account.get(index));
		account.remove(index);
	}

	public void deposit(int index, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(index).addAmount(money);
		states.get(account.get(index)).addList(today + money + "원 입금/" + account.get(index).getAmount());
	}

	public void draw(int index, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(index).drawAmount(money);
		states.get(account.get(index)).addList(today + money + "원 출금/" + account.get(index).getAmount());
	}

	public void transfer(int from, int to, int money) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		account.get(from).drawAmount(money);
		account.get(to).addAmount(money);
		states.get(account.get(from)).addList(today + account.get(to).getName() + "님께 " + money + "원 송금/" + account.get(from).getAmount());
		states.get(account.get(to)).addList(today + account.get(to).getName() + "님께 " + money + "원 송금/" + account.get(to).getAmount());
	}

	public void interest() {
		for (int i = 0; i < account.size(); i++) {
			int amount = account.get(i).getAmount();
			int grade = account.get(i).getGrade();
			SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
					Locale.KOREA);
					String today = date.format(new Date());
			if (amount < 0)
				continue;
			if (grade == 1) {
				account.get(i).addAmount((int) (amount * 0.05));
				states.get(account.get(i)).addList(today + (int) (amount * 0.05) + "원 이자 발급/" + account.get(i).getAmount());
			} else if (grade == 2) {
				account.get(i).addAmount((int) (amount * 0.03));
				states.get(account.get(i)).addList(today + (int) (amount * 0.03) + "원 이자 발급/" + account.get(i).getAmount());
			} else if (grade == 3) {
				account.get(i).addAmount((int) (amount * 0.01));
				states.get(account.get(i)).addList(today + (int) (amount * 0.03) + "원 이자 발급/" + account.get(i).getAmount());
			}
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
		if (this.id.getId().equals(id)) {
			if (encrypt(String.valueOf(passwd)).equals(this.id.getPasswd())) 
				return true;
			else
				return false;
		} else
			return false;
	}

	public ArrayList<Account> getAccounts() {
		return account;
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
			oos.writeObject(account);
			oos.writeObject(nums);
			oos.writeObject(states);
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
			id = (Id) ois.readObject();
			account = (ArrayList<Account>) ois.readObject();
			nums = (HashSet<String>) ois.readObject();
			states = (HashMap<Account, statement>) ois.readObject();
			ois.close();
			fin.close();
		} catch (Exception e) {

		}
	}
	
	public String encrypt(String planText) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(planText.getBytes());
			byte byteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<byteData.length;i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<byteData.length;i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length()==1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public HashSet<String> getNums() {
		return nums;
	}
	
	public Id getId() {
		return id;
	}
	
	public HashMap<Account, statement> getStatement() {
		return states;
	}
}