package com.itbulls.cunha.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.PrivilegeDAO;
import com.itbulls.cunha.dto.PrivilegeDTO;
import com.itbulls.cunha.entities.Privilege;

@Repository
public class JpaPrivilegeDao implements PrivilegeDAO {

	@Override
	public void save(PrivilegeDTO privilege) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(privilege);
			entityManager.getTransaction().commit();
		} finally {
			entityManagerFactory = null;
			entityManager = null;
		}
	}

	@Override
	public PrivilegeDTO getPrivilegeByName(String privilegeName) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<PrivilegeDTO> query = em.createQuery(
					"SELECT p FROM privilege p WHERE p.privilegeName = :privilegeName", PrivilegeDTO.class);
			query.setParameter("privilegeName", privilegeName);
			try {
				PrivilegeDTO privilegeDTO = query.getResultList().stream().findFirst().orElse(null);
				em.getTransaction().commit();
				return privilegeDTO;
			} catch (NoResultException e) {
				return null;
			}
		} finally {
			emf = null;
			em = null;
		}
	}

}
