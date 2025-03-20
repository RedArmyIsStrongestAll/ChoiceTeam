package ru.mephi.atomhack.Skaifom.choiceTeam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroDTO {
    private int id;
    private String type;
    private int level;
    private int manaStrat;
    private int manaMagic;
    private int manaBattle;
}
