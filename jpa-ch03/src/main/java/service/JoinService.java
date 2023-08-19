package service;

import exception.DuplicateEmailException;
import jpa.EMF;
import model.User;

import javax.persistence.EntityManager;

public class JoinService {

    public void join(User user){
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        try {
            User found = em.find(User.class,user.getEmail());
            if(found != null){
                throw new DuplicateEmailException();
            }
            em.persist(user);
            em.getTransaction().commit();
        }catch (DuplicateEmailException e){
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }

    }

}
