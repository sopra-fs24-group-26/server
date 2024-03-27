package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerIDDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerNameDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileIDTypeDTO;
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
    public List<PlayerNameDTO> getPlayersInSession(@RequestBody String sessionID) {
        List<Player> playersInSession = playerService.getPlayersInSession(sessionID);
        List<PlayerNameDTO> playerNameDTOs = new ArrayList<>();
        for (Player player : playersInSession) {
            playerNameDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerNameDTO(player));
        }
        return playerNameDTOs;
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

    @GetMapping("/tiles")
    @ResponseBody
    public List<TileDTO> getTilesInSession(@RequestBody String sessionID) {
        List<Tile> tilesInSession = tileService.getTilesInSession(sessionID);
        List<TileDTO> tileDTO = new ArrayList<>();

        for (Tile tile : tilesInSession) {
            tileDTO.add(DTOMapper.INSTANCE.convertEntityToTileDTO(tile));
        }

        return tileDTO;
    }

    @PutMapping("/drawTile")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void drawTile(@RequestBody TileIDTypeDTO clientTileIDTypeDTO) {
        tileService.drawTile(clientTileIDTypeDTO.getSessionID(), clientTileIDTypeDTO.getType());
    }

    @PutMapping("/placeTile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void placeTile(@RequestBody TilePlaceDTO tilePlaceDTO) {
        tileService.placeTile(tilePlaceDTO.getTileID(), tilePlaceDTO.getRotation(),
                tilePlaceDTO.getCoordinateX(), tilePlaceDTO.getCoordinateY());
    }
}