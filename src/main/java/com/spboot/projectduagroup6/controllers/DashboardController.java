/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import com.spboot.projectduagroup6.interfaces.DetailInterface;
import com.spboot.projectduagroup6.models.Detail;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Dell
 */
@Controller
public class DashboardController {

 
    
    @Autowired
    private DetailInterface detailInterface;

    @GetMapping("/dashboard")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        long id_user = (long) session.getAttribute("id");

        List<Detail> details = detailInterface.findByUserId(id_user);
       
        model.addAttribute("details", details);
        return "dashboard";
    }

}
