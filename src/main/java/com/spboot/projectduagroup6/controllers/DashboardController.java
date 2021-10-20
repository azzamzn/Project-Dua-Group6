/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.DetailInterface;
import com.spboot.projectduagroup6.interfaces.DonationInterface;
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
public class DashboardController {

 @Autowired
    private DonationInterface donationInterface;
    private DetailInterface detailInterface;

    @GetMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("list", donationInterface.getAll());
        
        return "dashboard";
    }
    





}
