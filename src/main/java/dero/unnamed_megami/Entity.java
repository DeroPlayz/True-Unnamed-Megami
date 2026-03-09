package dero.unnamed_megami;

import java.util.Map;

public class Entity {
    public static final double Weak = 1.5;
    public static final double Normal = 1;
    public static final double Resist = 0.5;
    public static final double Block = 0.0;
    public static final double Repel = 9.5;
    public static final double Drain = -1.0;
    
    public String Name;
    public String Race;
    
    public int Macca; //♄
    public int XP;
    public int Level;

    public int Current_HP;
    public int Max_HP;
    public int Current_MP;
    public int Max_MP;
    public Map<String, Double> Affinities;

    /* (Buff Level * 0.15) + 1 is added to your damage dealt,
     * damage taken, hit rate, or dodge rate, depending on buff.*/
    public int Taru_Level = 0;
    public int Raku_Level = 0;
    public int Suku_Level = 0;

    Entity(String Name, String Race, int Macca, int XP, int Level, int Max_HP, int Max_MP, Map<String, Double> Affinities){
        this.Name = Name;
        this.Race = Race;
        this.Macca = Macca;
        this.XP = XP;
        this.Level = Level;
        this.Current_HP = Max_HP;
        this.Max_HP = Max_HP;
        this.Current_MP = Max_MP;
        this.Max_MP = Max_MP;
        this.Affinities = Affinities;
    }
}