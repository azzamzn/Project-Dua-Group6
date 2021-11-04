/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.FunctionalTests;

import com.spboot.projectduagroup6.models.Donation;
import com.spboot.projectduagroup6.models.Message;
import com.spboot.projectduagroup6.models.User;
import com.spboot.projectduagroup6.repositories.DonationRepository;
import com.spboot.projectduagroup6.services.DonationService;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Dell
 */
@SpringBootTest
public class DonationIntegrationTests {

    @InjectMocks
    @Autowired
    DonationService service;

    @Mock
    DonationRepository repository;

    @Test
    public void createMessageTest() throws Exception {
        Donation donation = new Donation();
        donation.setName("PEMBANGUNAN TEST");
        donation.setTotal(10000);
        donation.setDescription("desc");

        when(repository.save(donation)).thenReturn(donation);
        service.store(donation);
        long id=1;
        when(repository.getById(id)).thenReturn(donation);

        Donation checkDonation = this.repository.getById(id);

        Assertions.assertEquals(donation, checkDonation);
    }

   

    @Test
    public void checkExistMessage() throws Exception {
        Donation donation = new Donation();
        donation.setName("PEMBANGUNAN TEST");
        donation.setTotal(10000);
        donation.setDescription("desc");
        
        List<Donation> donationList = new ArrayList<>();
        donationList.add(donation);

        when(repository.findAll()).thenReturn(donationList);

        List<Donation> getDonation = service.getAll();
        assertThat(getDonation.size()).isGreaterThan(0);
    }

  

   
}
