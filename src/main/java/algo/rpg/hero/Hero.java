package algo.rpg.hero;

import algo.rpg.enemy.Enemy;
import algo.rpg.Entity;


import java.util.List;

public abstract class Hero extends Entity
{
    private int armor;
    private boolean inDefense = false;

    List<String> potions;
    List<String> lembas;

    public int getArmor() { return this.armor; }
    public void setArmor(int armorValue) { this.armor = armorValue; }

    public void setDefense(boolean action) { this.inDefense = action; }
    public boolean isInDefense() { return this.inDefense; }

    public int substractHp(int damage)
    {
        int calculation = ( this.getHp() - damage ) * (int) ( isInDefense() ? 0.5 : 1 );
        this.setHp(calculation);
        return calculation;
    }

    public void attack(Enemy enemy)
    {
        enemy.substractHp(this.getWeaponDamage());
    }

    public void defend() { this.inDefense = true; }




    // public abstract String getType();

    public int getArrows() {return 0;}
    public int getManaPoints() {return 0;}





