package ru.mephi.atomhack.Skaifom.choiceTeam.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.MainRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.ExpeditionService;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ExpeditionServiceImpl implements ExpeditionService {

    private final MainRepository mainRepositoryImpl;

    @Autowired
    public ExpeditionServiceImpl(MainRepository mainRepositoryImpl) {
        this.mainRepositoryImpl = mainRepositoryImpl;
    }

    public Optional<ExpeditionDTO> addExpedition(String name, String description) {
        return mainRepositoryImpl.addExpedition(name, description);
    }

    public List<Integer> getAllExpeditionIds() {
        return mainRepositoryImpl.getAllExpeditionIds();
    }

    public List<HeroDTO> getHeroesByExpedition(int expeditionId) {
        return mainRepositoryImpl.getHeroesByExpeditionId(expeditionId);
    }

    public List<TaskDTO> getExpeditionTasks(int expeditionId) {
        return mainRepositoryImpl.getTasksByExpeditionId(expeditionId);
    }

    public List<SubTaskDTO> getSubtasks(int taskId) {
        return mainRepositoryImpl.getSubTasksByTaskId(taskId);
    }

    public List<BacklogDTO> getBacklog() {
        return mainRepositoryImpl.getAllBacklogEntries();
    }

    public Optional<SubTaskDTO> addSubtask(int taskId, int backlogId) {
        return mainRepositoryImpl.addSubTaskToTask(taskId, backlogId);
    }

    public Optional<TaskDTO> addTaskToExpedition(int expeditionId, String name, String description) {
        return mainRepositoryImpl.addTaskToExpedition(expeditionId, name, description);
    }

    public boolean deleteSubtask(int taskId, int subTaskId) {
        try {
            mainRepositoryImpl.removeSubTaskFromTask(taskId, subTaskId);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete subtask", e);
            return false;
        }
    }

    public boolean deleteTask(int taskId) {
        try {
            mainRepositoryImpl.removeTaskFromExpedition(taskId);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete task", e);
            return false;
        }
    }

    public boolean deleteExpedition(int expeditionId) {
        try {
            mainRepositoryImpl.removeExpedition(expeditionId);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete expedition", e);
            return false;
        }
    }
}
