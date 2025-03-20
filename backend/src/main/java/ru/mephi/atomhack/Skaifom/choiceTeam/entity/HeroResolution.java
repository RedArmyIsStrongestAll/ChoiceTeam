package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HeroResolution")
public class HeroResolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idExpedition", nullable = false)
    private Expedition expedition;

    @ManyToOne
    @JoinColumn(name = "idHero", nullable = false)
    private Hero hero;
}