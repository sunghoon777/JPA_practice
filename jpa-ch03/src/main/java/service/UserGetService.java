package service;

import jpa.EMF;
import model.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserGetService {

    public Optional<User> getUser(String email){
        EntityManager em = EMF.createEntityManager();
        User user = em.find(User.class,email);
        System.out.println(user.getClass().getName());
        em.close();
        return Optional.ofNullable(user);
    }

    public List<User> getUsers(){
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u order by u.name",User.class);
            List<User> userList = query.getResultList();
            em.getTransaction().commit();
            return userList;
        }catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }

    }

}
