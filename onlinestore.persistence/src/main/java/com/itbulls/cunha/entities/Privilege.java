package com.itbulls.cunha.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "privilege")
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "privilege_name")
	private String privilegeName;

	public Privilege() {
	}

	public Privilege(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}


	@Override
	public String toString() {
		return "Privilege [id=" + id + ", privilegeName=" + privilegeName + "]";
	}
}
