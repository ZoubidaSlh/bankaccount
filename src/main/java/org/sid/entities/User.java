package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User implements Serializable{
	@Id @GeneratedValue
	private int id;
	private String username;
	private String password;
	private boolean active;
	 
	@OneToMany(
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true,
	        mappedBy="user")
	private Collection<UsersRoles> roles = new ArrayList<>();;

	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.active = true;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Collection<UsersRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsersRoles> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setRoles(Collection<UsersRoles> roles) {
		this.roles = roles;
	}
	
}
