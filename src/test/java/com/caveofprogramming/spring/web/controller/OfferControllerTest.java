package com.caveofprogramming.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

/**
 * @author 227761
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferControllerTest {

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

	/*
	 * @Test public void testCreateOfferSuccess() throws Exception {
	 * 
	 * mockMvc.perform(post("/docreate").param("name", "Testman").
	 * param("email", "ddhara10@gmail.com").param("text", "MyNewOffer"))
	 * 
	 * .andExpect(status().isOk()) .andExpect(view().name("createoffer"));
	 * 
	 * }
	 */

	/**
	 * @throws Exception
	 */
	@Test
	public void testOfferCreatedSuccess() throws Exception {

		mockMvc.perform(get("/offercreated")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("offercreated"));
	}

	@Test
	public void testShowOffersSuccess() throws Exception {

		mockMvc.perform(get("/offers")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("servicedown"));

	}

}
