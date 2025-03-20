package ru.mephi.atomhack.Skaifom.choiceTeam.ebtity;

public enum CharacterMana {
    MAGE_LEVEL_1(10, 5, 2),
    MAGE_LEVEL_2(15, 7, 4),
    MAGE_LEVEL_3(23, 10, 6),

    WARRIOR_LEVEL_1(4, 8, 6),
    WARRIOR_LEVEL_2(6, 12, 9),
    WARRIOR_LEVEL_3(9, 18, 13),

    STRATEGIST_LEVEL_1(3, 7, 9),
    STRATEGIST_LEVEL_2(7, 11, 14),
    STRATEGIST_LEVEL_3(11, 19, 23),

    TOTAL(100, 80, 60);

    private final double manaForMagic;
    private final double manaForStrategy;
    private final double manaForBattle;

    CharacterMana(double manaForMagic, double manaForStrategy, double manaForBattle) {
        this.manaForMagic = manaForMagic;
        this.manaForStrategy = manaForStrategy;
        this.manaForBattle = manaForBattle;
    }

    public double getManaForMagic() {
        return manaForMagic;
    }

    public double getManaForStrategy() {
        return manaForStrategy;
    }

    public double getManaForBattle() {
        return manaForBattle;
    }
}
