package com.codingdojo.GroupProject.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.GroupProject.models.Event;
import com.codingdojo.GroupProject.models.User;
import com.codingdojo.GroupProject.services.EventService;
import com.codingdojo.GroupProject.services.UserService;

@Controller
public class EventController {
	@Autowired
	private EventService eService;
	@Autowired
	private UserService uService;
	
	//Paths, page names, and attribute names may have to be revised
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		} else {
			User loggedInUser = uService.findUserbyId((Long)session.getAttribute("user_id"));
			model.addAttribute("user", loggedInUser);
			model.addAttribute("events", eService.fetchAll());
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/event/{index}")
	public String showEvent(@PathVariable("index") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/dashboard";
		} else {
			User loggedInUser = uService.findUserbyId((Long)session.getAttribute("user_id"));
			Event event = eService.fetchEvent(id);
			model.addAttribute("user", loggedInUser);
			model.addAttribute("event", event);
			return "showEvent.jsp";
		}
	}
	// Allows the user to create a new event
	@GetMapping("/event/new")
	public String createEvent(@ModelAttribute("event") Event event, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/dashboard";
		} else {
			return "createEvent.jsp";
		}
	}
	// Adds the new event to the database
	@PostMapping("/event/new")
	public String postEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("event", event);
			return "createEvent.jsp";
		} else {
			User creator = uService.findUserbyId((Long)session.getAttribute("user_id"));
			Event newEvent = eService.createEvent(creator, event);
			return "dashboard.jsp";
		}
	}
	// Allows the user to join an event
	@GetMapping("/event/add/{id}")
	public String addStudent(@PathVariable("id") Long id, HttpSession session) {
		Long userID = (Long)session.getAttribute("user_id");
		Long eventID = id;
		User attendees = this.uService.findUserbyId(userID);
		Event attend = this.eService.fetchEvent(eventID);
		this.eService.addPerson(attendees, attend);;
		return "redirect:/dashboard";
	}
	// Allows a user to leave an event
	@GetMapping("/events/remove/{id}")
	public String removeStudent(@PathVariable("id") Long id, HttpSession session) {
		Long userID = (Long)session.getAttribute("user_id");
		Long eventID = id;
		User attendees = this.uService.findUserbyId(userID);
		Event attend = this.eService.fetchEvent(eventID);
		this.eService.removePerson(attendees, attend);;
		return "redirect:/dashboard";
	}
}
