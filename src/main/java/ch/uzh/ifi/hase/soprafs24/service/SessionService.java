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
        String sessionID = UUID.randomUUID().toString();
        newSession.setSessionID(sessionID);
        sessionRepository.save(newSession);
        sessionRepository.flush();
        return sessionID;
    }

    public void joinSession(String SessionID) {
        Session sessionByID = sessionRepository.findBySessionID(SessionID);
        Integer curr = sessionByID.getPlayerCount();
        sessionByID.setPlayerCount(curr + 1);
    }
}
