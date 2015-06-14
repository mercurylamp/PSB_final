package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class statement implements Serializable {
	private static final long serialVersionUID = 123456787L;
	private ArrayList<String> list;
	
	public statement() {
		this.list = new ArrayList<String>();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일/",
				Locale.KOREA);
		String today = date.format(new Date());
		addList(today + "계좌 개설/" + 0);
	}
	
	public ArrayList<String> getList() {
		return list;
	}

	public void addList(String detail) {
		list.add(detail);
	}
	
	public Object[] toModel(int index) {
		String[] ex = this.list.get(index).split("/");
		Object[] list = { ex[0], ex[1], ex[2] };
		return list;
	}
}