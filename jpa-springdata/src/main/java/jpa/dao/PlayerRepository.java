package jpa.dao;

import jpa.model.Player;
import jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,String> {


}
