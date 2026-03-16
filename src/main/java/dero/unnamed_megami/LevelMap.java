package dero.unnamed_megami;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LevelMap {
    public Map<Tile, ArrayList<Tile>> Bounds;

    @Override public String toString(){
        String s = "";
        for (int i = 0; i < Bounds.size(); i++){
            for (int j = 0; j < Bounds.get(i).size(); j++){
                s += Bounds.get(i).get(j);
            }
        }
        return s;
    }

    public LevelMap(Map<Tile, ArrayList<Tile>> Bounds){
        this.Bounds = Bounds;
    }

    public static LevelMap Overworld = new LevelMap();
}
