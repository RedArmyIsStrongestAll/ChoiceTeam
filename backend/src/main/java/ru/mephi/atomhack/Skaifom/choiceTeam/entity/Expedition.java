package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "expeditions")
public class Expedition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "expedition", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "expedition", cascade = CascadeType.ALL)
    private List<HeroResolution> heroResolutions;

    @Transient
    private List<Hero> heroes;

    @PostLoad
    public void loadHeroes() {
        if (heroResolutions != null) {
            this.heroes = heroResolutions.stream()
                    .map(HeroResolution::getHero)
                    .collect(Collectors.toList());
        }
    }
}
