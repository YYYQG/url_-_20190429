package com.xxx;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlYangqiangang20190429ApplicationTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
    }
    @Test
    public void shortUrl() throws Exception {

        String reuslt = mockMvc.perform(post("/shortUrl").param("longUrl","www.weixin.com"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(reuslt);
    }

    @Test
    public void shortUrlByCustomer() throws Exception {
        String reuslt = mockMvc.perform(post("/shortUrlByCustomer").param("longUrl","www.weixin.com").param("shortUrl","OKUN"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(reuslt);
    }
    @Test
    public void redirect() throws Exception {
        String reuslt = mockMvc.perform(get("/OKUN"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(reuslt);
    }
}
