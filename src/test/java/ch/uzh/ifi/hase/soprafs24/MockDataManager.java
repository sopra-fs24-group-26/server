package ch.uzh.ifi.hase.soprafs24;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.JoinDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MockDataManager {

    public static Player mockPlayer(String sessionId, String name) {
        Player player = new Player();
        player.setName(name);
        player.setSessionId(sessionId);
        player.setId(UUID.randomUUID().toString());
        return player;
    }

    public static List<Player> mockPlayers(String sessionId) {
        Player player1 = mockPlayer(sessionId, "Banane");
        Player player2 = mockPlayer(sessionId, "Apple");
        Player player3 = mockPlayer(sessionId, "Kiwi");
        return Arrays.asList(player1, player2, player3);
    }

    public static Session mockSession() {
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setSeed(UUID.randomUUID().toString());
        session.setTurnIndex(0);
        return session;
    }

    public static JoinDTO mockJoinDTO(String playerName, String sessionId) {
        JoinDTO joinDTO = new JoinDTO();
        joinDTO.setPlayerName(playerName);
        joinDTO.setSessionId(sessionId);
        return joinDTO;
    }

    public static List<Player> generateMockPlayers(String sessionId) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Player player = new Player();
            player.setId(UUID.randomUUID().toString());
            player.setName("Player" + (i + 1));
            player.setSessionId(sessionId);
            player.setOrderIndex(i); // Assigning some arbitrary order index
            players.add(player);
        }
        return players;
    }

    public static List<Tile> generateMockTiles(String sessionId) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tile tile = new Tile();
            tile.setId(UUID.randomUUID().toString());
            tile.setSessionId(sessionId);
            tiles.add(tile);
        }
        return tiles;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The request body could not be created.%s", e.toString()));
        }
    }
}