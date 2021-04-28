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
	
	@GetMapping("/events/{index}")
	public String showEvent(@PathVariable("index") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		} else {
			User loggedInUser = uService.findUserbyId((Long)session.getAttribute("user_id"));
			Event event = eService.fetchEvent(id);
			model.addAttribute("user", loggedInUser);
			model.addAttribute("event", event);
			return "showEvent.jsp";
		}
	}
	
	@GetMapping("/events/new")
	public String createEvent(@ModelAttribute("event") Event event, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		} else {
			return "createEvent.jsp";
		}
	}
	
	@PostMapping("/events/new")
	public String postEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("event", event);
			return "createEvent.jsp";
		} else {
			User creator = uService.findUserbyId((Long)session.getAttribute("user_id"));
			Event newEvent = eService.createEvent(creator, event);
			return "redirect:/events/" + newEvent.getId();
		}
	}
}
