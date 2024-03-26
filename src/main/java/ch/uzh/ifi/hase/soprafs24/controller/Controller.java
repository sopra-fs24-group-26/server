package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;

import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerIDDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerNameDTO;
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
    public List<PlayerNameDTO> getPlayersInSession(@RequestBody String sessionID) {
        List<Player> playersInSession = playerService.getPlayersInSession(sessionID);
        List<PlayerNameDTO> playerNameDTOs = new ArrayList<>();
        for (Player player : playersInSession) {
            playerNameDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerNameDTO(player));
        }
        return  playerNameDTOs;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerIDDTO createSession(@RequestBody String playerName) {
        String sessionID = sessionService.createSession();
        Player newPlayer = playerService.createPlayer(playerName, sessionID);
        PlayerIDDTO playerIDDTO = DTOMapper.INSTANCE.convertEntityToPlayerIDDTO(newPlayer);
        return playerIDDTO;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerIDDTO joinSession(@RequestBody String playerName, @RequestBody String sessionID) {
        sessionService.joinSession(sessionID);
        Player newPlayer = playerService.createPlayer(playerName, sessionID);
        PlayerIDDTO playerIDDTO = DTOMapper.INSTANCE.convertEntityToPlayerIDDTO(newPlayer);
        return playerIDDTO;
    }
}