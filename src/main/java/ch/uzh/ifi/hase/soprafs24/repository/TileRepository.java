package ch.uzh.ifi.hase.soprafs24.repository;

import org.springframework.stereotype.Repository;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository("tileRepository")
public interface TileRepository extends JpaRepository<Tile, Long> {
    Tile findById(String id);
    List<Tile> findAllBySessionId(String sessionId);
}