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

	//カテゴリー一覧を表示
	@GetMapping("/category/all")
	public String getCategoryAll(Model model) {
		List<CategoryEntity>categorylist = categoryService.findByAll();
		model.addAttribute("categorylist",categorylist);

		return "category_all_view.html";
	}

	//カテゴリー登録画面を表示
	@GetMapping("/category/register")
	public String geCategoryListRegister() {
		return "category_register_view.html";
	}
	//カテゴリー登録内容を保存
	@PostMapping("/category/create")
	public String register(@RequestParam String categoryName) {
		categoryService.insert(categoryName);
		return "redirect:/category/all";
	}
	//カテゴリー編集画面の表示
	@GetMapping("/category/edit/{categoryId}")
	public String getCategroyEditPage(@PathVariable Long categoryId,Model model) {
		CategoryEntity categoryEntity = categoryService.selectCategoryId(categoryId);
		model.addAttribute("category",categoryEntity);
		return "category_edit_view.html";
	}
	@PostMapping("/category/update")
	public String update(@RequestParam String categoryName,@RequestParam Long categoryId) {
		categoryService.update(categoryId, categoryName);
		return "redirect:/category/all";
	}
	//カテゴリー登録内容を削除
		@PostMapping("/category/delete")
		public String deleteCategory(@RequestParam Long categoryId) {
			categoryService.delete(categoryId);
			return "redirect:/category/all";
		}


}
