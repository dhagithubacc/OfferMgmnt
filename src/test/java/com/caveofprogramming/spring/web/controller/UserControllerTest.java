package com.caveofprogramming.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	private static final int TEST_USER_ID = 1;

	@Test
	public void contextLoads() {
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testRegistrationSuccess() throws Exception {
		/*
		 * MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 * map.set("rolename", "ROLE_ADMIN");
		 */

		mockMvc.perform(post("/newaccount").param("username", "Test0004").param("email", "ddhara10@gmail.com")
				.param("password", "Test0004").param("passwordConfirm", "Test0004")

		).andExpect(status().is2xxSuccessful()).andExpect(view().name("newaccount"));

	}

	/*
	 * @Test public void testRegistrationFailure() throws Exception {
	 * 
	 * mockMvc.perform(post("/newaccount").param("username",
	 * "Test0004").param("email", "ddhara10@gmail.com").param("password",
	 * "Test0004") .param("passwordConfirm", "Test0004")
	 * 
	 * ) .andExpect(status().is2xxSuccessful())
	 * .andExpect(model().attributeHasFieldErrors("Username", "Test0004"))
	 * .andExpect(model().attributeHasFieldErrors("Email",
	 * "ddhara10@gmail.com"))
	 * .andExpect(model().attributeHasFieldErrors("Password", "Test0004"))
	 * .andExpect(model().attributeHasFieldErrors("passwordconfirm",
	 * "Test0004")) .andExpect(view().name("newaccount"));
	 * 
	 * }
	 */

	@Test
	public void testAccountCreatedSuccess() throws Exception {

		mockMvc.perform(get("/accountcreated")

		).andExpect(status().isOk()).andExpect(view().name("accountcreated"));

	}

	/*
	 * @Test public void testAccountCreatedFailure() throws Exception {
	 * 
	 * mockMvc.perform(get("/accountcreated")
	 * 
	 * )
	 * 
	 * .andExpect(status().isOk())
	 * .andExpect(view().name("accountcreatedfailure"));
	 * 
	 * }
	 */

	@Test
	public void testShowAdminSuccess() throws Exception {

		mockMvc.perform(get("/admin")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("admin"));
	}

	@Test
	public void testLogoutSuccess() throws Exception {

		mockMvc.perform(get("/logout")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("login"));
	}

	/*
	 * @Test public void testShowAdminFailure() throws Exception {
	 * mockMvc.perform(get("/admin"))
	 * 
	 * .andExpect(status().isOk())
	 * 
	 * .andExpect(view().name("admin")); }
	 */

	/*
	 * @Test public void testShowAdminError() throws Exception {
	 * 
	 * mockMvc.perform(get("/admin", TEST_USER_ID)) .andExpect(status().isOk())
	 * .andExpect(model().attribute("user", hasProperty("userName",
	 * is("Test0005")))) .andExpect(model().attribute("user",
	 * hasProperty("password", is("Test0005"))))
	 * .andExpect(model().attribute("user", hasProperty("passwordConfirm",
	 * is("Test0005")))) .andExpect(model().attribute("user",
	 * hasProperty("email", is("email")))) .andExpect(model().attribute("user",
	 * hasProperty("enabled", is(false)))) .andExpect(view().name("admin")); }
	 */

}
