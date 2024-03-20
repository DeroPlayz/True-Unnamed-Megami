package src;
class Entity{
    //???
        private String Name;                                    public String getName(){return Name;}                   public void setName(String Name){this.Name = Name;}
        private String Arcana;                                  public String getArcana(){return Arcana;}               public void setArcana(String Arcana){this.Arcana = Arcana;}

    //Stats
        private int Level;                                      public int getLevel(){return Level;}                    public void setLevel(int Level){this.Level = Level;}
        private double XP;                                      public double getXP(){return XP;}                       public void setXP(double XP){this.XP = XP;}
         
        private int CurrentHP;                                  public int getCurrentHP(){return CurrentHP;}            public void setCurrentHP(int CurrentHP){this.CurrentHP = CurrentHP;}
        private int MaxHP;                                      public int getMaxHP(){return MaxHP;}                    public void setMaxHP(int MaxHP){this.MaxHP = MaxHP;}

        private int CurrentSP;                                  public int getCurrentSP(){return CurrentSP;}            public void setCurrentSP(int CurrentSP){this.CurrentSP = CurrentSP;}
        private int MaxSP;                                      public int getMaxSP(){return MaxSP;}                    public void setMaxSP(int MaxSP){this.MaxSP = MaxSP;}
 
        private int Strength;                                   public int getStrength(){return Strength;}              public void setStrength(int Strength){this.Strength = Strength;}
        private int Magic;                                      public int getMagic(){return Magic;}                    public void setMagic(int Magic){this.Magic = Magic;}
        private int Endurance;                                  public int getEndurance(){return Endurance;}            public void setEndurance(int Endurance){this.Endurance = Endurance;}
        private int Agility;                                    public int getAgility(){return Agility;}                public void setAgility(int Agility){this.Agility = Agility;}
 
        private double Cash;                                    public double getCash(){return Cash;}                   public void setCash(double Cash){this.Cash = Cash;}
        
        private double[] Affinities = {1, 1, 1, 1, 1, 1, 1};    public double[] getAffinities(){return Affinities;}     public void setAffinities(double[] Affinities){this.Affinities = Affinities;}

    //Position
        int X;                                                  public int getX(){return X;}                            public void setX(int X){this.X = X;}
        int Z;                                                  public int getZ(){return Z;}                            public void setZ(int Z){this.Z = Z;}
    
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

    public String toString(){
        String s = Name + 
    }
}