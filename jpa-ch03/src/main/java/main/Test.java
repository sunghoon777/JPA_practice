package main;

import jpa.EMF;
import model.Review;
import model.User;

import javax.persistence.EntityManager;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        EMF.init();
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("생성");
        Review review = new Review("tester",new Date());
        entityManager.persist(review);
        System.out.println("커밋");
        entityManager.getTransaction().commit();
        System.out.println(review.getId());
        entityManager.close();
        EMF.close();
    }

}
