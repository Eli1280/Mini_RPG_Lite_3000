package algo.rpg.enemy;

public class Basic extends Enemy
{
    // Construct
    public Basic()
    {
        this.setHp(30000);
        this.setWeaponDamage(10000);
    }

    public String getType() { return "BasicEnemy"; }
}
