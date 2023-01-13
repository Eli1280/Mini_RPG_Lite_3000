package algo.rpg.enemy;

import algo.rpg.Entity;
import algo.rpg.hero.Hero;

public abstract class Enemy extends Entity
{
    public void attack(Hero hero)
    {
        int damage = getWeaponDamage() - hero.getArmor();
        hero.substractHp(damage);
    }
}
