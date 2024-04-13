package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.*;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import ch.uzh.ifi.hase.soprafs24.service.TileService;
import ch.uzh.ifi.hase.soprafs24.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/ping")
    @ResponseBody
    public DataDTO getDataByPlayerId(@RequestBody String playerId) {
        validatePlayerId(playerId);
        Player player = playerService.getPlayerById(playerId);
        Session session = sessionService.getSessionById(player.getSessionId());
        List<Player> players = playerService.getPlayersInSession(session.getId());
        List<Tile> tiles = tileService.getTilesInSession(session.getId());
        return createDataDTO(session, players, tiles);
    }

    @PutMapping("/name")
    public void putPlayerName(@RequestBody NameDTO nameDTO) {
        validatePlayerId(nameDTO.getPlayerId());
        playerService.updateName(nameDTO);
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

    @DeleteMapping("/player")
    public void deletePlayer(@RequestBody String playerId) {
        validatePlayerId(playerId);
        playerService.deletePlayer(playerId);
    }

    private List<TileDTO> convertTilesToTileDTOs(List<Tile> tiles) {
        List<TileDTO> tileDTOs = new ArrayList<>();
        for (Tile tile : tiles) {
            tileDTOs.add(DTOMapper.INSTANCE.convertEntityToTileDTO(tile));
        }
        return tileDTOs;
    }

    private List<PlayerDTO> convertPlayersToPlayerDTOs(List<Player> players) {
        List<PlayerDTO> playerDTOs = new ArrayList<>();
        for (Player player : players) {
            playerDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerDTO(player));
        }
        return playerDTOs;
    }

    private void validatePlayerId(String playerId) {
        Player player = playerService.getPlayerById(playerId);
        if (player == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id doesn't match to a player");
    }

    private DataDTO createDataDTO(Session session, List<Player> players, List<Tile> tiles) {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setSession(DTOMapper.INSTANCE.convertEntityToSessionDTO(session));
        dataDTO.setPlayers(convertPlayersToPlayerDTOs(players));
        dataDTO.setTiles(convertTilesToTileDTOs(tiles));
        return dataDTO;
    }
}