package com.codingdojo.GroupProject.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.GroupProject.models.User;
import com.codingdojo.GroupProject.services.EventService;
import com.codingdojo.GroupProject.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService uservice;
	
	@Autowired
	private EventService eService;
	
	 @RequestMapping("/home")
	 public String homePage(Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("events", eService.fetchAll());
		 return "dashboard.jsp";
	 }
}
