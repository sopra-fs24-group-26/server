package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;

import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import ch.uzh.ifi.hase.soprafs24.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final SessionService sessionService;
    private final PlayerService playerService;

    Controller(SessionService sessionService, PlayerService playerService) {
        this.sessionService = sessionService;
        this.playerService = playerService;
    }

    @GetMapping("/players")
    @ResponseBody
    public List<PlayerDTO> getPlayersInSession(@RequestBody String sessionID) {
        List<Player> playersInSession = playerService.getPlayersInSession(sessionID);
        List<PlayerDTO> playerNameDTOs = new ArrayList<>();
        for (Player player : playersInSession) {
            playerNameDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerDTO(player));
        }
        return  playerNameDTOs;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerDTO createSession(@RequestParam String playerName) {
        String sessionId = sessionService.createSession();
        Player newPlayer = playerService.createPlayer(playerName, sessionId);
        return DTOMapper.INSTANCE.convertEntityToPlayerDTO(newPlayer);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerDTO joinSession(@RequestParam String playerName, String sessionId) {
        sessionService.joinSession(sessionId);
        Player newPlayer = playerService.createPlayer(playerName, sessionId);
        return DTOMapper.INSTANCE.convertEntityToPlayerDTO(newPlayer);
    }
}