package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.repository.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    SessionService sessionService;

    @Test
    public void whenCreateNewSession_NewSessionIsCreatedInDatabase() {
        List<Session> mockDataBase = new ArrayList<>();
        when(sessionRepository.save(any(Session.class))).thenAnswer(invocation -> {
            Session session = invocation.getArgument(0);
            mockDataBase.add(session);
            return null;
        });
        String id = sessionService.createSession();
        Session session = mockDataBase.get(0);
        assertThat(session.getId()).isEqualTo(id);
    }

    @Test
    public void whenCreateNewSession_ReturnsSessionId() {

        String id = sessionService.createSession();
        assertThat(id).isNotNull();
    }

    @Test
    public void whenBeginTurn_SetTurnIndexZero() {
        when(sessionRepository.findById(anyString())).thenAnswer(invocation -> {
            String id = invocation.getArgument(0);
            Session newSession = new Session();
            newSession.setId(id);
            newSession.setSeed("seed");
            return newSession;
        });
        List<Session> mockDataBase = new ArrayList<>();
        when(sessionRepository.save(any(Session.class))).thenAnswer(invocation -> {
            Session session = invocation.getArgument(0);
            mockDataBase.add(session);
            return null;
        });

        String id = "id542369";
        sessionService.beginTurn(id);
        assertThat(mockDataBase.get(0).getId()).isEqualTo(id);
        assertThat(mockDataBase.get(0).getSeed()).isEqualTo("seed");
        assertThat(mockDataBase.get(0).getTurnIndex()).isEqualTo(0);
    }

    @Test
    public void whenValidateSessionId_ValidIdPasses() {
        String id = "id542369";
        when(sessionRepository.findById(anyString())).thenReturn(null);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            sessionService.validateSessionId(id);
        });
    }

    @Test
    public void whenIncrementTurnIndex_ValidIdIncrements5To6() {

        when(sessionRepository.findById(anyString())).thenAnswer(invocation -> {
            String id = invocation.getArgument(0);
            Session newSession = new Session();
            newSession.setId(id);
            newSession.setSeed("seed");
            newSession.setTurnIndex(5);
            return newSession;
        });
        List<Session> mockDataBase = new ArrayList<>();
        when(sessionRepository.save(any(Session.class))).thenAnswer(invocation -> {
            Session session = invocation.getArgument(0);
            mockDataBase.add(session);
            return null;
        });

        String id = "id542369";
        sessionService.incrementTurnIndex(id);
        assertThat(mockDataBase.get(0).getTurnIndex()).isEqualTo(6);
    }
}
