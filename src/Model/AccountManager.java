package Model;

import java.util.ArrayList;

public class AccountManager {
	private String id = "admin";
	private char[] passwd = {'1', '1', '1', '1'};
	private ArrayList<Account> account;
	private int count = 0;
	
	public void addAccount(String name, int grade) {
		account.add(new Account(String.valueOf(count),name,0,grade));
	}
	
	public void removeAccount(int index) {
		account.remove(index);
	}
	
	public void deposit(int index, int money) {
		account.get(index).addAmount(money);
	}
	
	public void draw(int index, int money) {
		account.get(index).drawAmount(money);
	}
	
	public void transfer(int from, int to, int money) {
		account.get(from).drawAmount(money);
		account.get(to).addAmount(money);
	}
	
	public void interest(int index) {
		int amount = account.get(index).getAmount();
		int grade = account.get(index).getGrade();
		if (grade == 1) {
			
		} else if (grade == 2) {
			
		} else if (grade == 3) {
			
		}
	}
	
	public void findAccount() {
		
	}
	
	public boolean loginCheck(String id, char[] passwd) {
		if (this.id.equals(id)) {
			int length = passwd.length > this.passwd.length ? this.passwd.length : passwd.length;
			for (int i=0;i<length;i++) {
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
}