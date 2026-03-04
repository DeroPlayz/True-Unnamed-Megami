package dero.unnamed_megami;

import static dero.unnamed_megami.Main.CombatLoop;
import static dero.unnamed_megami.Main.current_turn;

import java.util.ArrayList;
import java.util.Arrays;

public class Demon extends Entity implements Cloneable{

    Demon(String Name, int Level, int MaxHP, int MaxSP, String Arcana, int Strength, int Magic, int Endurance, int Agility, double PhysicalAffinity, double FireAffinity, double IceAffinity, double ElectricAffinity, double ForceAffinity, double LightAffinity, double DarkAffinity, Skill[] KnownSkills){
        super(Name, Level, MaxHP, MaxSP, Arcana, Strength, Magic, Endurance, Agility, PhysicalAffinity, FireAffinity, IceAffinity, ElectricAffinity, ForceAffinity, LightAffinity, DarkAffinity, KnownSkills);
    }

    Demon(Demon d) {
        super(d.getName(), d.getLevel(), d.getMaxHP(), d.getMaxSP(), d.getArcana(), d.getStrength(), d.getMagic(), d.getEndurance(), d.getAgility(), d.getPhysicalAffinity(), d.getFireAffinity(), d.getIceAffinity(), d.getElectricAffinity(), d.getForceAffinity(), d.getLightAffinity(), d.getDarkAffinity(), d.getKnownSkills());
    }

    public String PrintBrief(){
        return super.getName() + " (" + super.getCurrentHP() + "/" + super.getMaxHP() + ") [Level " + super.getLevel() + "]";
    }

    public void Act(){
        System.out.println("WIP");
        current_turn++;
        CombatLoop();
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
        NORMAL,
        NORMAL,
        WEAK,
        RESISTANT,
        NORMAL,
        RESISTANT,
        WEAK,
        new Skill[]{Skill.Zio, Skill.Dia}
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
        NORMAL,
        WEAK,
        RESISTANT,
        NORMAL,
        NORMAL,
        NORMAL,
        NORMAL,
        new Skill[]{Skill.Mabufu, Skill.Bufula}
    );
    
    public static ArrayList<Demon> DemonIndex = new ArrayList<>(Arrays.asList(new Demon[]{Pixie, Jack_Frost}));

    public static Demon selectDemon(){
        return DemonIndex.get((int) (Math.random()*(DemonIndex.size())));
    }
}