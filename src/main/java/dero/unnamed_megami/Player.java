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

import javax.swing.plaf.synth.Region;

public class Player extends Entity{
    static FileInputStream FIS;
    static ObjectInput OIS;

    static FileOutputStream FOS;
    static ObjectOutputStream OOS;

    public int Y_Position = 1;
    public int X_Position = 3;
    public LevelMap Region = LevelMap.UserBedroom;
    
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
        MafLib.TimedPrint("1. Move\n", Main.menu_text_speed);
        MafLib.TimedPrint("2. View Map\n", Main.menu_text_speed);
        MafLib.TimedPrint("3. View Party Status\n", Main.menu_text_speed);
        MafLib.TimedPrint("4. Check Inventory\n", Main.menu_text_speed);
        MafLib.TimedPrint("5. Settings & Data\n", Main.menu_text_speed);
        Main.Answer = MafLib.askInt();
        if (Main.Answer == 1){
            QueryMove();
        }
        else if (Main.Answer == 2){
            System.out.println(Region);
            MafLib.TimedPrint("Press enter when you are done.", Main.menu_text_speed);
            MafLib.WaitForEnter();
        }
        else if (Main.Answer == 3){

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

    public void QueryMove(){
        MafLib.TimedPrint("Select your desired patht.\n", Main.menu_text_speed);
        MafLib.TimedPrint("1. Up\n", Main.menu_text_speed);
        MafLib.TimedPrint("2. Down\n", Main.menu_text_speed);
        MafLib.TimedPrint("3. Left\n", Main.menu_text_speed);
        MafLib.TimedPrint("4. Right\n", Main.menu_text_speed);
        MafLib.TimedPrint("5. Stop Moving\n", Main.menu_text_speed);
        Main.Answer = MafLib.askInt();
        Move(Main.Answer);
    }

    public void Move(int Direction){
        if (
            Direction == 1
         && Y_Position != 0
         && -1 < Y_Position - 1
         && Region.Bounds[Y_Position - 1][X_Position] != null
         && Region.Bounds[Y_Position - 1][X_Position].isCrossable()){
            Y_Position--;
        }
        else if (
            Direction == 2
         && Y_Position != Region.Bounds.length
         && Region.Bounds.length > Y_Position + 1
         && Region.Bounds[Y_Position + 1][X_Position] != null
         && Region.Bounds[Y_Position + 1][X_Position].isCrossable()){
            Y_Position++;
        }
        else if (
            Direction == 3
         && X_Position != 0
         && -1 < X_Position - 1
         && Region.Bounds[Y_Position][X_Position - 1] != null
         && Region.Bounds[Y_Position][X_Position - 1].isCrossable()){
            X_Position--;
        }
        else if (
            Direction == 4
         && Y_Position != Region.Bounds[Y_Position].length
         && Region.Bounds[Y_Position].length > X_Position + 1
         && Region.Bounds[Y_Position][X_Position + 1] != null
         && Region.Bounds[Y_Position][X_Position + 1].isCrossable()){
            X_Position++;
        }

       LevelMap Destination = LevelMap.Maps.get(Region.Bounds[Y_Position][X_Position].Destination);
        if (Destination != null){
            int new_X = Region.Bounds[Y_Position][X_Position].XPos;
            int new_Y = Region.Bounds[Y_Position][X_Position].YPos;
            X_Position = new_X;
            Y_Position = new_Y;
            Region = Destination;
        }
        MafLib.ClearScreen();
        System.out.println(Region);
        Move(MafLib.askInt());
    }
}
