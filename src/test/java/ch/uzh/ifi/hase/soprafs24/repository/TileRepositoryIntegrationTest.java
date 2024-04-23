package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TileRepositoryIntegrationTest {

    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private TestEntityManager tileManager;

    private String setUpWithSessionId() {
        String sessionId = UUID.randomUUID().toString();
        for(int i=0; i<5; i++){
            Tile newTile = new Tile();
            String id = "Tile" + i;
            newTile.setId(id);
            newTile.setSessionId(sessionId);
            newTile.setRotation(i%3);
            newTile.setCoordinateX(i-5);
            newTile.setCoordinateY((int)(Math.random()*5));
            tileManager.persist(newTile);
            tileManager.flush();
        }
        Tile newTile = new Tile();
        String id = "id542369";
        newTile.setId(id);
        newTile.setSessionId("1234");
        newTile.setRotation(0);
        newTile.setCoordinateX(2);
        newTile.setCoordinateY(-3);
        tileManager.persist(newTile);
        tileManager.flush();
        return sessionId;
    }

    @Test
    public void whenFindById_ThenReturnTile() {
        String unused = setUpWithSessionId();
        String id = "id542369";
        Tile found = tileRepository.findById(id);
        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getSessionId()).isEqualTo("1234");
        assertThat(found.getRotation()).isEqualTo(0);
        assertThat(found.getCoordinateX()).isEqualTo(2);
        assertThat(found.getCoordinateY()).isEqualTo(-3);
    }

    @Test
    public void whenFindAllBySessionId_ThenReturnAllTiles() {
        String sessionId = setUpWithSessionId();

        List<Tile> found = tileRepository.findAllBySessionId(sessionId);
        assertThat(found.size()).isEqualTo(5);
        for(int i=0; i<found.size(); i++){
            assertThat(found.get(i).getId()).isEqualTo("Tile" + i);
            assertThat(found.get(i).getSessionId()).isEqualTo(sessionId);
            assertThat(found.get(i).getRotation()).isEqualTo(i%3);
            assertThat(found.get(i).getCoordinateX()).isEqualTo(i-5);
            assertThat(found.get(i).getCoordinateY()).isLessThan(5);
        }

    }


}
