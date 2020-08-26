package recipes.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;
	
	@Column(unique=true)
	private String name;
	private String password;
	private String role;
	private int isActive;
	
	public User() {}
	
	public User(String name, String password, String role, int isActive) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int isActive() {
		return isActive;
	}

	public void setActive(int isActive) {
		this.isActive = isActive;
	}
}
