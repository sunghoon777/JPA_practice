package service;

import exception.UserNotFoundException;
import jpa.EMF;
import model.User;

import javax.persistence.EntityManager;

public class UserWithDrawService {

    public void withdrawUser(String email){
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        try {
            User user = em.find(User.class,email);

            if(user == null){
                throw new UserNotFoundException();
            }
            em.remove(user);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }

}
