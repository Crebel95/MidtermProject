package com.skilldistillery.skyreport.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.skyreport.data.SightingDAO;
import com.skilldistillery.skyreport.data.UserDAO;
import com.skilldistillery.skyreport.entities.Comment;
import com.skilldistillery.skyreport.entities.KnownObject;
import com.skilldistillery.skyreport.entities.Location;
import com.skilldistillery.skyreport.entities.Sighting;
import com.skilldistillery.skyreport.entities.SightingRating;
import com.skilldistillery.skyreport.entities.User;

@Controller
public class SightingController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SightingDAO sightingDAO;

	@GetMapping("sightingById.do")
	public String getSighting(int id, Model model, User user) {
		Sighting sighting = sightingDAO.findById(id);
		model.addAttribute("sighting", sighting);
		List<Comment> comments = sightingDAO.getCommentList(id);
		model.addAttribute("commentList", comments);
		KnownObject knownObject  = sightingDAO.findKnownObjectById(id);
		model.addAttribute("knownObject", knownObject);
        User theUser = userDAO.findUserById(user.getId());
        model.addAttribute("user", theUser);
        List<SightingRating> ratings = sightingDAO.getSightingRatingsList(id);
		model.addAttribute("sightingRatings", ratings);
	
		return "sightingById";
	}

	@RequestMapping(path = "submitKeyword.do", method = RequestMethod.POST)
	public String getSightingByKeyword(String keyword, Model model) {
		List<Sighting> sightingList = sightingDAO.viewSightingByKeyword(keyword);
		model.addAttribute("sightingsList", sightingList);
		return "sightingsList";
	}

	@GetMapping(path = "addNewSighting.do")
	public String routeToSighting() {
		return "addNewSighting";
	}

	@GetMapping(path = "sightingsList.do")
	public String routeToAllSighting(Model model) {
		List<Sighting> sightingList = sightingDAO.findAllSightings();
		model.addAttribute("sightingsList", sightingList);
		return "sightingsList";
	}

	@GetMapping(path = "sightingByKeyword.do")
	public String routeToSearch() {
		return "sightingByKeyword";
	}


	@GetMapping(path = "deleteSightingRouting.do")
	public String routeToDeleteSighting(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			List<Sighting> sightings = sightingDAO.viewSightingByUserId(user.getId());
			model.addAttribute("sightingList", sightings);
			
			List<Sighting> adminSightings = sightingDAO.findAllSightings();
			model.addAttribute("adminSighting", adminSightings);
			
			return "deleteSightingPage";
		} else {
			return "login";
		}
	}

	@RequestMapping(path = "deleteSightingPage.do", method = RequestMethod.POST)
	public String deleteSighting(int id, Model model) {
		boolean deleted = sightingDAO.deleteById(id);
		model.addAttribute("deleted", deleted);
		return "result";
	}

	@GetMapping(path = "updateSighting.do")
	public String routeToUpdateSighting(int id, Sighting sighting, Model model) {
		model.addAttribute("sighting", sightingDAO.findById(id));
		
		Sighting adminSightings = sightingDAO.findById(id);
		model.addAttribute("adminSighting", adminSightings);
		
		return "updateSighting";
	}

	@RequestMapping(path = "updatedSighting.do", method = RequestMethod.POST)
	public String updateSighting(HttpSession session, @RequestParam("id") int id, Sighting sighting, Location location,
			Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			System.out.println(sighting);
			sighting = sightingDAO.updateSighting(id, sighting, location);
			model.addAttribute("sighting", sighting);

			return "updatedSighting";
		} else {
			return "login";
		}
	}

	@GetMapping(path = "createSightingComment.do")
	public String comment(HttpSession session, Comment comment, int sightingId, Model model) {

		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			Comment userComment = sightingDAO.createComment(comment, sightingId, user);
			Sighting sighting = sightingDAO.findById(sightingId);
			model.addAttribute("sighting", sighting);
			model.addAttribute("sighting", sighting);
			model.addAttribute("user", user);
			List<Comment> comments = sightingDAO.getCommentList(sightingId);
			model.addAttribute("commentList", comments);
			return "sightingById";
		} else {
			return "login";
		}

	}
	@RequestMapping(path = "addNewSighting.do", method = RequestMethod.POST)
	public String addSighting(HttpSession session, Sighting sighting, Location location, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
		model.addAttribute("location", sightingDAO.createLocation(location));
		model.addAttribute("sighting", sightingDAO.create(location, sighting, user));
		}
		return "addedSighting";
	}
	@PostMapping(path= "getSightingList.do") 
	public String getComments(int sightingId, Model model) {
		List<Comment> comments = sightingDAO.getCommentList(sightingId);
		model.addAttribute("commentList", comments);
		return "sightingById";
		
	}
	
	
	

}
