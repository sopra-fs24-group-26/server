package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.repository.PlayerRepository;
import ch.uzh.ifi.hase.soprafs24.entity.Player;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;

    @Test
    public void whenValidId_thenPlayerIsFound() {
        String id = "id542369";

        Player player = new Player();
        player.setName("JPP");
        player.setId(id);
        player.setSessionId("123456");
        player.setOrderIndex(0);
        Mockito.when(playerRepository.findById(player.getId()))
                .thenReturn(player);

        Player found = playerService.getPlayerById(id);
        assertThat(found).isNotNull();
        assertThat(found.getId())
                .isEqualTo(id);
        assertThat(found.getName()).isEqualTo("JPP");
        assertThat(found.getSessionId()).isEqualTo("123456");
    }

    @Test
    public void whenValidSessionId_AllPlayersInSessionFound(){

        List<Player> inSession = setUpMultiplePlayersInSession();

        Mockito.when(playerRepository.findAllBySessionId("1234")).thenReturn(inSession);

        String sessionId = "1234";
        List<Player> foundPlayers = playerService.getPlayersInSession(sessionId);
        assertThat(foundPlayers.size()).isNotEqualTo(0);
        int counter = 0;
        for (Player p: foundPlayers){
            assertThat(p.getSessionId()).isEqualTo(sessionId);
            assertThat(p.getName()).isEqualTo("Player" + p.getId());
            counter += 1;
        }
        assertThat(counter).isEqualTo(3);
    }

    @Test
    public void whenCreatePlayer_ReturnNewPlayer(){

        String name = "JPP";
        String sessionId = "123456";
        String id = UUID.randomUUID().toString();

        Player player = new Player();
        player.setName(name);
        player.setSessionId(sessionId);
        player.setId(id);
        Mockito.when(playerRepository.save(Mockito.any())).thenReturn(player);

        Player newPlayer = playerService.createPlayer(name, sessionId);
        assertThat(newPlayer.getName()).isEqualTo(name);
        assertThat(newPlayer.getSessionId()).isEqualTo(sessionId);
        assertThat(newPlayer.getId()).isEqualTo(id);
        assertThat(newPlayer.getOrderIndex()).isNull();
    }

    @Test
    public void whenDistributeOrderIndex_AllPlayersInSessionAreAssigned(){
        List<Player> playersWithOrderIndex = new ArrayList<>();
        Mockito.when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> {
            Player player = invocation.getArgument(0);
            playersWithOrderIndex.add(player);
            return null;
        });
        Mockito.when(playerRepository.findAllBySessionId(anyString())).thenReturn(setUpMultiplePlayersInSession());

        String sessionId = "1234";
        playerService.distributeOrderIndex(sessionId);

        List<Integer> orders = new ArrayList<>();
        for(int i=0; i<playersWithOrderIndex.size(); i++){
            orders.add(playersWithOrderIndex.get(i).getOrderIndex());
        }
        assertThat(orders).containsExactlyInAnyOrder(0,1,2);

    }

    private List<Player> setUpMultiplePlayersInSession(){
        List<Player> inSession = new ArrayList<>();
        for(int i=0;i<3;i++) {
            Player p = new Player();
            p.setName("Player" + i);
            p.setId(Integer.toString(i));
            p.setSessionId("1234");
            inSession.add(p);
        }
        return inSession;
    }
}
