import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import lib.MafLib;

class Main{
    public static void main(String[] args){
        FileOutputStream FOS;
        ObjectOutputStream OOS;
        try {
        FOS = new FileOutputStream(new File("Save"));
        OOS = new ObjectOutputStream(FOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Entity Player = new Entity(MafLib.askString("What is your name?", true));
    }
}