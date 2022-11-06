package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blog.example.model.entity.BlogEntity;



public interface BlogDao extends JpaRepository<BlogEntity, Long> { 

	BlogEntity save(BlogEntity blogEntity);
	@Query(value="SELECT b.blog_id,b.blog_title,b.blog_image,b.category_name,b.message,b.user_id From blog b INNER JOIN account a ON b.user_id = a.user_id WHERE b.user_id=?1",nativeQuery = true)
    List<BlogEntity>findByUserId(Long userId);
	BlogEntity findByBlogId(Long blogId);
	List<BlogEntity>findAll();
	 List<BlogEntity>findByCategoryName(String categoryName);
}
