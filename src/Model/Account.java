package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class Account implements Serializable {
	private static final long serialVersionUID = 123456789L;
	private String num;
	private String name;
	private int amount;
	private int grade;
	private ArrayList<String> list;
	
	public Account(String num, String name, int amount, int grade) {
		this.num = num;
		this.name = name;
		this.amount = amount;
		this.grade = grade;
		list = new ArrayList<String>();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/", Locale.KOREA);
		String today = date.format(new Date());
		addList(today + "계좌 개설");
	}
	
	public void addAmount(int money) {
		this.amount += money;
	}
	
	public void drawAmount(int money) {
		this.amount -= money;
	}
	
	public void addList(String detail) {
		list.add(detail);
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getNum() {
		return this.num;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public int getGrade() {
		return this.grade;
	}
	
	public ArrayList<String> getList() {
		return this.list;
	}
	
	public Object[] toModel() {
		Object[] list = {getNum(), getName(), getAmount(), getGrade()};
		return list;
	}
	
	public Object[] toModel(int index) {
		String[] ex = this.list.get(index).split("/");
		Object[] list = {ex[0], ex[1]};
		return list;
	}
}