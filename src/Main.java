package src;

import static src.Entity.Stella;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import lib.MafLib;

class Main{
    static Entity Player = new Entity();
    static Entity[] Party = {Player, Stella, null, null};
    static int Answer = 0;
    public static void main(String[] args){
        // Source - https://stackoverflow.com/a/23487534
        // Posted by bobah, modified by community. See post 'Timeline' for change history
        // Retrieved 2026-02-17, License - CC BY-SA 3.0
        Runtime.getRuntime().addShutdownHook(
        new Thread("app-shutdown-hook") {
            @Override 
            public void run() { 
                System.out.println(MafLib.RESET); 
            }
        });
        ClearScreen();
        StartUp();
    }

    private static void StartUp(){
        //Generally speaking, NG is White, LG is Cyan, Settings are Black, and SG is Green.
        Player.setX(7);
        Player.setZ(7);
        
        System.out.println(MafLib.BOLD + MafLib.MAGENTA + "  🔥Unnamed Megami⚡" + MafLib.RESET);
        System.out.println(MafLib.BLUE + "------ Main Menu -----");
        System.out.println(MafLib.RESET + "1. New Game");
        System.out.println(MafLib.CYAN + "2. Load Game");
        System.out.println(MafLib.BLACK + "3. Settings" + MafLib.RESET);
        Answer = MafLib.askInt("");
        if (Answer < 1 || Answer > 3){
            ClearScreen();
            StartUp();
        }
        else if (Answer == 1){
            New();
        }
        else if (Answer == 2){
            Load();
        }
        else if (Answer == 3){
            Settings();
        }
    }

    private static void New(){
        Player.setName(MafLib.askString(MafLib.CYAN + "What is your name?" + MafLib.RESET + "\n"));
        Save();
    }

    private static void Save(){
        int slot = MafLib.askInt(MafLib.GREEN + "Which slot would you like to save in?\n" + MafLib.RESET);
        if (slot < 1 || slot > 10){
            ClearScreen();
            StartUp();
        }
        else{
            if (new File("User/Save" + slot).exists()){
                MafLib.askString(MafLib.RED + "This slot contains existing save data. If you intend to overwrite it, press Enter to continue.\nIf you do not wish to overwrite the save, press CTRL+C (or CMD+C on Mac) to exit the game.\n" + MafLib.RESET);
            }
            FileOutputStream FOS;
            ObjectOutputStream OOS;
            try {
                FOS = new FileOutputStream(new File("User/Save" + slot));
                OOS = new ObjectOutputStream(FOS);
                OOS.writeObject(Player.getName());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Load(slot);
        }
    }

    private static void Load(){
        //Loads actual save files so the user can make a selection.
        FileInputStream SR;
        String pr = "Which slot would you like to load from?";
        try{
            SR = new FileInputStream("User/Save1");
            for(int i = 1; i <= 10; i++){
                if (new File("User/Save" + i).exists()){
                    SR = new FileInputStream("User/Save" + i);
                    ObjectInputStream ReadFromSave = new ObjectInputStream(SR);
                    pr += "\n" + i + ". " + ReadFromSave.readObject();
                    ReadFromSave.close();
                }
            }
            SR.close();
        }
        catch(IOException | ClassNotFoundException e){}

        //Asks the user to select a slot.
        int slot = MafLib.askInt(MafLib.CYAN + pr + MafLib.RESET + "\n");
        //If the slot chosen is invalid, repeat the question.
        if (slot < 1 || slot > 10){
            ClearScreen();
            Load();
        }

        //If the slot is a real file and thers's save data located inside it, load it.
        else{
            FileInputStream FIS;
            ObjectInputStream OIS;
            try {
            FIS = new FileInputStream(new File("User/Save" + slot));
            OIS = new ObjectInputStream(FIS);
            Player.setName((String) OIS.readObject());
            Loop();
            }
            catch(IOException | ClassNotFoundException e){
                //Create a save file when an attempt is made to load when no save files exist.
                System.out.println(MafLib.RESET + "No save found. Initializing");
                New();
            }
        }
    }

    private static void Load(int slot){
        if (slot < 1 || slot > 10){
            ClearScreen();
            Load();
        }
        else{
            FileInputStream FIS;
            ObjectInputStream OIS;
            try {
            FIS = new FileInputStream(new File("User/Save" + slot));
            OIS = new ObjectInputStream(FIS);
            Player.setName((String) OIS.readObject());
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(MafLib.RESET + "No save found. Initializing");
                New();
            }
        }
    }

    private static void Settings(){
        System.out.println(MafLib.BLACK + "--- Settings ---");
        System.out.println("0. Go Back" + MafLib.RESET);
        Answer = MafLib.askInt("");
        if (Answer == 0){
            ClearScreen();
            StartUp();
        }
    }

    public static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void Loop() {
        ArrayList<Integer> i = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        int j = 0;
        while (i.size() > 0){
            j = (int) Math.round((Math.random()*10) + 1);
            if(i.indexOf(j) >= 0){i.remove(i.indexOf(j));}
            System.out.println(j + " removed.");
            System.out.println("Remaining: " + i.toString());
        }
    }
    public static void Losop() {
        // System.out.println(Map.WORLD_MAP.toString());
        // System.out.println(Player.toString());
        // System.out.println(Stella.toString());
        Answer = MafLib.askInt("What would you like to do?\n" + MafLib.GREEN + "1. Move\n" + MafLib.YELLOW + "2. View Map\n" + MafLib.BLUE + "3. Status\n" + MafLib.BLACK + "4. Settings\n" + MafLib.RESET);
        if (Answer == 1){
            Answer = MafLib.askInt("Which direction?\n1. Up\n2. Down\n3. Left\n4. Right\n");
            if (Answer == 1){Player.setZ(Player.Z - 1);} // Up
            if (Answer == 2){Player.setZ(Player.Z + 1);} // Down
            // P.S. I now understand why lower Y is used for "up" sometimes.
            if (Answer == 3){Player.setX(Player.Z - 1);} // Left
            if (Answer == 4){Player.setX(Player.Z + 1);} // Right

            int demon_attack_chance = (int) Math.round((Math.random()*10));
            System.out.println("Odds of getting attacked by demons: " + demon_attack_chance);
            if (demon_attack_chance <= 4){
                System.out.println(MafLib.RED + MafLib.BOLD + "Oh no! You've been ambushed by demons!" + MafLib.RESET);
            }
        }
        else if (Answer == 2){
            System.out.println(Map.WORLD_MAP.toString());
        }
        else if (Answer == 3){
            Answer = MafLib.askInt("What would you like to check?\n" + MafLib.BLUE + "1. Party\n" + MafLib.GREEN + "2. Inventory\n" + MafLib.RED + "3. Compendium\n" + MafLib.BLACK + "4. Nevermind, go back.\n" + MafLib.RESET);
            if (Answer == 4){
                ClearScreen();
                Loop();
            }
        }
        Loop();
    }
}