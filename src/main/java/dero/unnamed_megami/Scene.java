package dero.unnamed_megami;

import java.util.ArrayList;
import java.util.Arrays;

public class Scene {

    public String ID;
    public String Contents;
    public String Desc;

    public Scene (String ID , String Contents, String Desc){
        this.ID = ID;
        this.Contents = Contents;
        this.Desc = Desc;
    }

    public void print(){
        if(!Main.User.CompletedScenes.contains(ID)){
            MafLib.TimedPrint(Contents, Main.story_text_speed);
            MafLib.WaitForEnter();
            if (Main.User.CompletedScenes == null){
                Main.User.CompletedScenes = new ArrayList<>(Arrays.asList(ID));
            }
            else{
                Main.User.CompletedScenes.add(ID);
            }
        }
    }

    public void review(){
        MafLib.TimedPrint(Contents, Main.story_text_speed);
        MafLib.WaitForEnter();
    }

    public static final Scene Intro_Wake_Up = new Scene("Intro_Wake_Up",
    "You wake up.+(WAIT:1) It's Thursday, March 18th of 20XY.|\n" +
    "It's spring break, so you don't need to worry about getting to class on time.+(WAIT:6) That's nice.\n" +
    "+(WAIT:10)But it's quiet. +(UNDERLINE WAIT:8)Too+(RESET) quiet.\n", "Waking Up...");

    public static final Scene Intro_Look_Window = new Scene("Intro_Look_Window",
    "You look out the window, but the world looks unfamiliar.\n" +
    "+(BOLD WAIT:6)Vastly+(RESET) unfamiliar.\n" +
    "Corpses litter the streets;+(WAIT:4) human, dog, cat, bird, +(UNDERLINE)" +
    "anything+(RESET) you could imagine.\n" +
    "+(WAIT:6)Gutwrenching.+(WAIT:6) Utterly +(UNDERLINE)gutwrenching+(RESET).\n", "Through the window...");

    public static final Scene[] Intro = {Intro_Wake_Up, Intro_Look_Window};

    public static void ReviewStory(){
        MafLib.TimedPrint("What would you like to review?\n1. Intro\n0. Nevermind\n", Main.menu_text_speed);
        Main.Answer = MafLib.askInt();
        if (Main.Answer < -1 || Main.Answer > 1){
            MafLib.ClearScreen();
            ReviewStory();
        }
        else if (Main.Answer == 0){
            Main.User.GameplayLoop();
        }
        else if (Main.Answer == 1){
            Main.Answer = -1;
            MafLib.TimedPrint("Which scene?\n", 0);
            for(int i = 0; i < Intro.length; i++){
                MafLib.TimedPrint((i+1) + ". " + Intro[i].Desc + "\n", Main.menu_text_speed);
            }
            while(Main.Answer < 0 || Main.Answer > Intro.length-1){
                Main.Answer = MafLib.askInt() - 1;
            }
            Intro[Main.Answer].review();
            ReviewStory();
        }
    }
}