package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.RoleDAO;
import com.itbulls.cunha.dto.RoleDTO;

@Repository
public class MySqlJdbcRoleDao implements RoleDAO {

	@Override
	public List<RoleDTO> getRoles() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			List<RoleDTO> rolesDto = em.createQuery("SELECT r FROM role r", RoleDTO.class).getResultList();
			em.getTransaction().commit();
			return rolesDto;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public RoleDTO getRoleById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			RoleDTO roleDto = em.find(RoleDTO.class, id);
			em.getTransaction().commit();
			return roleDto;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

}
