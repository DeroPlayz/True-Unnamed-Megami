package src;

import static src.Entity.Stella;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lib.MafLib;

class Main{
    static Entity Player = new Entity();
    static Entity[] Party = {Player, Stella, null, null};
    static int Answer = 0;
    public static void main(String[] args){
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
        if(Answer < 1 || Answer > 3){
            ClearScreen();
            StartUp();
        }
        else if(Answer == 1){
            New();
        }
        else if(Answer == 2){
            Load();
        }
        else if(Answer == 3){
            Settings();
        }
    }

    /*
     * START
     * SAVE
     * LOAD
     * SETTINGS
     * CLEAR SCREEN
     */

    private static void New(){
        String s = MafLib.askString(MafLib.CYAN + "What is your name?" + MafLib.RESET + "\n");
        Player.setName(s);
        Save();
    }

    private static void Save(){
        int slot = MafLib.askInt(MafLib.GREEN + "Which slot would you like to save in?\n" + MafLib.RESET + "\n");
        if(slot < 1 || slot > 10){
            ClearScreen();
            StartUp();
        }
        else{
            FileOutputStream FOS;
            ObjectOutputStream OOS;
            try {
            FOS = new FileOutputStream(new File("User/Save" + slot));
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Player.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Load(){
        FileInputStream SR;
        String pr = "Which slot would you like to load from?";
        try{
            SR = new FileInputStream("User/Save1");
            for(int i = 1; i <= 10; i++){
                if(new File("User/Save" + i).exists()){
                    SR = new FileInputStream("User/Save" + i);
                    pr += "\n" + i + ". " + SR.read();
                }
            }
            SR.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int slot = MafLib.askInt(MafLib.CYAN + pr + MafLib.RESET + "\n");
        if(slot < 1 || slot > 10){
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
        if(Answer == 0){
            ClearScreen();
            StartUp();
        }
    }

    public static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}