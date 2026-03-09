package dero.unnamed_megami;

import java.text.NumberFormat;
import java.util.Locale;

import static dero.unnamed_megami.Main.ClearScreen;
import static dero.unnamed_megami.Main.CombatLoop;
import static dero.unnamed_megami.Main.EnemyParty;
import static dero.unnamed_megami.Main.Party;
import static dero.unnamed_megami.Main.current_turn;

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
        private double ForceAffinity = 1;                       public double getForceAffinity(){return ForceAffinity;}         public void setForceAffinity(double ForceAffinity){this.ForceAffinity = ForceAffinity;}
        private double LightAffinity = 1;                       public double getLightAffinity(){return LightAffinity;}         public void setLightAffinity(double LightAffinity){this.LightAffinity = LightAffinity;}
        private double DarkAffinity = 1;                        public double getDarkAffinity(){return DarkAffinity;}           public void setDarkAffinity(double DarkAffinity){this.DarkAffinity = DarkAffinity;}
    
        private Skill[] KnownSkills = new Skill[8];             public Skill[] getKnownSkills(){return KnownSkills;}            public void setKnownSkills(Skill[] KnownSkills){this.KnownSkills = KnownSkills;}

    //Position
        int X;                                                  public int getX(){return X;}                                    public void setX(int X){this.X = X;}
        int Z;                                                  public int getZ(){return Z;}                                    public void setZ(int Z){this.Z = Z;}

    public void DealDamage(int amount, Entity target){
        target.CurrentHP -= amount;
    }

    public void Act(){
        int Answer = MafLib.askInt(Name + "\'s Turn - What would you like to do?\n" + MafLib.RED + "1. Melee\t" + MafLib.BLUE + "2. Spell\n" + MafLib.GREEN + "3. Use an item\t" + MafLib.YELLOW + "4. Guard\n" + MafLib.RESET);
        if(Answer < 1 || Answer > 5){
            ClearScreen();
            Act();
        }
        else if(Answer == 1){
            int Target = SelectTarget(Skill.Melee);
            if (Target > EnemyParty.size() || Target < 0){SelectTarget(Skill.Melee);}
            else{UseSkill(Skill.Melee, EnemyParty.get(Target - 1));}
        }
        else if(Answer == 2){
        
        }
        current_turn++;
        CombatLoop();
    }

    public int SelectTarget(Skill skill){
        String prompt = "";
        if (skill.getTargetType() == 1){
            prompt = "Which ally will you target";
            for(int i = 0; i < Party.length; i++){
                if((Party[i] == null) == false){
                    if(Party[i].getCurrentHP() <= Party[i].getMaxHP()*0.3){;
                        prompt += MafLib.RED;
                    }
                    if(Party[i].getCurrentHP() <= Party[i].getMaxHP()*0.6){;
                        prompt += MafLib.YELLOW;
                    }
                    if(Party[i].getCurrentHP() > Party[i].getMaxHP()*0.6){;
                        prompt += MafLib.GREEN;
                    }    

                    prompt += "\n" + (i+1) + ". ";
                    prompt += Party[i].getName();
                }
            }
        }
        else if (skill.getTargetType() == 2){
            prompt = "Which enemy will you target";
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
        }
        prompt += "\n" + MafLib.RESET;
        return MafLib.askInt(prompt);
    }

    public void UseSkill(Skill Skill, Entity Target){
        // System.out.println("Base Power: " + Attack.getPower());
        // System.out.println("User Strength: " + Strength);
        double TotalPower = Skill.getPower() * Strength;
        // System.out.println("BP * US: " + d);
        if(Skill.getElement() == "Healing"){
            TotalPower = Skill.getPower() * Magic;
            DealDamage(GetVariance(TotalPower)*-1, Target);
        }
        else if(AccuracyCheck(Skill, Target)){

            int finalDamage = GetVariance(TotalPower);
            // System.out.println("Damage Dealt: " + finalDamage);
            DealDamage(finalDamage, Target);
            if (Target.getCurrentHP() < 1){
                EnemyParty.remove(EnemyParty.indexOf(Target));
            }
        }
    }

    public int GetVariance(double Damage){
        int c = (int) (Math.random()*3);
        // System.out.println("Variation: " + c);

        double m = Math.random()*2+1;
        m = (int) m;
        m /= 10;
        // System.out.println("Modifier: " + m);
        if (c == 1){Damage += Damage*m;}
        if (c == 2){Damage -= Damage*m;}
        // System.out.println(Damage);
        return (int) Damage;
    }

    public boolean AccuracyCheck(Skill Skill, Entity Target){
        double chance = (int) (Math.random() * 100) + 1;
        double threshold = (int) (Math.pow(Agility + 1, 3.74) * Math.pow((double) Target.getAgility(), 3.0) * (Skill.getAccuracy()/100.0));
        // System.out.println("Chance: " + chance);
        // // System.out.println("Threshold Pt. 1: " + Math.pow(Agility + 1, 3.74));
        // // System.out.println("Threshold Pt. 2: " + Math.pow(EnemyParty[Answer-1].getAgility(), 3.0));
        // // System.out.println("Threshold Pt. 3: " + (Attack.getAccuracy()/100.0));
        // System.out.println("Threshold: " + threshold);
        if (chance > threshold){
            System.out.println("Miss!");
            return false;
        }
        return true;
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
        Level = 0;
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
    Entity(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double PhysicalAffinity, double FireAffinity, double IceAffinity, double ElectricAffinity, double ForceAffinity, double LightAffinity, double DarkAffinity){
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
        this.ForceAffinity = ForceAffinity;
        this.LightAffinity = LightAffinity;
        this.DarkAffinity = DarkAffinity;
    }
    Entity(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double PhysicalAffinity, double FireAffinity, double IceAffinity, double ElectricAffinity, double ForceAffinity, double LightAffinity, double DarkAffinity, Skill[] KnownSkills){
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
        this.ForceAffinity = ForceAffinity;
        this.LightAffinity = LightAffinity;
        this.DarkAffinity = DarkAffinity;
        this.KnownSkills = KnownSkills;
    }

    //toString
    public String toString(){
        String s = "";
        if(Name.equals("Stella")){s += MafLib.MAGENTA;}
        s += Name + " (" + Arcana + ", Level " + Level + ")\n" + Currency.format(Cash) + "\n" + CurrentHP + "/" + MaxHP + " HP\n" + CurrentSP + "/" + MaxSP + " SP" + MafLib.RESET;
        return s;
    }

    public String PrintBrief(){
        return Name + " (" + CurrentHP + "/" + MaxHP + ") [Level " + Level + "]";
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