/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dell
 */

@Controller
public class LogoutController {
@GetMapping("/logout")
    public String index(HttpServletRequest request, RedirectAttributes ra) {
        HttpSession session = request.getSession(true);
        
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("email");
        session.removeAttribute("loggedIn");
        session.invalidate();
        
        ra.addFlashAttribute("success", "Logout Success!");
        return "redirect:/login";
    }
}

