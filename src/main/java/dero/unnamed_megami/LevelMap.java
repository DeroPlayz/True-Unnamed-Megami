package dero.unnamed_megami;

import static dero.unnamed_megami.Tile.*;

import java.util.HashMap;
import java.util.Map;

public class LevelMap {
    public Tile[][] Bounds;

    public LevelMap(Tile[][] Bounds){
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

    public static final LevelMap UserBedroom = new LevelMap(new Tile[][]{
        {Floor, Floor, Floor, BedLeft, BedRight},
        {UserBedroomWindow, Floor, Floor, Floor, Floor},
        {Floor, Floor, Floor, Floor, Floor},
        {Floor, Floor, Floor, Floor, Floor},
        {Floor, Door_User_Bedroom_To_User_House_Hallway1, Floor, Floor},
    });
    public static final LevelMap UserHouseHallway1 = new LevelMap(new Tile[][]{
        {Door_User_House_Hallway1_To_User_Bedroom, Floor, Blank},
        {Blank, Floor, Placeholder},
        {Blank, Floor},
        {Blank, Floor},
        {Blank, Floor},
        {Blank, Floor, Floor, Floor},
        {Blank, Floor},
        {Door_User_House_Hallway1_To_User_House_Bathroom1, Floor},
    });
    public static final LevelMap UserHouseBathroom1 = new LevelMap(new Tile[][]{
        {Floor, Floor, Floor, Floor},
        {ToiletFacingRight, Floor, Floor, Floor},
        {Floor, Floor, Floor, Door_User_House_Bathroom1_To_User_House_Hallway1},
        {Floor, Floor, Floor, Floor},
    });

    public static final LevelMap Overworld = new LevelMap(new Tile[][]{
        {Grass, Dirt, Floor, Grass, Dirt, Grass, Dirt, Dirt, Dirt, Grass},
        {Dirt, Grass, Grass, Dirt, Grass, Dirt, Grass, Grass, Grass, Dirt}
    });

    public static HashMap<Integer, LevelMap> Maps = new HashMap<>(Map.of(
        1, UserBedroom,
        2, UserHouseHallway1,
        3, UserHouseBathroom1
    ));
}