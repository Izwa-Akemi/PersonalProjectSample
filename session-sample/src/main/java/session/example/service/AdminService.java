package session.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import session.example.model.dao.AdminDao;
import session.example.model.entity.AdminEntity;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;


	//ログイン処理
	public AdminEntity findByAdminEmailAndPassword(String adminEmail, String password) {
		//コントローラークラスからadminEmailとpasswordと受け取って結果を受け取る
		List<AdminEntity> adminList = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		//もしadminListが空だった場合には、nullを返す処理
		if(adminList.isEmpty()){
			return null;
		}else{
			//もしadminListが空でなかった場合には、Ｌｉｓｔの0番目の要素を取得する
			return adminList.get(0);
		}

	}

	//内容を保存
	public void insert(String adminName,String adminEmail,String password) {
		//コントローラークラスで受け取った、内容をdaoのsaveメソッドに渡して保存をする。
		adminDao.save(new AdminEntity(adminName,adminEmail,password));
	}
	
	//一覧表示
	public List<AdminEntity> selectAdminAll(){
		return adminDao.findAll();
	}
}