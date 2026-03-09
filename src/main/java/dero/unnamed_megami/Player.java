package dero.unnamed_megami;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class Player extends Entity{    
    static FileOutputStream FIS;
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
            FIS = new FileOutputStream("User/" + Name + ".save");
            OOS = new ObjectOutputStream(FIS);
            OOS.writeObject(Name);
            OOS.writeObject(Race);
            OOS.close();
            FIS.close();
        } catch (IOException e) {
            WriteSave(true);
        }
    }

    public void WriteSave(boolean Folder_Exists){
        try {
            FIS = new FileOutputStream("User/" + Name + ".save");
            OOS = new ObjectOutputStream(FIS);
            OOS.writeObject(Name);
            OOS.writeObject(Race);
            OOS.close();
            FIS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowSaves(){
        int index = 1;
        MafLib.TimedPrint("Please select a save to load from.\n", 0);
        try {
            Iterator<Path> Saves = Files.list(Paths.get("User")).iterator();
            while (Saves.hasNext()){
                String SaveName = Saves.next().toString();
                SaveName = SaveName.substring(SaveName.indexOf("\\")+1, SaveName.indexOf(".save"));
                SaveName = index + ". " + SaveName;
                if(SaveName.length() > 0){
                    index++;
                    System.out.println(SaveName);
                }
            }
        } catch (IOException e) {
            Main.main(new String[0]);
        }
    }
}
