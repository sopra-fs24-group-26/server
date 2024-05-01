package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.repository.TileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TileServiceTest {

    @Mock
    private TileRepository tileRepository;

    @InjectMocks
    TileService tileService;

    @Test
    public void whenCreateTile_ValidInputAddsTileToDB() {
        Tile expectedTile = new Tile();
        String dummyStr = "dummy";
        int dummyInt = 1;
        boolean dummyBool = false;
        expectedTile.setId(dummyStr);
        expectedTile.setSessionId(dummyStr);
        expectedTile.setRotation(dummyInt);
        expectedTile.setCoordinateX(dummyInt);
        expectedTile.setCoordinateY(dummyInt);
        expectedTile.setDiscarded(dummyBool);

        List<Tile> mockDataBase = new ArrayList<>();
        Mockito.when(tileRepository.save(any(Tile.class))).thenAnswer(invocation -> {
            Tile newTile = invocation.getArgument(0);
            mockDataBase.add(newTile);
            return null;
        });

        tileService.createTile(dummyStr, dummyStr, dummyInt, dummyInt, dummyInt, dummyBool);
        assertThat(mockDataBase.get(0)).usingRecursiveComparison().isEqualTo(expectedTile);
    }
}
