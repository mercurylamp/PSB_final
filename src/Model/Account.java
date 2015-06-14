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

	public Account(String num, String name, int amount, int grade) {
		this.num = num;
		this.name = name;
		this.amount = amount;
		this.grade = grade;
	}

	public void addAmount(int money) {
		this.amount += money;
	}

	public void drawAmount(int money) {
		this.amount -= money;
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

	public Object[] toModel() {
		Object[] list = { getNum(), getName(), getAmount(), getGrade() };
		return list;
	}
}