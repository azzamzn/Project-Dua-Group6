/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.interfaces;

import com.spboot.projectduagroup6.models.Donation;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface DonationInterface {
    List<Donation> getAll();
    void store(Donation donation);
}
