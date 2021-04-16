package com.codingdojo.GroupProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.GroupProject.models.User;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String index() {
		return "home.jsp";
	}
}
