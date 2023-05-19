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

class SightingHasCategoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private SightingHasCategory sightingHasCategory;
	
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
		SightingHasCategoryId shc = new SightingHasCategoryId();
		shc.setCategoryId(1);
		shc.setSightingId(1);
		sightingHasCategory = em.find(SightingHasCategory.class, shc);
	}
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		sightingHasCategory = null;
	}

	@Test
	void test() {
		assertNotNull(sightingHasCategory);
	}


}
