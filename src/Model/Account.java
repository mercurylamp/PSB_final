package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
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
}