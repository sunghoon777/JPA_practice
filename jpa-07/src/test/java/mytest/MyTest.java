package mytest;

import jpa.EMF;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.misc.Perf;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    void ManyToOne(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = entityManager.find(Hotel.class,"001");
        Review review = new Review(1L,hotel,5,"매우 좋았음",new Date());
        entityManager.persist(review);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableListInsert(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        List<String> list = Arrays.asList("자유의 여신상","엠파이어 빌딩","센트럴 파크","차이나 타운");
        Itinerary itinerary = new Itinerary(1L,"뉴욕","미국 관광지임",list);
        entityManager.persist(itinerary);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableListSelect(){
        EntityManager entityManager = EMF.createEntityManager();
        Itinerary itinerary = entityManager.find(Itinerary.class,1L);
        List<String> sites = itinerary.getSites();
        System.out.println(sites.getClass().getName());
        // 실제 클래스는 org.hibernate.collection.internal.PersistentList 이다.
        sites.stream().forEach(site -> System.out.println(site));
        entityManager.close();
    }


    @Test
    void collectionTableListUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Itinerary itinerary = entityManager.find(Itinerary.class,1L);
        List<String> sites = itinerary.getSites();
        sites.set(3,"코리안 타운");
        sites.add("할랄푸드 푸드트럭");
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableListRemove(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Itinerary itinerary = entityManager.find(Itinerary.class,1L);
        List<String> sites = itinerary.getSites();
        sites.remove(3);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableListRemoveAll(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Itinerary itinerary = entityManager.find(Itinerary.class,1L);
        List<String> sites = itinerary.getSites();
        sites.clear();
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableListValue(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Itinerary itinerary = entityManager.find(Itinerary.class,1L);
        List<SiteInfo> siteInfos = itinerary.getSiteInfos();
        siteInfos.add(new SiteInfo("센트럴 파크",new Date()));
        siteInfos.add(new SiteInfo("자유의 여신상",new Date()));
        siteInfos.add(new SiteInfo("코리안 타운",new Date()));
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableSetSelect(){
        EntityManager entityManager = EMF.createEntityManager();
        User user = entityManager.find(User.class,"tester@test.com");
        Set<String> set = user.getKeywords();
        set.stream().forEach(keyword -> System.out.println(keyword));
        entityManager.close();
    }

    @Test
    void collectionTableSetInsert(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Set<String> set = new HashSet<>();
        set.add("빠삐코");
        set.add("아이스크림");
        set.add("설레임");
        User user = new User("kuku@test.com","kuku",new Date(),set);
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableSetUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class,"kuku@test.com");
        Set<String> keywords = user.getKeywords();
        keywords.remove("빠삐코");
        keywords.add("메로나");
        keywords.add("설레임");
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableSetRemoveAll(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class,"kuku@test.com");
        Set<String> keywords = user.getKeywords();
        keywords.clear();
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableSelect(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = entityManager.find(Hotel.class,"002");
        Map<String,String> properties = hotel.getProperties();
        properties.keySet().stream().forEach(key -> System.out.println(key+" "+properties.get(key)));
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableInsert(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = new Hotel("002","짱큰호텔",Grade.STAR5,new Address("1111","주소1","주소2"));
        Map<String,String> properties = new HashMap<>();
        properties.put("추가1","추가정보1");
        properties.put("추가2","추가정보2");
        hotel.setProperties(properties);
        entityManager.persist(hotel);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = entityManager.find(Hotel.class,"002");
        Map<String,String> properties = hotel.getProperties();
        properties.put("추가3","추가3");
        properties.put("추가2","추가정보22");
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableRemove(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = entityManager.find(Hotel.class,"002");
        Map<String,String> properties = hotel.getProperties();
        properties.remove("추가3");
        transaction.commit();
        entityManager.close();
    }

    @Test
    void collectionTableRemoveAll(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Hotel hotel = entityManager.find(Hotel.class,"002");
        Map<String,String> properties = hotel.getProperties();
        properties.clear();
        transaction.commit();
        entityManager.close();
    }

    //1:n 관계 단방향 , 주인이 1임
    @Test
    void oneToManyCollectionInsert(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Player player1 = new Player("P1","선수1");
        Player player2 = new Player("P2","선수2");
        entityManager.persist(player1);
        entityManager.persist(player2);
        Set<Player> set = new HashSet<>();
        set.add(player1);
        set.add(player2);
        Team team1 = new Team("T1","팀1",set);
        entityManager.persist(team1);
        transaction.commit();
        entityManager.close();
    }

    //1:n 관계 단방향 , 주인이 1임
    @Test
    void oneToManyCollectionUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Team team = entityManager.find(Team.class,"T1");
        Set<Player> set = team.getPlayers();
        Player player = new Player("P3","선수103");
        entityManager.persist(player);
        set.add(player);
        transaction.commit();
        entityManager.close();
    }

    //1:n 관계 단방향 , 주인이 1임
    @Test
    void oneToManyCollectionRemove(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Team team = entityManager.find(Team.class,"T1");
        Set<Player> set = team.getPlayers();
        Player removePlayer = null;
        for(Player player : set){
            if(player.getId().equals("P3")){
                removePlayer = player;
                break;
            }
        }
        set.remove(removePlayer);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void ManyToManyInsert(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Person person1 = new Person("001","사람1");
        Person person2 = new Person("002","사람2");
        entityManager.persist(person1);
        entityManager.persist(person2);
        Set<Person> set = new HashSet<>();
        set.add(person1);
        set.add(person2);
        Performance performance = new Performance("001","공연1",set);
        entityManager.persist(performance);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void ManyToManySelect(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Performance performance = entityManager.find(Performance.class,"001");
        Set<Person> set = performance.getCast();
        set.stream().forEach(person -> System.out.println(person.getId()+" "+person.getName()));
        transaction.commit();
        entityManager.close();
    }

    @Test
    void ManyToManyUpdate(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Performance performance = entityManager.find(Performance.class,"001");
        Set<Person> set = performance.getCast();
        Person newPerson = new Person("003","사람3");
        entityManager.persist(newPerson);
        set.add(newPerson);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void ManyToManyRemove(){
        EntityManager entityManager = EMF.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        Performance performance = entityManager.find(Performance.class,"001");
        Set<Person> set = performance.getCast();
        for(Person person : set){
            if(person.getId().equals("003")){
                set.remove(person);
            }
        }
        transaction.commit();
        entityManager.close();
    }
}
