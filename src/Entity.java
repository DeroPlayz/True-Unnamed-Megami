package src;

import java.text.NumberFormat;
import java.util.Locale;
import static src.Main.ClearScreen;
import lib.MafLib;

class Entity{
    @SuppressWarnings("deprecation")
    NumberFormat Currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    //???
        private String Name;                                    public String getName(){return Name;}                           public void setName(String Name){this.Name = Name;}
        private String Arcana;                                  public String getArcana(){return Arcana;}                       public void setArcana(String Arcana){this.Arcana = Arcana;}

    //Stats
        private int Level = 1;                                  public int getLevel(){return Level;}                            public void setLevel(int Level){this.Level = Level;}
        private long XP;                                        public long getXP(){return XP;}                                 public void setXP(long XP){this.XP = XP;}
         
        private int CurrentHP = 25;                             public int getCurrentHP(){return CurrentHP;}                    public void setCurrentHP(int CurrentHP){this.CurrentHP = CurrentHP;}
        private int MaxHP = 25;                                 public int getMaxHP(){return MaxHP;}                            public void setMaxHP(int MaxHP){this.MaxHP = MaxHP;}

        private int CurrentSP = 14;                             public int getCurrentSP(){return CurrentSP;}                    public void setCurrentSP(int CurrentSP){this.CurrentSP = CurrentSP;}
        private int MaxSP = 14;                                 public int getMaxSP(){return MaxSP;}                            public void setMaxSP(int MaxSP){this.MaxSP = MaxSP;}
 
        private int Strength = 1;                               public int getStrength(){return Strength;}                      public void setStrength(int Strength){this.Strength = Strength;}
        private int Magic = 1;                                  public int getMagic(){return Magic;}                            public void setMagic(int Magic){this.Magic = Magic;}
        private int Endurance = 1;                              public int getEndurance(){return Endurance;}                    public void setEndurance(int Endurance){this.Endurance = Endurance;}
        private int Agility = 1;                                public int getAgility(){return Agility;}                        public void setAgility(int Agility){this.Agility = Agility;}
 
        private long Cash;                                      public long getCash(){return Cash;}                             public void setCash(long Cash){this.Cash = Cash;}
        
        private double PhysicalAffinity = 1;                    public double getPhysicalAffinity(){return PhysicalAffinity;}   public void setPhysicalAffinity(double PhysicalAffinity){this.PhysicalAffinity = PhysicalAffinity;}
        private double FireAffinity = 1;                        public double getFireAffinity(){return FireAffinity;}           public void setFireAffinity(double FireAffinity){this.FireAffinity = FireAffinity;}
        private double IceAffinity = 1;                         public double getIceAffinity(){return IceAffinity;}             public void setIceAffinity(double IceAffinity){this.IceAffinity = IceAffinity;}
        private double ElectricAffinity = 1;                    public double getElectricAffinity(){return ElectricAffinity;}   public void setElectricAffinity(double ElectricAffinity){this.ElectricAffinity = ElectricAffinity;}
        private double WindAffinity = 1;                        public double getWindAffinity(){return WindAffinity;}           public void setWindAffinity(double WindAffinity){this.WindAffinity = WindAffinity;}
        private double LightAffinity = 1;                       public double getLightAffinity(){return LightAffinity;}         public void setLightAffinity(double LightAffinity){this.LightAffinity = LightAffinity;}
        private double DarkAffinity = 1;                        public double getDarkAffinity(){return DarkAffinity;}           public void setDarkAffinity(double DarkAffinity){this.DarkAffinity = DarkAffinity;}
    //Position
        int X;                                                  public int getX(){return X;}                                    public void setX(int X){this.X = X;}
        int Z;                                                  public int getZ(){return Z;}                                    public void setZ(int Z){this.Z = Z;}

    public static Entity[] EnemyParty;

    public void Act(){
        int Answer = MafLib.askInt("What would you like to do?\n" + MafLib.RED + "1. Melee\t" + MafLib.BLUE + "2. Spell\n" + MafLib.GREEN + "3.Use an item\t" + MafLib.YELLOW + "4. Guard" + MafLib.RESET, true);
        if(Answer < 1 || Answer > 5){
            ClearScreen();
            Act();
        }
        if(Answer == 1){
            Attack(Skill.Melee);    
        }
    }

    public void Attack(Skill Action){
        String prompt = "Which enemy will you target?";
        for(int i = 0; i < EnemyParty.length; i++){
            if((EnemyParty[i] == null) == false){
                prompt += "\n" + (i+1) + ". ";
                if(EnemyParty[i].getCurrentHP() > EnemyParty[i].getMaxHP()*0.9){;
                    prompt += MafLib.GREEN;
                }    
                if(EnemyParty[i].getCurrentHP() > EnemyParty[i].getMaxHP()*0.6){;
                    prompt += MafLib.YELLOW;
                }
                if(EnemyParty[i].getCurrentHP() > EnemyParty[i].getMaxHP()*0.3){;
                    prompt += MafLib.RED;
                }
                prompt += EnemyParty[i].getName();
            }
        }
        int Answer = MafLib.askInt(prompt, true);
        if((Action.getAccuracy()/EnemyParty[Answer-1].getAgility()) > ((int) (Math.random()*100) + 1)){
        
        }
    }

    //Constructors
    //Constructors
    //Constructors
    Entity(){
        Name = "";
        Level = 0;
        Cash = 0;
        Arcana = "Fool";
        Strength = 1;
        Magic = 1;
        Endurance = 1;
        Agility = 1;
    }
    Entity(String Name){
        this.Name = Name;
        Level = 1;
        Cash = 0;
        Arcana = "Fool";
        Strength = 1;
        Magic = 1;
        Endurance = 1;
        Agility = 1;
    }
    Entity(String Name, int Level, String Arcana){
        this.Name = Name;
        this.Level = Level;
        this.Arcana = Arcana;
        Strength = 1;
        Magic = 1;
        Endurance = 1;
        Agility = 1;
    }

    Entity(String Name, int Level, long Cash, String Arcana){
        this.Name = Name;
        this.Level = Level;
        this.Cash = Cash;
        this.Arcana = Arcana;
        Strength = 1;
        Magic = 1;
        Endurance = 1;
        Agility = 1;
    }
    Entity(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double PhysicalAffinity, double FireAffinity, double IceAffinity, double ElectricAffinity, double WindAffinity, double LightAffinity, double DarkAffinity){
        this.Name = Name;
        this.Level = Level;
        this.MaxHP = MaxHP;
        this.MaxSP = MaxSP;
        this.Arcana = Arcana;
        this.Strength = Strength;
        this.Magic = Magic;
        this.Endurance = Endurance;
        this.Agility = Agility;
        this.PhysicalAffinity = PhysicalAffinity;
        this.FireAffinity = FireAffinity;
        this.IceAffinity = IceAffinity;
        this.ElectricAffinity = ElectricAffinity;
        this.WindAffinity = WindAffinity;
        this.LightAffinity = LightAffinity;
        this.DarkAffinity = DarkAffinity;
        
    }

    //toString
    public String toString(){
        if(Name.equals("Stella")){
            return MafLib.MAGENTA + Name + " (" + Arcana + ", Level " + Level + ")\n" + Currency.format(Cash) + "\n" + CurrentHP + "/" + MaxHP + " HP\n" + CurrentSP + "/" + MaxSP + " SP" + MafLib.RESET;
        }
        else{
            return Name + " (" + Arcana + ", Level " + Level + ")\n" + Currency.format(Cash) + "\n" + CurrentHP + "/" + MaxHP + " HP\n" + CurrentSP + "/" + MaxSP + " SP";
        }
    }

    public static Entity Stella = new Entity(
        "Stella",
        2,
        15,
        8,
        "???",
        4,
        1,
        4,
        3,
        0.5,
        1.5,
        1,
        0.5,
        1,
        0.5,
        1.5
    );
}