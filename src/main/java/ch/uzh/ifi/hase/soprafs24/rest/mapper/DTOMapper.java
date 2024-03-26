package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.Player;

import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerIDDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.PlayerNameDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    @Mapping(source = "playerName", target = "playerName")
    @Mapping(source = "playerID", target = "playerID")
    PlayerIDDTO convertEntityToPlayerIDDTO(Player player);

    @Mapping(source = "playerName", target = "playerName")
    PlayerNameDTO convertEntityToPlayerNameDTO(Player player);
}