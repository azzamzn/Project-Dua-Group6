/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.DetailInterface;
import com.spboot.projectduagroup6.interfaces.DonationInterface;
import com.spboot.projectduagroup6.models.Detail;
import com.spboot.projectduagroup6.models.Donation;
import com.spboot.projectduagroup6.models.User;
import java.util.List;
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
public class DetailController {

    @Autowired
    private DetailInterface detailInterface;

    @Autowired
    private DonationInterface donationInterface;

    @GetMapping("/admin/detail")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        long id_user = (long) session.getAttribute("id");

        List<Detail> details = detailInterface.findByUserId(id_user);

        model.addAttribute("details", details);
        return "admin";
    }

    @GetMapping("/detail/create")
    public String index(Model model) {

        List<Donation> donations = donationInterface.getAll();
        model.addAttribute("donations", donations);

        Detail detail = new Detail();
        model.addAttribute("detail", detail);

        return "detail";
    }

    @PostMapping("/detail/store")
    public String store(@ModelAttribute("detail") Detail detail, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);

        User user = new User();
        user.setId((long) session.getAttribute("id"));

        detail.setUser(user);

        detailInterface.store(detail);
        return "redirect:/";
    }
}
