package com.skilldistillery.skyreport.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}

	@Test
	void test_User_Location_Mapping() {
		assertNotNull(user);
		assertNotNull(user.getLocation());
		assertEquals("Nellis AFB", user.getLocation().getCity());
	}

	@Test
	void test_User_Comment_Mapping() {
		assertNotNull(user);
		assertNotNull(user.getComment());
		assertTrue(user.getComment().size() > 0);
	}

	@Test
	void test_User_Sighting_Mapping() {
		assertNotNull(user);
		assertNotNull(user.getSightingRating());
		assertTrue(user.getSightingRating().size() > 0);
	}

	@Test
	void test_UserHasComment_Mapping() {
		assertNotNull(user);
		assertNotNull(user.getUserHasComment());
		assertTrue(user.getUserHasComment().size() > 0);
	}

	@Test
	void test_User_SightingRating_Mapping() {
		assertNotNull(user);
		assertNotNull(user.getSightingRating());
		assertTrue(user.getSightingRating().size() > 0);
	}
}
