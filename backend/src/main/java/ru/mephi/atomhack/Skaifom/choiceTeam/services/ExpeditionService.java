package ru.mephi.atomhack.Skaifom.choiceTeam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.BacklogDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.ExpeditionDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubTaskDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.TaskDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.MainRepository;

import java.util.List;

@Service
public class ExpeditionService {

    //todo всё через интерфейсы

    @Autowired
    private final MainRepository mainRepository;

    public ExpeditionService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public ExpeditionDTO addExpedition(String name, String description) {
        return mainRepository.addExpedition(name, description);
    }

    public List<Integer> getAllExpeditionIds() {
        return mainRepository.getAllExpeditionIds();
    }

    public List<TaskDTO> getExpeditionTasks(int expeditionId) {
        return mainRepository.getTasksByExpeditionId(expeditionId);
    }

    public List<SubTaskDTO> getSubtasks(int taskId) {
        return mainRepository.getSubTasksByTaskId(taskId);
    }

    public List<BacklogDTO> getBacklog() {
        return mainRepository.getAllBacklogEntries();
    }

    public SubTaskDTO addSubtask(int taskId, int backlogId) {
        return mainRepository.addSubTaskToTask(taskId, backlogId);
    }

    public TaskDTO addTaskToExpedition(int expeditionId, String name, String description) {
        return mainRepository.addTaskToExpedition(expeditionId, name, description);
    }

    public void deleteSubtask(int taskId, int subTaskId) {
        mainRepository.removeSubTaskFromTask(taskId, subTaskId);
    }

    public void deleteTask(int taskId) {
        mainRepository.removeTaskFromExpedition(taskId);
    }

    public void deleteExpedition(int expeditionId) {
        mainRepository.removeExpedition(expeditionId);
    }
}

