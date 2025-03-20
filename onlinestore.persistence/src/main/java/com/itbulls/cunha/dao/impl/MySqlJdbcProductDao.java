package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.ProductDAO;
import com.itbulls.cunha.dto.ProductDTO;

@Repository
public class MySqlJdbcProductDao implements ProductDAO {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	@Override
	public List<ProductDTO> getAllProducts() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			List<ProductDTO> productsDto = em.createQuery("SELECT p FROM product p", ProductDTO.class).getResultList();
			em.getTransaction().commit();
			return productsDto;
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
	public ProductDTO getProductById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			ProductDTO productDto = em.find(ProductDTO.class, id);
			em.getTransaction().commit();
			return productDto;
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
	public void saveProduct(ProductDTO productDTO) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(productDTO);
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

	public List<ProductDTO> getProductsByName(String productName) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			TypedQuery<ProductDTO> query = em.createQuery(
					"SELECT p FROM product p WHERE p.product_name LIKE CONCAT('%', :productName, '%')",
					ProductDTO.class);
			query.setParameter("productName", productName.toLowerCase());
			List<ProductDTO> productsDto = query.getResultList();
			em.getTransaction().commit();
			return productsDto;
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
	public List<ProductDTO> getProductsByCategoryId(int categoryId) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ProductDTO> query = em.createQuery("SELECT p FROM product p WHERE p.category.id = :categoryId",
					ProductDTO.class);
			query.setParameter("categoryId", categoryId);
			List<ProductDTO> productsDto = query.getResultList();
			em.getTransaction().commit();
			return productsDto;
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
	public ProductDTO getProductByGuid(String guid) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ProductDTO> query = em.createQuery("SELECT p FROM product p WHERE p.guid = :guid",
					ProductDTO.class);
			query.setParameter("guid", guid);
			try {
				ProductDTO productDTO = query.getSingleResult();
				em.getTransaction().commit();
				return productDTO;
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
}
