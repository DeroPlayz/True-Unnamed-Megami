class Entity{
    //???
        private String Name;                                    public String getName(){return Name;}
        private String Arcana;                                  public String getArcana(){return Arcana;}

    //Stats
        private int Level;                                      public int getLevel(){return Level;}
        private double XP;                                      public double getXP(){return XP;}
         
        private int CurrentHP;                                  public int getCurrentHP(){return CurrentHP;}
        private int MaxHP;                                      public int getMaxHP(){return MaxHP;}

        private int CurrentSP;                                  public int getCurrentSP(){return CurrentSP;}
        private int MaxSP;                                      public int getMaxSP(){return MaxSP;}
 
        private int Strength;                                   public int getStrength(){return Strength;}
        private int Magic;                                      public int getMagic(){return Magic;}
        private int Endurance;                                  public int getEndurance(){return Endurance;}
        private int Agility;                                    public int getAgility(){return Agility;}
 
        private double Cash;                                    public double getCash(){return Cash;}
        
        private double[] Affinities = {1, 1, 1, 1, 1, 1, 1};    public double[] getAffinities(){return Affinities;}

    //Position
        int X;                                                  public int getX(){return X;}
        int Z;                                                  public int getZ(){return Z;}
    
    Entity(String Name, int Level, double Cash, String Arcana){
        this.Name = Name;
        this.Level = Level;
        this.Cash = Cash;
        this.Arcana = Arcana;
        Strength = 1;
        Magic = 1;
        Endurance = 1;
        Agility = 1;
    }

    Entity(String Name){
        this.Name = Name;
        Level = 0; Cash = 0;
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

    Entity(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double[] Affinities){
        this.Name = Name;
        this.Level = Level;
        this.MaxHP = MaxHP;
        this.MaxSP = MaxSP;
        this.Arcana = Arcana;
        this.Strength = Strength;
        this.Magic = Magic;
        this.Endurance = Endurance;
        this.Agility = Agility;
        this.Affinities = Affinities;
    }
}