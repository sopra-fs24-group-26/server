package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.Player;

import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerDTO;
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
}