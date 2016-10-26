package com.ccc.testtask.service;

import com.ccc.testtask.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

/**
 * @author Kirill Milinevskiy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {
    private static final String USERS_URL = "/users";

    @Autowired
    private TestRestTemplate restTemplate;

    private User user = new User("userfour", "pa123ord", "Belarus Minsk");

    @Test
    public void canSaveUser() {
        ResponseEntity<User> response =
                this.restTemplate.postForEntity(USERS_URL, user, User.class, Collections.emptyMap());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void canFindUser() {
        this.restTemplate.postForEntity(USERS_URL, user, User.class, Collections.emptyMap());
        ResponseEntity<User> response =
                this.restTemplate.getForEntity(USERS_URL + "/1", User.class, Collections.emptyMap());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void canDeleteUser() {
        this.restTemplate.postForEntity(USERS_URL, user, User.class, Collections.emptyMap());
        this.restTemplate.delete(USERS_URL + "/1");

        ResponseEntity<User> response = this.restTemplate.getForEntity(USERS_URL + "/1", User.class, Collections.emptyMap());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void canEditUser() {
        this.restTemplate.postForEntity(USERS_URL, user, User.class, Collections.emptyMap());

        String newName = "Konstantin";
        String newAddress = "www leningrad spb tochka ru";
        String newPass = "123pp321";
        User editedUser = new User(newName, newPass, newAddress);

        this.restTemplate.put(USERS_URL + "/1", editedUser);

        ResponseEntity<User> response = this.restTemplate.getForEntity(USERS_URL + "/1", User.class, Collections.emptyMap());

        User fetchedUser = response.getBody();
        Assert.assertEquals(newName, fetchedUser.getName());
        Assert.assertEquals(newPass, fetchedUser.getPassword());
        Assert.assertEquals(newAddress, fetchedUser.getAddress());
    }

    //TODO Validation tests
}