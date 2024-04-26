package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.*;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMappingFunctions;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import ch.uzh.ifi.hase.soprafs24.service.TileService;
import ch.uzh.ifi.hase.soprafs24.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {

    private final SessionService sessionService;
    private final PlayerService playerService;
    private final TileService tileService;

    Controller(SessionService sessionService, PlayerService playerService, TileService tileService) {
        this.sessionService = sessionService;
        this.playerService = playerService;
        this.tileService = tileService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createSession(@RequestBody String playerName) {
        String sessionId = sessionService.createSession();
        String name = DTOMappingFunctions.cleanString(playerName);
        Player newPlayer = playerService.createPlayer(name, sessionId);
        return newPlayer.getId();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String joinSession(@RequestBody JoinDTO joinData) {
        sessionService.validateSessionId(joinData.getSessionId());

        if (playerService.getPlayersInSession(joinData.getSessionId()).size() == 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player maximum has been reached");
        }

        Player newPlayer = playerService.createPlayer(joinData.getPlayerName(), joinData.getSessionId());
        return newPlayer.getId();
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Boolean checkPlayerId(@RequestBody String playerId) {
        String id = DTOMappingFunctions.cleanString(playerId);
        return playerService.getPlayerById(id) != null;
    }

    @PostMapping("/ping")
    @ResponseBody
    public DataDTO getDataByPlayerId(@RequestBody String playerId) {
        String id = DTOMappingFunctions.cleanString(playerId);
        validatePlayerId(id);
        Player player = playerService.getPlayerById(id);
        Session session = sessionService.getSessionById(player.getSessionId());
        List<Player> players = playerService.getPlayersInSession(session.getId());
        List<Tile> tiles = tileService.getTilesInSession(session.getId());
        return createDataDTO(session, players, tiles);
    }

    @PutMapping("/placeTile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void placeTile(@RequestBody TileDTO tileDTO) {
        tileService.createTile(tileDTO.getId(), tileDTO.getSessionId(), tileDTO.getRotation(), tileDTO.getCoordinateX(),
                tileDTO.getCoordinateY());
        sessionService.incrementTurnIndex(tileDTO.getSessionId());
    }

    @PostMapping("/deletePlayer")
    public void deletePlayer(@RequestBody String playerId) {
        String id = DTOMappingFunctions.cleanString(playerId);
        validatePlayerId(id);
        Player player = playerService.getPlayerById(id);
        String sessionId = player.getSessionId();
        
        if (playerService.getPlayersInSession(sessionId).size() == 1){
            sessionService.deleteSession(sessionId);
        }
        playerService.deletePlayer(id);
    }

    @PutMapping("/start")
    public void startGameSession(@RequestBody String sessionId) {
        String id = DTOMappingFunctions.cleanString(sessionId);
        sessionService.validateSessionId(id);
        playerService.distributeOrderIndex(id);
        sessionService.beginTurn(id);
    }

    private void validatePlayerId(String playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id doesn't match to a player");
    }


    private DataDTO createDataDTO(Session session, List<Player> players, List<Tile> tiles) {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setSession(DTOMapper.INSTANCE.convertEntityToSessionDTO(session));
        dataDTO.setPlayers(DTOMappingFunctions.convertPlayersToPlayerDTOs(players));
        dataDTO.setTiles(DTOMappingFunctions.convertTilesToTileDTOs(tiles));
        return dataDTO;
    }
}