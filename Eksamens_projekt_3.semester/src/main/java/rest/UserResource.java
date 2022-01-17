package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import entities.Favorit;
import entities.User;
import errorhandling.API_Exception;
import facades.FacadeExample;
import facades.UserFacade;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
@Path("users")
public class UserResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
   private static final UserFacade userFACADE =  UserFacade.getUserFacade(EMF);
    
   
   
      @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
   
   
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfo() {
        // return other message
        return "{\"msg\":\"Hello anonymous\"}";
    }

  
    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/favorit")
    @RolesAllowed("user")
    public String getFavoritsForUser() throws API_Exception {
      
     String thisuser = securityContext.getUserPrincipal().getName();
        
        return GSON.toJson(userFACADE.getFavorits(thisuser));
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("tilføj")
    public String setNewUser(String user) throws AuthenticationException {
          User userToAdd = GSON.fromJson(user, User.class);
          User addedUser = userFACADE.addNewUser(userToAdd);
        return "{\"message\": \"Brugeren " + addedUser.getUserName() + " er nu oprettet"+"\"}";
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("favorit")
    @RolesAllowed("user")
    public String addFavoritToUser(String favorit) throws API_Exception {
     
           Favorit favoritToAdd = GSON.fromJson(favorit, Favorit.class);
         String thisuser = securityContext.getUserPrincipal().getName();
         
          User edditetUser = userFACADE.addFavoritToUser(thisuser, favoritToAdd);
      
        return "{\"message\": \"Brugeren "  + edditetUser + " er nu opdateret"+"\"}";
    }
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("favorit/remove/{id}")
    @RolesAllowed("user")
    public String removeFavorit(@PathParam("id") long id) throws API_Exception {
        try {
         
        String thisuser = securityContext.getUserPrincipal().getName();
         
         userFACADE.removeFavorit(thisuser, id);
        } catch(Exception e){
       
            throw new API_Exception(e.getMessage());
        }
        return "{\"message\": \"Brugeren er nu opdateret"+"\"}";
    }

}