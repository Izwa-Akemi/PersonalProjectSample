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
public class AdminLoginController {
	@Autowired
	AdminService adminService;
	/**
	 * ログイン済みユーザ情報を格納するための
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;
	
	//管理者Admin
	@GetMapping("/login")
	public String getAdminLoginPage() {
		//admin_login.htmlが表示される。
		return "/admin/admin_login.html";
	}
	@PostMapping("/login")
	public String adminAuth(@RequestParam String adminEmail,@RequestParam String password,Model model) {
		//adminServiceクラスのfindByAdminNameAndPasswordメソッドを使用して、該当するユーザー情報を取得する。
		AdminEntity adminEntity = adminService.findByAdminEmailAndPassword(adminEmail, password);
		//adminEntity　== null
		if(adminEntity == null) {
			//admin_login.htmlが表示される。
			return "/admin/admin_login.html";
		}else {
			//adminEntityの内容をsessionに保存する
			session.setAttribute("admin",adminEntity);
			//adminServiceクラスのselecFindAll()を使用して、一覧を取得する。
			List<AdminEntity>adminList = adminService.selectAdminAll();
			//adminListをキーにしてadminListをitem_list.htmlに渡す。
			model.addAttribute("adminList",adminList);
			//セッションから管理者の情報を取得する。
			AdminEntity auth = (AdminEntity) session.getAttribute("admin");
			//管理者情報(AdminEntity)から管理者名を取得する。
			String loginAdminName = auth.getAdminName();
			model.addAttribute("loginAdminName",loginAdminName);
			return "/admin/admin_all_view.html";
		}
	}
	//user_idをキーにして，cartからcart_idを取得．
			@GetMapping("/logout")
			public String adminLogout() {
				//セッションの情報を削除する。
				session.invalidate();
				//admin_login.htmlが表示される。
				return "/admin/admin_login.html";
			}
}
