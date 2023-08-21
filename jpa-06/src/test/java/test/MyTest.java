package test;

import jpa.EMF;
import model.Hotel;
import model.MembershipCard;
import model.User;
import model.UserBestSight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
    void oneToOneInsert1(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();

        //user select
        User user = entityManager.find(User.class,"test@test.com");
        //user를 참조한 membershipCard 생성
        MembershipCard membershipCard = new MembershipCard("001",user,new Date());
        entityManager.persist(membershipCard);//membershipCard insert

        transaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneInsert2(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();

        User user = new User("haha@test.com","haha",new Date()); // user 생성
        MembershipCard membershipCard = new MembershipCard("002",user,new Date()); // user를 참조한 membershipCard 생성
        entityManager.persist(user);// user insert
        entityManager.persist(membershipCard); // membershipCard insert
        //user persist 이후 membershipCard를 insert 해야만 한다. memeberShipcard를 persist 할 때 멤버십에서 참조한 user가 컨텍스트나 db에 존재하는지 확인하는데 둘 다 없으면 exception 발생하기 때문

        transaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneSelect(){
        EntityManager entityManager = EMF.createEntityManager();
        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"001"); // 식별자가 001인 엔티티 select, membershipCard이 참조한 user도 select 한다.
        System.out.println(membershipCard.getOwner().getEmail()+" "+membershipCard.getOwner().getName());
        User user = entityManager.find(User.class,"test@test.com");
        System.out.println(user == membershipCard.getOwner());
        entityManager.close();
    }

    @Test
    void oneToOneUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();

        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"003"); // 식별자가 003인 엔티티 select
        System.out.println(membershipCard.getOwner()); // 멤버십 카드의 owner가 null, 즉 아직 멤버십카드의 소유자 없음
        User user = new User("hoho@test.com","hoho123",new Date());
        membershipCard.assignTo(user); // 단 hoho@test.com라는 식별자를 가진 객체가 컨텍스트에 존재하거나 db에 존재해야 제대로 동작함

        transaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneUpdate2(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();

        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"004");
        User user = new User("hehe@test.com","hehe",new Date());
        membershipCard.assignTo(user);
        transaction.commit();

        entityManager.close();
    }

    @Test
    void oneToOneDelete(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();

        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"004");
        membershipCard.assignTo(null);

        transaction.commit();
        entityManager.close();
    }

    @Test
    void lazyLoading(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"001"); // 이 때 연관된 user는 가지고 오지 않음
        membershipCard.disable();
        membershipCard.getOwner().getName();//user 가져옴
        transaction.commit();
        entityManager.close();
    }

    //User와 연관돤 MemberShip 가져오기
    @Test
    void oneToOneMappedBySelect(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class,"hehe@test.com"); // user는 DB에서 물리적인 참조를 가지지는 않음
        System.out.println(user.getMembershipCard().getNumber());
        transaction.commit();
        entityManager.close();
    }


    @Test
    void oneToOneMappedByUpdateSuccess(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class,"tester@test.com");
        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"005");
        // membershipcard.set(user)을 해도 update는 일어남 , 왜냐하면 db에서는 membershipcard만 user를 참조하므로
        user.issue(membershipCard);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneMappedByUpdateFail(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class,"tester@test.com");
        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"005");
        user.setMembershipCard(membershipCard); //update 일어나지 않음 왜냐하면, db에서 직접적인 참조는 membership가 user를 참조하기 때문이다.
        transaction.commit();
        entityManager.close();
    }

    @Test
    void keyShare1(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = new User("hihi@test.com","hihi",new Date());
        UserBestSight userBestSight = new UserBestSight(user,"hihi","에펠탑");
        entityManager.persist(user);
        entityManager.persist(userBestSight);
        transaction.commit();
        entityManager.close();
    }


    @Test
    void keyShare2(){
        EntityManager entityManager = EMF.createEntityManager();
        User user = entityManager.find(User.class,"hihi@test.com");
        UserBestSight userBestSight = user.getUserBestSight();
        System.out.println(userBestSight.getDescription());
        TypedQuery<Hotel> query = entityManager.createQuery("", Hotel.class);
        entityManager.close();
    }

    @Test
    void test(){
        EntityManager entityManager = EMF.createEntityManager();
        MembershipCard membershipCard = entityManager.find(MembershipCard.class,"001"); // 이 때 연관된 user는 가지고 오지 않음
        System.out.println(membershipCard.getOwner().getClass());
        System.out.println(membershipCard.getOwner().getName());
        entityManager.close();
    }
}
