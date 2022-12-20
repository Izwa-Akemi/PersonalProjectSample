package session.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import session.example.model.entity.AdminEntity;
import session.example.service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminHelloController {
	@Autowired
	AdminService adminService;
	/**
	 * ログイン済みユーザ情報を格納するための
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;
	
	//管理者Admin
	@GetMapping("/hello")
	public String getAdminLoginPage(Model model) {
		//セッションから管理者の情報を取得する。
		AdminEntity auth = (AdminEntity) session.getAttribute("admin");
		//管理者情報(AdminEntity)から管理者名を取得する。
		String loginAdminName = auth.getAdminName();
		model.addAttribute("loginAdminName",loginAdminName);
		return "/admin/admin_hello.html";
	}
}
