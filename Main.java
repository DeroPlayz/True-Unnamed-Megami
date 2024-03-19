import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import lib.MafLib;

class Main{
    Entity Player;
    static int Answer = 0;
    public static void main(String[] args){
        StartUp();
    }

    private static void StartUp(){
        System.out.println(MafLib.RED + MafLib.BOLD + MafLib.MAGENTA + "  🔥Unnamed Megami⚡" + MafLib.RESET);
        System.out.println(MafLib.BLUE + "------ Main Menu ------");
        System.out.println(MafLib.RESET + "1. New Game");
        System.out.println(MafLib.GREEN + "2. Load Game");
        System.out.println(MafLib.YELLOW + "3. Settings" + MafLib.RESET);
        
        Answer = MafLib.askInt("", false);
        if(Answer < 1 || Answer > 3){
            ClearScreen();
            StartUp();
        }
    }

    private static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    private void Save(){
        FileOutputStream FOS;
        ObjectOutputStream OOS;
        try {
        FOS = new FileOutputStream(new File("Save"));
        OOS = new ObjectOutputStream(FOS);
        OOS.writeObject(Player.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}