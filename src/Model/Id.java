package Model;

import java.io.Serializable;

public class Id implements Serializable {
	private static final long serialVersionUID = 123456788L;
	private String id = "admin";
	private String passwd = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPasswd() {
		return this.passwd;
	}
}