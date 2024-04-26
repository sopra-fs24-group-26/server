package ch.uzh.ifi.hase.soprafs24.repository;


import ch.uzh.ifi.hase.soprafs24.entity.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SessionRepositoryIntegrationTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TestEntityManager sessionManager;

    @Test
    public void whenFindById_thenReturnSession() {
        // given
        Session session = new Session();
        String id = UUID.randomUUID().toString();
        String seed = UUID.randomUUID().toString();
        session.setId(id);
        session.setTurnIndex(null);
        session.setSeed(seed);
        sessionManager.persist(session);
        sessionManager.flush();

        // when
        Session found = sessionRepository.findById(id);

        // then
        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getSeed()).isEqualTo(seed);
        assertThat(found.getTurnIndex()).isNull();
    }
}
