package blog.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import blog.example.model.entity.UserEntity;



@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity deleteAllByPassword(String password);
	List<UserEntity> findByUserNameAndPassword(String userName, String password);
	UserEntity findByUserEmail(String userEmail);
}
