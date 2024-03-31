package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.JoinDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileIdTypeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TilePlaceDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import ch.uzh.ifi.hase.soprafs24.service.TileService;
import ch.uzh.ifi.hase.soprafs24.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/players")
    @ResponseBody
    public List<PlayerDTO> getPlayersInSession(@RequestBody String sessionId) {
        List<Player> playersInSession = playerService.getPlayersInSession(sessionId);
        List<PlayerDTO> playerNameDTOs = new ArrayList<>();
        for (Player player : playersInSession) {
            playerNameDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerDTO(player));
        }
        return playerNameDTOs;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerDTO createSession(@RequestBody String playerName) {
        String sessionId = sessionService.createSession();
        Player newPlayer = playerService.createPlayer(playerName, sessionId);
        return DTOMapper.INSTANCE.convertEntityToPlayerDTO(newPlayer);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerDTO joinSession(@RequestBody JoinDTO joinData) {
        sessionService.joinSession(joinData.getSessionId());
        Player newPlayer = playerService.createPlayer(joinData.getPlayerName(), joinData.getSessionId());
        return DTOMapper.INSTANCE.convertEntityToPlayerDTO(newPlayer);
    }

    @GetMapping("/tiles")
    @ResponseBody
    public List<TileDTO> getTilesInSession(@RequestBody String sessionId) {
        List<Tile> tilesInSession = tileService.getTilesInSession(sessionId);
        List<TileDTO> tileDTO = new ArrayList<>();

        for (Tile tile : tilesInSession) {
            tileDTO.add(DTOMapper.INSTANCE.convertEntityToTileDTO(tile));
        }

        return tileDTO;
    }

    @PutMapping("/drawTile")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void drawTile(@RequestBody TileIdTypeDTO clientTileIdTypeDTO) {
        tileService.drawTile(clientTileIdTypeDTO.getSessionId(), clientTileIdTypeDTO.getType());
    }

    @PutMapping("/placeTile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void placeTile(@RequestBody TilePlaceDTO tilePlaceDTO) {
        tileService.placeTile(tilePlaceDTO.getId(), tilePlaceDTO.getRotation(),
                tilePlaceDTO.getCoordinateX(), tilePlaceDTO.getCoordinateY());
    }
}