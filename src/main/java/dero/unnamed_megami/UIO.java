package dero.unnamed_megami;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class UIO {
    static FileInputStream FIS;
    static ObjectInput OIS;
    static FileOutputStream FOS;
    static ObjectOutputStream OOS;

    public static void CreateSave(){
        MafLib.TimedPrint("Initiating a new save file!\n", Main.menu_text_speed);
            MafLib.TimedPrint("Select difficulty. This can be changed later.\n"
            + "1. Gentle        "
            + "2. Easy\n"
            + "3. Normal        "
            + "4. Challenging   "
            + "5. Hard\n" + MafLib.RED
            + "6. Ruthless      " + MafLib.BOLD
            + "7. Merciless\n" + MafLib.RESET, Main.menu_text_speed);
        Main.User.Difficulty = MafLib.askInt();
        if (Main.User.Difficulty < 1 || Main.User.Difficulty > 7){CreateSave();}
        Main.User.Name = MafLib.askString("What is your name?\n");
        System.out.println(Main.User.Name);
        WriteSave(false);
    }

    public static void WriteSave(boolean Folder_Exists){
        try {
            int SaveSlot = MafLib.askInt("Which slot will you save in?\n");
            if (Folder_Exists == false){Files.createDirectory(Path.of("User/Save" + SaveSlot));}
            if (SaveSlot < 1 || SaveSlot > 10){
                WriteSave(false);
            }
            FOS = new FileOutputStream("User/Save" + SaveSlot + "/Player.umt");
            OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(Main.User.Difficulty);
            OOS.writeObject(Main.User.Name);
            OOS.writeObject(Main.User.Race);
            OOS.close();
            FOS.close();
        } catch (IOException e) {
            WriteSave(true);
        }
    }

    public static void RetrieveSaves(){
        int BlankSaves = 0;
        for(int i = 1; i < 11; i++){
            try {
                FIS = new FileInputStream("User/Save" + i + "/Player.umt");
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
    }

    public static void LoadSave(){
        int slot = MafLib.askInt("Which save will you load?\n");
        try {
            FIS = new FileInputStream("User/Save" + slot + "/Player.umt");
            OIS = new ObjectInputStream(FIS);
            Main.User.Difficulty = Integer.parseInt(String.valueOf(OIS.readObject()));
            Main.User.Name = (String) OIS.readObject();
            Main.User.Race = (String) OIS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Main.User.GameplayLoop();
    }
}
