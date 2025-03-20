package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subtaskresolution")
public class SubTaskResolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idSubTask", nullable = false)
    private SubTask subTask;

    @ManyToOne
    @JoinColumn(name = "idBackLogSubTask", nullable = false)
    private Backlog backlog;
}
