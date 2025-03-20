package ru.mephi.atomhack.Skaifom.choiceTeam.services.impl;

import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;
import org.springframework.stereotype.Service;
import ru.mephi.atomhack.Skaifom.choiceTeam.dto.HeroDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.ebtity.CharacterMana;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    public List<HeroDTO> createTeam() {
        //todo алгоритм
        return null;
    }

    private void calculation() {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        // Определяем переменные
        Variable x11 = model.addVariable("Маги 1 уровня").integer(true).lower(0)
                .weight(CharacterMana.MAGE_LEVEL_1.getManaForMagic() + CharacterMana.MAGE_LEVEL_1.getManaForStrategy() + CharacterMana.MAGE_LEVEL_1.getManaForBattle());
        Variable x12 = model.addVariable("Маги 2 уровня").integer(true).lower(0)
                .weight(CharacterMana.MAGE_LEVEL_2.getManaForMagic() + CharacterMana.MAGE_LEVEL_2.getManaForStrategy() + CharacterMana.MAGE_LEVEL_2.getManaForBattle());
        Variable x13 = model.addVariable("Маги 3 уровня").integer(true).lower(0)
                .weight(CharacterMana.MAGE_LEVEL_3.getManaForMagic() + CharacterMana.MAGE_LEVEL_3.getManaForStrategy() + CharacterMana.MAGE_LEVEL_3.getManaForBattle());

        Variable x21 = model.addVariable("Воины 1 уровня").integer(true).lower(0)
                .weight(CharacterMana.WARRIOR_LEVEL_1.getManaForMagic() + CharacterMana.WARRIOR_LEVEL_1.getManaForStrategy() + CharacterMana.WARRIOR_LEVEL_1.getManaForBattle());
        Variable x22 = model.addVariable("Воины 2 уровня").integer(true).lower(0)
                .weight(CharacterMana.WARRIOR_LEVEL_2.getManaForMagic() + CharacterMana.WARRIOR_LEVEL_2.getManaForStrategy() + CharacterMana.WARRIOR_LEVEL_2.getManaForBattle());
        Variable x23 = model.addVariable("Воины 3 уровня").integer(true).lower(0)
                .weight(CharacterMana.WARRIOR_LEVEL_3.getManaForMagic() + CharacterMana.WARRIOR_LEVEL_3.getManaForStrategy() + CharacterMana.WARRIOR_LEVEL_3.getManaForBattle());

        Variable x31 = model.addVariable("Стратеги 1 уровня").integer(true).lower(0)
                .weight(CharacterMana.STRATEGIST_LEVEL_1.getManaForMagic() + CharacterMana.STRATEGIST_LEVEL_1.getManaForStrategy() + CharacterMana.STRATEGIST_LEVEL_1.getManaForBattle());
        Variable x32 = model.addVariable("Стратеги 2 уровня").integer(true).lower(0)
                .weight(CharacterMana.STRATEGIST_LEVEL_2.getManaForMagic() + CharacterMana.STRATEGIST_LEVEL_2.getManaForStrategy() + CharacterMana.STRATEGIST_LEVEL_2.getManaForBattle());
        Variable x33 = model.addVariable("Стратеги 3 уровня").integer(true).lower(0)
                .weight(CharacterMana.STRATEGIST_LEVEL_3.getManaForMagic() + CharacterMana.STRATEGIST_LEVEL_3.getManaForStrategy() + CharacterMana.STRATEGIST_LEVEL_3.getManaForBattle());

        // Добавляем ограничения
        model.addExpression("Покрытие магии")
                .set(x11, CharacterMana.MAGE_LEVEL_1.getManaForMagic())
                .set(x12, CharacterMana.MAGE_LEVEL_2.getManaForMagic())
                .set(x13, CharacterMana.MAGE_LEVEL_3.getManaForMagic())
                .set(x21, CharacterMana.WARRIOR_LEVEL_1.getManaForMagic())
                .set(x22, CharacterMana.WARRIOR_LEVEL_2.getManaForMagic())
                .set(x23, CharacterMana.WARRIOR_LEVEL_3.getManaForMagic())
                .set(x31, CharacterMana.STRATEGIST_LEVEL_1.getManaForMagic())
                .set(x32, CharacterMana.STRATEGIST_LEVEL_2.getManaForMagic())
                .set(x33, CharacterMana.STRATEGIST_LEVEL_3.getManaForMagic())
                .lower(CharacterMana.TOTAL.getManaForMagic());

        model.addExpression("Покрытие стратегии")
                .set(x11, CharacterMana.MAGE_LEVEL_1.getManaForStrategy())
                .set(x12, CharacterMana.MAGE_LEVEL_2.getManaForStrategy())
                .set(x13, CharacterMana.MAGE_LEVEL_3.getManaForStrategy())
                .set(x21, CharacterMana.WARRIOR_LEVEL_1.getManaForStrategy())
                .set(x22, CharacterMana.WARRIOR_LEVEL_2.getManaForStrategy())
                .set(x23, CharacterMana.WARRIOR_LEVEL_3.getManaForStrategy())
                .set(x31, CharacterMana.STRATEGIST_LEVEL_1.getManaForStrategy())
                .set(x32, CharacterMana.STRATEGIST_LEVEL_2.getManaForStrategy())
                .set(x33, CharacterMana.STRATEGIST_LEVEL_3.getManaForStrategy())
                .lower(CharacterMana.TOTAL.getManaForStrategy());

        model.addExpression("Покрытие боя")
                .set(x11, CharacterMana.MAGE_LEVEL_1.getManaForBattle())
                .set(x12, CharacterMana.MAGE_LEVEL_2.getManaForBattle())
                .set(x13, CharacterMana.MAGE_LEVEL_3.getManaForBattle())
                .set(x21, CharacterMana.WARRIOR_LEVEL_1.getManaForBattle())
                .set(x22, CharacterMana.WARRIOR_LEVEL_2.getManaForBattle())
                .set(x23, CharacterMana.WARRIOR_LEVEL_3.getManaForBattle())
                .set(x31, CharacterMana.STRATEGIST_LEVEL_1.getManaForBattle())
                .set(x32, CharacterMana.STRATEGIST_LEVEL_2.getManaForBattle())
                .set(x33, CharacterMana.STRATEGIST_LEVEL_3.getManaForBattle())
                .lower(CharacterMana.TOTAL.getManaForBattle());

        // Решаем задачу оптимизации
        Optimisation.Result result = model.minimise();

        // Выводим результаты
        System.out.println("Оптимальное количество магов 1 уровня: " + x11.getValue().intValue());
        System.out.println("Оптимальное количество магов 2 уровня: " + x12.getValue().intValue());
        System.out.println("Оптимальное количество магов 3 уровня: " + x13.getValue().intValue());
        System.out.println("Оптимальное количество воинов 1 уровня: " + x21.getValue().intValue());
        System.out.println("Оптимальное количество воинов 2 уровня: " + x22.getValue().intValue());
        System.out.println("Оптимальное количество воинов 3 уровня: " + x23.getValue().intValue());
        System.out.println("Оптимальное количество стратегов 1 уровня: " + x31.getValue().intValue());
        System.out.println("Оптимальное количество стратегов 2 уровня: " + x32.getValue().intValue());
        System.out.println("Оптимальное количество стратегов 3 уровня: " + x33.getValue().intValue());
        System.out.println("Минимальная суммарная затраченная мана: " + result.getValue());
    }
}
