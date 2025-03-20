package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BacklogDTO {
    private int id;
    private String name;
    private String type;
    private String difficult;
    private int manaStrat;
    private int manaMagic;
    private int manaBattle;
}