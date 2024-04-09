package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.SessionDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.TileDTO;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "sessionId", target = "sessionId")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "orderIndex", target = "orderIndex")
    PlayerDTO convertEntityToPlayerDTO(Player player);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "sessionId", target = "sessionId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "isPlaced", target = "isPlaced")
    @Mapping(source = "rotation", target = "rotation")
    @Mapping(source = "coordinateX", target = "coordinateX")
    @Mapping(source = "coordinateY", target = "coordinateY")
    TileDTO convertEntityToTileDTO(Tile tile);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "turnPlayer", target = "turnPlayer")
    @Mapping(source = "playerCount", target = "playerCount")
    @Mapping(source = "seed", target = "seed")
    SessionDTO convertEntityToSessionDTO(Session session);
}