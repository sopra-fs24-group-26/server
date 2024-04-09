package ch.uzh.ifi.hase.soprafs24.service;


import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(@Qualifier("sessionRepository") SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String createSession() {
        Session newSession = new Session();
        newSession.setPlayerCount(1);
        String sessionId = UUID.randomUUID().toString();
        newSession.setId(sessionId);
        newSession.setTurnPlayer(0);
        newSession.setSeed(UUID.randomUUID().toString());
        sessionRepository.save(newSession);
        sessionRepository.flush();
        return sessionId;
    }

    public void joinSession(String sessionId) {
        Session sessionById = sessionRepository.findById(sessionId);
        Integer curr = sessionById.getPlayerCount();
        sessionById.setPlayerCount(curr + 1);
    }

    public Session getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId);
    }
}