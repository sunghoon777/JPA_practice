package test;

import jpa.EMF;
import model.Address;
import model.Hotel;
import model.Sight;
import model.SightDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MyTest {

    @BeforeEach
    public void set(){
        EMF.init();
    }

    @AfterEach
    public void close(){
        EMF.close();
    }

    @Test
    public void test(){
        EntityManager entityManager = EMF.createEntityManager();
        Hotel hotel = entityManager.find(Hotel.class,"001");
        System.out.println(hotel.getAddress().getAddress1()+" "+hotel.getAddress().getAddress2());
        System.out.println(hotel.getEngAddress().getAddress1()+" "+hotel.getEngAddress().getAddress2());
        entityManager.close();
    }

    @Test
    public void test2(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Sight sight = new Sight(1L,"t1본사",new Address("1557","에스케이티","티원"),new Address("1557","skt","t1"));
        sight.setSightDetail(new SightDetail("오전 9시 ~ 오후 5시","연중 무휴","10대 주차 가능"));
        entityManager.persist(sight);
        transaction.commit();
        entityManager.close();
    }
}
