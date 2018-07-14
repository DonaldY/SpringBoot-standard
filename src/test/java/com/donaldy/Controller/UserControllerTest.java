package com.donaldy.Controller;

import com.donaldy.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * Created by DonaldY on 2018/7/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /*@Test
    public void whenQuerySuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }*/
    
    /*@Test
    public void whenQueryUserSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/")
            .param("username", "123")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
            .andReturn().getResponse();
    }*/
    
    @Test
    public void whenGetInfoSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Donald"));
    }
    
    @Test
    public void whenGetInfoFail() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
    
    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"Donald\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(content))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Donald"))
            .andReturn().getResponse().getContentAsString();
    }
    
    
    @Test
    public void whenUploadSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
            .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello updload".getBytes("UTF-8"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();
    }
}
