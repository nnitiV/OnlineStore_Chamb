package com.itbulls.cunha.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "privilege")
public class PrivilegeDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "privilege_name")
	private String privilegeName;

	@ManyToMany(mappedBy = "privileges")
	private Set<RoleDTO> roles;

	public PrivilegeDTO() {
	}

	public PrivilegeDTO(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

}
