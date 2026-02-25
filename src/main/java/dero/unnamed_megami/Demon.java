package dero.unnamed_megami;

import java.util.ArrayList;
import java.util.Arrays;

public class Demon extends Entity implements Cloneable{

    Demon(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double PhysicalAffinity, double FireAffinity, double IceAffinity, double ElectricAffinity, double WindAffinity, double LightAffinity, double DarkAffinity) {
        super(Name, Level, MaxHP, MaxSP, Arcana, Strength, Magic, Endurance, Agility, PhysicalAffinity, FireAffinity, IceAffinity, ElectricAffinity, WindAffinity, LightAffinity, DarkAffinity);
    }

    Demon(Demon d) {
        super(d.getName(), d.getLevel(), d.getMaxHP(), d.getMaxSP(), d.getArcana(), d.getStrength(), d.getMagic(), d.getEndurance(), d.getAgility(), d.getPhysicalAffinity(), d.getFireAffinity(), d.getIceAffinity(), d.getElectricAffinity(), d.getWindAffinity(), d.getLightAffinity(), d.getDarkAffinity());
    }

    public String PrintBrief(){
        return super.getName() + " (" + super.getCurrentHP() + "/" + super.getMaxHP() + ") [Level " + super.getLevel() + "]";
    }

    public void Act(){
        
    }

    public static final Demon Pixie = new Demon(
        "Pixie",
        3,
        18,
        14,
        "Lovers",
        1,
        3,
        2,
        2,
        1,
        1,
        1.5,
        0.5,
        1,
        0.5,
        1.5
    );

    public static final Demon Jack_Frost = new Demon(
        "Jack Frost",
        7,
        24,
        12,
        "Magician",
        3,
        3,
        3,
        2,
        1,
        1.5,
        0.5,
        1,
        1,
        1,
        1
    );
    
    public static ArrayList<Demon> DemonIndex = new ArrayList<>(Arrays.asList(new Demon[]{Pixie, Jack_Frost}));

    public static Demon selectDemon(){
        return DemonIndex.get((int) (Math.random()*(DemonIndex.size())));
    }
}