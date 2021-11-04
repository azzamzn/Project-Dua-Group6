/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.DonationInterface;
import com.spboot.projectduagroup6.models.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dell
 */

@Controller
public class DonationController {

    @Autowired
    private DonationInterface donationInterface;

    @GetMapping("/donation")
    public String index(Model model) {
        model.addAttribute("list", donationInterface.getAll());
        return "donation";
    }
    


    @GetMapping("/adddonation")
    public String create(Model model) {
        
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        
        return "adddonation";
    }

    @PostMapping("/adddonation/store")
    public String store(@ModelAttribute("adddonation") Donation donation, RedirectAttributes ra) throws Exception {
       
        
        donationInterface.store(donation);
        return "redirect:/";
    }
}

