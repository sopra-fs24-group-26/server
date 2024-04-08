package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.MockDataManager;
import ch.uzh.ifi.hase.soprafs24.entity.Player;
import ch.uzh.ifi.hase.soprafs24.entity.Session;
import ch.uzh.ifi.hase.soprafs24.entity.Tile;
import ch.uzh.ifi.hase.soprafs24.rest.dto.JoinDTO;
import ch.uzh.ifi.hase.soprafs24.service.PlayerService;
import ch.uzh.ifi.hase.soprafs24.service.SessionService;
import ch.uzh.ifi.hase.soprafs24.service.TileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private TileService tileService;


    @Test
    public void creatSession_validInput_sessionCreated() throws Exception {
        String mockSessionId = MockDataManager.mockSession().getId();
        Player mockPlayer = MockDataManager.mockPlayer(mockSessionId);
        given(sessionService.createSession()).willReturn(mockSessionId);
        given(playerService.createPlayer(mockPlayer.getName(), mockSessionId)).willReturn(mockPlayer);

        MockHttpServletRequestBuilder postRequest = post("/create").content(mockPlayer.getName());

        mockMvc.perform(postRequest).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(mockPlayer.getId())))
                .andExpect(jsonPath("$.sessionId", is(mockPlayer.getSessionId())))
                .andExpect(jsonPath("$.name", is(mockPlayer.getName())))
                .andExpect(jsonPath("$.role", is(mockPlayer.getRole())))
                .andExpect(jsonPath("$.orderIndex", is(mockPlayer.getOrderIndex())));
    }

    @Test
    public void joinSession_validInput_sessionJoined() throws Exception {
        String mockSessionId = MockDataManager.mockSession().getId();
        Player mockPlayer = MockDataManager.mockPlayer(mockSessionId);
        JoinDTO joinDTO = MockDataManager.mockJoinDTO(mockPlayer.getName(), mockSessionId);

        Mockito.doNothing().when(sessionService).joinSession(mockSessionId);
        given(playerService.createPlayer(mockPlayer.getName(), mockSessionId)).willReturn(mockPlayer);

        MockHttpServletRequestBuilder postRequest = post("/join").contentType(MediaType.APPLICATION_JSON).content(MockDataManager.asJsonString(joinDTO));

        mockMvc.perform(postRequest).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(mockPlayer.getId())))
                .andExpect(jsonPath("$.sessionId", is(mockPlayer.getSessionId())))
                .andExpect(jsonPath("$.name", is(mockPlayer.getName())))
                .andExpect(jsonPath("$.role", is(mockPlayer.getRole())))
                .andExpect(jsonPath("$.orderIndex", is(mockPlayer.getOrderIndex())));
    }
    @Test
    public void pingData_validId_dataSent() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId());
        List<Tile> tiles = MockDataManager.generateMockTiles(mockedSession.getId());
        List<Player> players = MockDataManager.generateMockPlayers(mockedSession.getId());

        given(sessionService.getSessionById(mockedSession.getId())).willReturn(mockedSession);
        given(playerService.getPlayerById(mockedPlayer.getId())).willReturn(mockedPlayer);
        given(playerService.createPlayer(mockedPlayer.getName(), mockedSession.getId())).willReturn(mockedPlayer);
        given(playerService.getPlayersInSession(mockedSession.getId())).willReturn(players);
        given(tileService.getTilesInSession(mockedSession.getId())).willReturn(tiles);

        MockHttpServletRequestBuilder postRequest = post("/ping").content(mockedPlayer.getId());

        mockMvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerDTO.id", is(mockedPlayer.getId())))
                .andExpect(jsonPath("$.playerDTO.name", is(mockedPlayer.getName())))
                .andExpect(jsonPath("$.playerDTO.sessionId", is(mockedPlayer.getSessionId())))
                .andExpect(jsonPath("$.playerDTO.role", is(nullValue()))) // Assuming the role is not set in the mock
                .andExpect(jsonPath("$.playerDTO.orderIndex", is(nullValue()))) // Assuming the order index is not set in the mock
                .andExpect(jsonPath("$.sessionDTO.id", is(mockedSession.getId())))
                .andExpect(jsonPath("$.sessionDTO.playerCount", is(players.size())))
                .andExpect(jsonPath("$.playerDTOs.length()", is(players.size()))) // Assuming the number of players in the list is equal to the size of 'players' list
                .andExpect(jsonPath("$.tileDTOs.length()", is(tiles.size()))); // Assuming the number of tiles in the list is equal to the size of 'tiles' list
    }

    @Test
    public void pingData_invalidId_throwsNotFound() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId());

        MockHttpServletRequestBuilder postRequest = post("/ping").content(mockedPlayer.getId());

        mockMvc.perform(postRequest).andExpect(status().isNotFound());
    }
}