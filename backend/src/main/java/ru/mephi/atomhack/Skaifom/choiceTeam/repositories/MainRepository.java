package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import ru.mephi.atomhack.Skaifom.choiceTeam.dto.*;

import java.util.List;
import java.util.Optional;

public interface MainRepository {

    Optional<ExpeditionDTO> addExpedition(String name, String description);

    List<ExpeditionDTO> getAllExpeditionIds();

    List<TaskDTO> getTasksByExpeditionId(int expeditionId);

    List<HeroDTO> getHeroesByExpeditionId(int expeditionId);

    List<SubTaskDTO> getSubTasksByTaskId(int taskId);

    Optional<BacklogDTO> getSubTasksInBacklogByBacklogTaskId(int backlogTaskId);

    List<BacklogDTO> getAllBacklogEntries();

    Optional<SubTaskDTO> addSubTaskToTask(int taskId, int backlogId);

    Optional<TaskDTO> addTaskToExpedition(int expeditionId, String name, String description);

    void removeSubTaskFromTask(int taskId, int subTaskId);

    void removeTaskFromExpedition(int taskId);

    void removeExpedition(int expeditionId);

    void addHeroToExpedition(int expeditionId, int heroId);
}
