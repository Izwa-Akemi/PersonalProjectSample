package blog.example.model.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import blog.example.model.dao.UserDao;
import blog.example.model.entity.UserEntity;

@SpringBootTest
public class UserServiceTest {
	@MockBean
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	

	@Test
	public void testCreateAccount_NewUsername_ReturnTrue() {
		assertTrue(userService.createAccount("Bob","bob@test.com","Bob54321"));
	}
	

	

}
