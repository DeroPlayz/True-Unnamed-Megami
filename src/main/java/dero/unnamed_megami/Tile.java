package dero.unnamed_megami;

public class Tile {
    public char Icon;
    public String Formatting = "";
    public int EncounterChance; // Out of 10;

    public int Destination;
    public int XPos;
    public int YPos;

    public Tile(char Icon, String Formatting, int EncounterChance){
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = EncounterChance;
    }

    public Tile(char Icon, String Formatting, int Destination, int XPos, int YPos){
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = 0;
        this.Destination = Destination;
        this.XPos = XPos;
        this.YPos = YPos;
    }

    public Tile(char Icon, int EncounterChance){
        this.Icon = Icon;
        this.EncounterChance = EncounterChance;
    }

    //Special Tiles
    public static final Tile Blank = new Tile(' ', 0);
    public static final Tile Placeholder = new Tile('?', MafLib.MAGENTA + MafLib.BOLD + MafLib.UNDERLINE, 0);

    //Regular Tiles
    public static final Tile Grass = new Tile('G', MafLib.GREEN, 2);
    public static final Tile Dirt = new Tile('D', MafLib.YELLOW, 4);
    public static final Tile Floor = new Tile('■', 4);
    public static final Tile BedLeft = new Tile('b', MafLib.CYAN, 0);
    public static final Tile BedRight = new Tile('d', MafLib.CYAN, 0);

    //Doors
    public static final Tile Door_User_Bedroom_To_User_House_Hallway = new Tile(
        'D', MafLib.UNDERLINE,
        2, 0, 0);
    public static final Tile Door_User_House_Hallway_To_User_Bedroom = new Tile(
        'D', MafLib.UNDERLINE,
        1, 2, 4);
    
    public static final Tile[] Uncrossable_Tiles = {Blank, BedLeft, BedRight};

    public boolean isCrossable(){
        for(int i = 0; i < Uncrossable_Tiles.length; i++){
            if (Uncrossable_Tiles[i].Icon == Icon
            &&  Uncrossable_Tiles[i].Formatting == Formatting
            &&  Uncrossable_Tiles[i].EncounterChance == EncounterChance
            ){
                return false;
            }
        }
        return true;
    }
    @Override public String toString(){
        return Formatting + Icon + MafLib.RESET; 
    }

    public String Formatted(){
        return "Icon: " + Formatting + Icon + MafLib.RESET + "\nLeads to " + Destination + " at X-" + XPos + ", Y-" + YPos + "\n"; 
    }
}
