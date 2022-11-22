package blog.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.example.model.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {
	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	//メソッドは,各テストが実行される前に実行する。
	@BeforeEach
	//データの作成
	public void prepareData() {
		when(userService.createAccount(any(), any(),any())).thenReturn(true);
		when(userService.createAccount(eq("Alice"), any(),eq("Alice12345"))).thenReturn(false);
	}
	@Test
	//ユーザー登録ページを正しく取得出来た場合
	public void testGetRegisterPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/register");

		mockMvc.perform(request)
		//register.htmlを表示
		.andExpect(view().name("register.html"));
	}

//	@Test
//	//ユーザー情報が正しく入力保存できた場合は、ログイン画面に遷移させる
//	public void testRegister_NewUsername_Succeed() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders
//				.post("/register")
//				//ユーザー名としてBobをを登録
//				.param("userName", "Izawa")
//				//ユーザーのメールアドレスとしてtest@test.comを登録
//				.param("userEmail","test@test.com")
//				//パスワードとしてBob54321を登録
//				.param("password", "1234abcd");
//
//		mockMvc.perform(request)
//		//login.htmlを表示
//		.andExpect(view().name("login.html"));
//	}
//	
//	@Test
//	//すでにユーザーが存在していた場合つまりユーザー登録に失敗した場合は、登録画面へ遷移
//	public void testLogin_ExistingUsername_Fail() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders
//				.post("/register")
//				//ユーザー名としてAliceをを登録
//				.param("userName", "Alice")
//				//ユーザーのメールアドレスとしてalice@test.comを登録
//				.param("userEmail","alice@test.com")
//				//パスワードとしてAlice12345を登録
//				.param("password", "Alice12345");
//
//		mockMvc.perform(request)
//		//register.htmlを表示
//		.andExpect(view().name("register.html"));
//	}

}

