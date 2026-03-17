package dero.unnamed_megami;

public class Scene {
    
    private String Name;
    private String Contents;

    public Scene(String Name, String Contents){
        this.Name= Name;
        this.Contents = Contents;
    }

    public void print(){
        MafLib.TimedPrint(Contents, Main.story_text_speed);
        Main.User.StorySteps++;
    }

    public static Scene Intro = new Scene("Intro 1", "You wake up.| It's Thursday, March 18th of 20XY.|\n"
        + "It's spring break, so you don't need to worry about getting to class on time.|||| That's nice.\n"
        + "||||||But it's quiet.|||| " + MafLib.UNDERLINE + "Too" + MafLib.RESET + " quiet.\n"
    );
}
