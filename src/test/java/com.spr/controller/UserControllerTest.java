package com.spr.controller;

import com.spr.init.WebAppConfig;
import com.spr.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes=WebAppConfig.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Sql(scripts = "classpath:populate_DB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    void postConstruct() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }


    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Test
    public void testUserListPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp"))
                .andExpect(model().attribute("page", userService.findAll(new PageRequest(0,10))));
    }

    @Test
    public void testFindByName() throws Exception {
        mockMvc.perform(post("/user").param("searchName", "макс"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(forwardedUrl("/WEB-INF/pages/user.jsp"))
                .andExpect(model().attribute("users", hasItems(allOf(
                        hasProperty("name", is("макс")),
                        hasProperty("age", is(44))
                ))));

    }

    @Test
    public void testDelete() throws Exception {
         mockMvc.perform((get("/delete").param("id", "1001")))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/index"));
         mockMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(model().attribute("page", userService.findAll(new PageRequest(0, 10))));
    }
}