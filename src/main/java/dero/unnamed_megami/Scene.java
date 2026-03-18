package dero.unnamed_megami;

public class Scene {
    
    public String[] Contents;

    public Scene(String[] Contents){
        this.Contents = Contents;
    }

    public void print(int index){
        MafLib.TimedPrint(Contents[index], Main.story_text_speed);
    }

    public static Scene Intro = new Scene(
        new String[]{
            "You wake up.| It's Thursday, March 18th of 20XY.|\n"
            + "It's spring break, so you don't need to worry about getting to class on time.|||| That's nice.\n"
            + "||||||But it's quiet.|||| " + MafLib.UNDERLINE + "Too" + MafLib.RESET + " quiet.\n",

            "Intro 2"
        });

}