package test;

import jpa.EMF;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Date;

public class MyTest {

    @BeforeEach
    void set(){
        EMF.init();
    }

    @AfterEach
    void end(){
        EMF.close();
    }

    @Test
    void test(){
        EntityManager entityManager = EMF.createEntityManager();
        User user1 = entityManager.find(User.class,"test@test.com");
        User user2 = entityManager.find(User.class,"test@test.com");
        Assertions.assertEquals(user1==user2,true);
        entityManager.close();
    }

    @Test
    void test2(){
        EntityManager entityManager = EMF.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class,"test@test.com");
            user.changeName("hahaha");
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    @Test
    void test3(){
        EntityManager entityManager = EMF.createEntityManager();
        //new 상태
        entityManager.getTransaction().begin();
        User user = new User("haha@test.com","haha",new Date());
        entityManager.persist(user); // 관리됨 상태
        entityManager.remove(user);   //삭제 상태

        User user2 = entityManager.find(User.class,"test@test.com"); // 관리됨 상태
        //분리됨 상태
        entityManager.detach(user2);
        user2.changeName("mmmmm");//test@test.com user 변경 but 관리 x
        User user3 = entityManager.merge(user2);//새로 객체를 생성하여 내용이 user2와 완전 같은 객체 반환
        System.out.println(user2 == user3); //user2 user3는 내용은 완전 같지만 주소는 다른 객체임
        user2.changeName("lalala"); //user2는 관리되고 있는 객체가 아님 그래서 변경 내역 반영 안됨
        entityManager.getTransaction().commit(); //test@test.com user의(user3) 변경내역 반영
        entityManager.close();
    }

}
