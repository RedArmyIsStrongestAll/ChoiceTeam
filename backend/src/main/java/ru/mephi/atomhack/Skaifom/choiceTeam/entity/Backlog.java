package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "backlog")
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type;
    private String difficult;
    private Integer manaStrat;
    private Integer manaMagic;
    private Integer manaBattle;
}