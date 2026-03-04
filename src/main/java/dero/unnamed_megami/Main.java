package dero.unnamed_megami;

import static dero.unnamed_megami.Entity.Stella;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class Main{
    static Entity Player = new Entity();
    static Entity[] Party = {Player, Stella, null, null};
    static ArrayList<Demon> EnemyParty = new ArrayList<>();
    static int Answer = 0;
    static long story_speed = 100;
    static long menu_speed = 0;

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
        try{
            if (new File("User/Settings.txt").exists()){
                FileInputStream SR = new FileInputStream("User/Settings.txt");
                ObjectInputStream ReadConfig = new ObjectInputStream(SR);
                story_speed = (long) ReadConfig.readObject();
                menu_speed = (long) ReadConfig.readObject();
                ReadConfig.close();
                SR.close();
            }
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

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
            Settings(0);
        }
    }

    private static void New(){
        Player.setName(MafLib.askString(MafLib.CYAN + "What is your name?" + MafLib.RESET + "\n"));
        String Intro = "You wake up.| It's Thursday, March 18th of 20XY.|\n"
            + "It's spring break, so you don't need to worry about getting to class on time.|| That's nice.\n";
        PrintStory(Intro);
        Loop();
    }

    private static void AccessFiles(){
        int slot = MafLib.askInt(MafLib.GREEN + "Which slot would you like to save in?\n" + MafLib.RESET);
        if (slot < 1 || slot > 10){
            ClearScreen();
            AccessFiles();
        }
        else{
            if (new File("User/Save" + slot).exists()){
                Answer = MafLib.askInt(MafLib.RED + "This slot contains existing save data.\nIf you wish to overwrite the save, press 1.\nIf you do not wish to overwrite the save, press 2.\n" + MafLib.RESET);
                if (Answer == 2){
                    AccessFiles();
                }
            }
            try {
                Files.delete(Paths.get("User/Save" + slot));
                Files.delete(Paths.get("User"));
                Save(slot);
            } catch (IOException e) {
                Save(slot);
            }
            Load(slot);
        }
    }

    private static void Save(int slot){
        FileOutputStream FOS;
        ObjectOutputStream OOS;
        try {
            Files.createDirectory(Paths.get("User"));
        }
        catch (IOException e) {
            // e.printStackTrace();
        }

        try {
            FOS = new FileOutputStream(new File("User/Save" + slot));
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Player.getName());
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private static void Load(){
        //Loads actual save files so the user can make a selection.
        String pr = "Which slot would you like to load from?";
        try{
            for(int i = 1; i <= 10; i++){
                if (new File("User/Save" + i).exists()){
                    FileInputStream SR = new FileInputStream("User/Save" + i);
                    ObjectInputStream ReadFromSave = new ObjectInputStream(SR);
                    pr += "\n" + i + ". " + ReadFromSave.readObject();
                    ReadFromSave.close();
                    SR.close();
                }
            }
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
            // System.out.println("Fuck #5");
            FileInputStream FIS;
            ObjectInputStream OIS;
            try {
                // System.out.println("Fuck #6");
                FIS = new FileInputStream(new File("User/Save" + slot));
                OIS = new ObjectInputStream(FIS);
                Player.setName((String) OIS.readObject());
            }
            catch(IOException | ClassNotFoundException e){
                // System.out.println("Fuck #7");
                System.out.println(MafLib.RESET + "No save found. Initializing");
                New();
            }
        }
        // System.out.println("Fuck #8");
        Loop();
    }

    private static void Settings(int mode){
        System.out.println(MafLib.BLACK + "--- Settings ---");
        System.out.println("1. Go Back");
        if (mode == 1){
            System.out.println("2. Save");
            System.out.println("3. Load" + MafLib.RESET);
            System.out.println("4. Text Scroll Speed");
        }
        Answer = MafLib.askInt("");
        if (Answer == 1){
            ClearScreen();
            StartUp();
        }
        else if (Answer == 2){
            AccessFiles();
        }
        else if (Answer == 4){
            Answer = MafLib.askInt("Would you like to edit message text speed, or Menu text speed?\n1. Story Text\n2. Menu Text\n");
            System.out.println("Which speed would you like?");
            story_speed = 150;
            PrintStory("1: Slow - The quick brown fox jumps over the lazy dog\n");
            if (Answer == 1){story_speed = 150;}
            if (Answer == 2){menu_speed = 150;}
            
            story_speed = 100;
            PrintStory("2: Regular - The quick brown fox jumps over the lazy dog\n");
            if (Answer == 1){story_speed = 100;}
            if (Answer == 2){menu_speed = 100;}
            
            story_speed = 50;
            PrintStory("3: Fast - The quick brown fox jumps over the lazy dog\n");
            if (Answer == 1){story_speed = 50;}
            if (Answer == 2){menu_speed = 50;}
            
            story_speed = 0;
            PrintStory("4: Instant - The quick brown fox jumps over the lazy dog\n");
            if (Answer == 1){story_speed = 0;}
            if (Answer == 2){menu_speed = 0;}

            Answer = MafLib.askInt("");
            if (Answer == 1){
                story_speed = 150;
            }
            else if (Answer == 2){
                story_speed = 100;
            }
            else if (Answer == 3){
                story_speed = 50;
            }
            else if (Answer == 4){
                story_speed = 0;
            }
            SaveSettings();
        }
    }

    public static void SaveSettings(){
        FileOutputStream FOS;
        ObjectOutputStream OOS;
        try {
            // Files.deleteIfExists(Paths.get("User/Settings"));
            FOS = new FileOutputStream(new File("User/Settings.txt"));
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(story_speed);
            OOS.writeObject(menu_speed);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    public static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void Loop() {
        PrintStory("What would you like to do?\n" + MafLib.GREEN + "1. Move\n" + MafLib.YELLOW + "2. View Map\n" + MafLib.BLUE + "3. Status\n" + MafLib.BLACK + "4. Settings\n" + MafLib.RESET);
        Answer = MafLib.askInt("");
        if (Answer == 1){
            Move();
        }
        else if (Answer == 2){
            System.out.println(Map.WORLD_MAP.toString());
        }
        else if (Answer == 3){
            Answer = MafLib.askInt("What would you like to check?\n" + MafLib.BLUE + "1. Party\n" + MafLib.GREEN + "2. Inventory\n" + MafLib.RED + "3. Compendium\n" + MafLib.BLACK + "4. Nevermind, go back.\n" + MafLib.RESET);
            if (Answer == 1){
                System.out.println(Player);
                System.out.println(Stella);
            }
            else if (Answer == 3){
            }
            else if (Answer == 4){
                ClearScreen();
                Loop();
            }
        }
        else if (Answer == 4){
            Settings(1);
        }
        Loop();
    }

    public static void Move(){
        Answer = MafLib.askInt(MafLib.RESET + "Which direction?\n1. Up\n2. Down\n3. Left\n4. Right\n5. Stop Moving\n");
        if (Answer == 1){Player.setZ(Player.Z - 1);} // Up
        if (Answer == 2){Player.setZ(Player.Z + 1);} // Down
        // P.S. I now understand why lower Y is used for "up" sometimes.
        if (Answer == 3){Player.setX(Player.Z - 1);} // Left
        if (Answer == 4){Player.setX(Player.Z + 1);} // Right

        int demon_attack_chance = (int) Math.round((Math.random()*10)) + 1;
        if(Answer == 5){demon_attack_chance = 10;}

        if (demon_attack_chance <= 4){
            System.out.println(MafLib.RED + MafLib.BOLD + "Oh no! You've been ambushed by demons!" + MafLib.RESET);
            int j = (int) (Math.random()*2+1);
            for(int i = 0; i < j; i++){
                EnemyParty.add(new Demon(Demon.selectDemon()));
            }
            CombatLoop();
        }
        if (Answer != 5){
            Move();
        }
        Loop();
    }

    public static void CombatLoop(){
        while (EnemyParty.size() > 0){
            for(int i = 0; i < EnemyParty.size(); i++){
                System.out.println(EnemyParty.get(i).PrintBrief());
            }
            Player.PlayerChooseCombatAction();
        }
    }

    public static void PrintStory(String message){
        for(int i = 0; i < message.length(); i++){
            /* 0 = Instant
             * 50 = Fast
             * 100 = Regular
             * 150 = Slow
             * 200 = Are you kidding me? */
            long current_speed = story_speed;
            if (String.valueOf(message.charAt(i)).equals("|")){current_speed = story_speed * 2;}
            try {
                Thread.sleep(current_speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!String.valueOf(message.charAt(i)).equals("|")){
                System.out.print(message.charAt(i));}
        }
    }

        public static void PrintMenu(String message){
        for(int i = 0; i < message.length(); i++){
            /* 0 = Instant
             * 50 = Fast
             * 100 = Regular
             * 150 = Slow
             * 200 = Are you kidding me? */
            long current_speed = menu_speed;
            if (String.valueOf(message.charAt(i)).equals("|")){current_speed = menu_speed * 2;}
            try {
                Thread.sleep(current_speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!String.valueOf(message.charAt(i)).equals("|")){
                System.out.print(message.charAt(i));}
        }
    }
}