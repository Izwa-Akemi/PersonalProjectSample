package blog.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.entity.CategoryEntity;
import blog.example.model.service.CategoryService;



@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	//一覧を表示
	@GetMapping("/categoryAll")
	public String getCategoryAll(Model model) {
		List<CategoryEntity>categorylist = categoryService.findByAll();
		model.addAttribute("categorylist",categorylist);

		return "category_all_view.html";
	}

	//登録画面を表示
	@GetMapping("/categoryRegister")
	public String geCategoryListRegister() {
		return "category_register_view.html";
	}
	//登録内容を保存
	@PostMapping("/categoryCreate")
	public String register(@RequestParam String categoryName) {
		categoryService.insert(categoryName);
		return "redirect:/categoryAll";
	}

}
