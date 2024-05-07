package ch.uzh.ifi.hase.soprafs24;

import ch.uzh.ifi.hase.soprafs24.entity.Player;

public class MockPlayerBuilder {

    private String id = "1234";
    private String name = "gugus";
    private String sessionId = "id542369";
    private Integer orderIndex = null;

    public MockPlayerBuilder withPlayerId(String id){
        this.id = id;
        return this;
    }

    public MockPlayerBuilder withSessionId(String id){
        this.sessionId = id;
        return this;
    }

    public MockPlayerBuilder withName(String name){
        this.name = name;
        return this;
    }

    public MockPlayerBuilder withTurnIndex(Integer turnIndex){
        this.orderIndex = turnIndex;
        return this;
    }

    public Player build(){
        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setSessionId(sessionId);
        newPlayer.setId(id);
        newPlayer.setOrderIndex(orderIndex);
        return newPlayer;
    }


}
