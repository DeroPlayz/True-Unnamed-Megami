package dero.unnamed_megami;

import java.util.Comparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
class Main{
    public static Player User = new Player("", 0);
    public static long story_text_speed = 100;
    public static long menu_text_speed = 100;
    public static int Answer = 0;
    
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
        Preferences.LoadSettings();

        try {
            // 1. Tell the Windows Console to switch to UTF-8 mode (Code Page 65001)
            // This affects the actual window your app is sitting in.
            new ProcessBuilder("cmd", "/c", "chcp 65001 > nul")
                .inheritIO()
                .start()
                .waitFor();

            // 2. Tell Java to send UTF-8 bytes to System.out
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

            // 3. Test it
            // System.out.println("Success: UTF-8 enabled.");
            // System.out.println("♄");

        } catch (Exception e) {
            System.err.println("Could not set console encoding.");
        }

        System.exit(0);
        MafLib.ClearScreen();
        MafLib.TimedPrint(
            MafLib.MAGENTA + "--Unnamed Megami--\n" +
            MafLib.YELLOW + "A Megami Tensei Fan Game.\n" +
            MafLib.RESET + "What would you like to do?\n" +
            MafLib.GREEN + "1. Start a new save file.\n" +
            MafLib.BLUE + "2. Load a save file.\n" +
            MafLib.BLACK + "3. Access Settings\n" +
            MafLib.RESET + "4. View Credits\n", menu_text_speed
        );
        Answer = MafLib.askInt();
        if (Answer == 1){
            User.CreateSave();
        }
        else if (Answer == 2){
            User.LoadSaves();
        }
        else if (Answer == 3){
            Preferences.ViewSettings();
        }
        System.out.println(User);
    }
}