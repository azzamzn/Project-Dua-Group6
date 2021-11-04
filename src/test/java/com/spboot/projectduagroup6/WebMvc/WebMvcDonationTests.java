/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.WebMvc;

/**
 *
 * @author Dzakirah Septialisa
 */
import com.spboot.projectduagroup6.models.Donation;
import com.spboot.projectduagroup6.models.User;
import java.util.HashMap;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author Dell
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcDonationTests {

    @Autowired
    private MockMvc mockMvc;

    

    @Test
    public void testCreateDonation() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Project2");
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());

        mockMvc.perform(get("/adddonation")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());

        String name = "name-" + RandomString.make(10).toLowerCase();
        String description = "desc-" + RandomString.make(50).toLowerCase();

        
        

        Donation donation = new Donation();
        donation.setName(name);
        donation.setTotal(10000);
        donation.setDescription(description);

        mockMvc.perform(post("/adddonation/store")
                .sessionAttrs(sessionattr)
                .flashAttr("adddonation", donation))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
    
    @Test
    public void testDonationWithoutName() throws Exception {
        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Project2");
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/adddonation")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());

      
        String description = "desc-" + RandomString.make(50).toLowerCase();

        Donation donation = new Donation();
        donation.setName("");
        donation.setTotal(10000);
        donation.setDescription(description);

        mockMvc.perform(post("/adddonation/store")
                .sessionAttrs(sessionattr)
                .flashAttr("adddonation", donation))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
    
    @Test
    public void testDonationWithoutTotal() throws Exception {
        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Project2");
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/adddonation")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());

      
        String description = "desc-" + RandomString.make(50).toLowerCase();

        Donation donation = new Donation();
        donation.setName("PEMBANGUNAN");
        donation.setTotal(0);
        donation.setDescription(description);

        mockMvc.perform(post("/adddonation/store")
                .sessionAttrs(sessionattr)
                .flashAttr("adddonation", donation))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
    
    @Test
    public void testDonationWithoutDescription() throws Exception {
        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Project2");
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/adddonation")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());

      

        Donation donation = new Donation();
        donation.setName("PEMBANGUNAN TEST");
        donation.setTotal(10000);
        donation.setDescription("");

        mockMvc.perform(post("/adddonation/store")
                .sessionAttrs(sessionattr)
                .flashAttr("adddonation", donation))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
}
