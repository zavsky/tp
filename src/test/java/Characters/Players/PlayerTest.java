/*
package Characters.Players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import Equipment.DragonShield;
import Equipment.FlamingSword;
import Characters.Abilities.Ability;
import Characters.Abilities.BasicAttack;
import Characters.Abilities.PowerStrike;
import Equipment.Armor;
import Equipment.Weapon;
import Equipment.Equipment;

public class PlayerTest {

    private Player player;
    private Player opponent;

    @Test
    public void createNewPlayer_testStatusOfNewPlayer_playerIsAlive() {
        String simulatedInput = "TestHero\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        player = Player.createNewPlayer();
        assertTrue(player.isAlive());
    }

    @Test
    public void createNewPlayer_emptyInput_throwsException() {
        System.setIn(new ByteArrayInputStream("".getBytes()));

        assertThrows(NoSuchElementException.class, () -> {
            Player.createNewPlayer();
        });
    }



    @Test
    public void totalRoll_checkDiceSum_sumIsCorrect(){
        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 5, 2, equipmentList, true);

        player.diceRolls = new int[]{5,10,12};
        int total = player.totalRoll();

        assertEquals(27, total);
    }


    @Test
    public void totalRoll_NegativeRolls_sumIsCorrectlyCalculated() {
        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 5, 2, equipmentList, true);

        player.diceRolls = new int[]{-1, 5, 3};
        int total = player.totalRoll();

        assertEquals(7, total);
    }

    @Test
    public void computeDamageTo_validInputs_expectedDamage() throws InterruptedException{

        Weapon attackerWeapon = new Weapon("Flaming Sword", 5);
        Armor attackerArmor = new Armor("Cloth Armor", 0);
        List<Equipment> playerEquip = Arrays.asList(attackerArmor, null, attackerWeapon);

        Weapon opponentWeapon = new Weapon("Claws", 2);
        Armor opponentArmor = new Armor("Leather Armor", 3);
        List<Equipment> opponentEquip = Arrays.asList(opponentArmor, null, opponentWeapon);

        player = new Player("Attacker", 100, 10, 2, playerEquip, true);
        opponent = new Player("Opponent", 80, 8, 2, opponentEquip, false);

        player.diceRolls[0] = 4;
        player.diceRolls[1] = 5;

        player.power = 50;
        player.maxPower = 100;
        player.lastAbilityUsed = new BasicAttack();


        int totalRoll = 4 + 5;
        int numDice = 2;
        int weaponBonus = 5;
        // 9 + 10 = 19
        int base = totalRoll + (numDice * weaponBonus);

        double powerMultiplier = 1.0 + (player.power / (double) player.maxPower) * 0.5;
        double damageMult = player.lastAbilityUsed.damageMult;
        // (19 * 1.25 * 1.0) = 23
        int rawDamage = (int) (base * powerMultiplier * damageMult);
        // 23 - 3 = 20
        int expectedDamage = Math.max(0, rawDamage - opponent.getPlayerDefense());
        int actualDamage = player.computeDamageTo(opponent);

        assertEquals(expectedDamage, actualDamage);

    }

    @Test
    public void computeDamageTo_opponentIsNotAlive_assertionThrown() throws InterruptedException{
        try{

            Weapon attackerWeapon = new Weapon("Flaming Sword", 5);
            Armor attackerArmor = new Armor("Cloth Armor", 0);
            List<Equipment> playerEquip = Arrays.asList(attackerArmor, null, attackerWeapon);

            Weapon opponentWeapon = new Weapon("Claws", 2);
            Armor opponentArmor = new Armor("Leather Armor", 3);
            List<Equipment> opponentEquip = Arrays.asList(opponentArmor, null, opponentWeapon);

            opponent = new Player("Opponent", 0, 5, 2, opponentEquip, false);
            player = new Player("Hero", 100, 5, 2, playerEquip, true);

            player.lastAbilityUsed = new BasicAttack();
            player.lastAbilityUsed.damageMult = 1;
            player.computeDamageTo(opponent);
        }catch(AssertionError e){
            assertEquals("Opponent must be alive to receive damage", e.getMessage());

        }
    }



    @Test
    public void applyDamage_largeDamage_devastatingBlow() throws InterruptedException {

        Weapon attackerWeapon = new Weapon("Flaming Sword", 5);
        Armor attackerArmor = new Armor("Cloth Armor", 0);
        List<Equipment> playerEquip = Arrays.asList(attackerArmor, null, attackerWeapon);

        Weapon opponentWeapon = new Weapon("Claws", 2);
        Armor opponentArmor = new Armor("Leather Armor", 3);
        List<Equipment> opponentEquip = Arrays.asList(opponentArmor, null, opponentWeapon);

        opponent = new Player("Opponent", 100, 6, 4, opponentEquip, false);
        player = new Player("Hero", 100, 10, 2, playerEquip, true);


        opponent.lastAbilityUsed = new BasicAttack();
        String result = opponent.applyDamage(15, opponent, "Battle: ");

        assertEquals(85, opponent.getHp());
        assertTrue(result.contains("devastating blow"));
    }

    @Test
    public void applyDamage_playerDamageIsNegative_assertionThrown()throws InterruptedException {

        Weapon attackerWeapon = new Weapon("Flaming Sword", 5); // weapon bonus = 5
        Armor attackerArmor = new Armor("Cloth Armor", 0);
        List<Equipment> playerEquip = Arrays.asList(attackerArmor, null, attackerWeapon);

        Weapon opponentWeapon = new Weapon("Claws", 2);
        Armor opponentArmor = new Armor("Leather Armor", 3); // defense = 3
        List<Equipment> opponentEquip = Arrays.asList(opponentArmor, null, opponentWeapon);

        opponent = new Player("Opponent", 100, 6, 4, opponentEquip, false);
        player = new Player("Hero", 100, 10, 2, playerEquip, true);

        int attacker_damage = -10;

        try{
            player.applyDamage(attacker_damage,opponent,"Testing");

        }catch(AssertionError e){
            assertEquals("damage value must be non-negative",e.getMessage());

        }

    }

    @Test
    public void heal_healAmountIsNegative_assertionThrown() {
        try{
            List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
            player = new Player("Hero", 100, 10, 2, equipmentList, true);

            player.heal(-100);
        }catch(AssertionError e){
            assertEquals("amount to heal must be non-negative", e.getMessage());
        }
    }

    @Test
    public void heal_healAmountEqualsFIve_hpUpdatedCorrectly() {
        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 10, 2, equipmentList, true);

        player.heal(5);
        assertEquals(100, player.getHp());

    }

    @Test
    public void updatePower_updateAmountIsFive_powerValueUpdatedCorrectly(){
        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 10, 2, equipmentList, true);

        player.updatePower(5);
        assertEquals(55, player.power);
    }


    @Test
    public void updatePower_updateAmountIsNegative_assertionThrown() {
        try{
            List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
            player = new Player("Hero", 100, 10, 2, equipmentList, true);
            player.updatePower(-100);

        }catch(AssertionError e){
            assertEquals("power value must be non-negative", e.getMessage());
        }
    }

    @Test
    public void resetAllCooldowns_nullAbilityInList_gracefulHandling() {
        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 10, 2, equipmentList, true);

        player.abilities.add(null);
        player.abilities.add(new BasicAttack());

        assertThrows(NullPointerException.class, player::resetAllCooldowns);
    }

    @Test
    public void resetAllCooldowns_setsAllCooldownsToZero() {

        List<Equipment> equipmentList =  Arrays.asList(new DragonShield(), new FlamingSword());
        player = new Player("Hero", 100, 10, 2, equipmentList, true);

        Ability ability1 = new BasicAttack();
        Ability ability2 = new PowerStrike();

        ability1.currentCoolDown = 100;
        ability2.currentCoolDown = -100;

        player.abilities.add(ability1);
        player.abilities.add(ability2);

        player.resetAllCooldowns();

        assertEquals(0, ability1.currentCoolDown);
        assertEquals(0, ability2.currentCoolDown);
    }

}*/
