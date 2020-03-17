package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id")
	private int id;
	
	@Column(name="group_name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="manager_id")
	private int managerId;
	
	public Group (int id, String name, String password, int managerId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", password=" + password + ", managerId=" + managerId + "]";
	}
	
}