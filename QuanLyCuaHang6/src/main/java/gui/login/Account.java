package gui.login;

public class Account {
	private String user;
	private char[] password;
	private String pos;
	
	public Account(String user, char[] password) {
		this.user = user;
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}

	public char[] getPassword() {
		return password;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getPos() {
		return pos;
	}
}
