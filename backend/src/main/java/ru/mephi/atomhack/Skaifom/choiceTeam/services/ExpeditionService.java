package ru.mephi.atomhack.Skaifom.choiceTeam.services;

import ru.mephi.atomhack.Skaifom.choiceTeam.dto.*;

import java.util.List;
import java.util.Optional;

public interface ExpeditionService {
    Optional<ExpeditionDTO> addExpedition(String name, String description);

    List<ExpeditionDTO> getAllExpeditionIds();

    List<HeroDTO> getHeroesByExpedition(int expeditionId);

    List<TaskDTO> getExpeditionTasks(int expeditionId);

    List<SubTaskDTO> getSubtasks(int taskId);

    Optional<BacklogDTO> getSubtaskInBacklog(int backlogTaskId);

    List<BacklogDTO> getBacklog();

    Optional<SubTaskDTO> addSubtask(int taskId, int backlogId);

    Optional<TaskDTO> addTaskToExpedition(int expeditionId, String name, String description);

    boolean deleteSubtask(int taskId, int subTaskId);

    boolean deleteTask(int taskId);

    boolean deleteExpedition(int expeditionId);
}
