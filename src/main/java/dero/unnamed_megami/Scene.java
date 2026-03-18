package dero.unnamed_megami;

public class Scene {
    
    public String Contents;
    public int Step;

    public Scene(String Contents, int Step){
        this.Contents = Contents;
        this.Step = Step;
    }

    public static void print(){
        if(Main.User.StorySteps == 0){
            MafLib.TimedPrint(Intro1.Contents, Main.story_text_speed);
        }
        Main.User.StorySteps++;
    }

    public static Scene Intro1 = new Scene("You wake up.| It's Thursday, March 18th of 20XY.|\n"
        + "It's spring break, so you don't need to worry about getting to class on time.|||| That's nice.\n"
        + "||||||But it's quiet.|||| " + MafLib.UNDERLINE + "Too" + MafLib.RESET + " quiet.\n", 1
    );

    public static final Scene[] Scenes = {Intro1};
}
