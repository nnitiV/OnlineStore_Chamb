package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.CategoryDAO;
import com.itbulls.cunha.dto.CategoryDTO;

@Repository
public class MySqlJdbcCategoryDao implements CategoryDAO {

	@Override
	public CategoryDTO getCategoryById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<CategoryDTO> query = em.createQuery("SELECT c FROM category c WHERE c.id = :id",
					CategoryDTO.class);
			query.setParameter("id", id);
			try {
				CategoryDTO categoryDTO = query.getSingleResult();
				em.getTransaction().commit();
				return categoryDTO;
			} catch (NoResultException e) {
				return null;
			}
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
	public CategoryDTO getCategoryByName(String category_name) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<CategoryDTO> query = em.createQuery(
					"SELECT c FROM category c WHERE UPPER(c.category_name) = :categoryName", CategoryDTO.class);
			query.setParameter("categoryName", category_name);
			try {
				CategoryDTO categoryDTO = query.getSingleResult();
				em.getTransaction().commit();
				return categoryDTO;
			} catch (NoResultException e) {
				return null;
			}
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
	public void saveCategory(CategoryDTO category) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(category);
			em.getTransaction().commit();
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
	public List<CategoryDTO> getAllCategoriesName() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			List<CategoryDTO> categoriesDto = em.createQuery("SELECT c FROM category c", CategoryDTO.class)
					.getResultList();
			em.getTransaction().commit();
			return categoriesDto;
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
