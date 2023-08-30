package jpa.dao;

import jpa.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select u from User u")
    Slice<User> userFind(Pageable pageable);
}
