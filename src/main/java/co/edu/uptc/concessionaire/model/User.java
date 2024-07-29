package co.edu.uptc.concessionaire.model;

import java.io.Serializable;

public class User implements Serializable, Comparable<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public User() {
		
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
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	
}
