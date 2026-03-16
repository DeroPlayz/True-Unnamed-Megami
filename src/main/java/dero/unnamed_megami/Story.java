package dero.unnamed_megami;

public class Story {
    
    private String Name;
    private String Contents;

    public Story(String Name, String Contents){
        this.Name= Name;
        this.Contents = Contents;
    }

    public void print(){
        MafLib.TimedPrint(Contents, Main.story_text_speed);
        Main.User.StoryBeatsCompleted.add(Name);
    }

    public static Story Intro = new Story("Intro", "You wake up.| It's Thursday, March 18th of 20XY.|\n"
        + "It's spring break, so you don't need to worry about getting to class on time.|||| That's nice.\n"
        + "||||||But it's quiet.|||| " + MafLib.UNDERLINE + "Too" + MafLib.RESET + " quiet.\n"
    );
}
