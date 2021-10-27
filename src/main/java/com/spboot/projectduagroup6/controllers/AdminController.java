/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.UserInterface;
import com.spboot.projectduagroup6.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dell
 */
@Controller
public class AdminController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("list", userInterface.getAll());
        return "admin";
    }

    @PostMapping("/admin/store")
    public String store(@ModelAttribute("user") User user) {
        userInterface.store(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        User user = userInterface.getById(id);

        model.addAttribute("user", user);
        return "edituser";
    }

    @PostMapping("/admin/{id}/delete")
    public String delete(@PathVariable(value = "id") long id) {
        userInterface.delete(id);
        return "redirect:/admin";
    }
}
