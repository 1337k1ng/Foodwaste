/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Patrick
 */
public class CombinedDTO {
    
    
    List<FoodWasteDTO> foodwaste;
    VejrDataDTO vejret;

    public CombinedDTO(  List<FoodWasteDTO> foodwaste, VejrDTO vejret) {
        this.foodwaste = foodwaste;
        this.vejret = vejret.CurrentData;
    }

    public   List<FoodWasteDTO> getFoodwaste() {
        return foodwaste;
    }

    public void setFoodwaste(  List<FoodWasteDTO> foodwaste) {
        this.foodwaste = foodwaste;
    }

    public VejrDataDTO getVejret() {
        return vejret;
    }

    public void setVejret(VejrDataDTO vejret) {
        this.vejret = vejret;
    }
    
    
    
}
