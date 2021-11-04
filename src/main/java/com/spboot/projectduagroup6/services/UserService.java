/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.services;


import com.spboot.projectduagroup6.interfaces.UserInterface;
import com.spboot.projectduagroup6.models.User;
import com.spboot.projectduagroup6.repositories.UserRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) throws Exception {
        String hashed = this.hash(user.getPassword());
        user.setPassword(hashed);

        return this.userRepository.save(user);
    }

    @Override
    public User auth(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!this.match(user.getPassword(), password)) {
            return null;
        }

        return user;
    }

    private String hash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDiggest = md.digest(password.getBytes());

        BigInteger no = new BigInteger(1, messageDiggest);

        String hashText = no.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }

        return hashText;
    }

    private boolean match(String password, String rawPassword)
            throws Exception {
        rawPassword = this.hash(rawPassword);
        return password.equals(rawPassword);
    }

    @Autowired
    private UserRepository UserRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void store(User user) {
        this.userRepository.save(user);
        
    }

    @Override
    public User getById(long id) {
        Optional< User> optional = userRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RuntimeException(" User not found for id :: " + id);
        }

        User user = optional.get();
        return user;
    }

    @Override
    public void delete(long id) {
        this.userRepository.deleteById(id);
    }
}
