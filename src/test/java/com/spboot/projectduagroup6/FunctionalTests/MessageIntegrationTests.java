/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.FunctionalTests;

import com.spboot.projectduagroup6.models.Message;
import com.spboot.projectduagroup6.models.User;
import com.spboot.projectduagroup6.repositories.MessageRepository;
import com.spboot.projectduagroup6.repositories.UserRepository;
import com.spboot.projectduagroup6.services.MessageService;
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
public class MessageIntegrationTests {

    @InjectMocks
    @Autowired
    MessageService service;

    @Mock
    MessageRepository repository;

    @Test
    public void createMessageTest() throws Exception {
        Message message = new Message();
        User user = new User();
        user.setId(1);
        
        message.setMessage("Haloo");

        when(repository.save(message)).thenReturn(message);
        service.store(message);
        long id=1;
        when(repository.getById(id)).thenReturn(message);

        Message checkMessage = this.repository.getById(id);

        Assertions.assertEquals(message, checkMessage);
    }

   

    @Test
    public void checkExistMessage() throws Exception {
        Message message = new Message();
        User user = new User();
        user.setId(1);
        
        message.setMessage("Haloo");
        
        List<Message> messageList = new ArrayList<>();
        messageList.add(message);

        when(repository.findAll()).thenReturn(messageList);

        List<Message> getMessage = service.getAll();
        assertThat(getMessage.size()).isGreaterThan(0);
    }

  

   
}
