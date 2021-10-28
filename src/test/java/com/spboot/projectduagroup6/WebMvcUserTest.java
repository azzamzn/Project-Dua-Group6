/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6;

import com.spboot.projectduagroup6.interfaces.UserInterface;
import com.spboot.projectduagroup6.models.User;
import com.spboot.projectduagroup6.repositories.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

/**
 *
 * @author Dzakirah Septialisa
 */
public class WebMvcUserTest {
    @Autowired
    private UserRepository repo;
    private UserInterface inter;
@Test
@Rollback(false)
public void testUpdateUser() {
    User user = repo.findByEmail("email");
    user.setName("projectdua");
    user.setEmail("email");
    user.setPassword("password");
    
    repo.save(user);
     
    User updatedUser = repo.findByEmail("email");
     
    assertThat(updatedUser.getEmail()).isEqualTo("email");
}  


@Test
@Rollback(false)
public void testDeleteUser() {
    User user = repo.findByEmail("email");
     
    repo.delete(user.getEmail());
     
    User deleteUser = repo.findByEmail("email");
     
    assertThat(deleteUser).isNull();       
     
}
}
