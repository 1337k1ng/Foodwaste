/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Patrick
 */
public class VejrDTO {
    
     VejrDataDTO CurrentData;

    public VejrDTO(VejrDataDTO CurrentData) {
        this.CurrentData = CurrentData;
    }

    public VejrDataDTO getCurrentData() {
        return CurrentData;
    }

    public void setCurrentData(VejrDataDTO CurrentData) {
        this.CurrentData = CurrentData;
    }
     
     
     
    
}
