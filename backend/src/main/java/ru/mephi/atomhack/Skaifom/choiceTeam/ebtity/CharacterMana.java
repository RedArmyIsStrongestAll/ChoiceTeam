package ru.mephi.atomhack.Skaifom.choiceTeam.ebtity;

public enum CharacterMana {
    STRATEGIST_LEVEL_1(1, 3, 7, 9),
    STRATEGIST_LEVEL_2(2, 7, 11, 14),
    STRATEGIST_LEVEL_3(3, 11, 19, 23),

    MAGE_LEVEL_1(4, 10, 5, 2),
    MAGE_LEVEL_2(5, 15, 7, 4),
    MAGE_LEVEL_3(6, 23, 10, 6),

    WARRIOR_LEVEL_1(7, 4, 8, 6),
    WARRIOR_LEVEL_2(8, 6, 12, 9),
    WARRIOR_LEVEL_3(9, 9, 18, 13),

    TOTAL(0,100, 80, 60);

    private final double id;
    private final double manaForMagic;
    private final double manaForStrategy;
    private final double manaForBattle;

    CharacterMana(double id, double manaForMagic, double manaForStrategy, double manaForBattle) {
        this.id = id;
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
