package World;

import static lib.MafLib.*;

public class Tile{
    private String Name; public String getName(){return Name;}
    private boolean canSave; public boolean getSave(){return canSave;}
    private String Letter; public String getLetter(){return Letter;}
    private String Style;

    public Tile(String Name, boolean canSave, String Letter, String Style){
        this.Name = Name;
        this.canSave = canSave;
        this.Letter = Letter;
        this.Style = Style;
        if(Letter.equals("W")){
            this.Style += BOLD;
        }
    }

    public String toString(){
        return Style + Letter + RESET;
    }

    public static final Tile Home = new Tile("Home", true, "H", CYAN + UNDERLINE);
    public static final Tile Path = new Tile("Path", false, "P", YELLOW);
    public static final Tile Mountain = new Tile("Path", false, "M", BLACK);
    
    
    public static final Tile Coliseum = new Tile("Spirit Coliseum", false, "SC", BLUE);
        public static final Tile SCWall = new Tile("Wall", false, "W", BLUE);
    
    public static final Tile Prison = new Tile("Prison of Lies", false, "PL", RED);
        public static final Tile PLWall = new Tile("Wall", false, "W", RED);
    
    public static final Tile Palace = new Tile("Palace of Wrath", false, "PW", MAGENTA);
        public static final Tile PWWall = new Tile("Wall", false, "W", MAGENTA);
    
    public static final Tile Centralis = new Tile("Centralis Genesis", false, "CG", CYAN);
        public static final Tile CGWALL = new Tile("Wall", false, "W", CYAN);
}