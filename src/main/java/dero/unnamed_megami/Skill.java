package dero.unnamed_megami;

public class Skill {

    private String Name;        public String getName(){return Name;}           public void setName(String Name){this.Name = Name;}
    private String Element;     public String getElement(){return Element;}     public void setElement(String Element){this.Element = Element;}
    private String PointType;   public String getPointType(){return PointType;} public void setPointType(String PointType){this.PointType = PointType;}
    //HP for Physical skills, SP for Magic.

    private int Cost;           public int getCost(){return Cost;}              public void setCost(int Cost){this.Cost = Cost;}
    // Cost is the percentage of Max Health used for Physical skills.
    // Works as the flat rate for Magic.

    private int Power;          public int getPower(){return Power;}            public void setPower(int Power){this.Power = Power;}
    // For Physical skills, this is multiplied by your Strength stat (with a bit of variance) to determine damage.
    // For Magic, this is identical formula-wise, but ostensibly uses your Magic stat instead.

    private int Accuracy;       public int getAccuracy(){return Accuracy;}      public void setAccuracy(int Accuracy){this.Accuracy = Accuracy;}
    /* Accuracy/100 = Chance of the skill landing.
     * Support skills have an accuracy of 1,000,000,000 to ensure they will land.
     * This is to make sure supports can actually heal and buff their allies without a risk of it just doing nothing,
     * Additionally, this will ensure debuff skills land, even if you have an agility of 1 and your opponent's
     * is 99.
     */

    public Skill(String Name, String Element, String PointType, int Cost, int Power, int Accuracy){
        this.Name = Name;
        this.Element = Element;
        this.PointType = PointType;
        this.Cost = Cost;
        this.Power = Power;
        this.Accuracy = Accuracy;
    }

    public static final Skill Melee = new Skill("Melee", "Physical", "HP", 0, 5, 90);
    public static final Skill Dia = new Skill("Dia", "Heal", "SP", 3, 6, 90);
}