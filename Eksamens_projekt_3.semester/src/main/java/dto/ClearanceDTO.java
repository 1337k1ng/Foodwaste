/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Madsj
 */
class ClearanceDTO {
    
    OfferDTO offer; 
    ProductDTO product;

    public ClearanceDTO(OfferDTO offer, ProductDTO product) {
        this.offer = offer;
        this.product = product;
    }

    public OfferDTO getOffer() {
        return offer;
    }

    public void setOffer(OfferDTO offer) {
        this.offer = offer;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ClearanceDTO{" + "offer=" + offer.getNewPrice() + ", product=" + product.getDescription() + '}';
    }

   
  
    
    
    
}

