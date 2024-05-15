package ch.uzh.ifi.hase.soprafs24;

import ch.uzh.ifi.hase.soprafs24.entity.Session;

import java.util.UUID;

public class MockSessionBuilder {
    private String id = "id542369";
    private String seed = "seed";
    private Integer turnIndex = null;

    public MockSessionBuilder withSessionId(String id){
        this.id = id;
        return this;
    }

    public MockSessionBuilder withSeed(String seed) {
        this.seed = seed;
        return this;
    }

    public MockSessionBuilder withTurnIndex(Integer turnIndex){
        this.turnIndex = turnIndex;
        return this;
    }

    public Session build(){
        Session newSession = new Session();
        newSession.setId(id);
        newSession.setTurnIndex(turnIndex);
        newSession.setSeed(seed);
        return newSession;
    }

}
