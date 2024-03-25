package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.Session;

import ch.uzh.ifi.hase.soprafs24.rest.dto.SessionCreatedDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;





@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    //@Mapping(source = "sessionID", target = "sessionID")
    //@Mapping(source = "playerID", target = "playerID")
    SessionCreatedDTO convertEntityToSessionCreatedDTO(Session session);
}