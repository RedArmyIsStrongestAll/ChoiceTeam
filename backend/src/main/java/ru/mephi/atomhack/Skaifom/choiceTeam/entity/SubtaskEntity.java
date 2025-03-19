package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SubTasks")
public class SubtaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubTask")
    private Long idSubTask;

    @ManyToOne
    @JoinColumn(name = "idTask", nullable = false)
    private TaskEntity task;

    // Геттеры и сеттеры
    public Long getIdSubTask() {
        return idSubTask;
    }

    public void setIdSubTask(Long idSubTask) {
        this.idSubTask = idSubTask;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }
}