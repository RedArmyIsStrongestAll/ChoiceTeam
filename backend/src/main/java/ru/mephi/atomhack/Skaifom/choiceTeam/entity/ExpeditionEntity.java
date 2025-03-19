package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Expedition")
public class ExpeditionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpedition")
    private Long idExpedition;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Геттеры и сеттеры
    public Long getIdExpedition() {
        return idExpedition;
    }

    public void setIdExpedition(Long idExpedition) {
        this.idExpedition = idExpedition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
