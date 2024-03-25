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
    public Session createSession(){
        Session newSession = new Session();
        newSession.setPlayerCount(1);
        newSession.setSessionID(UUID.randomUUID().toString());
        newSession = sessionRepository.save(newSession);
        sessionRepository.flush();
        return newSession;
    }

    public Session getSessionByID(String SessionID){
        Session sessionByID = sessionRepository.findBySessionID(SessionID);
        return sessionByID;
    }
}
