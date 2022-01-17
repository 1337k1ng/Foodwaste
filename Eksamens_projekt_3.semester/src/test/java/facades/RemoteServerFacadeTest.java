/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CombinedDTO;
import dto.FoodWasteDTO;
import dto.*;
import errorhandling.API_Exception;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Patrick
 */
public class RemoteServerFacadeTest {
    
    
       private static EntityManagerFactory emf;
    private static RemoteServerFacade remoteFacade;
    
    public RemoteServerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
        remoteFacade = RemoteServerFacade.getRemoteServerFacade(emf);
    }
    
    

    /**
     * Test of getAllStoresAndOffers method, of class RemoteServerFacade.
     */
    @Test
    public void testGetAllStoresAndOffers() throws Exception {
        System.out.println("getAllStoresAndOffers");
        int result = remoteFacade.getAllStoresAndOffers().size();
        
        assertTrue(result > 0);     
    }

    /**
     * Test of getAllStoresAndOffersByZip method, of class RemoteServerFacade.
     */
    @Test
    public void testGetAllStoresAndOffersByZip() throws Exception {
        System.out.println("getAllStoresAndOffersByZip");
        String zip = "2100";
       
        int result = remoteFacade.getAllStoresAndOffersByZip(zip).size();
        
        assertTrue(result > 0);
    }

    /**
     * Test of getWheaterFromCity method, of class RemoteServerFacade.
     */
    @Test
    public void testGetWheaterFromCity() throws Exception {
        System.out.println("getWheaterFromCity");
        String city = "Roskilde";
        
        VejrDTO result = remoteFacade.getWheaterFromCity(city);
        
        assertTrue(result.getCurrentData() != null);
        
    }

    /**
     * Test of getDataFromApi method, of class RemoteServerFacade.
     */
    @Test
    public void testGetDataFromApi() throws Exception {
        System.out.println("getDataFromApi");
        String zip = "2100";
        String city = "Østerport";
       
        CombinedDTO result = remoteFacade.getDataFromApi(zip, city);
      
        // Should return both foodwaste and weather.
        assertTrue(result.getFoodwaste().size() > 0 && result.getVejret() != null);
    }
    
    
    
    /** NEGATIVE TEST CASES */ 
    
    @Test
    public void testGetDataFromApiWrongZip() throws API_Exception {
        System.out.println("getDataFromApi");
        String zip = "210"; // wrong zip format
        String city = "Østerport";
       Exception exception = assertThrows(API_Exception.class, () -> {
       remoteFacade.getDataFromApi(zip, city);
    });

    String expectedMessage = "Could not load data. Try antoher zip";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
      
    }
    
}
