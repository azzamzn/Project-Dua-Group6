/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spboot.projectduagroup6.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name="detail")
public class Detail {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="id_donation")
    private long iddonation;
    
    @Column(name="id_user")
    private long iduser;

    @Column(name="name_donation")
    private String namedonation;
    
    @Column(name="name_detail")
    private String namedetail;
    
    @Column(name="receipt")
    private String receipt;
    
    @Column(name="total")
    private long total;
    
    @Column(name="method")
    private String method;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    
    
    public long getIddonation() {
        return iddonation;
    }

    public void setIddonation(long iddonation) {
        this.iddonation = iddonation;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public String getNamedonation() {
        return namedonation;
    }

    public void setNamedonation(String namedonation) {
        this.namedonation = namedonation;
    }

    public String getNamedetail() {
        return namedetail;
    }

    public void setNamedetail(String namedetail) {
        this.namedetail = namedetail;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    

    
}
