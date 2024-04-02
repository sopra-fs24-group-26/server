package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.repository.TileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TileService {

    private final TileRepository tileRepository;

    @Autowired
    public TileService(@Qualifier("tileRepository") TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }

    public void drawTile(String sessionId, Integer type) {
        Tile newTile = new Tile();
        newTile.setId(UUID.randomUUID().toString());
        newTile.setSessionId(sessionId);
        newTile.setType(type);
        newTile.setIsPlaced(false);
        tileRepository.save(newTile);
        tileRepository.flush();
    }

    public void placeTile(String tileId, Integer rotation, Integer xCoordinate, Integer yCoordinate) {
        Tile toUpdateTile = tileRepository.findByid(tileId);
        toUpdateTile.setRotation(rotation);
        toUpdateTile.setCoordinateX(xCoordinate);
        toUpdateTile.setCoordinateY(yCoordinate);
        toUpdateTile.setIsPlaced(true);
        tileRepository.save(toUpdateTile);
        tileRepository.flush();
    }

    public List<Tile> getTilesInSession(String SessionId) {
        return tileRepository.findAllBySessionId(SessionId);
    }
}