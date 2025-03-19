package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "HeroTeam")
public class HeroTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeam")
    private Long idTeam;

    @ManyToOne
    @JoinColumn(name = "idExpedition", nullable = false)
    private ExpeditionEntity expedition;

    @Column(name = "idHero")
    private Long idHero;

    @Column(name = "description")
    private String description;

    // Геттеры и сеттеры
    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public ExpeditionEntity getExpedition() {
        return expedition;
    }

    public void setExpedition(ExpeditionEntity expedition) {
        this.expedition = expedition;
    }

    public Long getIdHero() {
        return idHero;
    }

    public void setIdHero(Long idHero) {
        this.idHero = idHero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
