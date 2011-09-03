package eu.kleinkoerkamp.boetepot.domain;

import org.springframework.data.annotation.Id;

public class Person {

	private boolean admin;
	@Id
	private String id;
	
	private String username;
	private String firstName;
	private String lastName;
	
	public Person() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return username + " : " + firstName + " " + lastName;
	}
}
