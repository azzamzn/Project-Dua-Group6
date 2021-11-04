/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.FunctionalTests;

import com.spboot.projectduagroup6.models.Detail;
import com.spboot.projectduagroup6.models.Donation;
import com.spboot.projectduagroup6.repositories.DetailRepository;
import com.spboot.projectduagroup6.services.DetailService;
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
public class DetailIntegrationTests {

    @InjectMocks
    @Autowired
    DetailService service;

    @Mock
    DetailRepository repository;

    @Test
    public void createMessageTest() throws Exception {
        Detail det = new Detail();
       
       
        Donation donation = new Donation();
        donation.setId(1);
        det.setTotal(10000);
        det.setReceipt("");
        det.setMethod("DANA");
        det.setDonation(donation);

        when(repository.save(det)).thenReturn(det);
        service.store(det);
        long id=1;
        when(repository.getById(id)).thenReturn(det);

        Detail checkDetail = this.repository.getById(id);

        Assertions.assertEquals(det, checkDetail);
    }

   

    @Test
    public void checkExistMessage() throws Exception {
       Detail det = new Detail();
       
       
        Donation donation = new Donation();
        donation.setId(1);
        det.setTotal(10000);
        det.setReceipt("");
        det.setMethod("DANA");
        det.setDonation(donation);
        
        List<Detail> detailList = new ArrayList<>();
        detailList.add(det);

        when(repository.findAll()).thenReturn(detailList);

        List<Detail> getDetail = service.getAll();
        assertThat(getDetail.size()).isGreaterThan(0);
    }

  

   
}
