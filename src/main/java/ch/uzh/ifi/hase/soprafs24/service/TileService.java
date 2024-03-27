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

    public void drawTile(String sessionID, Integer type) {
        Tile newTile = new Tile();
        newTile.setTileID(UUID.randomUUID().toString());
        newTile.setSessionID(sessionID);
        newTile.setType(type);
        newTile.setIsPlaced(false);

        tileRepository.save(newTile);
        tileRepository.flush();
    }

    public void placeTile(String tileID, Integer rotation, Integer xCoordinate, Integer yCoordinate) {
        Tile toUpdateTile = tileRepository.findByTileID(tileID);
        toUpdateTile.setRotation(rotation);
        toUpdateTile.setCoordinateX(xCoordinate);
        toUpdateTile.setCoordinateY(yCoordinate);
        toUpdateTile.setIsPlaced(true);

        tileRepository.save(toUpdateTile);
        tileRepository.flush();
    }

    public List<Tile> getTilesInSession(String SessionID) {
        List<Tile> TileInSession = tileRepository.findAllBySessionID(SessionID);
        return TileInSession;
    }
}