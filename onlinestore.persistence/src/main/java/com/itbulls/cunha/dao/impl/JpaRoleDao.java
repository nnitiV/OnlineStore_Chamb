package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.RoleDAO;
import com.itbulls.cunha.dto.RoleDTO;

@Repository
public class JpaRoleDao implements RoleDAO {

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

	@Override
	public RoleDTO getRoleByName(String roleName) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<RoleDTO> query = em.createQuery("SELECT r FROM role r WHERE r.roleName = :roleName", RoleDTO.class);
			query.setParameter("roleName", roleName);
			
			try {
				RoleDTO roleDto = query.getResultList().stream().findFirst().orElse(null);
				em.getTransaction().commit();
				return roleDto;
			} catch(NoResultException e) {
				return null;
			}
		} finally {
			emf = null;
			em = null;
		}
	}

	@Override
	public void save(RoleDTO roleDto) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(roleDto);
			entityManager.getTransaction().commit();
		} finally {
			entityManagerFactory = null;
			entityManager = null;
		}
	}

}
