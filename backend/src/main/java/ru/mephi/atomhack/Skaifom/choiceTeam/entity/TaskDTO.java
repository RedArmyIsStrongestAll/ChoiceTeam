package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {
    private int id;
    private int idExpedition;
    private String name;
    private String description;
}