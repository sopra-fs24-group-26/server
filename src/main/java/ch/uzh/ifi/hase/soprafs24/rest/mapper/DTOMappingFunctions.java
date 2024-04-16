package ch.uzh.ifi.hase.soprafs24.rest.mapper;
import java.util.ArrayList;
import java.util.List;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileDTO;

public class DTOMappingFunctions {

    public static List<TileDTO> convertTilesToTileDTOs(List<Tile> tiles) {
        List<TileDTO> tileDTOs = new ArrayList<>();
        for (Tile tile : tiles) {
            tileDTOs.add(DTOMapper.INSTANCE.convertEntityToTileDTO(tile));
        }
        return tileDTOs;
    }

    public static List<PlayerDTO> convertPlayersToPlayerDTOs(List<Player> players) {
        List<PlayerDTO> playerDTOs = new ArrayList<>();
        for (Player player : players) {
            playerDTOs.add(DTOMapper.INSTANCE.convertEntityToPlayerDTO(player));
        }
        return playerDTOs;
    }

    public static String cleanString(String input) {
        return input.replaceAll("^\"|\"$", "");
    }

}
