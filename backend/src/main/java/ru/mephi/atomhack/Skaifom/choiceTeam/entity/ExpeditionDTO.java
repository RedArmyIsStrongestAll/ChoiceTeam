package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpeditionDTO {
    private int id;
    private String name;
    private String description;
}
