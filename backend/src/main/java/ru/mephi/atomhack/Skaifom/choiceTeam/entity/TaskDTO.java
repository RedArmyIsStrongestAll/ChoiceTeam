package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private int id;
    private int idExpedition;
    private String name;
    private String description;
}