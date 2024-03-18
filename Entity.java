class Entity{
    //???
        String Name;
        String Arcana;
    
    //Stats
        int Level;
        double XP;
        
        int CurrentHP;
        int MaxHP;

        int CurrentSP;
        int MaxSP;

        int Strength;
        int Magic;
        int Endurance;
        int Agility;

        double Cash;
    
        double[] Affinities = {1, 1, 1, 1, 1, 1, 1};

    //Position
        int x;
        int z;
    
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