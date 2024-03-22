package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static src.Map.*;

import lib.MafLib;

class Main{
    static Entity Player = new Entity();
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
        Answer = MafLib.askInt("", false);
        if(Answer < 1 || Answer > 3){
            ClearScreen();
            StartUp();
        }
        else if(Answer == 1){
            Player.setName(MafLib.askString(MafLib.CYAN + "What is your name?" + MafLib.RESET, true));
            Save();
        }
        else if(Answer == 2){
            Load();
        }
        else if(Answer == 3){
            Settings();
        }
        System.out.println(WORLD_MAP);
    }

    private static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static void Save(){
        int slot = MafLib.askInt(MafLib.GREEN + "Which slot would you like to save in? (1-10)" + MafLib.RESET, true);
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
        int slot = MafLib.askInt(MafLib.CYAN + "Which slot would you like to load from? (1-10)" + MafLib.RESET, true);
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
            catch(IOException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private static void Settings(){
        System.out.println(MafLib.BLACK + "--- Settings ---");
        System.out.println("0. Go Back" + MafLib.RESET);
        Answer = MafLib.askInt("", false);
        if(Answer == 0){
            ClearScreen();
            StartUp();
        }
    }
}