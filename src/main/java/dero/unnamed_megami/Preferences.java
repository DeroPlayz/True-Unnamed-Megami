package dero.unnamed_megami;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Preferences {
    private static PrintWriter SettingsWriter;
    private static BufferedReader SettingsReader;
    
    public static void ViewSettings(){
        MafLib.TimedPrint("What would you like to change?\n" +
            "1. Text Speed\n" +
            "2. Save Game\n" +
            "3. Load Previous Save\n" +
            "0. Save Changes\n",
            Main.menu_text_speed
        );
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            ConfigureTextSpeed();
        }
        else if (Main.Answer == 2){
            Main.User.WriteSave(false);
        }
        else if (Main.Answer == 3){
            MafLib.TimedPrint("Are you sure you want to load a previous save? Any unsaved progress will be lost.\n1. No, go back.\n2. Yes, load a previous save.\n", Main.menu_text_speed);
            Main.Answer = MafLib.askInt();
            if (Main.Answer == 2){
                Main.User.LoadSaves();
            }
            else{
                ViewSettings();
            }
        }
        else if (Main.Answer == 0){
            SaveSettings(false);
            Main.User.GameplayLoop();
        }
    }

    public static void ConfigureTextSpeed(){
        MafLib.TimedPrint("Which text speed do you want?\n", Main.menu_text_speed);
        MafLib.TimedPrint("1. Slow - The quick brown fox jumps over the lazy dog\n", (long) 150);
        MafLib.TimedPrint("2. Normal - The quick brown fox jumps over the lazy dog\n", (long) 100);
        MafLib.TimedPrint("3. Fast - The quick brown fox jumps over the lazy dog\n", (long) 50);
        MafLib.TimedPrint("4. Instant - The quick brown fox jumps over the lazy dog\n", (long) 0);
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            Main.menu_text_speed = 150;
            ViewSettings();
        }
    }
    
    public static void SaveSettings(boolean Folder_Exists){
        try {
            if(Folder_Exists == false){Files.createDirectory(Path.of("User"));}
            SettingsReader = new BufferedReader(new FileReader("User/Settings.umt"));
            ArrayList<String> NewSettings = new ArrayList<>();
            String line = "";
            while(line != null){
                line = SettingsReader.readLine();
                if(line != null){NewSettings.add(line);}
            }
            SettingsWriter = new PrintWriter(new BufferedWriter(new FileWriter("User/Settings.umt")));
            if (Main.menu_text_speed == 150){SettingsWriter.print("MENU_TEXT_SPEED: SLOW");}
            else if (Main.menu_text_speed == 100){SettingsWriter.print("MENU_TEXT_SPEED: NORMAL");}
            else if (Main.menu_text_speed == 50){SettingsWriter.print("MENU_TEXT_SPEED: FAST");}
            else if (Main.menu_text_speed == 0){SettingsWriter.print("MENU_TEXT_SPEED: INSTANT");}
            else {SettingsWriter.print("MENU_TEXT_SPEED: " + Main.menu_text_speed);}
            SettingsWriter.print("\n");
            if (Main.story_text_speed == 150){SettingsWriter.print("STORY_TEXT_SPEED: SLOW");}
            else if (Main.story_text_speed == 100){SettingsWriter.print("STORY_TEXT_SPEED: NORMAL");}
            else if (Main.story_text_speed == 50){SettingsWriter.print("STORY_TEXT_SPEED: FAST");}
            else if (Main.story_text_speed == 0){SettingsWriter.print("STORY_TEXT_SPEED: INSTANT");}
            else {SettingsWriter.print("MENU_TEXT_SPEED: " + Main.menu_text_speed);}
            SettingsWriter.print("\n");
            SettingsWriter.print("# Delay, represented in milliseconds.\n"
                                +"# Valid Options: SLOW (150ms), NORMAL (100ms), FAST (50ms), Instant (0ms),\n"
                                +"  # Custom - Valid Range: 0-9223372036854775807 (Inclusive)");
            SettingsWriter.close();
        } catch (IOException e) {
            SaveSettings(true);
        }
    }

    public static void LoadSettings(){
        try {
            Scanner settingsScanner = new Scanner(new File("User/Settings.umt"));
            System.out.println("Settings loaded.");
            while(settingsScanner.hasNextLine()){
                String current = settingsScanner.nextLine();
                if (current.contains("MENU_TEXT_SPEED")){
                    String value = current.substring(current.indexOf(":")+1);
                    if (value.contains("SLOW")){Main.menu_text_speed = 150;
                        System.out.println("Menu Text Speed: Slow");
                    }
                    else if (value.contains("NORMAL")){Main.menu_text_speed = 100;
                        System.out.println("Menu Text Speed: Normal");
                    }
                    else if (value.contains("FAST")){Main.menu_text_speed = 50;
                        System.out.println("Menu Text Speed: Fast");
                    }
                    else if (value.contains("INSTANT")){Main.menu_text_speed = 0;
                        System.out.println("Menu Text Speed: Instant");
                    }
                    else if (MafLib.isNumeric(value.strip())){Long valueLong = Long.valueOf(value.strip());
                        if (valueLong > Long.MAX_VALUE || valueLong < 0){
                            System.out.println("Sorry! Your text speed for MENU is invalid.\n"
                            + "Please check your settings to ensure there are no negative numbers\n"
                            + "or values beyond Java's Long number limit, which is " + Long.MAX_VALUE + ".");
                            System.exit(0);}
                        else{Main.menu_text_speed = valueLong;
                            System.out.println("Menu Text Speed: Custom (" + Main.menu_text_speed + " milliseconds per character)");
                        }
                    }
                }

                if (current.contains("STORY_TEXT_SPEED")){
                    String value = current.substring(current.indexOf(":")+1);
                    if (value.contains("SLOW")){Main.story_text_speed = 150;
                        System.out.println("Story Text Speed: Slow");
                    }
                    else if (value.contains("NORMAL")){Main.story_text_speed = 100;
                        System.out.println("Story Text Speed: Normal");
                    }
                    else if (value.contains("FAST")){Main.story_text_speed = 50;
                        System.out.println("Story Text Speed: Fast");
                    }
                    else if (value.contains("INSTANT")){Main.story_text_speed = 0;
                        System.out.println("Story Text Speed: Instant");
                    }
                    else if (MafLib.isNumeric(value.strip())){Long valueLong = Long.valueOf(value.strip());
                        if (valueLong > Long.MAX_VALUE || valueLong < 0){
                            System.out.println("Sorry! Your text speed for STORY is invalid.\n"
                            + "Please check your settings to ensure there are no negative numbers\n"
                            + "or values beyond Java's Long number limit, which is " + Long.MAX_VALUE + ".");
                            System.exit(0);}
                        else{Main.story_text_speed = valueLong;
                            System.out.println("Story Text Speed: Custom (" + Main.story_text_speed + " milliseconds per character)");
                        }
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