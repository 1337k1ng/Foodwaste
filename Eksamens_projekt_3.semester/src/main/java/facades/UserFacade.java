package facades;

import entities.Favorit;
import entities.Role;
import entities.User;
import errorhandling.API_Exception;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    
    public User addNewUser(User newUser) throws AuthenticationException{
        
        
         if (newUser.getUserName().isEmpty() || newUser.getUserPass().isEmpty() ) {
                throw new AuthenticationException("User name or password must not be empty");   
            }
        
          EntityManager em = emf.createEntityManager();
        
          User user = new User(newUser.getUserName(), newUser.getUserPass());
          List<Role> roller = new ArrayList<>();
          roller.add(new Role("user"));
           user.addFavoritButikker(new Favorit("2200", "test"));
          user.setRoleList(roller);
         
        try {
          em.getTransaction().begin();
          em.persist(user);
          em.getTransaction().commit();
           
        } catch (RollbackException e) {
            throw new AuthenticationException("User already exist. Try another username");
            
        } catch (Exception e){
              throw new AuthenticationException("Something went wrong. Server may be unavailable at the moment");
        } finally {
            em.close();
        }
        return user;

        
    }
    
    
    public User addFavoritToUser(String username, Favorit favorit) throws API_Exception{
        
          EntityManager em = emf.createEntityManager();
         
          User user; 
          try {

          em.getTransaction().begin();
           user = em.find(User.class, username);
           user.addFavoritButikker(favorit);
          em.getTransaction().commit();
        
          } catch (Exception e){
              throw new API_Exception("Could not save to faveorites");
          } finally {
              em.close();
          }
          
         return user;
    }
     public List<Favorit> getFavorits(String username) throws API_Exception{
        
          EntityManager em = emf.createEntityManager();
         
          User user; 
          try {

     
           user = em.find(User.class, username);
     
          } catch (Exception e){
              throw new API_Exception("Could not load favorites");
          } finally {
              em.close();
          }
          
         return user.getFavoritButikker();
    }
    
      public User removeFavorit(String username, long favID) throws API_Exception{
        
          EntityManager em = emf.createEntityManager();
         Favorit favorit;
          User user; 
          try {

          em.getTransaction().begin();
          favorit = em.find(Favorit.class, favID);
           user = em.find(User.class, username);
           user.removeFavoritButik(favorit);
          em.getTransaction().commit();
        
          } catch (Exception e){
              throw new API_Exception("Could not remove from favorites. Try again later");
          } finally {
              em.close();
          }
          
         return user;
    }
     
     
     
}
