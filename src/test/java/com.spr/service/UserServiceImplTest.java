package com.spr.service;

import com.spr.exception.UserNotFound;
import com.spr.init.WebAppConfig;
import com.spr.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static com.spr.UserTestData.*;

/**
 * Created by admin on 01.11.2015.
 */

@ContextConfiguration(classes=WebAppConfig.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:populate_DB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private UserService userService;

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(UserNotFound.class);
        userService.delete(1);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(UserNotFound.class);
        User user=userService.findById(1000);
        user.setId(10);
        userService.update(user);
    }

    @Test
    public void testCreate() throws Exception {
        User tu=userService.create(new User("created",20,false));
        MATCHER.assertCollectionEquals(userService.findAll(), Arrays.asList(
                user1,user2, user3, user4, user5, user6,user7,user8,user9,user10,user11,user12,user13,user14,user15,tu));
    }


    @Test
    public void testGetByName() throws Exception {
        List<User> user=userService.getByName("leo");
        MATCHER.assertCollectionEquals(user, Arrays.asList(user15));

    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(1000);
        MATCHER.assertCollectionEquals(userService.findAll(), Arrays.asList(
                user2, user3, user4, user5, user6,user7,user8,user9,user10,user11,user12,user13,user14,user15));
    }

    @Test
    public void testFindById() throws Exception {
        User user=userService.findById(1000);
        MATCHER.assertEquals(user, user1);
    }

    @Test
    public void testUpdate() throws Exception {
        User user=userService.findById(USER_ID);
        user.setAge(100);
        userService.update(user);
        MATCHER.assertEquals(userService.findById(USER_ID),updatedUser);
    }

    @Test
    public void getAll() throws Exception{
        MATCHER.assertCollectionEquals(userService.findAll(), Arrays.asList(
                user1,user2, user3, user4, user5, user6,user7,user8,user9,user10,user11,user12,user13,user14,user15));
    }
}