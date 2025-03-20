package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

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
    private int mana;
}
