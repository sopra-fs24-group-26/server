package ch.uzh.ifi.hase.soprafs24.repository;
import org.springframework.stereotype.Repository;

import ch.uzh.ifi.hase.soprafs24.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findById(String sessionId);
    void deleteById(String sessionId);
}