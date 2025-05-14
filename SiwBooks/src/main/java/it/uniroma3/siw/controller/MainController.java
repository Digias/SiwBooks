package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index(Model model) {
		return"index.html";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return"index.html";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return"index.html";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return"index.html";
	}
	
	@GetMapping("/bestSeller")
	public String bestSeller(Model model) {
		return"index.html";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		return"index.html";
	}
	
	@GetMapping("/personalArea")
	public String personalArea(Model model) {
		return"index.html";
	}

}
