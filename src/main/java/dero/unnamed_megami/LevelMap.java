package dero.unnamed_megami;

import static dero.unnamed_megami.Tile.*;

import java.util.HashMap;
import java.util.Map;

public class LevelMap {
    public String ID;
    public Tile[][] Bounds;

    public LevelMap(String ID, Tile[][] Bounds){
        this.ID = ID;
        this.Bounds = Bounds;
    }

    @Override public String toString(){
        String s = "";
        for(int i = 0; i < Bounds.length; i++){
            for(int j = 0; j < Bounds[i].length; j++){
                if(Main.User.X_Position == j && Main.User.Y_Position == i){
                    s += "U";
                }
                else{s += Bounds[i][j].toString();}
                s += " ";
            }
            if(i != Bounds.length - 1){s += "\n";}
        }
        return s;
    }

    public static final LevelMap UserBedroom = new LevelMap("User's Bedroom", new Tile[][]{
        {Floor, Floor, Floor, BedLeft, BedRight},
        {Floor, Floor, Floor, Floor, Floor},
        {Floor, Floor, Floor, Floor, Floor},
        {Floor, Floor, Floor, Floor, Floor},
        {Floor, Floor, Door_User_Bedroom_To_User_House_Hallway, Floor, Floor},
    });
    public static final LevelMap UserHouse = new LevelMap("User's House", new Tile[][]{
        {Door_User_House_Hallway_To_User_Bedroom, Floor, Blank},
        {Blank, Floor, Placeholder},
        {Blank, Floor},
        {Blank, Floor},
        {Blank, Floor},
        {Blank, Floor, Floor, Floor},
        {Blank, Floor},
        {Placeholder, Floor},
    });

    public static final LevelMap Overworld = new LevelMap("Overworld", new Tile[][]{
        {Grass, Dirt, Floor, Grass, Dirt, Grass, Dirt, Dirt, Dirt, Grass},
        {Dirt, Grass, Grass, Dirt, Grass, Dirt, Grass, Grass, Grass, Dirt}
    });

    public static HashMap<Integer, LevelMap> Maps = new HashMap<>(Map.of(
        1, UserBedroom,
        2, UserHouse
    ));
}