package algo.rpg.hero;

public class Mage extends Mana
{
    // Construct
    public Mage()
    {
        this.setHp(50000);
        this.setArmor(500);
        this.setWeaponDamage(10000);
        this.setManaPoints(10000);
        this.setSpellCost(500);
    }

    public String getType() { return "Mage"; }
}
