/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import dto.FoodWasteDTO;
import dto.VejrDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import utils.HttpUtils;

/**
 *
 * @author Patrick
 */

             /** ALL CALLABLE CLASSES GOES HERE **/

/* Husk ikke at sætte public foran "class" ellers kan der ikke
    være flere classes i samme fil. Dette gør dog også at man
        kun kan bruge dem i samme package - "facades". */


     class FoodWasteHandler implements Callable<List<FoodWasteDTO>>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String foodWasteUrl;
        public FoodWasteHandler(String foodWasteUrl){
            this.foodWasteUrl = foodWasteUrl;
        }
        
        @Override
        public List<FoodWasteDTO> call() throws Exception {
     
            String data = HttpUtils.fetchDataWithToken(foodWasteUrl);
            
           List<Object> obj = (List<Object>) new JSONParser().parse(data); 
           List<FoodWasteDTO> foodWasteDTOs = new ArrayList<>();

            for (Object o : obj){
                foodWasteDTOs.add(GSON.fromJson(o.toString(), FoodWasteDTO.class)); 
                 }
           
                
            return foodWasteDTOs;
        }
        
      }


class VejrHandler implements Callable<VejrDTO>{
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        String verjUrl;
        public VejrHandler(String verjUrl){
            this.verjUrl = verjUrl;
        }
        
        @Override
        public VejrDTO call() throws Exception {
     
            String data = HttpUtils.fetchData(verjUrl);
            VejrDTO vejret = GSON.fromJson(data, VejrDTO.class);
                
            return vejret;
        }
        
      }