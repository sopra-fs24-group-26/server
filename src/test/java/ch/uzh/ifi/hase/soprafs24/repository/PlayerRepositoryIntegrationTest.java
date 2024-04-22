package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryIntegrationTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TestEntityManager playerManager;

    @Test
    public void whenFindByName_thenReturnPlayer() {
        // given
        Player player = new Player();
        player.setName("JPP");
        player.setId("id542369");
        player.setSessionId("123456");
        player.setOrderIndex(0);
        System.out.println(player);
        playerManager.persist(player);
        playerManager.flush();

        // when
        Player found = playerRepository.findById(player.getId());
        System.out.println(found.getName());

        // then
        assertThat(found.getName()).isEqualTo(player.getName());
    }

    @Test
    public void whenFindAllBySessionID_thenReturnListPlayers() {
        // given
        Player player = new Player();
        player.setName("JPP");
        player.setId("id542369");
        player.setSessionId("123456");
        player.setOrderIndex(0);
        playerManager.persist(player);
        playerManager.flush();

        List<Player> inSession = new ArrayList<Player>();
        for(int i=0;i<3;i++){
            Player p = new Player();
            p.setName("Player" + Integer.toString(i));
            p.setId(Integer.toString(i));
            p.setSessionId("1234");
            p.setOrderIndex(i);
            inSession.add(p);
            playerManager.persist(p);
            playerManager.flush();
        }

        // when
        List<Player> found = playerRepository.findAllBySessionId("1234");


        // then
        for(Player foundPlayer: found){
            assertThat(foundPlayer.getName()).isEqualTo(inSession.get(foundPlayer.getOrderIndex()).getName());
            assertThat(foundPlayer.getSessionId()).isEqualTo(inSession.get(foundPlayer.getOrderIndex()).getSessionId());
            assertThat(foundPlayer.getId()).isEqualTo(inSession.get(foundPlayer.getOrderIndex()).getId());
        }

    }

    @Test
    public void whenSaveInCreate_CanBeFound(){
        String name = "JPP";
        String sessionId = "123456";
        String id = UUID.randomUUID().toString();

        Player player = new Player();
        player.setName(name);
        player.setSessionId(sessionId);
        player.setId(id);
        playerManager.persist(player);
        playerManager.flush();

        Player savedPlayer = playerRepository.findById(player.getId());
        assertThat(savedPlayer).isNotNull();
        assertThat(name).isEqualTo(savedPlayer.getName());
        assertThat(sessionId).isEqualTo(savedPlayer.getSessionId());
        assertThat(id).isEqualTo(savedPlayer.getId());
        assertThat(savedPlayer.getOrderIndex()).isNull();

    }
}
