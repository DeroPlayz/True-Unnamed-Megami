package dero.unnamed_megami;

public class Skill {

    private String Name;        public String getName(){return Name;}               public void setName(String Name){this.Name = Name;}
    private String Element;     public String getElement(){return Element;}         public void setElement(String Element){this.Element = Element;}
    private String PointType;   public String getPointType(){return PointType;}     public void setPointType(String PointType){this.PointType = PointType;}
    //HP for Physical skills, SP for Magic.

    private int Cost;           public int getCost(){return Cost;}                  public void setCost(int Cost){this.Cost = Cost;}
    // Cost is the percentage of Max Health used for Physical skills.
    // Works as the flat rate for Magic.

    private int Power;          public int getPower(){return Power;}                public void setPower(int Power){this.Power = Power;}
    // For Physical skills, this is multiplied by your Strength stat (with a bit of variance) to determine damage.
    // For Magic, this is identical formula-wise, but ostensibly uses your Magic stat instead.

    private int Accuracy;       public int getAccuracy(){return Accuracy;}          public void setAccuracy(int Accuracy){this.Accuracy = Accuracy;}
    /* Accuracy/100 = Chance of the skill landing.
     * Support skills have an accuracy of 1,000,000,000 to ensure they will land.
     * This is to make sure supports can actually heal and buff their allies without a risk of it just doing nothing,
     * Additionally, this will ensure debuff skills land, even if you have an agility of 1 and your opponent's
     * is 99.
     * Just kidding; I changed it so Support & Healing skills entirely bypass the accuracy check.
     */

    private int TargetType;     public int getTargetType(){return TargetType;}      public void setTargetType(int TargetType){this.TargetType = TargetType;}
    public static final int SelfOnly = 0;
    public static final int Allies = 1;
    public static final int Enemies = 2;
    public static final int All = 3;

    private int TargetCount;    public int getTargetCount(){return TargetCount;}    public void setTargetCount(int TargetCount){this.TargetCount = TargetCount;}
    /* 0 is treated as "all valid targets";
     * if you're using an attack or debuff, it hits all enemies,
     * if you're using healing or buffs, it hits all allies,
     * and if you're using a weird-ass skill, it hits *everyone*.
     */

    public Skill(String Name, String Element, String PointType, int Cost, int Power, int Accuracy, int TargetType, int TargetCount){
        this.Name = Name;
        this.Element = Element;
        this.PointType = PointType;
        this.Cost = Cost;
        this.Power = Power;
        this.Accuracy = Accuracy;
        this.TargetType = TargetType;
        this.TargetCount = TargetCount;
    }
    public Skill(String Name, String Element, int Cost, int Power, int Accuracy, int TargetType, int TargetCount){
        this.Name = Name;
        this.Element = Element;
        if(Element.equals("Physical")){this.PointType = "HP";}
        else{this.PointType = "SP";}
        this.Cost = Cost;
        this.Power = Power;
        this.Accuracy = Accuracy;
        this.TargetType = TargetType;
        this.TargetCount = TargetCount;
    }


    public static final Skill Melee = new Skill("Melee", "Physical", 0, 5, 90, Enemies, 1);
    
    public static final Skill Agi = new Skill("Agi", "Fire", 4, 8, 95, Enemies, 1);
    public static final Skill Agilao = new Skill("Agilao", "Fire", 8, 16, 95, Enemies, 1);
    public static final Skill Agidyne = new Skill("Agidyne", "Fire", 12, 24, 95, Enemies, 1);
    public static final Skill Maragi = new Skill("Maragi", "Fire", 6, 8, 90, Enemies, 0);
    public static final Skill Maragion = new Skill("Maragion", "Fire", 12, 16, 90, Enemies, 0);
    public static final Skill Maragidyne = new Skill("Maragidyne", "Fire", 18, 24, 90, Enemies, 0);

    public static final Skill Bufu = new Skill("Bufu", "Ice", 4, 8, 95, Enemies, 1);
    public static final Skill Bufula = new Skill("Bufula", "Ice", 8, 16, 95, Enemies, 1);
    public static final Skill Bufudyne = new Skill("Bufudyne", "Ice", 12, 24, 95, Enemies, 1);
    public static final Skill Mabufu = new Skill("Mabufu", "Ice", 6, 8, 90, Enemies, 0);
    public static final Skill Mabufula = new Skill("Mabufula", "Ice", 12, 16, 90, Enemies, 0);
    public static final Skill Mabufudyne = new Skill("Mabufudyne", "Ice", 18, 24, 90, Enemies, 0);

    public static final Skill Zio = new Skill("Zio", "Electric", 4, 8, 95, Enemies, 1);
    public static final Skill Zionga = new Skill("Zionga", "Electric", 8, 16, 95, Enemies, 1);
    public static final Skill Ziodyne = new Skill("Ziodyne", "Electric", 12, 24, 95, Enemies, 1);
    public static final Skill Mazio = new Skill("Mazio", "Electric", 6, 8, 90, Enemies, 0);
    public static final Skill Mazionga = new Skill("Mazionga", "Electric", 12, 16, 90, Enemies, 0);
    public static final Skill Maziodyne = new Skill("Maziodyne", "Electric", 18, 24, 90, Enemies, 0);

    public static final Skill Zan = new Skill("Zan", "Force", 4, 8, 95, Enemies, 1);
    public static final Skill Zanma = new Skill("Zanma", "Force", 8, 16, 95, Enemies, 1);
    public static final Skill Zandyne = new Skill("Zandyne", "Force", 12, 24, 95, Enemies, 1);
    public static final Skill Mazan = new Skill("Mazan", "Force", 6, 8, 90, Enemies, 0);
    public static final Skill Mazanma = new Skill("Mazanma", "Force", 12, 16, 90, Enemies, 0);
    public static final Skill Mazandyne = new Skill("Mazandyne", "Force", 18, 24, 90, Enemies, 0);

    public static final Skill Hama = new Skill("Hama", "Light", 8, 30, 100, Enemies, 1);
    public static final Skill Hamaon = new Skill("Hamaon", "Light", 16, 55, 100, Enemies, 1);
    public static final Skill Mahama = new Skill("Mahama", "Light", 12, 25, 100, Enemies, 0);
    public static final Skill Mahamaon = new Skill("Mahamaon", "Light", 24, 50, 100, Enemies, 0);
    
    public static final Skill Mudo = new Skill("Mudo", "Dark", 8, 30, 100, Enemies, 1);
    public static final Skill Mudoon = new Skill("Mudoon", "Dark", 16, 55, 100, Enemies, 1);
    public static final Skill Mamudo = new Skill("Mamudo", "Dark", 12, 25, 100, Enemies, 0);
    public static final Skill Mamudoon = new Skill("Mamudoon", "Dark", 24, 50, 100, Enemies, 0);
    
    public static final Skill Dia = new Skill("Dia", "Heal", 3, 6, 100, Allies, 1);
    public static final Skill Diarama = new Skill("Diarama", "Heal", 6, 6, 100, Allies, 1);
    public static final Skill Diarahan = new Skill("Diarahan", "Heal", 18, Integer.MAX_VALUE, 100, Allies, 1);
    public static final Skill Media = new Skill("Media", "Heal", 7, 6, 100, Allies, 1);
    public static final Skill Mediarama = new Skill("Mediarama", "Heal", 14, 6, 100, Allies, 1);
    public static final Skill Mediarahan = new Skill("Mediarahan", "Heal", 36, Integer.MAX_VALUE, 100, Allies, 1);
}