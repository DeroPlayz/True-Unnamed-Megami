package dero.unnamed_megami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class Player extends Entity{
    static FileInputStream FIS;
    static ObjectInput OIS;

    static FileOutputStream FOS;
    static ObjectOutputStream OOS;

    Player(String Name){
        super(Name, "Human", 0, 0, 0, 10, 0, Map.of(
            "Physical", Entity.Normal,
            "Fire", Entity.Normal,
            "Ice", Entity.Normal,
            "Electric", Entity.Normal,
            "Force", Entity.Normal,
            "Light", Entity.Normal,
            "Dark", Entity.Normal,
            "Almighty", Entity.Normal)
        );
    }

    public void WriteSave(){
        try {
            Files.createDirectory(Path.of("User"));
            int SaveSlot = MafLib.askInt("Which slot will you save in?\n");
            if (SaveSlot < 1 || SaveSlot > 10){
                WriteSave();
            }
            FOS = new FileOutputStream("User/Save" + SaveSlot + ".umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Name);
            OOS.writeObject(Race);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            WriteSave(true);
        }
    }

    public void WriteSave(boolean Folder_Exists){
        try {
            int SaveSlot = MafLib.askInt("Which slot will you save in?\n");
            if (SaveSlot < 0 || SaveSlot > 10){
                WriteSave(true);
            }
            FOS = new FileOutputStream("User/Save" + SaveSlot + ".umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Name);
            OOS.writeObject(Race);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadSaves(){
        int BlankSaves = 0;
        for(int i = 1; i < 11; i++){
            try {
                FIS = new FileInputStream("User/Save" + i + ".umt");
                OIS = new ObjectInputStream(FIS);
                if (String.valueOf(i).length() < 2){System.out.print("0");}
                System.out.println(i + ". " + OIS.readObject());
            } catch (IOException | ClassNotFoundException e) {
                // e.printStackTrace();
                // System.out.println("Slot " + i + " doesn't exist.");
                BlankSaves++;
            }
        }
        if(BlankSaves > 9){
            MafLib.TimedPrint("No saves were found. Creating a new save file.", Main.menu_text_speed);
            WriteSave();
        }
        int slot = MafLib.askInt("Which save will you load?\n");
        try {
            FIS = new FileInputStream("User/Save" + slot + ".umt");
            OIS = new ObjectInputStream(FIS);
            Name = (String) OIS.readObject();
            System.out.println(Name);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ViewSettings(){
        MafLib.TimedPrint("What would you like to change?\n" +
            "1. Text Speed\n",
            Main.menu_text_speed
        );
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            MafLib.TimedPrint("Which text speed do you want?\n", Main.menu_text_speed);
            MafLib.TimedPrint("1. Slow - The quick brown fox jumps over the lazy dog\n", (long) 150);
            MafLib.TimedPrint("2. Normal - The quick brown fox jumps over the lazy dog\n", (long) 100);
            MafLib.TimedPrint("3. Fast - The quick brown fox jumps over the lazy dog\n", (long) 50);
            MafLib.TimedPrint("4. Instant - The quick brown fox jumps over the lazy dog\n", (long) 0);
            Main.Answer = MafLib.askInt();
            if (Main.Answer < 1 || Main.Answer > 4){
                ViewSettings();
            }
        }
    }

    public void WriteSettings(){
        try {
            Files.createDirectory(Path.of("User"));
            FOS = new FileOutputStream("User/Settings.umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeLong(Main.story_text_speed);
            OOS.writeLong(Main.menu_text_speed);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            WriteSettings(true);
        }
    }
    
    public void WriteSettings(boolean Folder_Exists){
        try {
            FOS = new FileOutputStream("User/Settings.umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeLong(Main.story_text_speed);
            OOS.writeLong(Main.menu_text_speed);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadSettings(){
        try {
            Scanner settingsScanner = new Scanner(new File("User/Settings.umt"));
            System.out.println("Settings loaded.");
            while(settingsScanner.hasNextLine()){
                String current = settingsScanner.nextLine();
                if (current.contains("MENU_TEXT_SPEED")){
                    if (current.substring(current.indexOf(":")+1).contains("SLOW")){
                        Main.menu_text_speed = 150;
                        System.out.println("Menu Text Speed: Slow");
                    }
                    else if (current.substring(current.indexOf(":")+1).contains("NORMAL")){
                        Main.menu_text_speed = 100;
                        System.out.println("Menu Text Speed: Normal");
                    }
                    else if (current.substring(current.indexOf(":")+1).contains("FAST")){
                        Main.menu_text_speed = 50;
                        System.out.println("Menu Text Speed: Fast");
                    }
                    else if (current.substring(current.indexOf(":")+1).contains("INSTANT")){
                        Main.menu_text_speed = 0;
                        System.out.println("Menu Text Speed: Instant");
                    }
                    else if (MafLib.isNumeric(current.substring(current.indexOf(":")+1).strip())){
                        Main.menu_text_speed = Long.valueOf(current.substring(current.indexOf(":")+1).strip());
                        System.out.println("Menu Text Speed: Custom (" + Main.menu_text_speed + " milliseconds per character)");
                    }
                }
            }
            settingsScanner.close();
            // Main.story_text_speed = OIS.readLong();
            // Main.menu_text_speed = OIS.readLong();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
