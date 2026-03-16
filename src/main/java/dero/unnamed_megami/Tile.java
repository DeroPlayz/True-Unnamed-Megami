package dero.unnamed_megami;

import java.util.ArrayList;

public class Tile {
    public char Icon;
    public String Formatting;
    public int EncounterChance; // Out of 10;
    public int Region;
    public ArrayList<String> SpecialData;

    public Tile(char Icon, String Formatting, int EncounterChance, int Region){
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = EncounterChance;
        this.Region = Region;
    }

    public static final Tile Dirt = new Tile('D', "", 4, 0);
}
