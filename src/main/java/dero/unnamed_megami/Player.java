package dero.unnamed_megami;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class Player extends Entity{
    static FileInputStream FIS;
    static ObjectInput OIS;

    static FileOutputStream FOS;
    static ObjectOutputStream OOS;

    public int X_Position;
    public int Y_Position;
    public int Difficulty;
    public ArrayList<String> StoryBeatsCompleted = new ArrayList<>();

    Player(String Name, int Difficulty){
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

    public void CreateSave(){
        MafLib.TimedPrint("Initiating a new save file!\n", Main.menu_text_speed);
            MafLib.TimedPrint("Select difficulty. This can be changed later.\n"
            + "1. Gentle        "
            + "2. Easy\n"
            + "3. Normal        "
            + "4. Challenging   "
            + "5. Hard\n" + MafLib.RED
            + "6. Ruthless      " + MafLib.BOLD
            + "7. Merciless\n" + MafLib.RESET, Main.menu_text_speed);
        Difficulty = MafLib.askInt();
        if (Difficulty < -1 || Difficulty > 7){CreateSave();}
        Name = MafLib.askString("What is your name?\n");
        System.out.println(Name);
        WriteSave(false);
    }

    public void WriteSave(boolean Folder_Exists){
        try {
            if (Folder_Exists == false){Files.createDirectory(Path.of("User"));}
            int SaveSlot = MafLib.askInt("Which slot will you save in?\n");
            if (SaveSlot < 1 || SaveSlot > 10){
                WriteSave(false);
            }
            FOS = new FileOutputStream("User/Save" + SaveSlot + ".umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Difficulty);
            OOS.writeObject(Name);
            OOS.writeObject(Race);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            WriteSave(true);
        }
    }

    public void LoadSaves(){
        int BlankSaves = 0;
        for(int i = 1; i < 11; i++){
            try {
                FIS = new FileInputStream("User/Save" + i + ".umt");
                OIS = new ObjectInputStream(FIS);
                if (String.valueOf(i).length() < 2){System.out.print("0");}
                OIS.readObject();
                System.out.println(i + ". " + OIS.readObject());
            } catch (IOException | ClassNotFoundException e) {
                // e.printStackTrace();
                // System.out.println("Slot " + i + " doesn't exist.");
                BlankSaves++;
            }
        }
        if(BlankSaves > 9){
            MafLib.TimedPrint("No saves were found. Creating a new save file.", Main.menu_text_speed);
            WriteSave(false);
        }
        int slot = MafLib.askInt("Which save will you load?\n");
        try {
            FIS = new FileInputStream("User/Save" + slot + ".umt");
            OIS = new ObjectInputStream(FIS);
            Difficulty = Integer.parseInt(String.valueOf(OIS.readObject()));
            Name = (String) OIS.readObject();
            Race = (String) OIS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void GameplayLoop(){
        MafLib.TimedPrint("How will you proceed?\n", Main.menu_text_speed);
        MafLib.TimedPrint("1. Move", Main.menu_text_speed);
        MafLib.TimedPrint("2. View Party Status", Main.menu_text_speed);
        MafLib.TimedPrint("3. Check Inventory", Main.menu_text_speed);
        MafLib.TimedPrint("4. Settings & Data", Main.menu_text_speed);
        System.out.println();
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            
        }
        if (Main.Answer == 2){

        }
        if (Main.Answer == 3){

        }
        if (Main.Answer == 4){
            Preferences.ViewSettings();
        }
        else{
            MafLib.ClearScreen();
            GameplayLoop();
        }
    }
}
