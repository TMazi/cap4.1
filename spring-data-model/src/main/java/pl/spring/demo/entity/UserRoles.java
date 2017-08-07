package pl.spring.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERROLES")
public class UserRoles implements Serializable {

	@Id
	@Column(nullable = false, length = 50)
	private String username;
	@Column(nullable = false, length = 50)
	private String role;

	protected UserRoles() {
		super();
	}

	public UserRoles(String name, String userrole) {
		super();
		this.username = name;
		this.role = userrole;
	}

	public String getuserrole() {
		return role;
	}

	public void setuserrole(String userrole) {
		this.role = userrole;
	}

	public String getUserrole() {
		return role;
	}

	public void setUserrole(String userrole) {
		this.role = userrole;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

}