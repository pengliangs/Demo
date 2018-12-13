package com.pl.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void queryTest() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders
                .get("/user/query")
                .param("username", "penglinag")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);
    }

    @Test
    public void userInfoTest() throws Exception {
       String result =  mvc.perform(MockMvcRequestBuilders
                .get("/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("李四"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);
    }

    @Test
    public void insertUserTest() throws Exception {
        String data = "{\"password\":\"123\"}";
        String result =  mvc.perform((MockMvcRequestBuilders
                .post("/user/insert"))
                .content(data)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);
    }
}
