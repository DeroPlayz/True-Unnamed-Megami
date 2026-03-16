package dero.unnamed_megami;

import java.util.ArrayList;

public class Tile {
    public char Icon;
    public String Formatting = "";
    public int EncounterChance; // Out of 10;
    public int Region;
    public ArrayList<String> SpecialData;

    public Tile(char Icon, String Formatting, int EncounterChance, int Region){
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = EncounterChance;
        this.Region = Region;
    }

    public Tile(char Icon, int EncounterChance, int Region){
        this.Icon = Icon;
        this.EncounterChance = EncounterChance;
        this.Region = Region;
    }

    public static final Tile Grass = new Tile('G', MafLib.GREEN, 4, 0);
    public static final Tile Dirt = new Tile('D', MafLib.YELLOW, 4, 0);

    @Override public String toString(){
        return Formatting + Icon + MafLib.RESET; 
    }
}
