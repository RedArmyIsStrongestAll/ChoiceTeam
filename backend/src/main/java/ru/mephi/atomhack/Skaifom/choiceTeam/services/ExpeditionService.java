package ru.mephi.atomhack.Skaifom.choiceTeam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.ExpeditionEntity;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.TaskEntity;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubtaskEntity;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.HeroTeamEntity;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.ExpeditionRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.SubtaskRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.TaskRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpeditionService {

    @Autowired
    private ExpeditionRepository expeditionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Long addExpedition() {
        ExpeditionEntity expedition = new ExpeditionEntity();
        expeditionRepository.save(expedition);
        return expedition.getIdExpedition();
    }

    public List<ExpeditionEntity> getAllExpeditions() {
        return expeditionRepository.findAll();
    }

    public List<TaskEntity> getExpedition(Long idExpedition) {
        return taskRepository.findByExpedition_IdExpedition(idExpedition);
    }

    public List<SubtaskEntity> getSubtasks(Long idTask) {
        return subtaskRepository.findByTask_IdTask(idTask);
    }

    public Long addTask(TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
        return taskEntity.getIdTask();
    }

    public List<SubtaskEntity> getLibrary() {
        return subtaskRepository.findAll();
    }

    public Long addSubtask(Long idTask, SubtaskEntity subtaskEntity) {
        TaskEntity task = taskRepository.findById(idTask).orElseThrow();
        subtaskEntity.setTask(task);
        subtaskRepository.save(subtaskEntity);
        return subtaskEntity.getIdSubTask();
    }

    public void deleteSubtask(Long idSubtask) {
        subtaskRepository.deleteById(idSubtask);
    }

    public void deleteTask(Long idTask) {
        taskRepository.deleteById(idTask);
    }

    public void deleteExpedition(Long idExpedition) {
        expeditionRepository.deleteById(idExpedition);
    }
}
