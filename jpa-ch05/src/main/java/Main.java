import jpa.EMF;
import model.User;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        EMF.init();
        EntityManager entityManager = EMF.createEntityManager();
        User user1 = entityManager.find(User.class,"test@test.com");
        User user2 = entityManager.find(User.class,"test@test.com");
        entityManager.close();
        EMF.close();
    }
}
