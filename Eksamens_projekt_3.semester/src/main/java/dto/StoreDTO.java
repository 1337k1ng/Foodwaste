/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Madsj
 */
public class StoreDTO {

    AddressDTO address;
    String brand;
    List<String> coordinates;
    String name;

    public StoreDTO(AddressDTO address, String brand, List<String> coordinates, String name) {
        this.address = address;
        this.brand = brand;
        this.coordinates = coordinates;
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
}
    

