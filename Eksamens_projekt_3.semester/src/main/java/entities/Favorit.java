/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Patrick
 */
@Entity
public class Favorit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favID;
    
    private String postnummer;
    private String storeName;

    
     public Favorit() {
       
    }
    
    public Favorit(String postnummer, String storeName) {
        this.postnummer = postnummer;
        this.storeName = storeName;
    }
    

    public Long getId() {
        return favID;
    }

    public void setId(Long id) {
        this.favID = id;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "{" + "favID=" + favID + ", postnummer=" + postnummer + ", storeName=" + storeName + '}';
    }

    
   
    
}
