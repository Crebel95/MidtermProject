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

class KnownObjectTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private KnownObject knownObject;
	
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
		knownObject = em.find(KnownObject.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		knownObject = null;
	}

	@Test
	void test() {
		assertNotNull(knownObject);
		assertNotNull(knownObject.getName());
		assertEquals("Weather balloon", knownObject.getName());
	}

	@Test
	void test_KnownObject_to_Category_mapping() {
		assertNotNull(knownObject);
		assertNotNull(knownObject.getCategory());
		assertEquals("Wreckage", knownObject.getCategory().getName());
	}
	
	@Test
	void test_KnownObject_to_Sighting_mapping() {
		assertNotNull(knownObject);
		assertNotNull(knownObject.getSightings());
		assertTrue(knownObject.getSightings().size() > 0);
	}

	

}
