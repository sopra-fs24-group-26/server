package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.repository.TileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;
@Service
@Transactional
public class TileService {

    private final TileRepository tileRepository;

    @Autowired
    public TileService(@Qualifier("tileRepository") TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }

    public void createTile(String tileId, String sessionId, Integer rotation, Integer xCoordinate, Integer yCoordinate) {
        if(rotation > 3 || rotation < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client Error: Invalid rotation value");
        }
        Tile newTile = new Tile();
        newTile.setId(tileId);
        newTile.setSessionId(sessionId);
        newTile.setRotation(rotation);
        newTile.setCoordinateX(xCoordinate);
        newTile.setCoordinateY(yCoordinate);

        tileRepository.save(newTile);
        tileRepository.flush();
    }

    public List<Tile> getTilesInSession(String SessionId) {
        return tileRepository.findAllBySessionId(SessionId);
    }
}