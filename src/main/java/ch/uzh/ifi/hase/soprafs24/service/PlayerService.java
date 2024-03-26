package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(@Qualifier("playerRepository") PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(String playerName, String sessionID) {
        Player newPlayer = new Player();
        newPlayer.setPlayerName(playerName);
        newPlayer.setSessionID(sessionID);
        newPlayer.setPlayerID(UUID.randomUUID().toString());
        newPlayer = playerRepository.save(newPlayer);
        playerRepository.flush();
        return newPlayer;
    }

    public List<Player> getPlayersInSession(String SessionID) {
        List<Player> playersInSession = playerRepository.findAllBySessionID(SessionID);
        return playersInSession;
    }
}