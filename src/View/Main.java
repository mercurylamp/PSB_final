package View;

import Model.AccountManager;

public class Main {
	public static void main(String args[]) {
		AccountManager am = new AccountManager();
		new loginDialog(am);
	}
}