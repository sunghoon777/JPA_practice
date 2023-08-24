package mytest;

import jpa.EMF;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.Year;
import java.util.*;

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
    void test1(){
        EntityManager entityManager = EMF.createEntityManager();
        //타입을 지정해서 쿼리 실행
        TypedQuery<Hotel> query = entityManager.
                createQuery("select h from Hotel h",Hotel.class);
        List<Hotel> list = query.getResultList();
        for(Hotel hotel : list){
            System.out.println(hotel.getId()+" "+hotel.getName());
        }
        //타입을 지정하지 않고 쿼리 실행
        Query query2 = entityManager
                .createQuery("select u.email, u.name from User u" +
                        " order by u.createDate desc, u.email asc");
        List<Object[]> results = query2.getResultList();
        for(Object[] result : results){
            System.out.println((String)result[0]+" "+(String)result[1]);
        }
        entityManager.close();
    }

    //정렬하기
    @Test
    void test2(){
        EntityManager entityManager = EMF.createEntityManager();
        TypedQuery<Player> query = entityManager.createQuery("select p from Player p order by p.name desc ",Player.class);
        List<Player> players = query.getResultList();
        players.stream().forEach(player -> System.out.println(player.getId()+" "+player.getName()));
        entityManager.close();
    }

    
    //검색 조건 지정
    @Test
    void test3(){
        EntityManager entityManager = EMF.createEntityManager();
        TypedQuery<Player> query = entityManager.
                createQuery("select p from Player p where p.name = :name",Player.class);
        query.setParameter("name","페이커");
        Player player = query.getSingleResult();
        System.out.println(player.getName());
        entityManager.close();
    }


    @Test
    void test4(){
        EntityManager entityManager = EMF.createEntityManager();
        //exist
        TypedQuery<Team> query1 = entityManager.
                createQuery("select t from Team t " +
                        "where not exists (select p.id from t.players p)",Team.class);
        List<Team> list = query1.getResultList();

        //all

        TypedQuery<Team> query2 = entityManager.
                createQuery("select t from Team t " +
                        "where :name <> all(select p.name from t.players p)",Team.class);
        query2.setParameter("name","페이커");

        Team team1 = query2.getSingleResult();
        System.out.println(team1.getName());

        //any
        TypedQuery<Team> query3 = entityManager.
                createQuery("select t from Team t " +
                        "where :name = any(select p.name from t.players p)",Team.class);
        query3.setParameter("name","페이커");

        Team team2 = query3.getSingleResult();
        System.out.println(team2.getName());

        entityManager.close();
    }

    @Test
    void test5(){
        EntityManager entityManager = EMF.createEntityManager();
        //페이징 처리
        TypedQuery<User> query = entityManager.
                createQuery("select u from User u order by u.createDate desc",User.class);
        query.setFirstResult(4);
        query.setMaxResults(5);
        List<User> users = query.getResultList();
        entityManager.close();
    }

    @Test
    void test6(){
        EntityManager entityManager = EMF.createEntityManager();
        //특정 객체로 조회하기
        TypedQuery<UserSummary> query = entityManager.
                createQuery("select new model.UserSummary(u.email,u.name) " +
                        "from User u order by u.createDate desc",UserSummary.class);
        List<UserSummary> userSummaries = query.getResultList();
        userSummaries.stream().forEach(result -> System.out.println(result.getEmail()+" "+result.getName()));
        entityManager.close();
    }

    @Test
    void test7(){
        EntityManager entityManager = EMF.createEntityManager();
        //암시적 조인ㄴ
        TypedQuery<Player> query = entityManager
                .createQuery("select p from Player p where p.team.name = :teamName",Player.class);
        query.setParameter("teamName","T1");

        List<Player> players = query.getResultList();
        players.stream().forEach(player -> System.out.println(player.getName()));
        entityManager.close();
    }

    @Test
    void test8(){
        EntityManager entityManager = EMF.createEntityManager();
        //명시적 조인
        TypedQuery<Player> query = entityManager
                .createQuery("select p from Player p join p.team t where t.name = :teamName",Player.class);
        query.setParameter("teamName","T1");
        List<Player> players = query.getResultList();
        players.stream().forEach(player -> System.out.println(player.getName()));
        entityManager.close();
    }

    @Test
    void test9(){
        EntityManager entityManager = EMF.createEntityManager();
        //명시적 조인, on 절 사용
        TypedQuery<Player> query = entityManager
                .createQuery("select p from Player p join p.team t on t.name = :teamName",Player.class);
        query.setParameter("teamName","T1");
        List<Player> players = query.getResultList();
        players.stream().forEach(player -> System.out.println(player.getName()));
        entityManager.close();
    }

    @Test
    void test10(){
        EntityManager entityManager = EMF.createEntityManager();
        //where 절 조인
        TypedQuery<Player> query = entityManager
                .createQuery("select p from Player p,User u where p.name = u.name",Player.class);
        List<Player> players = query.getResultList();
        players.stream().forEach(player -> System.out.println(player.getName()));
        entityManager.close();
    }


    @Test
    void test11(){
        EntityManager entityManager = EMF.createEntityManager();
        //group by 사용
        TypedQuery<Long> query = entityManager
                .createQuery("select p from Player p group by p.team.id having count(p) >= 5",Long.class);
        List<Long> list = query.getResultList();
        list.stream().forEach(count -> System.out.println(count));
        entityManager.close();
    }

    @Test
    void test12(){
        EntityManager entityManager = EMF.createEntityManager();
        //네임드 쿼리 사용
        TypedQuery<User> query = entityManager.createNamedQuery("User.all",User.class);
        List<User> list = query.getResultList();
        list.stream().forEach(user -> System.out.println(user.getEmail()+" "+user.getName()));
        entityManager.close();
    }

    @Test
    void test13(){
        EntityManager entityManager = EMF.createEntityManager();

        //엔티티 프로젝션
        TypedQuery<Hotel> query = entityManager.createQuery("select h from Hotel h",Hotel.class);
        List<Hotel> list = query.getResultList();
        list.stream().forEach(hotel -> System.out.println(hotel.getName()+" "+hotel.getGrade()));

        //임베디드 프로젝션
        TypedQuery<Address> query2 = entityManager.createQuery("select h.address from Hotel h",Address.class);
        List<Address> list2 = query2.getResultList();
        list2.stream().forEach(address -> System.out.println(address));

        entityManager.close();
    }

    @Test
    void test14(){
        EntityManager entityManager = EMF.createEntityManager();
        //fetch 조인 엔티티 연관
        TypedQuery<Player> query = entityManager.createQuery("select p from Player p join fetch p.team",Player.class);
        List<Player> players = query.getResultList();
        for(Player player : players){
            System.out.println("player name : "+player.getName());
            System.out.println("team : "+player.getTeam()+" team name : "+player.getTeam().getName());
        }
        entityManager.close();
    }

    @Test
    void test15(){
        EntityManager entityManager = EMF.createEntityManager();
        //fetch 조인 컬랙션 연관
        TypedQuery<Team> query = entityManager.createQuery("select distinct t from Team t join fetch t.players",Team.class);
        List<Team> list = query.getResultList();
        for(Team team : list){
            System.out.println("team : "+team+" team name : "+team.getName());
            System.out.println("team players");
            for(Player player : team.getPlayers()){
                System.out.println("player : "+player+" player name : "+player.getName());
            }
        }
        entityManager.close();
    }

}
