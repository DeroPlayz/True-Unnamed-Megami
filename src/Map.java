package src;

import static src.Tile.*;
import static src.Main.Player;

public class Map {
    String Name;
    Tile[][] Board;

    Map(String Name, Tile[][] Board){
        this.Name = Name;
        this.Board = Board;
    }

    public static final Map WORLD_MAP = new Map("World Map", new Tile[][]{
    //              1           2           3           4         5           6           7           8           9           10          11          12          13          14          15          16          17          18         },
    /*Y=1*/     {SCWall,    SCWall,     SCWall,     SCWall,     SCWall,     Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=2*/     {SCWall,    Coliseum,   Coliseum,   Coliseum,   SCWall,     Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=3*/     {SCWall,    Coliseum,   Coliseum,   Coliseum,   SCWall,     Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=4*/     {SCWall,    Coliseum,   Coliseum,   Coliseum,   SCWall,     Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=5*/     {SCWall,    SCWall,     SCWall,     SCWall,     SCWall,     Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=6*/     {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=7*/     {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=8*/     {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=9*/     {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=10*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=11*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,   Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=12*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,},
    /*Y=13*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Path,       Path,    },
    /*Y=14*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,    },
    /*Y=15*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=16*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,    },
    /*Y=17*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,},
    /*Y=18*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Home,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,},
    /*Y=19*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,},
    /*Y=20*/    {Path,      Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Path,       Mountain,   Mountain,   Mountain,   Mountain,   Mountain,   Mountain,},
    });

    public String toString(){
        String s = "";
        s += "WM 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20\n";
        for(int i = 0; i < Board.length; i++){
            if(i < 9){
                s += "0";
            }
            s += String.valueOf(i+1) + " ";
            for(int j = 0; j < Board[i].length; j++){
                if(Player.getX() == j && Player.getZ() == i){
                    s += "Y";
                }
                else{
                    s += Board[i][j];
                }
                if(Board[i][j].getLetter().length() == 1){
                    s += " ";
                }
                s += " ";
            }
            s += "\n";
        }
        return s;
    }
}
