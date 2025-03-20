package ru.mephi.atomhack.Skaifom.choiceTeam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "subtasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idTask", nullable = false)
    private Task task;

    @OneToMany(mappedBy = "subTask", cascade = CascadeType.ALL)
    private List<SubTaskResolution> subTaskResolutions;

    @Transient
    private List<Backlog> backlogs;

    @PostLoad
    public void loadBacklogs() {
        if (subTaskResolutions != null) {
            this.backlogs = subTaskResolutions.stream()
                    .map(SubTaskResolution::getBacklog)
                    .collect(Collectors.toList());
        }
    }
}