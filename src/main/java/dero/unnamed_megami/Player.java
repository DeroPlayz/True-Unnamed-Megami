package dero.unnamed_megami;

import java.util.ArrayList;
import java.util.HashMap;
import static dero.unnamed_megami.Main.Player_Party;

public class Player extends Entity{

    public int Y_Position = 1;
    public int X_Position = 3;
    public LevelMap Region = LevelMap.UserBedroom;

    public ArrayList<Item> Inventory;

    public int Difficulty;

    Player(String Name, int Difficulty){
        super(Name, "Human", 0, 0, 0, 10, 0,
            Entity.Normal,
            Entity.Normal,
            Entity.Normal,
            Entity.Normal,
            Entity.Normal,
            Entity.Normal,
            Entity.Normal);
        this.Difficulty = Difficulty;
        GainExp(0);
    }

    public void GainExp(int Amount){
        XP_Needed = Level+1*(150);
        XP += Amount;
        if (XP >= XP_Needed){
            Lifetime_XP += XP;
            XP = 0;
            Level++;
        }
    }

    public String toString(){
        String[] diffs = {"Gentle", "Easy", "Normal", "Challenging", "Hard", MafLib.RED + "Ruthless" + MafLib.RESET, MafLib.RED + MafLib.BOLD + "Merciless" + MafLib.RESET};
        return Name + "\nLevel " + Level + " (" + XP + " xp, " + (XP_Needed-XP) + " until Level " + (Level+1) + ")" + "\nRace: " + Race + "\nHP: "
        + Current_HP + "/" + Max_HP + "\nMP: " + Current_MP + "/" + Max_MP + "\nMacca: " + Macca + " ♄\nDifficulty: " + diffs[Difficulty] + "\n";
    }

    public void GameplayLoop(){
        MafLib.TimedPrint("How will you proceed?\n", Main.menu_text_speed);
        MafLib.TimedPrint("1. Move\n", Main.menu_text_speed);
        MafLib.TimedPrint("2. View Map\n", Main.menu_text_speed);
        MafLib.TimedPrint("3. View Party Status\n", Main.menu_text_speed);
        MafLib.TimedPrint("4. Check Inventory\n", Main.menu_text_speed);
        MafLib.TimedPrint("5. Settings & Data\n", Main.menu_text_speed);
        MafLib.TimedPrint("6. Check Story Log\n", Main.menu_text_speed);
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            System.out.println(Region);
            Main.Answer = -1;
            MafLib.TimedPrint("Select your desired path.\n", Main.menu_text_speed);
            MafLib.TimedPrint("1. Up\n", Main.menu_text_speed);
            MafLib.TimedPrint("2. Down\n", Main.menu_text_speed);
            MafLib.TimedPrint("3. Left\n", Main.menu_text_speed);
            MafLib.TimedPrint("4. Right\n", Main.menu_text_speed);
            MafLib.TimedPrint("5. Stop Moving\n", Main.menu_text_speed);
            while(Main.Answer < 1 || Main.Answer > 5){
                Main.Answer = MafLib.askInt();
            }
            // Move(Main.Answer);
        }
        else if (Main.Answer == 2){
            System.out.println(Region);
            MafLib.TimedPrint("Press enter when you are done.", Main.menu_text_speed);
            MafLib.WaitForEnter();
        }
        else if (Main.Answer == 3){
            CheckPartyStatus();
        }
        else if (Main.Answer == 4){

        }
        else if (Main.Answer == 5){
            Preferences.ViewSettings();
        }
        else{
            MafLib.ClearScreen();
        }
        GameplayLoop();
    }

    public void CheckPartyStatus(){
        MafLib.TimedPrint("Which party member would you like to check?\n", Main.menu_text_speed);
        for(int i = 0; i < Player_Party.length; i++){
            MafLib.TimedPrint((i+1) + ". " + Player_Party[i].Name + " (" + Player_Party[i].Race + ", Level " + Player_Party[i].Level + ")\n", Main.menu_text_speed);
        }
        Main.Answer = MafLib.askInt();
        if (Main.Answer < 1 || Main.Answer > Player_Party.length){
            CheckPartyStatus();
        }
        else{
            MafLib.TimedPrint(Player_Party[Main.Answer - 1].toString(), Main.menu_text_speed);
            MafLib.TimedPrint("Press enter to close this menu.", Main.menu_text_speed);
            MafLib.WaitForEnter();
        }
    }
}
