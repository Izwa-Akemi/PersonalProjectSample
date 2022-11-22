package blog.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.example.model.entity.BlogEntity;
import blog.example.model.service.BlogService;
import blog.example.model.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@MockBean
	private UserService userService;
	@MockBean
	private BlogService blogService;
	@Autowired
	private MockMvc mockMvc;


	@Test
	//ログインページ正常に表示させる
	public void AccessLoginPage() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/login");

		mockMvc.perform(request)//
		//レスポンスとしてlogin.htmlが返されるはず
		.andExpect(view().name("login.html"));

	}

	@Test
	public void AccessDefaultPage() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login")
				//パラメータとしてユーザーの名前とユーザーのパスワードを受け取る。
				.param("userEmail", "bob@test.com")
				.param("password", "Bob54321");

		mockMvc.perform(request)//
		.andExpect(redirectedUrl("http://localhost:8080/blogall"));
		//.andExpect(model().attribute("blogList", blogList));
	}
}
