package com.skilldistillery.skyreport.data;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.skyreport.entities.Comment;
import com.skilldistillery.skyreport.entities.KnownObject;
import com.skilldistillery.skyreport.entities.Location;
import com.skilldistillery.skyreport.entities.Sighting;
import com.skilldistillery.skyreport.entities.SightingRating;
import com.skilldistillery.skyreport.entities.User;

@Service
@Transactional
public class SightingDAOImpl implements SightingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Sighting findById(int id) {
		return em.find(Sighting.class, id);
	}

	@Override
	public List<Sighting> findAllSightings() {
		String jpql = "SELECT s FROM Sighting s WHERE s.enabled = true";
		return em.createQuery(jpql, Sighting.class).getResultList();
	}
	
//	@Override
//	public Sighting findByKeyword(String keyword) {
//		return em.find(Sighting.class, keyword);
//	}

	@Override
	public Location createLocation(Location location) {
		em.persist(location);
		return location;
	}


	@Override
	public boolean deleteById(int id) {
		try {
			Sighting sighting = em.find(Sighting.class, id);
//			em.remove(sighting);
			sighting.setEnabled(false);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Sighting> viewSightingByUserId(int id) {
		String jpql = "SELECT s FROM Sighting s WHERE s.userId = :userId AND s.enabled = true";
		List<Sighting> userSighting = em.createQuery(jpql, Sighting.class).setParameter("userId", id).getResultList();
		return userSighting;
	}
	@Override
	public List<Sighting> viewSightingByKeyword(String keyword) {
		String jpql = "SELECT s FROM Sighting s WHERE s.title LIKE :keyword OR s.description LIKE :keyword";
		List<Sighting> keywordSighting = em.createQuery(jpql, Sighting.class)
				.setParameter("keyword", "%"+ keyword +"%")
				.getResultList();
		System.out.println(keywordSighting);
		return keywordSighting;
	}

	@Override
	public Sighting updateSighting(int sightingId, Sighting editedSighting, Location location) {
		Sighting sighting = em.find(Sighting.class, sightingId);
		if (sighting != null) {
			sighting.setEnabled(true);
			sighting.getLocation().setAddress(location.getAddress());
			sighting.getLocation().setCity(location.getCity());
			sighting.getLocation().setCountry(location.getCountry());
			sighting.getLocation().setState(location.getState());
			sighting.getLocation().setZipcode(location.getZipcode());
			if(editedSighting.getKnownObject().getId() == 0) {
				
				sighting.setKnownObject(null);
				
			} else {
				KnownObject kn= em.find(KnownObject.class, editedSighting.getKnownObject().getId());
			sighting.setKnownObject(kn);
			}
			sighting.setSightingDate(editedSighting.getSightingDate());
			sighting.setTitle(editedSighting.getTitle());
			sighting.setDescription(editedSighting.getDescription());
			sighting.setDateCreated(editedSighting.getDateCreated());
			sighting.setPictureUrl(editedSighting.getPictureUrl());
			sighting.setLastUpdate(editedSighting.getLastUpdate());
			System.out.println("**************************");
			System.out.println(sighting);
			em.flush();
			if (editedSighting != null) {
				return sighting;
			}
		}
		return sighting;
	}
	
	@Override
	public Sighting create(Location location, Sighting sighting, User user) {
		System.out.println("top of impl");
		LocalDate ldt = LocalDate.now();
		sighting.setLocation(location);
		sighting.setSightingDate(ldt);
		sighting.setUserId(user.getId());
		System.out.println("below userid");
		System.out.println(user);
		System.out.println(location);
		sighting.setEnabled(true);
		em.persist(sighting);
		System.out.println(sighting);
		return sighting;
	}
	@Override
	public Comment createComment(Comment comment, int sightingId, User user) {
		Sighting sighting = em.find(Sighting.class, sightingId);
	
		sighting.addComment(comment);
		
		
		comment.setSighting(sighting);
		//user.addComment(comment);
		comment.setUser(user);

		em.persist(comment);
		return comment;
	}
	
	@Override
	public List<Comment> getCommentList(int sightingId) {
		String jpql = "SELECT s FROM Comment s WHERE s.sighting.id = :sightingId";
		List<Comment> comments =em.createQuery(jpql, Comment.class).setParameter("sightingId", sightingId).getResultList();
		return comments;
	}

	
	@Override
	public KnownObject findKnownObjectById(int sightingId) {
		String jpql ="SELECT k FROM KnownObject k JOIN Sighting s ON k.id = s.knownObject.id WHERE s.id = :sightingId";
//		KnownObject knownObject= em.createQuery(jpql, KnownObject.class).setParameter("sightingId", sightingId).getSingleResult();
//		
//		System.out.println("In the Impl method^^^^^^^^^^^^^^^^^^^^^^" + sightingId);
//		System.out.println("In the Impl method^^^^^^^^^^^^^^^^^^^^^^" + knownObject);
//		System.out.println("In the Impl method^^^^^^^^^^^^^^^^^^^^^^" + jpql);
		Sighting sighting = em.find(Sighting.class, sightingId);
		if(sighting != null) {
			return sighting.getKnownObject();
		}
		return null;
		
	
	}
	@Override
	public List<SightingRating> getSightingRatingsList(int sightingId) {
		String jpql = "SELECT s FROM SightingRating s WHERE s.sighting.id = :sightingId";
		List<SightingRating> ratings =em.createQuery(jpql, SightingRating.class).setParameter("sightingId", sightingId).getResultList();
		return ratings;
	}
	
	@Override
	public List<KnownObject> getKnownObjectList(int sightingId) {
		
		String jpql = "SELECT k FROM KnownObject k JOIN k.sightings s WHERE s.id = :sightingId";
		List<KnownObject> knownObjectList = em.createQuery(jpql, KnownObject.class)
				.setParameter("sightingId", sightingId)
				.getResultList();
		
	
		return knownObjectList;
		
	}
	

}
