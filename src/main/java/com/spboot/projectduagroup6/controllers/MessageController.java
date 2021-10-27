/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.MessageInterface;
import com.spboot.projectduagroup6.models.Message;
import com.spboot.projectduagroup6.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



/**
 *
 * @author Dell
 */

@Controller
public class MessageController {

    @Autowired
    private MessageInterface messageInterface;

    @GetMapping("/message")
    public String index(Model model) {
        model.addAttribute("list", messageInterface.getAll());
        return "message";
    }

    @GetMapping("/sendmessage")
    public String create(Model model) {
        
        Message message = new Message();
        model.addAttribute("message", message);
        
        return "sendmessage";
    }

    @PostMapping("/sendmessage/store")
    public String store(@ModelAttribute("sendmessage") Message message, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        
        User user = new User();
        user.setId((long) session.getAttribute("id"));
        
        message.setUser(user);
        
        messageInterface.store(message);
        return "redirect:/";
    }
}

