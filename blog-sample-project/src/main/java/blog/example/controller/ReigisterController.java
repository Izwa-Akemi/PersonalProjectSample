package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blog.example.model.service.UserService;



@Controller
public class ReigisterController {
	@Autowired
	private UserService accountService;

	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	@PostMapping("/register")
	public String register(@RequestParam String userName,@RequestParam String userEmail,
			@RequestParam String password, Model model) {
		if (accountService.createAccount(userName, userEmail,password)) {
			return "login.html";
		} else {
			return "register.html";

		}

	}
}
