package algo.rpg.hero;

public class Healer extends Mana
{
    // Construct
    public Healer()
    {
        this.setHp(50000);
        this.setArmor(8000);
        this.setWeaponDamage(4500);
        this.setManaPoints(10000);
        this.setSpellCost(300);
    }

    public String getType() { return "Healer"; }
}
