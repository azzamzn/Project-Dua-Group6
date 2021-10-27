/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.services;

import com.spboot.projectduagroup6.interfaces.MessageInterface;
import com.spboot.projectduagroup6.models.Message;
import com.spboot.projectduagroup6.repositories.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author Dell
 */
@Service
public class MessageService implements MessageInterface {

    @Autowired
    private MessageRepository messageRepository;
    
    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public void store(Message message) {
        this.messageRepository.save(message);
    }
    
    @Override
    public List<Message> findByUserId(long id_user) {
        return messageRepository.findByUserId(id_user);
    }
}
