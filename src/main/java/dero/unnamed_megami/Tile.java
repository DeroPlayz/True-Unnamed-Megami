package dero.unnamed_megami;

public class Tile {
    public String ID;
    public char Icon;
    public String Formatting = "";
    public int EncounterChance; // Out of 10;

    public int Destination;
    public int XPos;
    public int YPos;

    public Tile(String ID, char Icon, String Formatting, int EncounterChance){
        this.ID = ID;
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = EncounterChance;
    }

    public Tile(String ID, char Icon, String Formatting, int Destination, int XPos, int YPos){
        this.ID = ID;
        this.Icon = Icon;
        this.Formatting = Formatting;
        this.EncounterChance = 0;
        this.Destination = Destination;
        this.XPos = XPos;
        this.YPos = YPos;
    }

    public Tile(String ID, char Icon, int EncounterChance){
        this.ID = ID;
        this.Icon = Icon;
        this.EncounterChance = EncounterChance;
    }

    //Special Tiles
    public static final Tile Blank = new Tile("Blank", ' ', 0);
    public static final Tile Placeholder = new Tile("Placeholder", '?', MafLib.MAGENTA + MafLib.BOLD + MafLib.UNDERLINE, 0);

    //Indoor Tiles
    public static final Tile UserBedroomWindow = new Tile("UserBedroomWindow", '□', 0);
    public static final Tile Floor = new Tile("Floor", '■', 0);
    public static final Tile BedLeft = new Tile("BedLeft", 'b', MafLib.CYAN, 0);
    public static final Tile BedRight = new Tile("BedRight", 'd', MafLib.CYAN, 0);
    public static final Tile ToiletFacingLeft = new Tile("ToiletFacingLeft", 'd', MafLib.YELLOW, 0);
    public static final Tile ToiletFacingRight = new Tile("ToiletFacingRight", 'b', MafLib.YELLOW, 0);

    //Outdoor Tiles
    public static final Tile Grass = new Tile("Grass", 'G', MafLib.GREEN, 2);
    public static final Tile Dirt = new Tile("Dirt", 'D', MafLib.YELLOW, 4);

    public static final int DEST_User_Bedroom = 1;
    public static final int DEST_User_House_Hallway1 = 2;
    public static final int DEST_User_House_Bathroom1 = 3;
    // public static final int DEST_User_Bedroom = 1;
    // public static final int DEST_User_Bedroom = 1;
    // public static final int DEST_User_Bedroom = 1;

    //Doors
    public static final Tile Door_User_Bedroom_To_User_House_Hallway1 = new Tile(
        "Door_User_Bedroom_To_User_House_Hallway1", 'D', MafLib.UNDERLINE,
        DEST_User_House_Hallway1, 0, 0);

    public static final Tile Door_User_House_Hallway1_To_User_Bedroom = new Tile(
        "Door_User_House_Hallway1_To_User_Bedroom", 'D', MafLib.UNDERLINE,
        DEST_User_Bedroom, 2, 4);

    public static final Tile Door_User_House_Hallway1_To_User_House_Bathroom1 = new Tile(
        "Door_User_House_Hallway1_To_User_House_Bathroom1", 'D', MafLib.UNDERLINE,
        DEST_User_House_Bathroom1, 3, 2);

    public static final Tile Door_User_House_Bathroom1_To_User_House_Hallway1 = new Tile(
        "Door_User_House_Bathroom1_To_User_House_Hallway1", 'D', MafLib.UNDERLINE,
        DEST_User_House_Hallway1, 0, 7);
    
    
    public static final Tile[] Uncrossable_Tiles = {
        Blank,
        BedLeft, BedRight
    };

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
