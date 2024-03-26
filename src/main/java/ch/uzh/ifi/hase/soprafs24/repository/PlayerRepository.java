package ch.uzh.ifi.hase.soprafs24.repository;
import org.springframework.stereotype.Repository;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository("playerRepository")
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByPlayerID(String playerID);
    List<Player> findAllBySessionID(String sessionID);
}