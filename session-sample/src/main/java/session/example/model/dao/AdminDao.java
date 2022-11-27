package session.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import session.example.model.entity.AdminEntity;

@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
	
	AdminEntity save(AdminEntity adminEntity);
	List<AdminEntity> findByAdminEmailAndPassword(String adminEmail, String password);
	List<AdminEntity> findAll();
}