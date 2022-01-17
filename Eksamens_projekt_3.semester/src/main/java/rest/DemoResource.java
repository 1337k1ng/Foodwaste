package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import entities.User;
import errorhandling.API_Exception;
import facades.FacadeExample;
import facades.RemoteServerFacade;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
@Path("foodwaste")
public class DemoResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
   private static final RemoteServerFacade remoteFACADE =  RemoteServerFacade.getRemoteServerFacade(EMF);
    
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("postnummer")
    public String getAllFoodWaste() throws IOException, ParseException, API_Exception {
        return GSON.toJson(remoteFACADE.getAllStoresAndOffers());
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("postnummer/{zip}")
    public String getAllFoodWaste(@PathParam("zip")String zip) throws IOException, ParseException, API_Exception{
        return GSON.toJson(remoteFACADE.getAllStoresAndOffersByZip(zip));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("data/{zip}/{city}")
    public String getData(@PathParam("zip")String zip,@PathParam("city")String city) throws IOException, ParseException, API_Exception, InterruptedException, ExecutionException{
        return GSON.toJson(remoteFACADE.getDataFromApi(zip, city));
    }



}