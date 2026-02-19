package src;

import java.text.NumberFormat;
import java.util.Locale;
import static src.Main.ClearScreen;
import static src.Main.CombatLoop;

import lib.MafLib;
import static src.Main.EnemyParty;

class Entity {
    public static final double WEAK = 1.5;
    public static final double NORMAL = 1.0;
    public static final double RESISTANT = 0.5;
    public static final double IMMUNE = 0.0;
    public static final double ABSORB = -1.0;

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

    public void DealDamage(int amount, Entity target){
        target.CurrentHP -= amount;
    }

    public void CombatAct(){
        int Answer = MafLib.askInt("What would you like to do?\n" + MafLib.RED + "1. Melee\t" + MafLib.BLUE + "2. Spell\n" + MafLib.GREEN + "3. Use an item\t" + MafLib.YELLOW + "4. Guard\n" + MafLib.RESET);
        if(Answer < 1 || Answer > 5){
            ClearScreen();
            CombatAct();
        }
        if(Answer == 1){
            Attack(Skill.Melee);
        }
        CombatLoop();
    }

    public void Attack(Skill Attack){
        String prompt = "Which enemy will you target?";
        for(int i = 0; i < EnemyParty.size(); i++){
            if((EnemyParty.get(i) == null) == false){
                if(EnemyParty.get(i).getCurrentHP() <= EnemyParty.get(i).getMaxHP()*0.3){;
                    prompt += MafLib.RED;
                }
                if(EnemyParty.get(i).getCurrentHP() <= EnemyParty.get(i).getMaxHP()*0.6){;
                    prompt += MafLib.YELLOW;
                }
                if(EnemyParty.get(i).getCurrentHP() > EnemyParty.get(i).getMaxHP()*0.6){;
                    prompt += MafLib.GREEN;
                }    

                prompt += "\n" + (i+1) + ". ";
                prompt += EnemyParty.get(i).getName();
            }
        }
        prompt += "\n" + MafLib.RESET;
        int Answer = MafLib.askInt(prompt);
        if (Answer > EnemyParty.size()){Attack(Attack);}
        
        double chance = (int) (Math.random() * 100) + 1;
        double threshold = (int) (Math.pow(Agility + 1, 3.74) * Math.pow((double) EnemyParty.get(Answer-1).getAgility(), 3.0) * (Attack.getAccuracy()/100.0));
        // System.out.println("Chance: " + chance);
        // // System.out.println("Threshold Pt. 1: " + Math.pow(Agility + 1, 3.74));
        // // System.out.println("Threshold Pt. 2: " + Math.pow(EnemyParty[Answer-1].getAgility(), 3.0));
        // // System.out.println("Threshold Pt. 3: " + (Attack.getAccuracy()/100.0));
        // System.out.println("Threshold: " + threshold);
        if (chance > threshold){
            System.out.println("Miss!");
        }
        else{
            // System.out.println("Base Power: " + Attack.getPower());
            // System.out.println("User Strength: " + Strength);
            double d = Attack.getPower() * Strength;
            // System.out.println("BP * US: " + d);

            int c = (int) (Math.random()*3);
            // System.out.println("Variation: " + c);

            double m = Math.random()*2+1;
            m = (int) m;
            m /= 10;
            if (c == 1){d += d*m;}
            if (c == 2){d -= d*m;}
            // System.out.println("Modifier: " + m);
            // System.out.println("Damage Dealt: " + (int) (d));
            DealDamage((int) d, EnemyParty.get(Answer - 1));
            if (EnemyParty.get(Answer - 1).getCurrentHP() < 1){
                EnemyParty.remove(Answer - 1);
            }
        }
    }

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
        this.CurrentHP = this.MaxHP = MaxHP;
        this.CurrentSP = this.MaxSP = MaxSP;
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
        String s = "";
        if(Name.equals("Stella")){s += MafLib.MAGENTA;}
        s += Name + " (" + Arcana + ", Level " + Level + ")\n" + Currency.format(Cash) + "\n" + CurrentHP + "/" + MaxHP + " HP\n" + CurrentSP + "/" + MaxSP + " SP" + MafLib.RESET;
        return s;
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
        RESISTANT,
        WEAK,
        NORMAL,
        RESISTANT,
        NORMAL,
        RESISTANT,
        WEAK
    );
}