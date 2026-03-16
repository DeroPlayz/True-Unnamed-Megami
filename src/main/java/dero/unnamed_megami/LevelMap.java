package dero.unnamed_megami;

import static dero.unnamed_megami.Tile.*;

public class LevelMap {
    public Tile[][] Bounds;

    public LevelMap(Tile[][] Bounds){
        this.Bounds = Bounds;
    }

    @Override public String toString(){
        String s = "";
        for(int i = 0; i < Bounds.length; i++){
            for(int j = 0; j < Bounds[i].length; j++){
                s += Bounds[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static final LevelMap Overworld = new LevelMap(new Tile[][]{
        {Grass, Dirt, Dirt, Grass, Dirt, Grass, Dirt, Dirt, Dirt, Grass},
        {Dirt, Grass, Grass, Dirt, Grass, Dirt, Grass, Grass, Grass, Dirt}
    });
}