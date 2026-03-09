package dero.unnamed_megami;

public class Skill {
    public static final int PURPOSE_OFFENSE = 0;
    public static final int PURPOSE_AID = 1;

    public static final int TARGETS_SELF = 0;
    public static final int TARGETS_ALLY_ONE = 1;
    public static final int TARGETS_ALLY_ALL = 2;
    public static final int TARGETS_ENEMY_ONE = 3;
    public static final int TARGETS_ENEMY_ALL = 4;

    public static final int BUFF_TYPE_ATTACK = 0;
    public static final int BUFF_TYPE_DEFENSE = 1;
    public static final int BUFF_TYPE_AGILITY = 2;
    
    /* All skills have a name, description, and an element.
     * Element in this context refers to the skill type, the possibilities being:
       * Physical
       * Fire
       * Ice
       * Force
       * Electric
       * Light
       * Dark
       * Almighty
       * Healing (Healing, removing ailments, reviving)
       * Support (Buffs, Debuffs, etc.)
     * Purpose indicates what to use the spell data for. Offense skill? Base Power is used in the damage equation.
     * Healing spell? Base Power 
     */
    public String Name;
    public String Description;
    public String Element;
    public int Purpose;

    /* Base Power is used in the damage equation and healing equation; duh.
       * Since the only Light and Dark Skills are Instant-Kills, this represents the chance to instantly kill the opponent.
     * Hit Count dictates how many times it should hit; duh. Not relevant in most cases, so it defaults to 1, but
       * moves like Hassou Tobi, Ayamur, and Shining Arrows hit more than once, and I want to make sure my framework
       * leaves room to implemenent them. 
     * Critical Hit Rate - Use your best guess. Exclusive to Physical skills, defaults to 5%.
     * Accuracy should also be obvious.
       * Most Magic offense skills will have a base accuracy of PLACEHOLDER.
       * Most Physical skills will have a notably lower accuracy in exchange for higher power.
       * Non-offense Magic has an accuracy of 100 to ensure Healing and Support skills will always land.
     * HP/MP Cost is the currency of skills. Generally, Physical skills will use HP
       * and everything else will use MP.
         * HP Cost represents a percentage of the user's max health.
         * MP Cost represents a flat value.
     */

    public int Base_Power;
    public int Hit_Count = 1;
    public int Critical_Hit_Rate = 0;
    public int Accuracy;
    public int Target_Type;
    public int HP_Cost = 0;
    public int MP_Cost = 0;

    public int Buff_Type;

    /*
     * Skills to test validity of Skill implementation
     * Ayamur (Bael) - 3x Medium Phys damage to one foe. High Accuracy (95%)
     * Samsara (Daisoujou) - High chance to instantly kill all foes (Light Type) (70%)
     * Die For Me! (Alice) - High chance to instantly kill all foes (Dark Type) (70%)
     * Hassou Tobi (Yoshitsune) - 8x Weak Phys damage to all foes. (75%)
     * Phantom Show (Raoul) - High chance to sleep all foes. (90%)
    */

    Skill(String Name, String Description, String Element, int Purpose, int Base_Power, int Hit_Count, int Critical_Hit_Rate, int Accuracy, int Target_Type, int HP_Cost, int MP_Cost){
        this.Name = Name;
        this.Description = Description;
        this.Element = Element;
        this.Purpose = Purpose;
        this.Base_Power = Base_Power;
        this.Hit_Count = Hit_Count;
        this.Critical_Hit_Rate = Critical_Hit_Rate;
        this.Target_Type = Target_Type;
        this.Accuracy = Accuracy;
        this.HP_Cost = HP_Cost;
        this.MP_Cost = MP_Cost;
    }

    Skill(String Name, String Description, int Base_Power, int Hit_Count, int Critical_Hit_Rate, int Accuracy, int Target_Type, int Cost){
        this.Name = Name;
        this.Description = Description;
        Element = "Physical";
        Purpose = PURPOSE_OFFENSE;
        this.Base_Power = Base_Power;
        this.Hit_Count = Hit_Count;
        this.Critical_Hit_Rate = Critical_Hit_Rate;
        this.Accuracy = Accuracy;
        this.Target_Type = Target_Type;
        HP_Cost = Cost;
    }

    Skill(String Name, String Description, int Target_Type, int Buff_Type, int Cost){
        this.Name = Name;
        this.Description = Description;
        Element = "Support";
        Purpose = PURPOSE_AID;
        Base_Power = 1;
        Accuracy = 100;
        this.Target_Type = Target_Type;
        this.Buff_Type = Buff_Type;
        HP_Cost = 0;
        MP_Cost = Cost;
    }


    public static final Skill Hassou_Tobi = new Skill("Hassou Tobi", "Weak Physical damage to all foes 8 times.", 40, 8, 5, 75, TARGETS_ENEMY_ALL, 25);
}
