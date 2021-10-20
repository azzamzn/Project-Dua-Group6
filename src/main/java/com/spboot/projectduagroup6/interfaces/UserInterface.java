/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.interfaces;


import com.spboot.projectduagroup6.models.User;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface UserInterface {
    void register(User user) throws Exception;
    User auth(String email, String password) throws Exception;
    List<User> getAll();
    void store(User user);
    User getById(long id);
    void delete(long id);
}

