package com.skilldistillery.skyreport.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("CoastToCoastSkyReport");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void test() {
		assertNotNull(comment);
		assertNotNull(comment.getCommentDate());
		assertEquals(2023, comment.getCommentDate().getYear());
	}
	@Test
	void test_UserHasComment_to_comment() {
		assertNotNull(comment);
		assertNotNull(comment.getUserHasComments());
		assertTrue(comment.getUserHasComments().size() > 0);
	}


}
