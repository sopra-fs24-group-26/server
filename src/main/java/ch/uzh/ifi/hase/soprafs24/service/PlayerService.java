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

    public Player createPlayer(String playerName, String sessionId) {
        Player newPlayer = new Player();
        newPlayer.setName(playerName);
        newPlayer.setSessionId(sessionId);
        newPlayer.setId(UUID.randomUUID().toString());
        newPlayer = playerRepository.save(newPlayer);
        playerRepository.flush();
        return newPlayer;
    }

    public void deletePlayer(String playerId) {
        playerRepository.deleteById(playerId);
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId);
    }

    public List<Player> getPlayersInSession(String sessionId) {
        return playerRepository.findAllBySessionId(sessionId);
    }

    public void distributeOrderIndex(String sessionID){
        List<Player> players = this.getPlayersInSession(sessionID);
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.setOrderIndex(i);
            playerRepository.save(player);
            playerRepository.flush();
        }

    }
}