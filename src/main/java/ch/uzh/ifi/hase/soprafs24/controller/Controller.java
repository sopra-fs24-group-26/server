package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.SessionCreatedDTO;

import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    private final SessionService sessionService;

    Controller(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SessionCreatedDTO createSession(@RequestBody String playerName) {
        Session newSession = sessionService.createSession();
        //Player newPlayer = playerService.createPlayer(playerName, newSession.getSessionID());
        SessionCreatedDTO sessionCreatedDTO = DTOMapper.INSTANCE.convertEntityToSessionCreatedDTO(newSession);
        //sessionPlayerDTO.setPLayerID(newPlayer.getPlayerID())
        return sessionCreatedDTO;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SessionCreatedDTO joinSession(@RequestBody String sessionID) {
        Session joinSession = sessionService.getSessionByID(sessionID);
        //Player newPlayer = playerService.createPlayer(playerName, newSession.getSessionID());
        SessionCreatedDTO sessionPlayerDTO = DTOMapper.INSTANCE.convertEntityToSessionCreatedDTO(joinSession);
        return sessionPlayerDTO;
    }
}





