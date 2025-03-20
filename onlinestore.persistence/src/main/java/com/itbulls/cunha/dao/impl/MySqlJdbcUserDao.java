package com.itbulls.cunha.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.itbulls.cunha.dao.UserDAO;
import com.itbulls.cunha.dto.UserDTO;

@Repository
public class MySqlJdbcUserDao implements UserDAO {
	

	@Override
	public List<UserDTO> getUsers() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			List<UserDTO> usersDto = em.createQuery("SELECT u FROM user u", UserDTO.class).getResultList();
			em.getTransaction().commit();
			return usersDto;
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
	public UserDTO getUserById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			UserDTO userDto = em.find(UserDTO.class, id);
			em.getTransaction().commit();
			return userDto;
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
	public UserDTO getUserByEmail(String email) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			TypedQuery<UserDTO> query = em.createQuery("SELECT u FROM user u WHERE u.email = :email", UserDTO.class);
			query.setParameter("email", email);

			try {
				UserDTO userDto = query.getSingleResult();
				em.getTransaction().commit();
				System.out.println(userDto);
				return userDto;
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
	public UserDTO getUserByPartnerCode(String partnerCode) {
		if(partnerCode.isBlank() || partnerCode.isEmpty()) return null;
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<UserDTO> query = em.createQuery("SELECT u FROM user u WHERE u.partnerCode = :partnerCode",
					UserDTO.class);
			query.setParameter("partnerCode", partnerCode);
			try {
				UserDTO userDto = query.getSingleResult();
				em.getTransaction().commit();
				return userDto;
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
	public void saveUser(UserDTO user) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(user);
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
	public void updateUser(UserDTO userDto) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(userDto);
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
	public List<UserDTO> getAllUsersWithReferralCode(int userId) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<UserDTO> query = em.createQuery("SELECT u FROM user u WHERE u.referralUser.id = :userId",
					UserDTO.class);
			query.setParameter("userId", userId);
			List<UserDTO> usersDto = query.getResultList();
			em.getTransaction().commit();
			return usersDto;
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
