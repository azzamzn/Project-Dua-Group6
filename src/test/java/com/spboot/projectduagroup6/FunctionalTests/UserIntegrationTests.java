/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.FunctionalTests;

import com.spboot.projectduagroup6.models.User;
import com.spboot.projectduagroup6.repositories.UserRepository;
import com.spboot.projectduagroup6.services.UserService;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Dell
 */
@SpringBootTest
public class UserIntegrationTests {

    @InjectMocks
    @Autowired
    UserService service;

    @Mock
    UserRepository repository;

    @Test
    public void createUserTest() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");

        when(repository.save(user)).thenReturn(user);
        service.register(user);

        when(repository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = this.repository.findByEmail("test@mail.com");

        Assertions.assertEquals(user, checkUser);
    }

    @Test
    public void createUserTestWithEmptyName() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setName("");
        user.setPassword("test-strong-password");

        when(repository.save(user)).thenReturn(user);
        service.register(user);

        when(repository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = this.repository.findByEmail("test@mail.com");

        Assertions.assertEquals(user, checkUser);
    }

    @Test
    public void createUserTestWithEmptyEmail() throws Exception {
        Throwable e = null;
        String message = null;

        try {
            User user = new User();
            user.setEmail("");
            user.setName("Test");
            user.setPassword("test-strong-password");

            when(repository.save(user))
                    .thenThrow(new Exception("Email cannot be null!"));

            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }

        Assertions.assertTrue(e instanceof Exception);
    }

    @Test
    public void createUserExistEmail() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(repository.findAll()).thenReturn(userList);

        List<User> getUser = service.getAll();
        assertThat(getUser.size()).isGreaterThan(0);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(4);
        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        long id = 4;

        repository.deleteById(id);

        service.delete(user.getId());

    }

   
}
