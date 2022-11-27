package session.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import session.example.service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminRegisterController {
	 @Autowired
	    private AdminService adminService;
	    //新規登録画面を表示
	    @GetMapping("/register")
	    public String getRegisterPage() {
	        return "/admin/admin_register.html";
	    }
	    //登録内容を保存
	    @PostMapping("/create")
	    public String register(@RequestParam String adminName,@RequestParam String adminEmail,@RequestParam String password) {
	        adminService.insert(adminName, adminEmail, password);
	        return "redirect:/admin/login";
	    }
	  
}
