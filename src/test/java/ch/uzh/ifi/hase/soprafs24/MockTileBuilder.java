package ch.uzh.ifi.hase.soprafs24;

import ch.uzh.ifi.hase.soprafs24.entity.Tile;

import javax.persistence.Column;

public class MockTileBuilder {

    private String id = "tileId";
    private String sessionId = "id542369";
    private Integer rotation = 0;
    private Integer coordinateX = 5;
    private Integer coordinateY = 2;
    private Boolean discarded = false;

    public MockTileBuilder withTileId(String id){
        this.id = id;
        return this;
    }

    public MockTileBuilder withSessionId(String sessionId){
        this.sessionId = sessionId;
        return this;
    }

    public MockTileBuilder withRotation(Integer r){
        this.rotation = r;
        return this;
    }

    public MockTileBuilder withCoordinateX(Integer x){
        this.coordinateX = x;
        return this;
    }

    public MockTileBuilder withCoordinate(Integer y){
        this.coordinateY = y;
        return this;
    }

    public MockTileBuilder isPlaced(){
        this.discarded = false;
        return this;
    }

    public MockTileBuilder isDiscarded(){
        this.discarded = true;
        return this;
    }

    public Tile build() {
        Tile newTile = new Tile();
        newTile.setId(id);
        newTile.setSessionId(sessionId);
        newTile.setRotation(rotation);
        newTile.setCoordinateX(coordinateX);
        newTile.setCoordinateY(coordinateY);
        newTile.setDiscarded(discarded);
        return newTile;
    }
}
