package dero.unnamed_megami;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

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

    public void WriteSave(boolean Folder_Exists){
        try {
            if (Folder_Exists == false){Files.createDirectory(Path.of("User"));}
            int SaveSlot = MafLib.askInt("Which slot will you save in?\n");
            if (SaveSlot < 1 || SaveSlot > 10){
                WriteSave(false);
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
            WriteSave(false);
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
}
