package blog.example.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import blog.example.config.WebSecurityConfig;
import blog.example.model.dao.UserDao;
import blog.example.model.entity.UserEntity;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public boolean createAccount(String userName,String userEmail, String password) {
		List<UserEntity> userList = userDao.findByUserNameAndPassword(userName, password);
		if (userList.isEmpty()) {
			userDao.save(new UserEntity(userName,userEmail, password));
			WebSecurityConfig.addUser(userEmail, password);
			return true;
		} else {
			return false;
		}
	}

	public List<UserEntity> getAllAccounts() {
		return userDao.findAll();
	}

	//idを見つける
	public UserEntity selectById(String userEmail) {
		return userDao.findByUserEmail(userEmail);
	}

	//ログイン処理
	//	public UserEntity findByUserNameAndPassword(String userName, String password) {
	//		List<UserEntity> userList = repository.findByUserNameAndPassword(userName, password);
	//	    if(userList.isEmpty()){
	//	        return null;
	//	    }else{
	//	        return userList.get(0);
	//	    }
	//
	//	}
}
