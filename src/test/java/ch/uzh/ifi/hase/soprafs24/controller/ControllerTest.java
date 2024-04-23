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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void createSession_validInput_sessionCreated() throws Exception {
        String mockSessionId = MockDataManager.mockSession().getId();
        Player mockPlayer = MockDataManager.mockPlayer(mockSessionId, "gugus");
        given(sessionService.createSession()).willReturn(mockSessionId);
        given(playerService.createPlayer(mockPlayer.getName(), mockSessionId)).willReturn(mockPlayer);

        MockHttpServletRequestBuilder postRequest = post("/create").content(mockPlayer.getName());

        MvcResult  result = mockMvc.perform(postRequest)
            .andExpect(status().isCreated())
            .andReturn();

        String returnValue = result.getResponse().getContentAsString();
        assertEquals(mockPlayer.getId(), returnValue);
    }

    @Test
    public void joinSession_validInput_sessionJoined() throws Exception {
        String mockSessionId = MockDataManager.mockSession().getId();
        Player mockPlayer = MockDataManager.mockPlayer(mockSessionId, "gugus");
        JoinDTO joinDTO = MockDataManager.mockJoinDTO(mockPlayer.getName(), mockSessionId);

        Mockito.doNothing().when(sessionService).validateSessionId(mockSessionId);
        given(playerService.createPlayer(mockPlayer.getName(), mockSessionId)).willReturn(mockPlayer);

        MockHttpServletRequestBuilder postRequest = post("/join").contentType(MediaType.APPLICATION_JSON).content(MockDataManager.asJsonString(joinDTO));

        MvcResult  result = mockMvc.perform(postRequest)
            .andExpect(status().isCreated())
            .andReturn();

        String returnValue = result.getResponse().getContentAsString();
        assertEquals(mockPlayer.getId(), returnValue);
    }
    
    @Test
    public void pingData_validId_dataSent() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId(), "gugus");
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
                .andExpect(jsonPath("$.session.id", is(mockedSession.getId())))
                .andExpect(jsonPath("$.players.length()", is(players.size())))
                .andExpect(jsonPath("$.tiles.length()", is(tiles.size())));
    }

    @Test
    public void pingData_invalidId_throwsNotFound() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId(), "gugus");

        MockHttpServletRequestBuilder postRequest = post("/ping").content(mockedPlayer.getId());

        mockMvc.perform(postRequest).andExpect(status().isNotFound());
    }

    @Test
    public void deletePlayer_validId_isOk() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId(), "gugus");

        Mockito.doNothing().when(playerService).deletePlayer(mockedPlayer.getId());
        given(playerService.getPlayerById(mockedPlayer.getId())).willReturn(mockedPlayer);

        MockHttpServletRequestBuilder deleteRequest = post("/deletePlayer").content(mockedPlayer.getId());

        mockMvc.perform(deleteRequest).andExpect(status().isOk());
    }

    @Test
    public void deletePlayer_validId_throwsNotFound() throws Exception {
        Session mockedSession = MockDataManager.mockSession();
        Player mockedPlayer = MockDataManager.mockPlayer(mockedSession.getId(), "gugus");

        MockHttpServletRequestBuilder deleteRequest = delete("/player").content(mockedPlayer.getId());

        mockMvc.perform(deleteRequest).andExpect(status().isNotFound());
    }

    @Test
    public void startGameSession_validId_isOk() throws Exception {
        Session mockedSession = MockDataManager.mockSession();


        // mock validateSession to evaluate request to have valid sessionId
        given(sessionService.getSessionById(mockedSession.getId())).willReturn(mockedSession);

        MockHttpServletRequestBuilder distributeOrderIndexRequest = put("/start").content(mockedSession.getId());
        mockMvc.perform(distributeOrderIndexRequest).andExpect(status().isOk());
        Mockito.verify(playerService).distributeOrderIndex(mockedSession.getId());
        Mockito.verify(sessionService).beginTurn(mockedSession.getId());
    }

    @Test
    public void startGameSession_invalidId_throwsException() throws Exception {
        Session mockedSession = MockDataManager.mockSession();

        // put request to an inexistent session
        MockHttpServletRequestBuilder distributeOrderIndexRequest = put("/start").content("invalid sessionId");
        mockMvc.perform(distributeOrderIndexRequest).andExpect(status().isBadRequest());
        Mockito.verify(playerService, never()).distributeOrderIndex(Mockito.any());
        Mockito.verify(sessionService, never()).beginTurn(Mockito.any());

    }
}