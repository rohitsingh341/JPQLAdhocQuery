package com.hql.query.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.hql.query.demo.model.Book;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJpqlQuery {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	@Test
	public void jpqlQuery() {
		log.info("... jpqlQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
		q.setParameter("id", 1L);
		Book b = q.getSingleResult();
		Assert.assertTrue(b instanceof Book);
		Assert.assertEquals(new Long(1), ((Book)b).getId());
		
		em.getTransaction().commit();
		em.close();
	}
}
