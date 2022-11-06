package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.example.model.entity.CategoryEntity;



public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity save(CategoryEntity categoryEntity);
	List<CategoryEntity> findAll();
}
