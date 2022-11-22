package blog.example.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.example.model.service.BlogService;
import blog.example.model.service.CategoryService;
import blog.example.model.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	
	//ユーザーがログインしていた場合には、ブログ一覧画面が表示される
	@Test
	public void testGetBlogAllPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("alice@test.com")
				.password("alice1234")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blogall")
				.with(csrf())
				.with(user(alice));
	}

	//ユーザーがログインしていない場合には、ブログ登録画面は表示されず、ログイン画面にリダイレクトされる
	@Test
	public void testGetBlogAllPage_Fail() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blogall");
		mockMvc.perform(request)//
		.andExpect(redirectedUrl("http://localhost/login"));
			
	}
	
	//ユーザーがログインしていた場合には、ブログ登録画面が表示される
	@Test
	public void testGetBlogCreatePage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("alice@test.com")
				.password("alice1234")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blogcreate")
				.with(csrf())
				.with(user(alice));
	}
	//ユーザーがログインしていない場合には、ブログ登録画面は表示されずログイン画面がリダイレクトされる
	@Test
	public void testGetBlogCreatePage_Fail() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blogcreate");
		mockMvc.perform(request)//
		.andExpect(redirectedUrl("http://localhost/login"));
			
	}
	
	
}
