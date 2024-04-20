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
        String sessionId = UUID.randomUUID().toString();
        newSession.setId(sessionId);
        newSession.setTurnIndex(null);
        newSession.setSeed(UUID.randomUUID().toString());
        sessionRepository.save(newSession);
        sessionRepository.flush();
        return sessionId;
    }

    public void validateSessionId(String sessionId) {
        if (sessionRepository.findById(sessionId) == null){
            throw new IllegalArgumentException("Invalid Session ID");
        }
    }

    public Session getSessionById(String sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public void beginTurn(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.setTurnIndex(0);
        sessionRepository.save(session);
        sessionRepository.flush();
    }
}