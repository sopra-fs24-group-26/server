package ch.uzh.ifi.hase.soprafs24.repository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import ch.uzh.ifi.hase.soprafs24.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findBySessionID(String sessionID);
}
