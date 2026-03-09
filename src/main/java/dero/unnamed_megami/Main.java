package dero.unnamed_megami;

import static dero.unnamed_megami.Entity.Stella;

import java.util.Comparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
    static Entity Player = new Entity();
    static Entity[] Party = {Player, Stella};
    static ArrayList<Demon> EnemyParty = new ArrayList<>();
    static ArrayList<Entity> TurnOrder = new ArrayList<>();
    static int Answer = 0;
    static long story_text_speed = 100;
    static long menu_text_speed = 0;
    
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
                story_text_speed = (long) ReadConfig.readObject();
                menu_text_speed = (long) ReadConfig.readObject();
                ReadConfig.close();
                SR.close();
            }
        }
        catch(IOException | ClassNotFoundException e){
            // e.printStackTrace();
        }

        //Generally speaking, NG is White, LG is Cyan, Settings are Black, and SG is Green.
        Player.setX(7);
        Player.setZ(7);
        
        System.out.println(MafLib.BOLD + MafLib.MAGENTA + "  🔥Unnamed Megami⚡" + MafLib.RESET);
        System.out.println(MafLib.BLUE + "------ Main Menu -----");
        System.out.println(MafLib.RESET + "1. New Game");
        System.out.println(MafLib.CYAN + "2. Load Game");
        System.out.println(MafLib.BLACK + "3. Settings" + MafLib.RESET);
        Answer = MafLib.askInt();
        if (Answer == 1){
            New();
        }
        else if (Answer == 2){
            Load();
        }
        else if (Answer == 3){
            Settings(0);
        }
        else {
            ClearScreen();
            StartUp();
        }
    }

    public static void Loop() {
        PrintMenu("What would you like to do?\n" + MafLib.GREEN + "1. Move\n" + MafLib.YELLOW + "2. View Map\n" + MafLib.BLUE + "3. Status\n" + MafLib.BLACK + "4. Settings\n" + MafLib.RESET);
        Answer = MafLib.askInt("");
        if (Answer == 1){
            Move();
        }
        else if (Answer == 2){
            PrintMenu(Map.WORLD_MAP.toString() + "\n");
        }
        else if (Answer == 3){
            Answer = MafLib.askInt("What would you like to check?\n" + MafLib.BLUE + "1. Party\n" + MafLib.GREEN + "2. Inventory\n" + MafLib.RED + "3. Compendium\n" + MafLib.BLACK + "4. Nevermind, go back.\n" + MafLib.RESET);
            if (Answer == 1){
                PrintMenu(Player.toString() + "\n");
                PrintMenu(Stella.toString() + "\n");
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
        PrintMenu(MafLib.RESET + "Which direction?\n1. Up\n2. Down\n3. Left\n4. Right\n5. Stop Moving\n");
        Answer = MafLib.askInt("");
        if (Answer == 1){Player.setZ(Player.Z - 1);} // Up
        if (Answer == 2){Player.setZ(Player.Z + 1);} // Down
        // P.S. I now understand why lower Y is used for "up" sometimes.
        if (Answer == 3){Player.setX(Player.Z - 1);} // Left
        if (Answer == 4){Player.setX(Player.Z + 1);} // Right

        int demon_attack_chance = (int) Math.round((Math.random()*10)) + 1;
        if(Answer == 5){demon_attack_chance = 10;}

        if (demon_attack_chance <= 4){
            PrintMenu(MafLib.RED + MafLib.BOLD + "Oh no! You've been ambushed by demons!" + MafLib.RESET + "\n");
            System.out.print("\n\n\n");
            int j = (int) (Math.random()*2+1);
            for(int i = 0; i < j; i++){
                EnemyParty.add(new Demon(Demon.selectDemon()));
            }
            TurnOrder.addAll(EnemyParty);
            TurnOrder.addAll(Arrays.asList(Party));
            TurnOrder.sort(Comparator.comparingInt(Entity::getAgility).reversed());
            
            for(int i = 0; i < TurnOrder.size(); i++){
                System.out.println(TurnOrder.get(i).getName());
            }

            CombatLoop();
        }
        if (Answer != 5){
            Move();
        }
        Loop();
    }

    public static void CombatLoop(){
        for (int i = 0; i < TurnOrder.size(); i++){
            if(TurnOrder.get(i).getCurrentHP() < 1){TurnOrder.remove(i);}
        }
        TurnOrder.trimToSize();
        if (current_turn > TurnOrder.size()-1){current_turn = 0;}
        // System.out.println("Current Turn: " + current_turn);
        // System.out.println("Turn Order Size: " + TurnOrder.size());
        while (EnemyParty.size() > 0){
            if(TurnOrder.get(current_turn) == Player){
                for(int i = 0; i < TurnOrder.size(); i++){
                    PrintMenu(TurnOrder.get(i).PrintBrief() + "\n");
                }
                Player.Act();
            }
            else if(TurnOrder.get(current_turn) == Stella){
                for(int i = 0; i < EnemyParty.size(); i++){
                    PrintMenu(EnemyParty.get(i).PrintBrief() + "\n");
                }
                Stella.Act();
            }
            else{TurnOrder.get(current_turn).Act();}
        }
        TurnOrder.clear();
    }

    public static void ClearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}