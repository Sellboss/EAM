package se.sellboss.eam.domain;

import org.springframework.data.annotation.Id;

/**
 * 
 * Domain class mapping to eam.user in MongoDB.
 * 
 * @author Martin
 *
 */
public class User {

	@Id
	private String id;

	private String username;
	private String password;
	private boolean administrator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

}
