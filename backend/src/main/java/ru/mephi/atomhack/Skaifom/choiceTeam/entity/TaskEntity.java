package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask")
    private Long idTask;

    @ManyToOne
    @JoinColumn(name = "idExpedition", nullable = false)
    private ExpeditionEntity expedition;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Геттеры и сеттеры
    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public ExpeditionEntity getExpedition() {
        return expedition;
    }

    public void setExpedition(ExpeditionEntity expedition) {
        this.expedition = expedition;
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