package src;

public class Skill {
    private String Name;        public String getName(){return Name;}           public void setName(String Name){this.Name = Name;}
    private String Element;     public String getElement(){return Element;}     public void setElement(String Element){this.Element = Element;}
    private String PointType;   public String getPointType(){return PointType;} public void setPointType(String PointType){this.PointType = PointType;}
    private int Cost;           public int getCost(){return Cost;}              public void setCost(int Cost){this.Cost = Cost;}
    private int Power;          public int getPower(){return Power;}            public void setPower(int Power){this.Power = Power;}
    private int Accuracy;       public int getAccuracy(){return Accuracy;}      public void setAccuracy(int Accuracy){this.Accuracy = Accuracy;}

    //HP for Physical Skills, SP for Magic

    public Skill(String Name, String Element, String PointType, int Cost, int Power, int Accuracy){
        this.Name = Name;
        this.Element = Element;
        this.PointType = PointType;
        this.Cost = Cost;
        this.Power = Power;
        this.Accuracy = Accuracy;
    }

    public static Skill Melee = new Skill("Melee", "Physical", "HP", 0, 5, 90);
}