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
public class FoodWasteDTO {

    StoreDTO store;
    List<ClearanceDTO> clearances;

    public FoodWasteDTO(StoreDTO store, List<ClearanceDTO> clearances) {
        this.store = store;
        this.clearances = clearances;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public List<ClearanceDTO> getClearances() {
        return clearances;
    }

    public void setClearances(List<ClearanceDTO> clearances) {
        this.clearances = clearances;
    }

   
    
    
}